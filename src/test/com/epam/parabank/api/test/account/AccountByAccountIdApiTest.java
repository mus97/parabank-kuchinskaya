package com.epam.parabank.api.test.account;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateEndPoint.createComplexEndpoint;
import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_RIGHT_ACCOUNTS_ENDPOINT;


public class AccountByAccountIdApiTest extends ParentApiTest {
    private static final String message = "Could not find account #%s";
    private Response response;

    @Test(description = "EPMFARMATS-16759 | Get account by correct account ID")
    public void testGetAccountByCorrectAccountId() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match.");

        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        String accountId = customer.getAccounts().getAccountList().get(0).getAccountId();
        String type = customer.getAccounts().getAccountList().get(0).getType();
        String balance = customer.getAccounts().getAccountList().get(0).getBalance();

        response = makeRequestAndGetResponse(createComplexEndpoint(PARABANK_RIGHT_ACCOUNTS_ENDPOINT.getPropertyName(), accountId));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match.");

        Account account = createJaxbModel(response.asString(), Account.class);
        softAssert.assertEquals(account.getAccountId(), accountId, "AccountId doesn't match.");
        softAssert.assertEquals(account.getType(), type, "Account type doesn't match.");
        softAssert.assertEquals(account.getBalance(), balance, "Balance doesn't match.");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16760 | Get account by incorrect account ID")
    public void testGetAccountByIncorrectAccountId() {
        String incorrectAccountId = "0";
        response = makeRequestAndGetResponse(createComplexEndpoint(PARABANK_RIGHT_ACCOUNTS_ENDPOINT.getPropertyName(), incorrectAccountId));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, "Status code doesn't match.");
        Assert.assertEquals(response.asString(), String.format(message, incorrectAccountId), "Error mesage is absent.");
    }

    @Test(description = "EPMFARMATS-16761 | Get account by empty account ID")
    public void testGetAccountByEmptyAccountId() {
        response = makeRequestAndGetResponse(PARABANK_RIGHT_ACCOUNTS_ENDPOINT.getPropertyName());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code doesn't match.");
    }
}
