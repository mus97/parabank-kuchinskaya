package com.epam.parabank.ui.test.findtransaction;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;

import com.epam.parabank.ui.pageobject.AccountOverviewPage;
import com.epam.parabank.ui.pageobject.BillPayPage;
import com.epam.parabank.ui.pageobject.TransactionResultPage;
import org.testng.annotations.*;

import java.time.format.DateTimeFormatter;

import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;

public class FindTransactionsWithInvalidDataTest extends FindTransactionParentTest {

    private static final String ERROR_MESSAGE = "An internal error has occurred and has been logged.";
    private static final String AMOUNT = "100.00";

    private BillPayPage billPayPage;

    @BeforeClass(alwaysRun = true)
    public void setTestEnvironment() {
        user = new UserManager().getUser();
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        AccountOverviewPage accountOverviewPage = cleanDatabaseAndRegister(user)
                .getAccountServicesComponent()
                .openAccountOverviewPage()
                .saveFirstAccountNumber();

        billPayPage = accountOverviewPage
                .getAccountServicesComponent()
                .openBillPayPage()
                .sendPayment(user, accountOverviewPage.getAccountNumber(), AMOUNT);
    }

    @Test(description = "EPMFARMATS-16631 | Find transactions with invalid data in Find by ID field", dataProvider = "incorrectId")
    public void findTransactionWithInvalidDataTest(String id) {
        TransactionResultPage transactionResultPage = billPayPage
                .getAccountServicesComponent()
                .openFindTransactionPage()
                .insertId(id)
                .clickFindTransactionByIDButton();

        softAssert.assertEquals(transactionResultPage.getErrorMessage(), ERROR_MESSAGE, "Error message not match with expected error message : ");
        softAssert.assertAll();
    }

    @DataProvider(name = "incorrectId")
    private Object[][] getLoanData() {
        return new Object[][]{{"@#@3"},
                {"console"},
                {"@#@#"},
                {"    "}};
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}