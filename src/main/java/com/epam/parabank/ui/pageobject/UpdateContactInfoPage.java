package com.epam.parabank.ui.pageobject;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class UpdateContactInfoPage extends AbstractPage{

    @FindBy(css = "[id *= firstName]")
    private WebElement firstNameField;
    @FindBy(css = "[id *= lastName]")
    private WebElement lastNameField;
    @FindBy(css = "[id *= street]")
    private WebElement addressField;
    @FindBy(css = "[id *= city]")
    private WebElement cityField;
    @FindBy(css = "[id *= state]")
    private WebElement stateField;
    @FindBy(css = "[id *= zipCode]")
    private WebElement zipCodeField;
    @FindBy(css = "[id *= phoneNumber]")
    private WebElement phoneField;
    @FindBy(xpath = "//input[@value = 'Update Profile']")
    private WebElement updateButton;
    @FindBy(xpath = "//*[@ng-if= 'customer && !customer.firstName']")
    private WebElement errorText;
    @FindBy(css = "#rightPanel p")
    private WebElement messageAfterUpdate;
    @FindBy(xpath = "//h1[text()='Update Profile']")
    private WebElement titleUpdateProfile;
    @FindBy(xpath = "//*[@id = 'customer.firstName' and @class = 'input ng-pristine ng-untouched ng-valid ng-not-empty']")
    private WebElement dataField;

    @Override
    public void waitForPageLoad() {
        waitForElementVisibilityByWebElement(dataField);
    }

    public String getTitle() {
        return waitForElementVisibilityByWebElement(titleUpdateProfile).getText();
    }

    public String getErrorText() {
        return waitForElementVisibilityByWebElement(errorText).getText().trim();
    }

    public String getMessageAfterUpdate() {
        return waitForElementVisibilityByWebElement(messageAfterUpdate).getText().trim();
    }

    public String getFirstName() {
        return firstNameField.getText().trim();
    }

    public String getLastName() {
        return lastNameField.getText().trim();
    }

    public UpdateContactInfoPage clearFirstNameField() {
        log.info("Clear first name field");
        firstNameField.clear();
        return this;
    }

    public UpdateContactInfoPage changeFirstName(String firstName) {
        log.info("Clear old value and input new value into first name field");
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public UpdateContactInfoPage changeLastName(String lastName) {
        log.info("Clear old value and input new value into last name field");
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public UpdateContactInfoPage changeAddress(String address) {
        log.info("Clear old value and input new value into Address field");
        addressField.clear();
        addressField.sendKeys(address);
        return this;
    }

    public UpdateContactInfoPage changeCity(String city) {
        log.info("Clear old value and input new value into City field");
        cityField.clear();
        cityField.sendKeys(city);
        return this;
    }

    public UpdateContactInfoPage changeState(String state) {
        log.info("Clear old value and input new value into State field");
        stateField.clear();
        stateField.sendKeys(state);
        return this;
    }

    public UpdateContactInfoPage changeZipCode(String zipCode) {
        log.info("Clear old value and input new value into Zip Code field");
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    public UpdateContactInfoPage changePhoneNumber(String phoneNumber) {
        log.info("Clear old value and input new value into Phone Number field");
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
        return this;
    }

    public UpdateContactInfoPage clickUpdateInfoButton() {
        log.info("Click Update info button");
        updateButton.click();
        return this;
    }

    public UpdateContactInfoPage refreshPage() {
        log.info("Refresh page");
        driver.navigate().refresh();
        return this;
    }
}