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

public class TransferFundsNegativeTest extends AbstractTest {

    private TransferFundsPage transferFundsPage;
    private static final String AMOUNT_HUGE = "100000";
    private static final String AMOUNT_NEGATIVE = "-100";
    private static final String MESSAGE_TRANSFER_FUNDS_WITH_IMPOSSIBLE_SETUP = "We cannot transfer funds more than available.";
    private static final String MESSAGE_TRANSFER_FUNDS_WITH_NEGATIVE_AMOUNT = "Please enter a valid amount";

    @BeforeMethod(alwaysRun = true)
    public void setBeforeSuite() {
        user = new UserManager().getUser();
        CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister(user);
    }

    @Test(description = "EPMFARMATS-16591 | Transfers with amount more than available are impossible | SHOULD FALL")
    public void successTransferFundsBetweenTwoAccounts() {
        String secondAccountId = new AccountServicesComponent()
                .clickOpenAccountBtn()
                .clickOpenNewAccount()
                .getNewAccountId();
        transferFundsPage = new OpenNewAccountPage()
                .openTransferFundsPage()
                .chooseSecondAcc(secondAccountId)
                .inputAmount(AMOUNT_HUGE)
                .clickTransferButton();

        Assert.assertEquals(transferFundsPage.getMessageAfterInvalidTransferFund(),
                MESSAGE_TRANSFER_FUNDS_WITH_IMPOSSIBLE_SETUP,
                "messages about Transfer Fund with invalid setup don't match");
    }

    @Test(description = "EPMFARMATS-16590 | Transfers within one account are impossible | SHOULD FALL")
    public void transfersWithinOneAccountAreImpossible() {
        transferFundsPage = new OpenNewAccountPage()
                .openTransferFundsPage();

        Assert.assertFalse(transferFundsPage.isFirstAccIdInToAccFieldPresented(),
                "first Account Id available in toAccount field");
    }

    @Test(description = "EPMFARMATS-16609 | Insert symbols or/and letters or negative amount is impossible | SHOULD FALL")
    public void transferWithNegativeValue() {
        transferFundsPage = new OpenNewAccountPage()
                .openTransferFundsPage()
                .inputAmount(AMOUNT_NEGATIVE)
                .clickTransferButton();

        Assert.assertEquals(transferFundsPage.getMessageAfterInvalidTransferFund(),
                MESSAGE_TRANSFER_FUNDS_WITH_NEGATIVE_AMOUNT,
                "messages about Transfer Fund with negative amount don't match");
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