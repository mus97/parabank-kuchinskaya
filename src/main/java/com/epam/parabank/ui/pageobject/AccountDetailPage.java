package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.log4j.Log4j;

@Log4j
public class AccountDetailPage extends AbstractPage{

    @FindBy(css = "#accountId")
    private WebElement accountId;
    @FindBy(css = "#accountType")
    private WebElement accountType;
    @FindBy(css = "#balance")
    private WebElement accountBalance;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public boolean isAccountIdDisplayed(String id) {
        log.info("Lock for account ID");
            return waitForTextToBePresentByWebElement(accountId, id);
    }

    public String getType() {
        log.info("Get account type");
        return accountType.getText();
    }

    public boolean isAccountDisplayed(String type) {
        log.info("Get account type");
        return waitForTextToBePresentByWebElement(accountType, type);
    }

    public String getBalance() {
        log.info("Get account balance");
        return accountBalance.getText();
    }
}
