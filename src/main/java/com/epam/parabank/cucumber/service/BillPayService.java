package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.service.*;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.MainPage;

import io.restassured.response.Response;

import org.testng.Assert;

public class BillPayService {

    private final User user;
    private Customer customer;
    private Response response;
    private final RestBillPayService restBillPayService;
    private final RestAccountService restAccountService;
    private final RestCustomerService restCustomerService;


    public BillPayService() {
        user = new UserManager().getUser();
        restBillPayService = new RestBillPayService();
        restAccountService = new RestAccountService();
        restCustomerService = new RestCustomerService();
    }

    public void setInitialAccountBalance(float amount) {
        new MainPage()
                .openAdminPage()
                .cleanEndpoints()
                .setupInitialBalance(amount)
                .openMainPage()
                .openRegisterPage()
                .fillRegistrationForm()
                .setUpUser(user);
    }

    public void billPay(String amount) {
        String accountId;
        customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        customer.setAccounts(restAccountService.createModel(restAccountService.getCustomerAccounts(customer.getId()), Accounts.class));

        accountId = customer.getAccounts().getAccountList().get(0).getAccountId();
        response = restBillPayService.payBill(
                restBillPayService.createBillPayEndpoint(accountId, amount),
                new JsonCreator().createJson(new RestBillPayeeService().createBillPayee(customer)));
    }

    public void verifyAccountBalance(float amount) {
        customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        customer.setAccounts(restAccountService.createModel(restAccountService.getCustomerAccounts(customer.getId()), Accounts.class));

        Account account = restAccountService.createModel(
                restAccountService.getAccountById(customer.getAccounts().getAccountList().get(0).getAccountId()),
                Account.class);
        Assert.assertEquals(Float.parseFloat(account.getBalance()), amount,"Unexpected account balance : ");
    }

    public void verifyErrorMessage(String message) {
        Assert.assertEquals(response.asString(), String.format(message), "Wrong message text : ");
    }
}