package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.WithdrawService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WithdrawSteps {

    private final WithdrawService withdrawService;

    public WithdrawSteps() {
        withdrawService = new WithdrawService();
    }

    @When("I withdraw ${string}")
    public void makeWithdraw(String amount) {
        withdrawService.withdrawFunds(amount);
    }

    @When("Withdraw {int} response code is received")
    public void isResponseCodeCorrect(final int responseCode) {
        withdrawService.verifyResponseCode(responseCode);
    }

    @Then("{string} message is in response")
    public void isResponseMessageCorrect(String responseMessage) {
        withdrawService.verifyResponseMessage(responseMessage);
    }

}
