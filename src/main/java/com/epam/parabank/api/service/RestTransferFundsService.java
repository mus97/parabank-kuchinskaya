package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import io.restassured.response.Response;

public class RestTransferFundsService extends RestBaseService {

    private static final String TRANSFER_FUNDS_PATH = "/transfer?fromAccountId=%s&toAccountId=%s&amount=%s";

    public Response transferFunds(String fromAccountId, String toAccountId, String amount) {
        return getSpecification().post(String.format(TRANSFER_FUNDS_PATH, fromAccountId, toAccountId, amount));
    }
}
