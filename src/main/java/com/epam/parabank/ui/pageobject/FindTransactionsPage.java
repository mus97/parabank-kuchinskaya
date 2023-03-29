package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.log4j.Log4j;

@Log4j
public class FindTransactionsPage extends AbstractPage{

    @FindBy (css = "#accountId option")
    private WebElement accountNumber;
    @FindBy (css = "#criteria\\.transactionId")
    private WebElement idField;
    @FindBy (css = "button[ng-click*='ID']")
    private WebElement findTransactionByIDButton;
    @FindBy(css =  "button[ng-click*='DATE']")
    private WebElement findTransactionByDateButton;
    @FindBy (css = "#criteria\\.amount")
    private WebElement amountField;
    @FindBy (css = "button[ng-click*='AMOUNT']")
    private WebElement findTransactionByAmountButton;
    @FindBy (css = "#criteria\\.fromDate")
    private WebElement findFromDateField;
    @FindBy (css = "#criteria\\.toDate")
    private WebElement findToDateField;
    @FindBy (css = "#criteria\\.onDate")
    private WebElement dateField;
    @FindBy (css = "button[ng-click*='DATE_RANGE']")
    private WebElement findTransactionByDateRangeButton;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public FindTransactionsPage selectAccountId() {
        log.info("Select account number");
        accountNumber.click();
        return this;
    }

    public TransactionResultPage clickFindTransactionByIDButton() {
        log.info("Click find transaction by ID button");
        findTransactionByIDButton.click();
        return new TransactionResultPage();
    }

    public TransactionResultPage clickFindTransactionByAmountButton() {
        log.info("Click find transaction by amount button");
        findTransactionByAmountButton.click();
        return new TransactionResultPage();
    }

    public FindTransactionsPage insertId(String id) {
        log.info("Insert ID");
        idField.sendKeys(id);
        return this;
    }

    public FindTransactionsPage insertAmount(String amount) {
        log.info("Insert amount into field");
        amountField.click();
        amountField.sendKeys(amount);
        return this;
    }

    public FindTransactionsPage insertDateFrom(String dateFrom) {
        log.info("Insert start date for search transactions");
        findFromDateField.sendKeys(dateFrom);
        return this;
    }

    public FindTransactionsPage insertDateTo(String dateTo) {
        log.info("Insert end date for search transactions");
        findToDateField.sendKeys(dateTo);
        return this;
    }

    public FindTransactionsPage insertDate(String date) {
        log.info("Insert date for search transaction");
        dateField.click();
        dateField.sendKeys(date);
        return this;
    }

    public TransactionResultPage clickFindByDateButton() {
        log.info("Click find transaction by date button");
        findTransactionByDateButton.click();
        return new TransactionResultPage();
    }

    public TransactionResultPage clickFindByDateRangeButton() {
        log.info("Click find transaction by date range button");
        findTransactionByDateRangeButton.click();
        return new TransactionResultPage();
    }
}