package com.epam.parabank.api.service;

import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_BILLPAY_ENDPOINT;

public class CreateEndPoint {

    public static String createComplexEndpoint(String endpoint, String... values) {
        StringBuilder resultEndpoint = new StringBuilder(endpoint);
        for (String value : values) {
            resultEndpoint.append("/").append(value);
        }
        return resultEndpoint.toString();
    }
    public static String createBillpayEndpoint(String accountId, String amount) {
        return new StringBuilder(PARABANK_BILLPAY_ENDPOINT.getPropertyName())
                .append("?accountId=")
                .append(accountId)
                .append("&amount=")
                .append(amount)
                .toString();
    }
}
