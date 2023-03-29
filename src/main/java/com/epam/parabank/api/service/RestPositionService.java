package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import io.restassured.response.Response;

public class RestPositionService extends RestBaseService {

    private final String BUY_POSITION = "/customers/%s/buyPosition?accountId=%s&name=%s&symbol=%s&shares=%s&pricePerShare=%s";
    private final String GET_POSITION_BY_ID = "/positions/%s";
    private final String SELL_POSITION = "/customers/%s/sellPosition?accountId=%s&positionId=%s&shares=%s&pricePerShare=%s";

    public Response buyPosition(final String customerId, final String accountId, final String name,
                                final String symbol, final String shares, final String pricePerShare) {
        return getSpecification().post(String.format(BUY_POSITION,
                customerId,
                accountId,
                name,
                symbol,
                shares,
                pricePerShare));
    }

    public Response getPositionById(final String positionId) {
        return getSpecification().get(String.format(GET_POSITION_BY_ID, positionId));
    }

    public Response sellPosition(final String customerId, final String accountId, final String positionId,
                                final String shares, final String pricePerShare) {
        return getSpecification().post(String.format(SELL_POSITION,
                customerId,
                accountId,
                positionId,
                shares,
                pricePerShare));
    }
}