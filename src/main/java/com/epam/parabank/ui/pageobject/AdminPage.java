package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.URL_ADMINPAGE;

import lombok.extern.log4j.Log4j;

@Log4j
public class AdminPage extends AbstractPage {

    private static final String LOAN_PROVIDER_BASE_LOCATOR = "#loanProvider option[value='%s']";
    private static final String LOAN_PROCESSOR_BASE_LOCATOR = "#loanProcessor option[value='%s']";

    @FindBy(css = "input[value=Shutdown]")
    private WebElement shutdownButton;
    @FindBy(css = "input[value=Startup]")
    private WebElement startupButton;
    @FindBy(css = ".button[value=CLEAN]")
    private WebElement cleanDatabaseBtn;
    @FindBy(css = "#rightPanel p b")
    private WebElement databaseCleanedMessage;
    @FindBy(css = "form[name=toggleJms] td:nth-of-type(2)")
    private WebElement statusForm;
    @FindBy(css = "#soapEndpoint")
    private WebElement soapEndPoint;
    @FindBy(css = "#restEndpoint")
    private WebElement restEndPoint;
    @FindBy(css = "#endpoint")
    private WebElement endPoint;
    @FindBy(css = "#loanProcessorThreshold")
    private WebElement loanThreshold;
    @FindBy(css = "#initialBalance")
    private WebElement initBalance;
    @FindBy(css = "#minimumBalance")
    private WebElement minBalance;
    @FindBy(css = "input[value='Submit']")
    private WebElement submitButton;
    @FindBy(css = "#loanProvider")
    private WebElement loanProvider;
    @FindBy(css = "#loanProcessor")
    private WebElement loanProcessor;

    @Override
    protected void waitForPageLoad() {
        new WebDriverWait(driver, BASE_WAIT_TIME).until(ExpectedConditions.visibilityOf(shutdownButton));
    }

    private void openLoanProviderDropDownList() {
        log.info("Open loan provider drop down list");
        loanProvider.click();
    }

    private void setLoanProvider(String loanProvider) {
        log.info("Set loan provider as " + loanProvider);
        driver.findElement(By.cssSelector(String.format(LOAN_PROVIDER_BASE_LOCATOR, loanProvider))).click();
    }

    private void openLoanProcessorDropDownList() {
        log.info("Open loan processor drop down list");
        loanProcessor.click();
    }

    private void setLoanProcessor(String loanProcessor) {
        log.info("Set loan provider as " + loanProcessor);
        driver.findElement(By.cssSelector(String.format(LOAN_PROCESSOR_BASE_LOCATOR, loanProcessor))).click();
    }

    private void clearSoapEndPointField() {
        soapEndPoint.clear();
    }

    private void clearRestEndPointField() {
        restEndPoint.clear();
    }

    private void clearEndPointField() {
        endPoint.clear();
    }

    private void setThreshold(String key) {
        loanThreshold.clear();
        loanThreshold.sendKeys(key);
    }

    public AdminPage openPage() {
        String parabankAdminPage =
                propertyReader.getProperty(URL_ADMINPAGE.getPropertyName());
        driver.get(parabankAdminPage);

        log.info("Admin Page has been opened.");
        return this;
    }

    public AdminPage clickShutdownButton() {
        shutdownButton.click();
        return new AdminPage();
    }

    public void clickStartupButton() {
        startupButton.click();
    }

    public MainPage openMainPage() {
        return new MainPage();
    }

    public String getStatusName() {
        return statusForm.getText();
    }

    public AdminPage chooseButton() {
        String statusName = getStatusName();
        log.info("Status is " + statusName);
        switch (statusName) {
            case "Running":
                return clickShutdownButton();
            case "Stopped":
                clickStartupButton();
                return clickShutdownButton();
            default: {
                log.info("No match with status.");
                return null;
            }
        }
    }

    public AdminPage cleanDatabase() {
        log.info("Clean database.");
        cleanDatabaseBtn.click();
        return this;
    }

    public String getDatabaseCleanedMessage() {
        return databaseCleanedMessage.getText();
    }

    public AdminPage setInitBalance(String balance) {
        log.info("Set Init.Balance:$");
        waitForElementVisibilityByWebElement(initBalance).clear();
        initBalance.sendKeys(balance);
        return this;
    }

    public AdminPage setMinBalance(String balance) {
        log.info("Set Min.Balance:$");
        waitForElementVisibilityByWebElement(minBalance).clear();
        minBalance.sendKeys(balance);
        return this;
    }

    public AdminPage clickSubmitButton() {
        log.info("Click submit button");
        waitForElementToBeClickableByWebElement(submitButton).click();
        return this;
    }

    public AdminPage cleanEndpoints() {
        clearEndPointField();
        clearRestEndPointField();
        clearSoapEndPointField();
        return this;
    }

    public AdminPage setupApplication(String initBalance, String minBalance, String loanProvider, String loanProcessor, String threshold) {
        setInitBalance(initBalance);
        setMinBalance(minBalance);
        openLoanProviderDropDownList();
        setLoanProvider(loanProvider);
        openLoanProcessorDropDownList();
        setLoanProcessor(loanProcessor);
        setThreshold(threshold);
        clickSubmitButton();
        return new AdminPage();
    }

    public AdminPage setupInitialBalanceAndDeposit(float initBalance, float deposit) {
        setInitBalance(String.valueOf(initBalance));
        setMinBalance(String.valueOf(deposit));
        clickSubmitButton();
        return new AdminPage();
    }

    public AdminPage setupInitialBalance(float initBalance) {
        setInitBalance(String.valueOf(initBalance));
        clickSubmitButton();
        return new AdminPage();
    }

    public RegisterPage openRegisterPage() {
        return new RegisterPage();
    }
}