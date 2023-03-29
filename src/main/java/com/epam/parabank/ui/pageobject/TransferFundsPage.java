package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.log4j.Log4j;

@Log4j
public class TransferFundsPage extends AbstractPage{

    @FindBy(css = "#amount")
    private WebElement amountField;
    @FindBy(css = "#fromAccountId")
    private WebElement fromAccount;
    @FindBy(css = "option[label*='1']")
    private WebElement forWait;
    @FindBy(css = "#toAccountId")
    private WebElement toAccount;
    @FindBy(css = "input[value='Transfer']")
    private WebElement transferButton;
    @FindBy(css = "span[class='ng-binding']:first-child")
    private WebElement getAmountField;
    @FindBy(xpath = "//h1[text()='Transfer Complete!']")
    private WebElement messageAfterInvalidTransferFund;

    private String valueForToString = "select:last-child option[value='%s']";

    @Override
    protected void waitForPageLoad() {}

    public String getAmount() {
        return waitForElementVisibilityByWebElement(getAmountField).getText();
    }

    public String getMessageAfterInvalidTransferFund() {
        return waitForElementVisibilityByWebElement(messageAfterInvalidTransferFund).getText();
    }

    public String getFirstAccountId() {
        return fromAccount.getText().trim();
    }

    public String getSecondAccountId() {
        return toAccount.getText().trim();
    }

    public TransferFundsPage inputAmount(String amount) {
        log.info("Input amount.");
        waitForElementVisibilityByWebElement(forWait);
        waitForElementVisibilityByWebElement(amountField).sendKeys(amount);
        return this;
    }

    public TransferFundsPage chooseSecondAcc(String toAccountId) {
        log.info("Choose any account.");
        waitForElementVisibilityByWebElement(stringFormatToWebElement(valueForToString,toAccountId));
        stringFormatToWebElement(valueForToString,toAccountId).click();
        return this;
    }

    public TransferFundsPage clickTransferButton() {
        log.info("Click transfer button.");
        transferButton.click();
        return this;
    }

    public Boolean isFirstAccIdInToAccFieldPresented() {
        return transferButton.isEnabled();
    }

    private WebElement stringFormatToWebElement(String selector, String value) {
         return new WebDriverWait(driver,BASE_WAIT_TIME).
                until(ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(selector,value))));
    }
}