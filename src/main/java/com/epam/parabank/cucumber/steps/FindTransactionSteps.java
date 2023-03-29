package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.FindTransactionService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FindTransactionSteps {

    private final FindTransactionService findTransactionService;

    public FindTransactionSteps() {
        findTransactionService = new FindTransactionService();
    }

    @And("I have already existed transaction with {float}")
    public void makeTransaction(final float amount) {
        findTransactionService.createTransaction(amount);
    }

    @When("^I open Find transaction page$")
    public void openFindTransactionPage() {
        findTransactionService.getTransactionResultPage();
    }

    @And("^I insert date of transaction$")
    public void insertTransactionDate() {
        findTransactionService.findTransactionByDate();
    }

    @And("I insert {float} of transaction")
    public void insertTransactionAmount(final float amount) {
        findTransactionService.findTransactionByAmount(amount);
    }

    @Then("I see transaction {float}")
    public void isTransactionAmountDisplayed(final float amount) {
        findTransactionService.verifyTransactionAmount(amount);
    }

    @And("^I see transaction date$")
    public void isTransactionDateDisplayed() {
        findTransactionService.verifyTransactionDate();
    }
}