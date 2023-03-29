package com.epam.parabank.api.test.billpay;

import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.BillPay;
import com.epam.parabank.api.model.BillPayee;
import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateEndPoint.createBillpayEndpoint;
import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.BillPayUtil.createBillPayee;
import static com.epam.parabank.api.utils.BillPayUtil.createJson;
import static com.epam.parabank.api.utils.GetRequestUtil.makePostWithBodyRequest;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;


public class BillPayApiTest extends ParentApiTest {
    private Response response;
    private String mainAccountId;
    private JSONObject body;
    private BillPay billPay;
    private BillPayee billPayee;

    @BeforeClass
    public void setUp() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        billPayee = createBillPayee(customer);
        body = createJson(billPayee);
    }

    @DataProvider(name = "amount")
    private Object[][] getAmount() {
        return new Object[][]{{"12.20"}, {"42"}};
    }

    @Test(description = "EPMFARMATS-16764 | Check the payment is sent with the correct data. Positive", dataProvider = "amount")
    public void testCheckPaymentWithCorrectDataIntFloatApi(String amount) {
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();

        response = makePostWithBodyRequest(createBillpayEndpoint(mainAccountId, amount), body);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match.");

        billPay = createJaxbModel(response.asString(), BillPay.class);

        softAssert.assertEquals(billPay.getAccountId(), mainAccountId, "AccountId doesn't match.");
        softAssert.assertEquals(billPay.getAmount(), amount, "Amount is not the same.");
        softAssert.assertEquals(billPay.getPayeeName(), customer.getFirstName(), "Payee name doesn't match.");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16765 | Check that the Account value can only be integer data. Negative")
    public void testCheckAccountTakeIntApi() {
        String amount = "42";
        mainAccountId = "12.20";

        response = makePostWithBodyRequest(createBillpayEndpoint(mainAccountId, amount), body);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code doesn't match.");
    }
}
