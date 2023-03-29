package com.epam.parabank.ui.test.admin;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AdminPage;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.parabank.ui.util.PropertyReader.*;

public class AdminPageTest extends AbstractTest {

    private String expectedDatabaseCondition;

    @BeforeMethod(alwaysRun = true)
    public void setSoftAssert() {
        user = new UserManager().getUser(); softAssert = new SoftAssert();
        PropertyReader propertyReader = PropertyReader.getInstance();
        expectedDatabaseCondition = propertyReader.getProperty(DatabaseConditions.DB_CLEANED.getPropertyName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @Test(description = "EPMFARMATS-16693 | Checking the changeability of the JMS operation mode")
    public void testCheckingTheChangeabilityOfTheJMSOperationMode() {
        AdminPage adminPage = new AdminPage();

        softAssert.assertEquals(adminPage.openPage().chooseButton().getStatusName(), "Stopped", "Status doesn't match");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16692 | Checking database cleanup")
    public void testCheckingDatabaseCleanup() {
        AdminPage adminPage = new MainPage().openAdminPage().cleanDatabase();
        String databaseCleanedMessage = adminPage.getDatabaseCleanedMessage();

        softAssert.assertEquals(databaseCleanedMessage, expectedDatabaseCondition, "Message \"Database Cleaned\" is absent. ");

        String errorLoginMessage = adminPage.getLoginComponent().loginManually(user.getUsername(), user.getPassword()).getErrorMessage();

        softAssert.assertTrue(errorLoginMessage.contains("Error"),  "Error message is absent");
        softAssert.assertAll();
    }
}
