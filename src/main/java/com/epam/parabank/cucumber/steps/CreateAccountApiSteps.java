package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.AccountApiService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.epam.parabank.cucumber.service.AccountApiService.getAccountApiService;

public class CreateAccountApiSteps {

    private final AccountApiService accountApiService;

    public CreateAccountApiSteps() {
        accountApiService = getAccountApiService();
    }

    @When("I create new {string} account via API")
    public void createNewAccount(final String accountType) {
        accountApiService.createNewAccount(accountType);
    }

    @Then("New account API credentials are correct")
    public void verifyNewAccountCredentials() {
        accountApiService.verifyCreatedAccountType();
    }
}
