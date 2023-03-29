package com.epam.parabank.ui.test.accountoverview;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AccountOverviewPage;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.OpenNewAccountPage;
import com.epam.parabank.ui.pageobject.RegisterPage;
import com.epam.parabank.ui.test.AbstractTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class AccountOverviewPageTest extends AbstractTest {
    private RegisterPage loginUser;
    private static final String BALANCE = "999";
    private static final String BALANCE_FLOAT = String.format(Locale.US, "$%.2f", new BigDecimal(BALANCE));
    private String accountNumber;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        loginUser = new MainPage().openAdminPage().cleanDatabase().setInitBalance(BALANCE).clickSubmitButton().getLoginComponent()
                .openRegisterPage().fillRegistrationForm().setUpUser(user);
        accountNumber = loginUser.getAccountServicesComponent().openAccountOverviewPage().getFirstAccountNumber();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @Test(description = "EPMFARMATS-16689 | Account overview page is correct for registered users test")
    public void testAccOvPageIsCorrectForRegistered() {
        AccountOverviewPage accountOverviewPage = loginUser.getAccountServicesComponent().openMainPageViaLogOutBtn().getLoginComponent().loginDefaultUser(user);

        //Expected results to be replaced with API method getAccount
        softAssert.assertEquals(accountOverviewPage.getFirstAccountNumber(), accountNumber, "Account number doesn't match.");
        softAssert.assertEquals(accountOverviewPage.getCurrentBalanceOfFirstAcc(), BALANCE_FLOAT, "Current balance doesn't match.");
        softAssert.assertEquals(accountOverviewPage.getAvailableAmountOfFirstAcc(), BALANCE_FLOAT, "Available amount doesn't match.");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16691 | Account overview page is correct for new users test")
    public void testAccOvPageIsCorrectForNew() {
        AccountOverviewPage accountOverviewPage = loginUser.getAccountServicesComponent().openAccountOverviewPage();

        //Expected results to be replaced with API method getAccount
        softAssert.assertEquals(accountOverviewPage.getFirstAccountNumber(), accountNumber, "Account number doesn't match.");
        softAssert.assertEquals(accountOverviewPage.getCurrentBalanceOfFirstAcc(), BALANCE_FLOAT, "Current balance doesn't match.");
        softAssert.assertEquals(accountOverviewPage.getAvailableAmountOfFirstAcc(), BALANCE_FLOAT, "Available amount doesn't match.");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16690 | Adding new account test")
    public void testAddingNewAccount() {
        String mainAccountId = loginUser.getAccountServicesComponent().openAccountOverviewPage().getFirstAccountNumber();

        OpenNewAccountPage newAcc = loginUser
                .getAccountServicesComponent()
                .clickOpenAccountBtn()
                .openNewCheckingAccount();
        String newAccountId = newAcc.getNewAccountId();
        List<String> allAccountNumbers = newAcc.getAccountServicesComponent().openAccountOverviewPage().getAccountNumbers();

        softAssert.assertTrue(allAccountNumbers.contains(newAccountId), "Cannot find new account number.");
        softAssert.assertAll();
    }
}
