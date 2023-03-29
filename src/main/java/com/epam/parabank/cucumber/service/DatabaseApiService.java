package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.service.RestDatabaseService;
import io.restassured.response.Response;
import org.testng.Assert;

public class DatabaseApiService implements ResponseValidator {

    private static DatabaseApiService databaseApiService;
    private final RestDatabaseService databaseRestService;
    private Response cleanDatabaseResponse;

    private DatabaseApiService() {
        databaseRestService = new RestDatabaseService();
    }

    public static DatabaseApiService getDatabaseApiService() {
        if(databaseApiService == null) {
            databaseApiService = new DatabaseApiService();
        }
        return databaseApiService;
    }

    public void sendCleanDatabaseRequest() {
        cleanDatabaseResponse = databaseRestService.cleanDatabase();
    }

    @Override
    public void verifyResponse(int responseCode) {
        Assert.assertEquals(cleanDatabaseResponse.getStatusCode(), responseCode, "Wrong code");
    }
}
