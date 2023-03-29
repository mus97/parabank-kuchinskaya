package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.BillPayUiService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BillPayUiSteps {

    private final BillPayUiService billPayUiService;

    public BillPayUiSteps() {
        billPayUiService = new BillPayUiService();
    }

    @When("I open Bill Pay Page")
    public void userIsOnBillpayPage() {
        billPayUiService.openBillpayPage();
    }

    @Given("I create a bill pay to account {string} in amount of {string}")
    public void createBillpay(String account, String amount) {
        billPayUiService.sendBillpay(account, amount);
    }

    @Then("{string} message is displayed on Bill Pay Page")
    public void isMessageCorrect(String message) {
        billPayUiService.verifyResultMessage(message);
    }

    @Then("{string} warning message is displayed on Bill Pay Page")
    public void isWarningMessageDisplayed(String message) {
        billPayUiService.verifyErrorMessage(message);
    }
}
