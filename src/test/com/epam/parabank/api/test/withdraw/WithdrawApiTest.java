package com.epam.parabank.api.test.withdraw;

import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.GetRequestUtil.makePostRequest;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_WITHDRAW_ENDPOINT;

public class WithdrawApiTest extends ParentApiTest {
    private Response response;
    private String mainAccountId;
    private static final String message = "Successfully withdrew $%s from account #%s";

    @BeforeClass
    public void setUp() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();
    }

    @DataProvider(name = "correctAmount")
    private Object[][] getAmount() {
        return new Object[][]{{"12.20"}, {"42"}};
    }

    @DataProvider(name = "incorrectAmount")
    private Object[][] getIncAmount() {
        return new Object[][]{{"forty two"}, {"42.."}, {"."}};
    }

    @Test(description = "EPMFARMATS-16762 | Withdraw correct amount", dataProvider = "correctAmount")
    public void testWithdrawCorrectAmount(String amount) {
        response = makePostRequest(createWithrawEndpoint(mainAccountId, amount));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match.");

        Assert.assertEquals(response.asString(), String.format(message, amount, mainAccountId), "Message is absent.");
    }

    @Test(description = "EPMFARMATS-16763 | Withdraw incorrect amount", dataProvider = "incorrectAmount")
    public void testWithdrawIncorrectAmount(String incorrectAmount) {
        response = makePostRequest(createWithrawEndpoint(mainAccountId, incorrectAmount));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code doesn't match.");
    }

    public String createWithrawEndpoint(String accountId, String amount) {
        return new StringBuilder(PARABANK_WITHDRAW_ENDPOINT.getPropertyName())
                .append("?accountId=")
                .append(accountId)
                .append("&amount=")
                .append(amount)
                .toString();
    }
}
