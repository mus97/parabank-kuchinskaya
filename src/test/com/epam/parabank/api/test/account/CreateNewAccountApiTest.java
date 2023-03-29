package com.epam.parabank.api.test.account;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.GetRequestUtil.*;
import static com.epam.parabank.api.utils.EndpointFormatUtil.*;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;

public class CreateNewAccountApiTest extends ParentApiTest {

    private Response response;
    private String mainAccountId;
    private Account account;

    @BeforeClass
    public void preconditions() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();
    }

    @Test
    public void createNewSavingsAccount() {
        response = createNewAccount(customer, CHOOSE_SAVINGS_TYPE, mainAccountId);

        account = createJaxbModel(response.asString(), Account.class);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");
        softAssert.assertEquals(account.getCustomerId(), customer.getId(), "Response CustomerId is wrong : ");
        softAssert.assertEquals(account.getType(), SAVINGS_TYPE, "Response Type is wrong : ");
        softAssert.assertAll();
    }
}