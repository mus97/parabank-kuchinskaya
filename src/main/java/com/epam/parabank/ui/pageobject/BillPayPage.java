package com.epam.parabank.ui.pageobject;

import static java.lang.String.format;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.parabank.ui.businessobject.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
public class BillPayPage extends AbstractPage {
    @FindBy(css = "form.ng-pristine")
    private WebElement sendPaymentForm;
    @FindBy(css = "input[name='payee.name']")
    private WebElement payeeNameField;
    @FindBy(css = "input[name='payee.address.street']")
    private WebElement payeeAddressField;
    @FindBy(css = "input[name='payee.address.city']")
    private WebElement payeeCityField;
    @FindBy(css = "input[name='payee.address.state']")
    private WebElement payeeStateField;
    @FindBy(css = "input[name='payee.address.zipCode']")
    private WebElement payeeZipCodeField;
    @FindBy(css = "input[name='payee.phoneNumber']")
    private WebElement payeePhoneField;
    @FindBy(css = "input[name='payee.accountNumber']")
    private WebElement payeeAccountNumberField;
    @FindBy(css = "input[name='verifyAccount']")
    private WebElement payeeVerifyAccountField;
    @FindBy(css = "input[name='amount']")
    private WebElement amountField;
    @FindBy(css = "input[value='Send Payment']")
    private WebElement sendPaymentBtn;
    @FindBy(css = "div[ng-show='showResult']")
    private WebElement resultMessage;
    @FindBy(css = "option[selected='selected']")
    private WebElement selectedAccount;
    @FindBy(css = "[ng-show=\"validationModel.verifyAccount == 'invalid'\"]")
    private WebElement warningMessage;

    @Override
    protected void waitForPageLoad() {
        new WebDriverWait(driver, BASE_WAIT_TIME).until(ExpectedConditions.visibilityOf(sendPaymentForm));
    }

    public BillPayPage sendPayment(User user, String account, String amount) {
        log.info(format("Send bill payment with input account: %s", account));
        payeeNameField.sendKeys(user.getFirstName());
        payeeAddressField.sendKeys(user.getAddress());
        payeeCityField.sendKeys(user.getCity());
        payeeStateField.sendKeys(user.getState());
        payeeZipCodeField.sendKeys(user.getZipCode());
        payeePhoneField.sendKeys(user.getPhoneNumber());
        payeeAccountNumberField.sendKeys(account);
        payeeVerifyAccountField.sendKeys(account);
        amountField.sendKeys(amount);
        sendPaymentBtn.click();
        return new BillPayPage();
    }

    public String getResultMessage() {
        return waitForElementVisibilityByWebElement(resultMessage).getText();
    }

    public String getWarningMessage() {
        return waitForElementVisibilityByWebElement(warningMessage).getText();
    }

    public String getSelectedAccount() {
        return waitForElementToBeClickableByWebElement(selectedAccount).getText();
    }
}
