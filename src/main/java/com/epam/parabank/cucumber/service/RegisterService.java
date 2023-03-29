package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.RegisterPage;
import com.epam.parabank.ui.util.RegistrationFormFiller;
import org.testng.Assert;

public class RegisterService {

    private final User user;
    private final RegistrationFormFiller registrationFiller;
    private RegisterPage registerPage;

    public RegisterService() {
        user = new UserManager().getUser();
        registrationFiller = new RegistrationFormFiller();
    }

    public void openRegisterPage() {
        registerPage = new MainPage().openRegisterPage();
    }

    public void enterUserDataToRegistrationForm() {
        registrationFiller.setUpUser(user);
    }

    public void verifyTextVisible(final String text) {
        Assert.assertEquals(registerPage.getRegistrationMessage(), text, "Wrong text!");
    }
}
