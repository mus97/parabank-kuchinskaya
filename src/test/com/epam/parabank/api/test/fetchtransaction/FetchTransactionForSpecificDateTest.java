package com.epam.parabank.api.test.fetchtransaction;

import com.epam.parabank.api.test.ParentApiTest;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.format.DateTimeFormatter;

import static com.epam.parabank.api.utils.GetRequestUtil.makePostRequest;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.*;
import static java.time.LocalDate.now;

public class FetchTransactionForSpecificDateTest extends ParentApiTest {

    static final String DATE_PATTERN = "MM-dd-yyyy";

    @Test(description = "EPMFARMATS-16748 | Fetch transactions for a specific date")
    public void fetchTransactionsForSpecificDateTest() {
        Response response;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        response = makePostRequest(createFetchTransactionEndpoint(customer.getId(), now().format(dateTimeFormatter)));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");
    }

    public String createFetchTransactionEndpoint(String id, String date) {
        return new StringBuilder(PARABANK_FETCH_TRANSACTION_ENDPOINT.getPropertyName())
                .append(id)
                .append("/transactions/onDate/")
                .append(date)
                .toString();
    }
}
