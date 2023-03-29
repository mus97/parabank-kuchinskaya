package com.epam.parabank.ui.test.contactus;

import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.test.AbstractTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.parabank.ui.util.TextFileReader.FilePath;
import static com.epam.parabank.ui.util.TextFileReader.readTextFromFileByLines;
import static org.testng.Assert.assertEquals;

public class ContactUsEmptyFieldsTest extends AbstractTest {
    private List<String> expectedErrorText;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        expectedErrorText = readTextFromFileByLines(FilePath
                .CONTACT_US_ERROR.getFilePath());
    }

    @Test(description = "EPMFARMATS-16580 | Contact form with empty fields")
    public void ContactUsFormWithEmptyFieldsTest() {
        List<String> emptyFieldErrorText = new MainPage()
                .openContactUsPage()
                .sendMessageToCostumerCare()
                .getEmptyFieldErrorText();

        assertEquals(emptyFieldErrorText, expectedErrorText,
                "Contact error messages when fields are empty are not correct");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
