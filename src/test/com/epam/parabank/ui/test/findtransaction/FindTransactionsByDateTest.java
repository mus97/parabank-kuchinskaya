package com.epam.parabank.ui.test.findtransaction;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.pageobject.AccountOverviewPage;
import com.epam.parabank.ui.pageobject.TransactionResultPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.format.DateTimeFormatter;

import java.util.List;

import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;

import static java.time.LocalDate.now;

public class FindTransactionsByDateTest extends FindTransactionParentTest{

    private static final String EXPECTED_TEXT = "Transaction Results";
    private static final String AMOUNT = "60.00";

    private TransactionResultPage transactionResultPage;

    @BeforeClass(alwaysRun = true)
    public void setTestEnvironment() {
        user = new UserManager().getUser();
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        AccountOverviewPage accountOverviewPage = cleanDatabaseAndRegister(user)
                .getAccountServicesComponent()
                .openAccountOverviewPage()
                .saveFirstAccountNumber();

        transactionResultPage = accountOverviewPage
                .getAccountServicesComponent()
                .openBillPayPage()
                .sendPayment(user, accountOverviewPage.getAccountNumber(), AMOUNT)
                .getAccountServicesComponent()
                .openFindTransactionPage()
                .insertDate(now().format(dateTimeFormatter))
                .clickFindByDateButton()
                .getDataOnTransactionResultPage();
    }

    @Test(description = "EPMFARMATS-16618 | Find transaction by Amount")
    public void findTransactionsByDateTest() {
        List<String> transactionsAmounts = transactionResultPage.getTransactionsAmountsList();
        List<String> transactionsDates = transactionResultPage.getTransactionsDatesList();
        softAssert.assertFalse(transactionsAmounts.isEmpty(), "The list of Amounts is empty : ");
        softAssert.assertFalse(transactionsDates.isEmpty(), "The list of Dates is empty : ");
        softAssert.assertEquals(transactionResultPage.getTransactionResultPageTitleText(), EXPECTED_TEXT, "Incorrect Transaction result page title : ");
        for (String amount : transactionsAmounts) {
            softAssert.assertEquals(amount, String.format(EXPECTED_AMOUNT_BASE, AMOUNT), "Transaction amount on Transaction result page not match with requested : ");
        }
        for (String date : transactionsDates) {
            softAssert.assertEquals(date, now().format(dateTimeFormatter), "Displayed date and date of transaction are not the same : ");
        }
        softAssert.assertAll();
    }
}