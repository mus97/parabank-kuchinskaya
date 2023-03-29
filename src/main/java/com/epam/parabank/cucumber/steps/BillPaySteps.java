package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.BillPayService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BillPaySteps {

    private final BillPayService billPayService;

    public BillPaySteps() {
        billPayService = new BillPayService();
    }

    @Given("my account has ${float}")
    public void setInitialBalance(final float amount) {
        billPayService.setInitialAccountBalance(amount);
    }

    @Given("I enter {string} in the Amount field")
    public void enterLettersInAmountField(final String text) {
        billPayService.billPay(text);
    }

    @When("I paid ${float}")
    public void payBill(final float amount) {
        billPayService.billPay(String.valueOf(amount));
    }

    @Then("account balance is ${float}")
    public void checkAccountBalance(final float amount) {
        billPayService.verifyAccountBalance(amount);
    }

    @Then("I get a message {string}")
    public void checkErrorMessage(final String message) {
        billPayService.verifyErrorMessage(message);
    }
}