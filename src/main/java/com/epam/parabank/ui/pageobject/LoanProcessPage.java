package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoanProcessPage extends AbstractPage{

    @FindBy(css = "#loanStatus")
    private WebElement loanStatus;
    @FindBy(css = "#newAccountId")
    private WebElement newAccountId;
    @FindBy(css = "div[ng-if*='loanResponse']>p")
    private WebElement responseText;
    @FindBy(css = "p.error")
    private WebElement errorMessage;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public String getStatusText() {
        log.info("Get status text");
        return waitForElementVisibilityByWebElement(loanStatus).getText();
    }

    public String getResponseText() {
        return responseText.getText();
    }

    public boolean isNewAccountIdDisplayed() {
        return newAccountId.isDisplayed();
    }

    public String saveNewAccountId() {
        log.info("Save new account ID");
        return newAccountId.getText();
    }

    public AccountDetailPage openAccountDetails() {
        log.info("Open new account details");
        newAccountId.click();
        return new AccountDetailPage();
    }

    public String getErrorMessage() {
        return waitForElementVisibilityByWebElement(errorMessage).getText();
    }
}
