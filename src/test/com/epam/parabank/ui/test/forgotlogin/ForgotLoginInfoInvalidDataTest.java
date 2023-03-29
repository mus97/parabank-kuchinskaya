package com.epam.parabank.ui.test.forgotlogin;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.ForgotLoginInfoPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.TextFileReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;
import static com.epam.parabank.ui.util.TextFileReader.readTextFromFile;
import static com.epam.parabank.ui.util.TextFileReader.readTextFromFileByLines;
import static org.testng.Assert.assertEquals;

public class ForgotLoginInfoInvalidDataTest extends AbstractTest {
    private ForgotLoginInfoPage loggedOutUser;
    private List<String> expectedEmptyErrorText;
    private String expectedNonExistenceErrorText;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        expectedEmptyErrorText = readTextFromFileByLines(TextFileReader.FilePath
                .FORGOT_LOGIN_INFO_EMPTY_ERROR.getFilePath());
        expectedNonExistenceErrorText = readTextFromFile(TextFileReader.FilePath
                .FORGOT_LOGIN_INFO_NONEXISTENCE_ERROR.getFilePath());
        loggedOutUser = cleanDatabaseAndRegister(user)
                .getAccountServicesComponent()
                .openMainPageViaLogOutBtn()
                .openForgotLoginInfoPage();
        user.setFirstName(user.getLastName());
    }

    @Test(description = "EPMFARMATS-16621 | Find login with empty fields")
    public void testForgotLoginInfoEmptyField() {
        loggedOutUser
                .sendForgotLoginInfoRequest()
                .getEmptyErrorText();

        assertEquals(loggedOutUser.getEmptyErrorText(), expectedEmptyErrorText,
                "Empty field errors are not correct");
    }

    @Test(description = "EPMFARMATS-16622 | Find login with non-existent user")
    public void testForgotLoginInfoNonExistentUser() {
        loggedOutUser
                .enterUserData(user)
                .sendForgotLoginInfoRequest();

        softAssert.assertTrue(loggedOutUser.isErrorMessageDisplayed(),
                "Forgot login error message is not displayed");
        softAssert.assertEquals(loggedOutUser.getErrorText(), expectedNonExistenceErrorText,
                "Message for non-existence user is not correct");
        softAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
