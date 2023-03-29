package com.epam.parabank.ui.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class TransactionResultPage extends AbstractPage{

    private static final String TRANSACTION_DATE = "td.ng-binding";
    private static final String TRANSACTION_AMOUNT= "span[ng-if*='Debit']";

    @FindBy(css = "div[ng-if='showResult'] h1")
    private WebElement resultsText;
    @FindBy(css = TRANSACTION_DATE)
    private WebElement transactionDate;
    @FindBy (css = TRANSACTION_AMOUNT)
    private WebElement transactionAmount;
    @FindBy (css = TRANSACTION_DATE)
    private List<WebElement> transactionsDates;
    @FindBy (css = TRANSACTION_AMOUNT)
    private List<WebElement> debitTransactionsAmount;
    @FindBy (css = "p.error")
    private WebElement errorMessage;

    @Getter
    private List<String> transactionsDatesList;
    @Getter
    private List<String> transactionsAmountsList;
    @Getter
    private String transactionResultPageTitleText;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    private void findTransactionsDates() {
        log.info("Find transactions date");
        waitForElementVisibilityByCssLocator(TRANSACTION_DATE);
        transactionsDatesList = getTextFromWebElements(transactionsDates);
    }

    private void findTransactionsAmounts() {
        log.info("Find transactions amount");
        waitForElementVisibilityByCssLocator(TRANSACTION_AMOUNT);
        transactionsAmountsList = getTextFromWebElements(debitTransactionsAmount);
    }

    private void findTransactionResultsText() {
        log.info("Find transactions result title text");
        waitForElementVisibilityByWebElement(resultsText);
        transactionResultPageTitleText = resultsText.getText();
    }

    public TransactionResultPage getDataOnTransactionResultPage() {
        log.info("Get data on transaction result page");
        findTransactionsDates();
        findTransactionsAmounts();
        findTransactionResultsText();
        return this;
    }

    public String getErrorMessage() {
        log.info("Get error message on transaction result page");
        waitForElementVisibilityByWebElement(errorMessage);
        return errorMessage.getText();
    }

    public String getTransactionAmount() {
        log.info("Find transaction amount");
        waitForElementVisibilityByCssLocator(TRANSACTION_AMOUNT);
        return transactionAmount.getText();
    }

    public String getTransactionDate() {
        log.info("Find transaction amount");
        waitForElementVisibilityByCssLocator(TRANSACTION_AMOUNT);
        return transactionDate.getText();
    }
}