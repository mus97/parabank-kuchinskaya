package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.ContactUsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsSteps {

    private final ContactUsService contactUsService;

    public ContactUsSteps() {
        contactUsService = new ContactUsService();
    }

    @Given("I open Contact Us Page")
    public void userIsOnContactUsPage() {
        contactUsService.openContactUsPage();
    }

    @When("I fill out the contact details")
    public void fillOutContactDetails() {
        contactUsService.fillOutUserData();
    }

    @When("Write a message {string}")
    public void writeMessage(String message) {
        contactUsService.typeMessage(message);
    }

    @When("Send the message to Customer Care")
    public void sendMessageToCustomerCare() {
        contactUsService.clickSendButton();
    }

    @Then("{string} message is displayed on Contact Us Page")
    public void isMessageCorrect(String string) {
        contactUsService.verifyMessage(string);
    }

}
