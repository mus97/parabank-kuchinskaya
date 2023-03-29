package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.service.RestAccountService;
import com.epam.parabank.api.service.RestCustomerService;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.epam.parabank.api.model.AccountType.getAccountTypeNumber;

public class AccountApiService implements ResponseValidator {

    private static AccountApiService accountApiService;
    private final RestAccountService restAccountService;
    private Customer customer;
    private Response response;
    private String requestedAccountType;

    private AccountApiService() {
        restAccountService = new RestAccountService();
    }

    public static AccountApiService getAccountApiService() {
        if (accountApiService == null) {
            accountApiService = new AccountApiService();
        }
        return accountApiService;
    }

    public void createNewAccount(final String accountType) {
        requestedAccountType = accountType;
        User user = new UserManager().getUser();
        customer = new RestCustomerService().getCustomer(user.getUsername(), user.getPassword());
        customer.setAccounts(restAccountService
                .createModel(restAccountService.getCustomerAccounts(customer.getId()), Accounts.class));
        response = restAccountService.createNewAccount(customer.getId(), getAccountTypeNumber(accountType),
                customer.getAccounts().getAccountList().get(0).getAccountId());
    }

    public void verifyCreatedAccountType() {
        SoftAssert softAssert = new SoftAssert();
        Account newAccount = restAccountService.createModel(response, Account.class);
        softAssert.assertEquals(newAccount.getCustomerId(), customer.getId(), "Response CustomerId is wrong : ");
        softAssert.assertEquals(newAccount.getType(), requestedAccountType, "Response Type is wrong : ");
        softAssert.assertAll();
    }

    @Override
    public void verifyResponse(final int responseCode) {
        Assert.assertEquals(response.getStatusCode(), responseCode, "Wrong code!");
    }
}
