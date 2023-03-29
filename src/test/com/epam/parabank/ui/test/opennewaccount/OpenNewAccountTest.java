package com.epam.parabank.ui.test.opennewaccount;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AccountServicesComponent;
import com.epam.parabank.ui.pageobject.OpenNewAccountPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenNewAccountTest extends AbstractTest {

    private OpenNewAccountPage openNewAccountPage;
    private static final String MESSAGE_AFTER_SUCCESSFULLY_OPENING_NEW_ACCOUNT = "Account Opened!";

    @BeforeMethod
    public void preconditions() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        openNewAccountPage = CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister(user).getAccountServicesComponent()
                .clickOpenAccountBtn();
    }

    @Test(description = "EPMFARMATS-16582 | Success opening new account with minimal deposit | Checking type")
    public void openNewCheckingAccount() {
        openNewAccountPage
                .choseCheckingType()
                .clickOpenNewAccount();

        softAssert.assertEquals(openNewAccountPage.getMessageAfterOpenNewAccount(), MESSAGE_AFTER_SUCCESSFULLY_OPENING_NEW_ACCOUNT,
                "Message after open new acc don't match");
        softAssert.assertTrue(openNewAccountPage.isAccountIdPresented(),
                "Id after open new acc isn't presented");

        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16582 | Success opening new account with minimal deposit | Savings type")
    public void openNewSavingAccount() {
        openNewAccountPage
                .choseSavingType()
                .clickOpenNewAccount();

        softAssert.assertEquals(openNewAccountPage.getMessageAfterOpenNewAccount(), MESSAGE_AFTER_SUCCESSFULLY_OPENING_NEW_ACCOUNT,
                "Message after open new acc don't match");
        softAssert.assertTrue(openNewAccountPage.isAccountIdPresented(),
                "Id after open new acc isn't presented");

        softAssert.assertAll();
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