package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.UpdateContactInfoService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateContactInfoSteps {

    private final UpdateContactInfoService updateContactInfoService;

    public UpdateContactInfoSteps() {
        updateContactInfoService = new UpdateContactInfoService();
    }

    @Given("I am on Update contact info page")
    public void openUpdateContactInfoPage() {
        updateContactInfoService.openUpdateContactInfoPage();
    }

    @Given("{string} is presented")
    public void isTitlePresented(String title) {
        updateContactInfoService.verifyTitle(title);
    }

    @When("I input new values into {string}, {string}, {string}, {string}, {string}, {string}, {string} fields")
    public void updateContactInformation(String firstName, String lastName, String address, String city, String state, String zipCode, String phone) {
        updateContactInfoService.updateContactInfo(firstName, lastName, address, city, state, zipCode, phone);
    }

    @When("I click Update profile button")
    public void clickUpdateProfileButton() {
        updateContactInfoService.clickUpdateProfileButton();
    }

    @Then("Message {string} is presented")
    public void message_is_presented(String message) {
        updateContactInfoService.verifyMessage(message);
    }
}