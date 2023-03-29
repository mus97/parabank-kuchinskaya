package com.epam.parabank.ui.test.updatecontactinfo;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AccountServicesComponent;
import com.epam.parabank.ui.pageobject.UpdateContactInfoPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateContactInfoTest extends AbstractTest {

    private UpdateContactInfoPage updateContactInfoPage;
    private final String FIRST_NAME = "Don";
    private final String LAST_NAME = "Babus";
    private final static String MESSAGE_IN_CASE_EMPTY_VALUE = "First name is required.";
    private final static String MESSAGE_AFTER_SUCCESSFUL_UPDATE = "Your updated address and phone number have been added to the system.";

    @BeforeMethod(alwaysRun = true)
    public void preconditions() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        updateContactInfoPage = CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister(user).openUpdateContactInfoPage();
        updateContactInfoPage.waitForPageLoad();
    }

    @Test(description = "EPMFARMATS-16616 | Checking the 'Update contact info' form for entering blank values in a field")
    public void checkForBlankValue() {
         updateContactInfoPage
                 .clearFirstNameField()
                 .clickUpdateInfoButton();

         Assert.assertEquals(updateContactInfoPage.getErrorText(),MESSAGE_IN_CASE_EMPTY_VALUE,
                "message about blank value not found or don't match");
    }

    @Test(description = "EPMFARMATS-16624 | Checking the 'Update contact info' form for changes in the field")
    public void checkDataChangeability() {
        updateContactInfoPage
                .changeFirstName(FIRST_NAME)
                .changeLastName(LAST_NAME)
                .clickUpdateInfoButton();

        Assert.assertEquals(updateContactInfoPage.getMessageAfterUpdate(), MESSAGE_AFTER_SUCCESSFUL_UPDATE,
                "message about successful Update Contact Info not found or don't match");
    }

    @Test(description = "EPMFARMATS-16626 | Check the steps required to save the data")
    public void checkValueChangeability () {
        updateContactInfoPage
                .changeFirstName(FIRST_NAME)
                .changeLastName(LAST_NAME)
                .refreshPage();

        softAssert.assertNotEquals(updateContactInfoPage.getFirstName(), FIRST_NAME,
                "first name values equal (they should not) or are not found");
        softAssert.assertNotEquals(updateContactInfoPage.getLastName(), LAST_NAME,
                "last name values equal (they should not) or are not found");
        softAssert.assertAll("");
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        new AccountServicesComponent().openMainPageViaLogOutBtn();
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }
}