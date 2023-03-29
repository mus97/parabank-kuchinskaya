package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.ResponseFactory;
import com.epam.parabank.cucumber.service.ResponseValidator;
import io.cucumber.java.en.Then;

public class VerifyResponseSteps {

    @Then("{string} {int} response code is received")
    public void isResponseCodeCorrect(final String validatorType, final int responseCode) {
        ResponseValidator validator = ResponseFactory.getValidator(validatorType);
        validator.verifyResponse(responseCode);
    }
}
