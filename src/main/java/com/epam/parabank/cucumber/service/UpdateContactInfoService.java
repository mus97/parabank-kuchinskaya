package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.pageobject.RegisterPage;
import com.epam.parabank.ui.pageobject.UpdateContactInfoPage;
import org.testng.Assert;

public class UpdateContactInfoService {

    private final UpdateContactInfoPage updateContactInfoPage;

    public UpdateContactInfoService() {
        updateContactInfoPage = new UpdateContactInfoPage();
    }

    public void openUpdateContactInfoPage() {
        new RegisterPage().openUpdateContactInfoPage().waitForPageLoad();
    }

    public void verifyTitle(String title) {
        Assert.assertEquals(updateContactInfoPage.getTitle(), title, "Title of Update contact info page doesn't match");
    }

    public void updateContactInfo(String firstName, String lastName, String address, String city, String state, String zipCode, String phone) {
        updateContactInfoPage.changeFirstName(firstName)
                .changeLastName(lastName)
                .changeAddress(address)
                .changeCity(city)
                .changeState(state)
                .changeZipCode(zipCode)
                .changePhoneNumber(phone);
    }

    public void clickUpdateProfileButton() {
        updateContactInfoPage.clickUpdateInfoButton();
    }

    public void verifyMessage(String message) {
        Assert.assertEquals(updateContactInfoPage.getMessageAfterUpdate(), message, "Message after update doesn't match");
    }
}
