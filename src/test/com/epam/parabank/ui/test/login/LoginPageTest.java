package com.epam.parabank.ui.test.login;

import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.URL_ACCOUNTOVERVIEW;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AccountOverviewPage;
import com.epam.parabank.ui.pageobject.LoginComponent;
import com.epam.parabank.ui.pageobject.LoginPage;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep;
import com.epam.parabank.ui.util.PropertyReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPageTest extends AbstractTest {
    private String expectedUrl;
    private LoginComponent loginUser;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        PropertyReader propertyReader = PropertyReader.getInstance();
        expectedUrl = propertyReader.getProperty(URL_ACCOUNTOVERVIEW.getPropertyName());
        user = new UserManager().getUser();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @Test(description = "EPMFARMATS-16683 | Logging in with correct credentials")
    public void testLoginWithCorrectData() {
        loginUser = CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister(user).getAccountServicesComponent().openMainPageViaLogOutBtn().getLoginComponent();

        softAssert.assertTrue(loginUser.isUserNameFieldDisplayed(), "Username Field is not displayed.");
        softAssert.assertTrue(loginUser.isPasswordFieldDisplayed(), "Password Field is not displayed.");
        softAssert.assertTrue(loginUser.isLoginButtonDisplayed(), "Login button is not displayed.");

        loginUser.enterUsername(user.getUsername());
        softAssert.assertEquals(loginUser.getInputUsernameField(), "SergeiD", "Username doesn't match.");

        loginUser.enterPassword(user.getPassword());
        softAssert.assertEquals(loginUser.getInputPasswordText(), "dovlatov123", "Password doesn't match.");

        AccountOverviewPage loggedUser = loginUser.openAccountOverviewPageViaLoginButton();

        softAssert.assertEquals(loggedUser.getUrlOfCurrentPage(), expectedUrl, "URL doesn't match");
        softAssert.assertTrue(loggedUser.isAccountTableDisplayed(), "Account Table is not displayed.");
        log.info("EPMFARMATS-16683 | Logging in with correct credentials");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16684 | Logging in with incorrect credentials")
    public void testLoginWithIncorrectData() {
        log.info("EPMFARMATS-16684 | Logging in with incorrect credentials");
        loginUser = new MainPage().getLoginComponent();
        softAssert.assertTrue(loginUser.isUserNameFieldDisplayed(), "Username Field is not displayed.");
        softAssert.assertTrue(loginUser.isPasswordFieldDisplayed(), "Password Field is not displayed.");
        softAssert.assertTrue(loginUser.isLoginButtonDisplayed(), "Login button is not displayed.");

        LoginPage wrongLogin = loginUser.loginManually("Incorrect_Name", "Incorrect_Password");

        softAssert.assertTrue(wrongLogin.getErrorMessage().contains("Error"),  "Error message is absent");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16685 | Logging in with empty form")
    public void testLoginWithEmptyFields() {
        log.info("EPMFARMATS-16685 | Logging in with empty form");
        loginUser = new MainPage().getLoginComponent();
        LoginPage emptyLogin = loginUser.openLoginPageViaLoginButton();

        softAssert.assertEquals(emptyLogin.getErrorMessage(), "Error!\n" + "Please enter a username and password.", "Error message is absent.");
        softAssert.assertAll();
    }
}
