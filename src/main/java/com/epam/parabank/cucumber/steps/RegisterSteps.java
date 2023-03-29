package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.RegisterService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class RegisterSteps {

    private final RegisterService registerService;

    public RegisterSteps() {
        registerService = new RegisterService();
    }

    @And("^I open Register Page$")
    public void userIsOnRegisterPage() {
        registerService.openRegisterPage();
    }

    @When("^I register new user$")
    public void userEnterRegisterForm() {
        registerService.enterUserDataToRegistrationForm();
    }

    @And("{string} text is displayed")
    public void isTextVisible(final String text) {
        registerService.verifyTextVisible(text);
    }
}
