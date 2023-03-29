package com.epam.parabank.api.test.account;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.test.ParentApiTest;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccountByIdApiTest extends ParentApiTest {

    @Test(description = "EPMFARMATS-16740 | Get Accounts request by user ID")
    public void getAccountsRequestByUserIdTest() {
        Response response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");

        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        List<Account> accounts = customer.getAccounts().getAccountList();
        for (Account account : accounts) {
            assertThat(account.getType(),
                    anyOf(is(SAVINGS_TYPE), is(LOAN_TYPE), is(CHECKING_TYPE)));
            softAssert.assertTrue(account.getAccountId().matches(ID_REGEX), "ID is not matches with expected format : ");
            softAssert.assertTrue(account.getCustomerId().matches(ID_REGEX), "Customer ID is not matches with expected format : ");
            softAssert.assertTrue(account.getBalance().matches(BALANCE_REGEX), "Balance is not matches with expected format : ");
        }
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16741 | Get account by invalid/non-existent user ID", dataProvider = "invalidIds")
    public void getAccountByInvalidOrNonExistentUserIdTest(String invalidId) {
        Response response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), invalidId));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,"Response status code is wrong : ");
    }

    @DataProvider(name = "invalidIds")
    private Object[][] getId() {
        return new Object[][]{{"123"},
                {"00000"}};
    }
}
