package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.ContactUsPage;
import com.epam.parabank.ui.pageobject.MainPage;
import org.testng.Assert;

public class ContactUsService {
    private ContactUsPage contactUsPage;
    private final User user = new UserManager().getUser();

    public void openContactUsPage() {
        contactUsPage = new MainPage().openContactUsPage();
    }

    public void fillOutUserData(){
        contactUsPage.enterUserData(user);
    }

    public void typeMessage(String message){
        contactUsPage.enterContactUsMessage(message);
    }

    public void clickSendButton(){
        contactUsPage.sendMessageToCostumerCare();
    }

    public void verifyMessage(String message){
        Assert.assertTrue(contactUsPage.getResultText().contains(message), "Wrong message");
    }
}
