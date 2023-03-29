package com.epam.parabank.ui.test.forgotlogin;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.ForgotLoginInfoPage;
import com.epam.parabank.ui.test.AbstractTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;
import static com.epam.parabank.ui.util.TextFileReader.FilePath;
import static com.epam.parabank.ui.util.TextFileReader.readTextFromFile;

public class ForgotLoginInfoTest extends AbstractTest {
    private ForgotLoginInfoPage loggedOutUser;
    private String forgotLoginMessage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        loggedOutUser = cleanDatabaseAndRegister(user)
                .getAccountServicesComponent()
                .openMainPageViaLogOutBtn()
                .openForgotLoginInfoPage();
        forgotLoginMessage = readTextFromFile(FilePath
                .FORGOT_LOGIN_INFO_SUCCESS.getFilePath());
    }

    @Test(description = "EPMFARMATS-16620 | Find login with valid data")
    public void testForgotLoginInfo() {
        loggedOutUser
                .enterUserData(user)
                .sendForgotLoginInfoRequest()
                .getResultText();

        softAssert.assertTrue(loggedOutUser.getResultText().contains(forgotLoginMessage),
                "Result message does not contain requested success message");
        softAssert.assertTrue(loggedOutUser.isRequiredDataReturnedInForgotLoginInfoMessage(user.getUsername()),
                "Forgot login result does not contain login data");
        softAssert.assertTrue(loggedOutUser.isRequiredDataReturnedInForgotLoginInfoMessage(user.getPassword()),
                "Forgot login result does not contain password data");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
