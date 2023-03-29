package com.epam.parabank.api.utils;

import io.restassured.response.Response;

import static com.epam.parabank.api.utils.GetRequestUtil.makePostRequest;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_CLEAN_DATABASE_ENDPOINT;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_INITIALIZE_DATABASE_ENDPOINT;

public class CleanDataBase {

    public static Response cleanDataBase() {
        return makePostRequest(PARABANK_CLEAN_DATABASE_ENDPOINT.getPropertyName());
    }

    public static Response initializeDatabase() {
        return makePostRequest(PARABANK_INITIALIZE_DATABASE_ENDPOINT.getPropertyName());
    }
}
