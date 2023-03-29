package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.*;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;

public class FindTransactionService {

    private static final String DATE_PATTERN = "MM-dd-yyyy";
    static final String EXPECTED_AMOUNT_PATTERN = "$";

    private final User user;
    private final DateTimeFormatter dateTimeFormatter;
    private final AccountOverviewPage accountOverviewPage;
    private final BillPayPage billPayPage;
    private final FindTransactionsPage findTransactionsPage;
    private final TransactionResultPage transactionResultPage;

    public FindTransactionService() {
        user = new UserManager().getUser();
        billPayPage = new BillPayPage();
        accountOverviewPage = new AccountOverviewPage();
        findTransactionsPage = new FindTransactionsPage();
        transactionResultPage = new TransactionResultPage();
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    public void createTransaction(final float amount) {
        String accountNumber = accountOverviewPage
                .getAccountServicesComponent()
                .openAccountOverviewPage()
                .saveFirstAccountNumber()
                .getAccountNumber();

        billPayPage
                .getAccountServicesComponent()
                .openBillPayPage()
                .sendPayment(user, accountNumber, String.valueOf(amount));
    }

    public void getTransactionResultPage() {
        billPayPage
                .getAccountServicesComponent()
                .openFindTransactionPage();
    }

    public void findTransactionByDate() {
        findTransactionsPage
                .insertDate(now().format(dateTimeFormatter))
                .clickFindByDateButton();
    }

    public void findTransactionByAmount(final float amount) {
        findTransactionsPage
                .insertAmount(String.valueOf(amount))
                .clickFindTransactionByAmountButton();
    }

    public void verifyTransactionAmount(final float amount) {
        Assert.assertEquals(Float.parseFloat(transactionResultPage.getTransactionAmount().replace(EXPECTED_AMOUNT_PATTERN, StringUtils.EMPTY)), amount, "Expected and actual amounts don't match :");
    }

    public void verifyTransactionDate() {
        Assert.assertEquals(transactionResultPage.getTransactionDate(), now().format(dateTimeFormatter), "Expected and actual dates don't match :");
    }
}