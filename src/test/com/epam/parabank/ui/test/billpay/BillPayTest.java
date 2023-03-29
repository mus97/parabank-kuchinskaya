package com.epam.parabank.ui.test.billpay;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.BillPayPage;
import com.epam.parabank.ui.pageobject.RegisterPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.Locale;

public class BillPayTest extends AbstractTest {
    private RegisterPage loginUser;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        loginUser = CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister(user);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @DataProvider(name = "amount")
    private Object[][] getAmount() {
        return new Object[][]{{"12.20"},{"42"}};
    }

    @Test(description = "EPMFARMATS-16686 | EPMFARMATS-16688", dataProvider = "amount")
    public void testCheckPaymentWithCorrectDataIntFloat(String amount) {
        String currentAcc = loginUser.getAccountServicesComponent().openBillPayPage().getSelectedAccount();
        String account = "1";
        String amountFloat = String.format(Locale.US, "%.2f", new BigDecimal(amount));

        BillPayPage billPay = loginUser.getAccountServicesComponent().openBillPayPage().sendPayment(user, account, amount);
        String message = String.format("""
                Bill Payment Complete
                Bill Payment to %s in the amount of $%s from account %s was successful.
                See Account Activity for more details.""",user.getFirstName(), amountFloat, currentAcc);

        softAssert.assertEquals(billPay.getResultMessage(), message, "Bill payment failed.");
        softAssert.assertAll();
    }

    @Test (description = "EPMFARMATS-16687 | Check that the \"Account\" and \"Verify Account\" field work only with integer data. Negative")
    public void testCheckAccountTakeInt () {
        String message = "Please enter a valid number.";
        String account = "Incorrect_account";
        String amount = "12.20";
        BillPayPage billPayWithWrongAcc = loginUser.getAccountServicesComponent().openBillPayPage().sendPayment(user, account, amount);

        softAssert.assertEquals(billPayWithWrongAcc.getWarningMessage(), message, "Warning message is absent.");
        softAssert.assertAll();
    }
}
