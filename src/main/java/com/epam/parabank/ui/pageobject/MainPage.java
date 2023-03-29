package com.epam.parabank.ui.pageobject;

import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.URL_BASE;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;
import lombok.extern.log4j.Log4j;


@Getter
@Log4j
public class MainPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='footerPanel']/descendant::*[contains(text(), 'Contact Us')]")
    private WebElement contactUsFooterMenuLink;
    @FindBy(css = "#footerPanel a[href*='about']")
    private WebElement aboutUsLinkFooterMenu;
    @FindBy(css = "p a[href*='news']")
    private WebElement readMeLinkNewsSection;
    @FindBy(xpath = "//div[@id='headerPanel']//a[contains (text(),'Admin Page')]")
    private WebElement adminLink;
    @FindBy(xpath = "//a[contains (text(),'Forgot login info?')]")
    private WebElement forgotLoginInfoLink;
    @FindBy(xpath = "//a[contains (text(),'Register')]")
    private WebElement registerLink;
    @FindBy(css = "#leftPanel h2")
    private WebElement customerLogin;

    public MainPage() {
        super();
        String parabankWebsite =
                propertyReader.getProperty(URL_BASE.getPropertyName());
        driver.get(parabankWebsite);
    }

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public AboutUsPage openAboutUsPage() {
        log.info("Open About us page");
        waitForElementToBeClickableByWebElement(aboutUsLinkFooterMenu)
                .click();
        return new AboutUsPage();
    }

    public AdminPage openAdminPage() {
        waitForElementToBeClickableByWebElement(adminLink)
                .click();
        return new AdminPage();
    }

    public ContactUsPage openContactUsPage() {
        waitForElementToBeClickableByWebElement(contactUsFooterMenuLink)
                .click();
        return new ContactUsPage();
    }

    public ForgotLoginInfoPage openForgotLoginInfoPage() {
        waitForElementToBeClickableByWebElement(forgotLoginInfoLink)
                .click();
        return new ForgotLoginInfoPage();
    }

    public NewsPage openNewsPage() {
        log.info("Open News page");
        waitForElementToBeClickableByWebElement(readMeLinkNewsSection)
                .click();
        return new NewsPage();
    }

    public RegisterPage openRegisterPage() {
        waitForElementToBeClickableByWebElement(registerLink)
                .click();
        return new RegisterPage();
    }
}
