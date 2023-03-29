package com.epam.parabank.ui.pageobject;

import static java.lang.String.format;

import com.epam.parabank.ui.businessobject.model.User;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
public class ForgotLoginInfoPage extends AbstractPage {
    @FindBy(css = "#firstName")
    private WebElement firstNameField;
    @FindBy(css = "#lastName")
    private WebElement lastNameField;
    @FindBy(css = "input[name='address.street']")
    private WebElement addressField;
    @FindBy(css = "input[name='address.city']")
    private WebElement cityField;
    @FindBy(css = "input[name='address.state']")
    private WebElement stateField;
    @FindBy(css = "input[name='address.zipCode']")
    private WebElement zipCodeField;
    @FindBy(css = "#ssn")
    private WebElement ssnField;
    @FindBy(css = "input[value='Find My Login Info']")
    private WebElement forgotLoginInfoButton;
    @FindBy(css = "p.error")
    private WebElement errorText;
    @FindBy(css = "span.error")
    private List<WebElement> emptyErrorText;
    @FindBy(css = "#rightPanel > p")
    private List<WebElement> forgotLoginInfoResultText;
    private List<String> passwordAndLoginData;

    @Override
    protected void waitForPageLoad() {
        waitForElementVisibilityByWebElement(firstNameField);
    }

    public ForgotLoginInfoPage enterUserData(User user) {
        log.info(format("Enter forgot login info data for user %s", user.toString()));
        waitForPageLoad();
        enterFirstName(user.getFirstName());
        enterLastName(user.getLastName());
        enterAddress(user.getAddress());
        enterCity(user.getCity());
        enterState(user.getState());
        enterZipCode(user.getZipCode());
        enterSsn(user.getSsn());
        return this;
    }

    public ForgotLoginInfoPage sendForgotLoginInfoRequest() {
        log.info("Send forgot login info request");
        forgotLoginInfoButton.click();
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return errorText.isDisplayed();
    }

    public String getErrorText() {
        return waitForElementVisibilityByWebElement(errorText).getText();
    }

    public List<String> getEmptyErrorText() {
        return getTextFromWebElements(emptyErrorText);
    }

    public List<String> getResultText() {
        passwordAndLoginData = getTextFromWebElements(forgotLoginInfoResultText);
        return passwordAndLoginData;
    }

    public boolean isRequiredDataReturnedInForgotLoginInfoMessage(String userData) {
        return passwordAndLoginData.stream().anyMatch(s -> s.contains(userData));
    }

    private void enterFirstName(String userFirstName) {
        firstNameField.sendKeys(userFirstName);
    }

    private void enterLastName(String userLastName) {
        lastNameField.sendKeys(userLastName);
    }

    private void enterAddress(String userAddress) {
        addressField.sendKeys(userAddress);
    }

    private void enterCity(String userCity) {
        cityField.sendKeys(userCity);
    }

    private void enterState(String userState) {
        stateField.sendKeys(userState);
    }

    private void enterZipCode(String userZipCode) {
        zipCodeField.sendKeys(userZipCode);
    }

    private void enterSsn(String userSsn) {
        ssnField.sendKeys(userSsn);
    }
}
