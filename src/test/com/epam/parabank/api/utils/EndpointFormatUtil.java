package com.epam.parabank.api.utils;

import com.epam.parabank.api.model.Customer;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import static com.epam.parabank.api.utils.GetRequestUtil.makePostRequest;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.*;


public class EndpointFormatUtil {

    public static Response createNewAccount(Customer customer, String accountType, String fromAccountId) {
        return makePostRequest(String.format
                (PARABANK_CREATE_NEW_ACCOUNT_ENDPOINT.getPropertyName(),
                        customer.getId(),
                        accountType,
                        fromAccountId));
    }

    public static Response transferFunds(String fromAccountId, String toAccountId, String amount) {
        return makePostRequest(String.format
                (PARABANK_TRANSFER_FUNDS.getPropertyName(),
                        fromAccountId,
                        toAccountId,
                        amount));
    }

    public static Response getListOfTransactions(String accountId) {
        return makeRequestAndGetResponse(String.format
                (PARABANK_LIST_OF_TRANSFERS_FOR_ACCOUNT.getPropertyName(),
                        accountId));
    }

    public static Response createTransaction(String accountId, String transactionAmount) {
        return makeRequestAndGetResponse(String.format
                (PARABANK_CREATE_TRANSACTION_BY_AMOUNT_ENDPOINT.getPropertyName(),
                        accountId, transactionAmount));
    }

    public static Response getTransactionById(String transactionId) {
        return makeRequestAndGetResponse(String.format
                (PARABANK_GET_TRANSACTION_BY_ID_ENDPOINT.getPropertyName(),
                        transactionId));
    }
}
