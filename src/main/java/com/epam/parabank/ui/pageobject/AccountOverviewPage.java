package com.epam.parabank.ui.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class AccountOverviewPage extends AbstractPage {
    @FindBy(css = "table#accountTable")
    private WebElement accountTable;
    @FindBy(css = "tr.ng-scope:nth-child(1) a.ng-binding")
    private WebElement firstAccountNumber;
    @FindBy(css = "tr.ng-scope:nth-child(1) td.ng-binding:nth-of-type(2)")
    private WebElement currentBalanceOfFirstAcc;
    @FindBy(css = "tr.ng-scope:nth-child(1) td.ng-binding:nth-of-type(3)")
    private WebElement availableAmountOfFirstAcc;
    @FindBy(css = "a.ng-binding")
    private List<WebElement> accountNumbers;
    @Getter
    private List<String> accountNumbersList;

    @Getter
    private String accountNumber;

    public AccountOverviewPage() {
        super();
    }

    @Override
    protected void waitForPageLoad() {
        new WebDriverWait(driver, BASE_WAIT_TIME).until(ExpectedConditions.visibilityOf(accountTable));
    }


    public boolean isAccountTableDisplayed() {
        return accountTable.isDisplayed();
    }

    public String getFirstAccountNumber() {
        return waitForElementToBeClickableByWebElement(firstAccountNumber).getText();
    }

    public String getCurrentBalanceOfFirstAcc() {
        return waitForElementVisibilityByWebElement(currentBalanceOfFirstAcc).getText();
    }

    public String getAvailableAmountOfFirstAcc() {
        return waitForElementVisibilityByWebElement(availableAmountOfFirstAcc).getText();
    }

    public AccountOverviewPage saveFirstAccountNumber() {
        accountNumber = waitForElementToBeClickableByWebElement(firstAccountNumber).getText();
        return this;
    }

    public List<String> getAccountNumbers() {
        log.info("Save all user's account numbers.");
        waitForElementToBeClickableByWebElement(firstAccountNumber);
        accountNumbersList = getTextFromWebElements(accountNumbers);
        return accountNumbersList;
    }
}
