package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import io.restassured.response.Response;

public class RestLoginService extends RestBaseService {
    private static final String LOGIN_PATH = "/login/%s/%s";

    public Response login(final String username, final String password) {
        return getSpecification().get(String.format(LOGIN_PATH, username, password));
    }
}