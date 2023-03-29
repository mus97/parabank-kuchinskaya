package com.epam.parabank.ui.test.logout;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.LoginComponent;
import com.epam.parabank.ui.test.AbstractTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;
import static com.epam.parabank.ui.util.TextFileReader.FilePath;
import static com.epam.parabank.ui.util.TextFileReader.readTextFromFile;

public class LogOutTest extends AbstractTest {
    private LoginComponent loggedOutUser;
    private String expectedLogOutTitle;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        expectedLogOutTitle = readTextFromFile(FilePath.LOG_OUT_TITLE.getFilePath());
        loggedOutUser = cleanDatabaseAndRegister(user)
                .getAccountServicesComponent()
                .openMainPageViaLogOutBtn()
                .getLoginComponent();
    }

    @Test(description = "EPMFARMATS-16623 | Logging out by 'Log Out' button")
    public void LogOutTest() {
        String logOutUserMenuTitle = loggedOutUser
                .getCustomerLoginTitleText();

        softAssert.assertEquals(logOutUserMenuTitle, expectedLogOutTitle,
                "After user's log out title is not displayed");
        softAssert.assertTrue(loggedOutUser.getInputUsernameField().isEmpty(),
                "username field in left panel is not empty");
        softAssert.assertTrue(loggedOutUser.getInputPasswordText().isEmpty(),
                "password field in left panel is not empty");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
