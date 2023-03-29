package com.epam.parabank.cucumber.steps;

import com.epam.parabank.api.service.RestAccountService;
import com.epam.parabank.cucumber.service.TransferFundsService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class TransferFundsSteps {

    private final TransferFundsService transferFundsService;
    private final RestAccountService restAccountService;

    public TransferFundsSteps() {
        restAccountService = new RestAccountService();
        transferFundsService = new TransferFundsService();
    }

    @When("I transfer ${float} from my first account to second")
    public void createTransaction(final float amount) {
        transferFundsService.transferFunds(String.valueOf(amount));
    }

    @And("I create new account {string}")
    public void createNewAccount(String newAccountType) {
        restAccountService.createANewAccount(newAccountType);
    }
}
