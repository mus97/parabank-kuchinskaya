package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import io.restassured.response.Response;

public class RestDatabaseService extends RestBaseService {

    private static final String CLEAN_DATABASE_PATH = "/cleanDB";
    private static final String INITIALIZE_DATABASE_PATH = "/initializeDB";

    public Response cleanDatabase() {
        return getSpecification().post(CLEAN_DATABASE_PATH);
    }

    public Response initializeDatabase() {
        return getSpecification().post(INITIALIZE_DATABASE_PATH);
    }
}
