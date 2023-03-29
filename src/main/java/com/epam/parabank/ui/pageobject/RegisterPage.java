package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.parabank.ui.util.RegistrationFormFiller;

import lombok.extern.log4j.Log4j;

@Log4j
public class RegisterPage extends AbstractPage {

    /**
     * Getter locators
     */
    @FindBy(xpath = "//*[@id='rightPanel']/p")
    private WebElement registrationMessage;
    @FindBy(id = "repeatedPassword.errors")
    private WebElement invalidConfirmMessage;
    @FindBy(id = "customer.address.street.errors")
    private WebElement emptyAddressFieldMessage;
    @FindBy(id = "customer.address.city.errors")
    private WebElement emptyCityFieldMessage;
    @FindBy(id = "customer.ssn.errors")
    private WebElement emptySsnFieldMessage;

    /**
     * Registration form fields locators
     */
    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;
    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;
    @FindBy(id = "customer.address.street")
    private WebElement addressField;
    @FindBy(id = "customer.address.city")
    private WebElement cityField;
    @FindBy(id = "customer.address.state")
    private WebElement stateField;
    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeField;
    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneNumberField;
    @FindBy(id = "customer.ssn")
    private WebElement ssnField;
    @FindBy(id = "customer.username")
    private WebElement usernameField;
    @FindBy(id = "customer.password")
    private WebElement passwordField;
    @FindBy(id = "repeatedPassword")
    private WebElement confirmField;
    @FindBy(xpath = "//*[@class = 'button' and @value = 'Register']")
    private WebElement registerButton;

    /**
     * Left Panel buttons locators
     * */

    @FindBy(css = "a[href*='update']")
    private WebElement updateContactInfo;
    @FindBy(css = "a[href*='requestloan']")
    private WebElement requestLoanButton;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public String getRegistrationMessage() {
        return registrationMessage.getText().trim();
    }

    public String getInvalidConfirmFieldMessage() {
        return invalidConfirmMessage.getText().trim();
    }

    public String getEmptyAddressFieldMessage() {
        return emptyAddressFieldMessage.getText().trim();
    }

    public String getEmptyCityFieldMessage() {
        return emptyCityFieldMessage.getText().trim();
    }

    public String getEmptySsnFieldMessage() {
        return emptySsnFieldMessage.getText().trim();
    }

    public RegisterPage inputFirstName(String firstName) {
        return sendKeysIntoField(firstNameField, firstName);
    }

    public RegisterPage inputLastName(String lastName) {
        return sendKeysIntoField(lastNameField, lastName);
    }

    public RegisterPage inputAddress(String address) {
        return sendKeysIntoField(addressField, address);
    }

    public RegisterPage inputCity(String city) {
        return sendKeysIntoField(cityField, city);
    }

    public RegisterPage inputState(String state) {
        return sendKeysIntoField(stateField, state);
    }

    public RegisterPage inputZipCode(String zipCode) {
        return sendKeysIntoField(zipCodeField, zipCode);
    }

    public RegisterPage inputPhoneNumber(String phoneNumber) {
        return sendKeysIntoField(phoneNumberField, phoneNumber);
    }

    public RegisterPage inputSsn(String ssn) {
        return sendKeysIntoField(ssnField, ssn);
    }

    public RegisterPage inputUsername(String username) {
        return sendKeysIntoField(usernameField, username);
    }

    public RegisterPage inputPassword(String password) {
        return sendKeysIntoField(passwordField, password);
    }

    public RegisterPage inputConfirm(String confirm) {
        return sendKeysIntoField(confirmField, confirm);
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    public RegistrationFormFiller fillRegistrationForm() {
        log.info("Fill the registration form");
        return new RegistrationFormFiller();
    }

    public RequestLoanPage openRequestLoanPage() {
        log.info("Open request lon page");
        requestLoanButton.click();
        return new RequestLoanPage();
    }

    private RegisterPage sendKeysIntoField(WebElement webElement, String str) {
        webElement.sendKeys(str);
        return this;
    }

    public AccountServicesComponent getAccountServicesComponent() {
        return new AccountServicesComponent();
    }

    public UpdateContactInfoPage openUpdateContactInfoPage(){
        log.info("Open Update contact info page");
        updateContactInfo.click();
        return new UpdateContactInfoPage();
    }
}