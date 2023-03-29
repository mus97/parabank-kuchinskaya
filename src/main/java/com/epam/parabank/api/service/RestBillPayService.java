package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import io.restassured.response.Response;
import org.json.JSONObject;

public class RestBillPayService extends RestBaseService {

    private static final String BILL_PAY_PATH = "/billpay";

    public Response payBill(String endpoint, JSONObject body) {
        return getSpecification()
                .given()
                .contentType("application/json")
                .body(body.toString())
                .post(endpoint);
    }

    public String createBillPayEndpoint(String accountId, String amount) {
        return new StringBuilder(BILL_PAY_PATH)
                .append("?accountId=")
                .append(accountId)
                .append("&amount=")
                .append(amount)
                .toString();
    }
}
