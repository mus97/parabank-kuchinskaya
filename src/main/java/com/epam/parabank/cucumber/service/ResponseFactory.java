package com.epam.parabank.cucumber.service;

import static com.epam.parabank.cucumber.service.AccountApiService.*;
import static com.epam.parabank.cucumber.service.DatabaseApiService.getDatabaseApiService;
import static com.epam.parabank.cucumber.service.LoginApiService.getLoginApiService;

public class ResponseFactory {

    public static ResponseValidator getValidator(final String validatorType) {
        ResponseValidator validator = null;
        switch (validatorType) {
            case "Login" -> validator = getLoginApiService();
            case "Clean database" -> validator = getDatabaseApiService();
            case "Create account" -> validator = getAccountApiService();
        }
        return validator;
    }
}
