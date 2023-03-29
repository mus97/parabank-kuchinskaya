package com.epam.parabank.api.test.account;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.EndpointFormatUtil.createNewAccount;
import static com.epam.parabank.api.utils.EndpointFormatUtil.getListOfTransactions;
import static com.epam.parabank.api.utils.EndpointFormatUtil.transferFunds;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Transaction;
import com.epam.parabank.api.model.Transactions;
import com.epam.parabank.api.test.ParentApiTest;

import io.restassured.response.Response;

public class ListOfTransactionsForAccountApiTest extends ParentApiTest {

    private Response response;
    private String mainAccountId;
    private String toAccountId;
    private List<Transaction> transactionsList;
    private final String amount = "150.00";
    private final int transactionsCount = 2;

    @BeforeClass
    public void preconditions() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();

        response = createNewAccount(customer, CHOOSE_CHECKING_TYPE, mainAccountId);
        toAccountId = createJaxbModel(response.asString(), Account.class).getAccountId();

        transferFunds(mainAccountId, toAccountId, amount);
    }

    @Test
    public void getListOfTransactionsForAccount() {
        response = getListOfTransactions(mainAccountId);
        customer.setTransactions(createJaxbModel(response.asString(), Transactions.class));
        transactionsList = customer.getTransactions().getTransactionList();

        softAssert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Response status code is wrong : ");
        softAssert.assertEquals(transactionsList.size(), transactionsCount,"Count of transactions doesn't match");
        softAssert.assertEquals(transactionsList.get(1).getAmount(), amount,"Amount of transaction doesn't match");
        softAssert.assertAll();
    }
}