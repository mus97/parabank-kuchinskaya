package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import io.restassured.response.Response;

public class RestWithdrawService extends RestBaseService {

    private static final String WITHDRAW_PATH = "/withdraw?accountId={accountId}&amount={amount}";

    public Response withdraw(final String accountId, final String amount) {
        return getSpecification().post(WITHDRAW_PATH, accountId, amount);
    }
}
