package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.LoginPage;
import com.epam.parabank.ui.pageobject.MainPage;
import org.testng.Assert;

public class LoginPageService {

    private final User user;
    private MainPage mainPage;
    private LoginPage loginPage;

    public LoginPageService() {
        user = new UserManager().getUser();
    }

    public void userIsOnLoginPage() {
        mainPage = new MainPage();
    }

    public void verifyThatTextVisible(final String text) {
        Assert.assertEquals(mainPage.getCustomerLogin().getText(), text, "Wrong text!");
    }

    public void loginUser() {
        loginPage = mainPage.getLoginComponent().loginManually(user.getUsername(), user.getPassword());
    }

    public void openAdminPage() {
        mainPage.openAdminPage();
    }

    public void verifyLoginErrorMessage(final String errorMessage) {
        Assert.assertTrue(loginPage.getErrorMessage().contains(errorMessage), "Wrong error message!");
    }

    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }
}
