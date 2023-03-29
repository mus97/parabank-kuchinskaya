package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.LoginPageService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {

    private final LoginPageService loginPageService;

    public LoginPageSteps() {
        loginPageService = new LoginPageService();
    }

    @Given("^I open Login Page$")
    @Given("^I am in the parabank app$")
    public void userIsOnCartPage() {
        loginPageService.userIsOnLoginPage();
    }

    @And("{string} is visible")
    public void isTextVisible(final String text) {
        loginPageService.verifyThatTextVisible(text);
    }

    @When("^I login on Login Page$")
    public void loginViaUi() {
        loginPageService.loginUser();
    }

    @And("^I open Admin Page$")
    public void openAdminPage() {
        loginPageService.openAdminPage();
    }

    @Then("Access is denied. {string} text is displayed")
    public void isErrorMessageDisplayed(final String errorMessage) {
        loginPageService.verifyLoginErrorMessage(errorMessage);
    }

    @Given("Browser is closed after registration")
    public void closeBrowser() {
        loginPageService.closeBrowser();
    }
}
