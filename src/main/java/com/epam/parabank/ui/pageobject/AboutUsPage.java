package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.log4j.Log4j;

@Log4j
public class AboutUsPage extends AbstractPage {

    @FindBy(css = "#rightPanel p")
    private WebElement textOnPage;
    @FindBy(css = "p a[href='http://www.parasoft.com/']")
    private WebElement parasoftLink;

    public String getPageText() {
        log.info("Get text on page");
        return textOnPage.getText().replaceAll("\n", "");
    }

    public AboutUsPage clickParasoftLink() {
        log.info("Open Parasoft website.");
        parasoftLink.click();
        return this;
    }

    public String getPageUrl() {
        log.info("Get current url");
        return driver.getCurrentUrl();
    }

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }
}
