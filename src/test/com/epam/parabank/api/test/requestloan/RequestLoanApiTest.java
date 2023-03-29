package com.epam.parabank.api.test.requestloan;

import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.LoanRequest;
import com.epam.parabank.api.test.ParentApiTest;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.GetRequestUtil.makePostRequest;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_REQUEST_LOAN_ENDPOINT;

public class RequestLoanApiTest extends ParentApiTest {

    @Test(description = "EPMFARMATS-16744 | Request a loan with existed user data", dataProvider = "loanRequestData")
    public void requestALoanWithExistedUserDataTest(String amount, String downPayment) {
        String xml;
        Response response;
        LoanRequest loanRequest;

        xml = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId())).asString();
        customer.setAccounts(createJaxbModel(xml, Accounts.class));

        response = makePostRequest(createRequestLoanEndpoint(customer.getId(), amount, downPayment, customer.getAccounts().getAccountList().get(0).getAccountId()));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");

        loanRequest = createJaxbModel(response.asString(), LoanRequest.class);
        softAssert.assertEquals(loanRequest.getLoanProviderName(), LOAN_PROVIDER_NAME, "The received account type is not WSDL : ");
        softAssert.assertTrue(loanRequest.getApproved(), "The new lastname does not match with expected : ");
        softAssert.assertTrue(loanRequest.getAccountId().matches(ID_REGEX), "New account ID is not matches with expected format : ");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16745 | Request a loan with invalid user data", dataProvider = "requestLoanWithInvalidUserDataTest")
    public void requestLoanWithInvalidUserDataTest(String invalidId, String amount, String downPayment) {
        Response response = makePostRequest(createRequestLoanEndpoint(invalidId, amount, downPayment, customer.getAccounts().getAccountList().get(0).getAccountId()));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,"Response status code is wrong : ");
    }

    public String createRequestLoanEndpoint(String id, String amount, String downPayment, String fromAccountId) {
        return new StringBuilder(PARABANK_REQUEST_LOAN_ENDPOINT.getPropertyName())
                .append(id)
                .append("&amount=")
                .append(amount)
                .append("&downPayment=")
                .append(downPayment)
                .append("&fromAccountId=")
                .append(fromAccountId)
                .toString();
    }

    @DataProvider(name = "loanRequestData")
    private Object[][] getLoanData() {
        return new Object[][]{{"200", "50"},
                {"150", "15"},
                {"20", "10"}};
    }

    @DataProvider(name = "requestLoanWithInvalidUserDataTest")
    private Object[][] getInvalidLoanData() {
        return new Object[][]{{"123", "200", "50"},
                {"00000", "150", "15"},
                {"     ", "20", "10"}};
    }
}
