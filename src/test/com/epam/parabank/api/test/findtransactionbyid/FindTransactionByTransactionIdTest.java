package com.epam.parabank.api.test.findtransactionbyid;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Transactions;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.EndpointFormatUtil.*;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;

public class FindTransactionByTransactionIdTest extends ParentApiTest {

    private static final String NON_EXISTENCE_TRANSACTION_ID = "1234";
    private static final String TRANSACTION_AMOUNT = "100.00";
    private String transactionId;

    @BeforeClass
    public void setUp() {
        Response response = makeRequestAndGetResponse(String
                .format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        response = createNewAccount(customer, CHOOSE_SAVINGS_TYPE,
                customer.getAccounts().getAccountList().get(0).getAccountId());
        Account account = createJaxbModel(response.asString(), Account.class);
        response = createTransaction(account.getAccountId(), TRANSACTION_AMOUNT);
        customer.setTransactions(createJaxbModel(response.asString(), Transactions.class));
        transactionId = customer.getTransactions().getTransactionList().get(0).getTransactionId();
    }

    @Test(description = "EPMFARMATS-16720 | Implement 5 API TC for Uladzimir | Find transaction by ID")
    public void findTransactionByExistingTransactionIdTest() {
        Response findTransactionResponse = getTransactionById(transactionId);
        Assert.assertEquals(findTransactionResponse.getStatusCode(),
                HttpStatus.SC_OK,
                "Find existing transaction by id api response code is invalid");
    }

    @Test(description = "EPMFARMATS-16720 | Implement 5 API TC for Uladzimir | Find transaction by ID")
    public void findTransactionForNonExistingIdTest() {
        Response findTransactionResponse = getTransactionById(NON_EXISTENCE_TRANSACTION_ID);
        Assert.assertEquals(findTransactionResponse.getStatusCode(),
                HttpStatus.SC_BAD_REQUEST,
                "Find existing transaction by non-existence id api response code is invalid");
    }
}
