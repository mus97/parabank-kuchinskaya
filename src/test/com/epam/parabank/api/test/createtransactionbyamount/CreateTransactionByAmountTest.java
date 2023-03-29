package com.epam.parabank.api.test.createtransactionbyamount;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Transactions;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.EndpointFormatUtil.createNewAccount;
import static com.epam.parabank.api.utils.EndpointFormatUtil.createTransaction;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;

public class CreateTransactionByAmountTest extends ParentApiTest {

    private static final String TRANSACTION_AMOUNT = "100.00";
    private Account account;

    @BeforeClass
    public void setUp() {
        Response preconditionResponse = makeRequestAndGetResponse(String
                .format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(preconditionResponse.asString(), Accounts.class));
        preconditionResponse = createNewAccount(customer,
                CHOOSE_SAVINGS_TYPE, customer.getAccounts().getAccountList().get(0).getAccountId());
        account = createJaxbModel(preconditionResponse.asString(), Account.class);
    }

    @Test(description = "EPMFARMATS-16720 | Implement 5 API TC for Uladzimir | Find transaction by ID")
    public void createTransactionByAmountForAccountTest() {
        Response transactionResponse = createTransaction(account.getAccountId(), TRANSACTION_AMOUNT);
        customer.setTransactions(createJaxbModel(transactionResponse.asString(), Transactions.class));
        softAssert.assertEquals(transactionResponse.getStatusCode(), HttpStatus.SC_OK,
                "Create transaction by amount api response code is invalid");
        softAssert.assertEquals(customer.getTransactions().getTransactionList().get(0).getAmount(),
                TRANSACTION_AMOUNT,
                "Amount of created transaction is invalid");
        softAssert.assertAll();
    }
}
