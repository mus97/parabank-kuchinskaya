package com.epam.parabank.ui.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.util.PropertyReader;

import lombok.extern.log4j.Log4j;

@Log4j
public abstract class AbstractPage {

    protected static final Duration BASE_WAIT_TIME = Duration.ofSeconds(10);

    protected WebDriver driver;
    protected PropertyReader propertyReader;

    protected AbstractPage() {
        this(DriverSingleton.getDriver());
    }

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        propertyReader = PropertyReader.getInstance();
        PageFactory.initElements(driver, this);
    }

    protected abstract void waitForPageLoad();

    public WebElement waitForElementVisibilityByWebElement(WebElement webElement) {
        return new WebDriverWait(driver, BASE_WAIT_TIME)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForElementToBeClickableByWebElement(WebElement webElement) {
        return new WebDriverWait(driver, BASE_WAIT_TIME)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public boolean waitForTextToBePresentByWebElement(WebElement webElement, String text) {
        return new WebDriverWait(driver, BASE_WAIT_TIME)
                .until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public void waitForElementVisibilityByCssLocator(String locator) {
        new WebDriverWait(driver, BASE_WAIT_TIME)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
    }

    public String getUrlOfCurrentPage() {
        return driver.getCurrentUrl();
    }

    public List<String> getTextFromWebElements(List<WebElement> webElementsList) {
        log.info("Create list of WebElements text.");
        return webElementsList
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public AccountServicesComponent getAccountServicesComponent() {
        return new AccountServicesComponent();
    }

    public LoginComponent getLoginComponent() {
        return new LoginComponent();
    }
}