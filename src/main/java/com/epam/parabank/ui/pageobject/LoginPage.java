package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginPage extends AbstractPage {
    @FindBy(css = "div#rightPanel")
    private WebElement errorMessage;

    @Override
    protected void waitForPageLoad() {
        new WebDriverWait(driver, BASE_WAIT_TIME).until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public String getErrorMessage() {
        log.info("Login denied: incorrect credentials or empty fields.");
        return errorMessage.getText();
    }
}
