package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.LoginApiService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.epam.parabank.cucumber.service.LoginApiService.getLoginApiService;

public class LoginApiSteps {

    private final LoginApiService loginApiService;

    public LoginApiSteps() {
        loginApiService = getLoginApiService();
    }

    @When("^I login via API$")
    public void loginViaApi() {
        loginApiService.loginUser();
    }

    @Then("Logged in API credentials are correct")
    public void verifyLoggedInCredential() {
        loginApiService.verifyCustomerCredential();
    }
}
