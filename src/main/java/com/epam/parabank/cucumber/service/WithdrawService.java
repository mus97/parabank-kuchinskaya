package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.service.RestAccountService;
import com.epam.parabank.api.service.RestCustomerService;
import com.epam.parabank.api.service.RestWithdrawService;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import io.restassured.response.Response;
import org.testng.Assert;

public class WithdrawService {

    private final User user;
    private final RestWithdrawService restWithdrawService;
    private final RestAccountService restAccountService;
    private final RestCustomerService restCustomerService;
    private Response response;

    public WithdrawService() {
        user = new UserManager().getUser();
        restWithdrawService = new RestWithdrawService();
        restAccountService = new RestAccountService();
        restCustomerService = new RestCustomerService();
    }

    public void withdrawFunds(String amount) {
        String accountId;
        Customer customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        customer.setAccounts(restAccountService.createModel(restAccountService.getCustomerAccounts(customer.getId()), Accounts.class));

        accountId = customer.getAccounts().getAccountList().get(0).getAccountId();
        response = restWithdrawService.withdraw(accountId, amount);
    }

    public void verifyResponseCode(final int responseCode) {
        Assert.assertEquals(response.getStatusCode(), responseCode, "Wrong code!");
    }

    public void verifyResponseMessage(String responseMessage) {
        Assert.assertTrue(response.asString().contains(responseMessage), "Wrong message");
    }

}
