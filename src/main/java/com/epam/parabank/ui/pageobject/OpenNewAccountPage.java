package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.log4j.Log4j;

@Log4j
public class OpenNewAccountPage extends AbstractPage{
    @FindBy(css = ".button[value='Open New Account']")
    private WebElement openNewAccountButton;
    @FindBy(css = "#newAccountId")
    private WebElement newAccountId;
    @FindBy(css ="#fromAccountId")
    private WebElement listOfAccounts;
    @FindBy(css = "select#type")
    private WebElement typesOfAccount;
    @FindBy (xpath = "//option[text()='CHECKING']")
    private WebElement typeChecking;
    @FindBy(css = "#type option:last-child")
    private WebElement typeSavings;
    @FindBy(xpath = "//h1[text()='Account Opened!']")
    private WebElement textAfterOpenNewAccount;
    @FindBy(css = "a[href*='transfer']")
    private WebElement transferFundsPageButton;
    @FindBy(css = "option[label*='1']")
    private WebElement mainAccountId;
    @FindBy (css = "option[value*='13']")
    private WebElement fromAccountIdOption;

    @Override
    protected void waitForPageLoad() {
        new WebDriverWait(driver, BASE_WAIT_TIME).until(ExpectedConditions.visibilityOf(openNewAccountButton));
    }

    public OpenNewAccountPage openNewCheckingAccount(){
        log.info("Create new account.");
        choseCheckingType();
        chooseFromAccount();
        clickOpenNewAccountButton();
        getNewAccountId();
        return new OpenNewAccountPage();
    }

    public OpenNewAccountPage chooseFromAccount(){
        log.info("Choose account");
        waitForElementToBeClickableByWebElement(listOfAccounts).click();
        waitForElementToBeClickableByWebElement(fromAccountIdOption).click();
        return this;
    }

    public OpenNewAccountPage clickOpenNewAccountButton(){
        log.info("Click 'OPEN NEW ACCOUNT' button.");
        waitForElementToBeClickableByWebElement(openNewAccountButton).click();
        return this;
    }

    public String getNewAccountId() {
        log.info("Get new account Id.");
        waitForElementVisibilityByWebElement(newAccountId);
        return newAccountId.getText();
    }

    public String getFirstAccountId() {
        return waitForElementVisibilityByWebElement(mainAccountId).getText();
    }

    public String getMessageAfterOpenNewAccount() {
        waitForElementVisibilityByWebElement(newAccountId);
        return textAfterOpenNewAccount.getText().trim();
    }

    public OpenNewAccountPage choseCheckingType() {
        log.info("Chose 'Checking' type.");
        waitForElementToBeClickableByWebElement(typeChecking).click();
        return this;
    }

    public OpenNewAccountPage choseSavingType() {
        log.info("Chose 'Saving' type.");
        waitForElementToBeClickableByWebElement(typeSavings).click();
        return this;
    }

    public OpenNewAccountPage clickOpenNewAccount() {
        log.info("Click open new account button.");
        waitForElementVisibilityByWebElement(mainAccountId);
        waitForElementToBeClickableByWebElement(openNewAccountButton).click();
        return this;
    }

    public boolean isAccountIdPresented() {
        return getNewAccountId()!=null;
    }

    public TransferFundsPage openTransferFundsPage(){
        log.info("Open Update contact info page");
        transferFundsPageButton.click();
        return new TransferFundsPage();
    }

    public AccountDetailPage getAccountDetailsPage() {
        log.info("Get new account Id.");
        waitForElementVisibilityByWebElement(newAccountId).click();
        return new AccountDetailPage();
    }
}