package com.epam.parabank.api.test.account;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.EndpointFormatUtil.createNewAccount;
import static com.epam.parabank.api.utils.GetRequestUtil.*;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;

public class GetListOfAccountsTest extends ParentApiTest {

    private Response response;
    private String mainAccountId;
    private List<Account> accounts;
    private final int accountsCount = 3;

    @BeforeClass
    public void preconditions() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();
        createNewAccount(customer,CHOOSE_SAVINGS_TYPE,mainAccountId);
        createNewAccount(customer,CHOOSE_LOAN_TYPE,mainAccountId);
    }

    @Test
    public void getListWithAllTypesOfAccounts() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        accounts = customer.getAccounts().getAccountList();

        softAssert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Response status code is wrong : ");
        softAssert.assertEquals(accounts.size(), accountsCount,"Amount of accounts doesn't match");
        softAssert.assertEquals(accounts.get(0).getType(), CHECKING_TYPE, "Type of the first account doesn't match");
        softAssert.assertEquals(accounts.get(1).getType(), SAVINGS_TYPE, "Type of the second account doesn't match");
        softAssert.assertEquals(accounts.get(2).getType(), LOAN_TYPE, "Type of the third account doesn't match");
        softAssert.assertAll();
    }
}