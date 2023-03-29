package com.epam.parabank.ui.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.parabank.ui.businessobject.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
public class ContactUsPage extends AbstractPage {
    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameField;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneField;
    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement messageField;
    @FindBy(xpath = "//input[@value='Send to Customer Care']")
    private WebElement sendToCostumerCareButton;
    @FindBy(xpath = "//span[@class='error']")
    private List<WebElement> emptyFieldErrorMessage;
    @FindBy(xpath = "//div[@id='rightPanel']/descendant::*")
    private List<WebElement> resultMessageText;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public ContactUsPage enterUserData(User user) {
        log.info("Enter username/phoneNumber/email on contact us page");
        enterName(user);
        enterPhoneNumber(user);
        enterEmail(user);
        return this;
    }

    public ContactUsPage enterContactUsMessage(String contactUsMessage) {
        log.info("Enter message on contact us page");
        messageField.sendKeys(contactUsMessage);
        return this;
    }

    public ContactUsPage sendMessageToCostumerCare() {
        log.info("Send contact us report to costumer care");
        sendToCostumerCareButton.click();
        return this;
    }

    public List<String> getResultText() {
        return getTextFromWebElements(resultMessageText);
    }

    public List<String> getEmptyFieldErrorText() {
        return getTextFromWebElements(emptyFieldErrorMessage);
    }

    private void enterName(User user) {
        nameField.sendKeys(user.getUsername());
    }

    private void enterPhoneNumber(User user) {
        phoneField.sendKeys(user.getPhoneNumber());
    }

    private void enterEmail(User user) {
        emailField.sendKeys(user.getEmail());
    }
}
