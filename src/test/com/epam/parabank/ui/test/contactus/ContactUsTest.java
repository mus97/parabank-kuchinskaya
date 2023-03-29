package com.epam.parabank.ui.test.contactus;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.test.AbstractTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.parabank.ui.util.TextFileReader.*;
import static org.testng.Assert.assertEquals;

public class ContactUsTest extends AbstractTest {
    private String contactUsMessage;
    private List<String> expectedResultText;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        user = new UserManager().getUser();
        contactUsMessage = readTextFromFile(FilePath
                .CONTACT_US_MESSAGE.getFilePath());
        expectedResultText = readTextFromFileByLines(FilePath
                .CONTACT_US_RESULT.getFilePath());
    }

    @Test(description = "EPMFARMATS-16567 | Contact form with valid user's data")
    public void ContactUsWithAllFieldsTest() {
        List<String> resultText = new MainPage()
                .openContactUsPage()
                .enterUserData(user)
                .enterContactUsMessage(contactUsMessage)
                .sendMessageToCostumerCare()
                .getResultText();

        assertEquals(resultText, expectedResultText,
                "Contact us result text after message is sent is not correct");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
