package com.epam.parabank.ui.test.opennewaccount;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.OpenNewAccountPage;
import com.epam.parabank.ui.test.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenNewAccountTestNegativeTest extends AbstractTest {

    private OpenNewAccountPage openNewAccountPage;
    private static final String MESSAGE_AFTER_UNSUCCESSFUL_OPENING_NEW_ACCOUNT =
            "We cannot open a new account with your available funds";

    @BeforeMethod
    public void preconditions() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        openNewAccountPage = new MainPage()
                .openAdminPage()
                .cleanDatabase()
                .setInitBalance("500")
                .setMinBalance("1000")
                .clickSubmitButton()
                .openMainPage()
                .openRegisterPage()
                .fillRegistrationForm()
                .setUpUser(user)
                .getAccountServicesComponent()
                .clickOpenAccountBtn();
    }

    @Test(description = "EPMFARMATS-16583 | Impossible opening new account without minimal deposit | SHOULD FALL")
    public void openNewAccountWithInvalidSetUp() {
        openNewAccountPage
                .choseCheckingType()
                .clickOpenNewAccount();

        Assert.assertEquals(openNewAccountPage.getMessageAfterOpenNewAccount(),
                MESSAGE_AFTER_UNSUCCESSFUL_OPENING_NEW_ACCOUNT,
                "Message after open new acc don't match");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }
}