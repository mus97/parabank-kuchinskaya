package com.epam.parabank.ui.test.transferfunds;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AccountServicesComponent;
import com.epam.parabank.ui.pageobject.OpenNewAccountPage;
import com.epam.parabank.ui.pageobject.TransferFundsPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransferFundsTest extends AbstractTest {

    private TransferFundsPage transferFundsPage;
    private String firstAccountId;
    private String secondAccountId;
    private static final String AMOUNT = "75.00";
    private static final String EXPECTED_TRANSFER_FUNDS_MESSAGE = "$%s has been transferred from account #%s to account #%s. ";

    @BeforeMethod(alwaysRun = true)
    public void setBeforeSuite() {
        user = new UserManager().getUser();
        firstAccountId = CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister(user).getAccountServicesComponent()
                .clickOpenAccountBtn()
                .choseCheckingType()
                .getFirstAccountId();
        secondAccountId = new OpenNewAccountPage()
                .clickOpenNewAccount()
                .getNewAccountId();
    }

    @Test(description = "EPMFARMATS-16589 | Success Transfer Funds between two accounts")
    public void successTransferFundsBetweenTwoAccounts() {
        transferFundsPage = new OpenNewAccountPage()
                .openTransferFundsPage()
                .chooseSecondAcc(secondAccountId)
                .inputAmount(AMOUNT)
                .clickTransferButton();

        String expectedMessage = String.format(
                EXPECTED_TRANSFER_FUNDS_MESSAGE,
                AMOUNT,
                firstAccountId,
                secondAccountId);

        Assert.assertEquals(transferFundsPage.getAmount() + " has been transferred from account #"
                        + transferFundsPage.getFirstAccountId() +" to account #" + transferFundsPage.getSecondAccountId() + ". ",
                expectedMessage, "messages about successful Transfer Fund don't match");
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