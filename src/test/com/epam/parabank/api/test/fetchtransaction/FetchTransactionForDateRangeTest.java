package com.epam.parabank.api.test.fetchtransaction;

import static com.epam.parabank.api.service.CreateEndPoint.createBillpayEndpoint;
import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.BillPayUtil.createBillPayee;
import static com.epam.parabank.api.utils.BillPayUtil.createJson;
import static com.epam.parabank.api.utils.GetRequestUtil.makePostWithBodyRequest;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_FETCH_TRANSACTION_ENDPOINT;
import static java.time.LocalDate.now;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.BillPayee;
import com.epam.parabank.api.model.Transaction;
import com.epam.parabank.api.model.Transactions;
import com.epam.parabank.api.test.ParentApiTest;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class FetchTransactionForDateRangeTest extends ParentApiTest {
    private static final String DATE_PATTERN = "MM-dd-yyyy";
    public final String amount = "42.00";
    private int transactionsCount;
    private Response response;
    private String mainAccountId;
    private List<Transaction> transactionsList;
    private JSONObject body;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private final String yesterdayDate = now().minus(1, ChronoUnit.DAYS).format(dateTimeFormatter);
    private final String todayDate = now().format(dateTimeFormatter);
    private BillPayee billPayee;

    @BeforeClass
    public void setUp() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();
        billPayee = createBillPayee(customer);
        body = createJson(billPayee);
        response = makePostWithBodyRequest(createBillpayEndpoint(mainAccountId, amount), body);
        ++transactionsCount;
        log.info("Billpay response: " + response.asString());
    }

    @Test(description = "EPMFARMATS-16766 | Fetch transactions for date range for account")
    public void testFetchTransactionsForDateRange() {
        var endpoint = createFetchTransactionEndpoint(mainAccountId, yesterdayDate, todayDate);
        response = makeRequestAndGetResponse(endpoint);
        log.info("Transaction response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");

        customer.setTransactions(createJaxbModel(response.asString(), Transactions.class));
        transactionsList = customer.getTransactions().getTransactionList();

        softAssert.assertEquals(transactionsList.size(), transactionsCount, "Count of transactions doesn't match");
        softAssert.assertEquals(transactionsList.get(0).getAmount(), amount, "Amount of transaction doesn't match");
        softAssert.assertAll();
    }

    public String createFetchTransactionEndpoint(String id, String firstDate, String secondDate) {
        return new StringBuilder(PARABANK_FETCH_TRANSACTION_ENDPOINT.getPropertyName())
                .append(id)
                .append("/transactions/fromDate/")
                .append(firstDate)
                .append("/toDate/")
                .append(secondDate)
                .toString();
    }
}
