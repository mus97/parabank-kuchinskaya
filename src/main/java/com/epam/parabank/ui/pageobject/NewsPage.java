package com.epam.parabank.ui.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class NewsPage extends AbstractPage {

    @FindBy (css = "li.captionthree")
    private WebElement newsDateOnHomePage;
    @FindBy (css = "li.home a")
    private WebElement mainPageLink;
    @FindBy (css = "#rightPanel h3 b")
    private List<WebElement> newsDatesOnNewsPage;
    @FindBy (css = "a.headline")
    private List<WebElement> newsOnNewsPage;
    @FindBy (css = "#rightPanel ul.events a")
    private List<WebElement> newsOnMainPage;
    @Getter
    private List<String> newsTitlesOnNewsPage;
    @Getter
    private List<String> newsTitlesOnHomePage;
    @Getter
    private List<String> datesList;
    @Getter
    private String newsDate;

    public NewsPage openMainPage() {
        log.info("Open main page");
        mainPageLink.click();
        return this;
    }

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public boolean isNewsDatePresent() {
        return datesList.get(0) != null;
    }

    public NewsPage findNewsTitlesOnNewsPage() {
        log.info("Save news titles on News page");
        newsTitlesOnNewsPage = getTextFromWebElements(newsOnNewsPage);
        return this;
    }

    public NewsPage findNewsTitlesOnMainPage() {
        log.info("Save news titles on Main page");
        newsTitlesOnHomePage = getTextFromWebElements(newsOnMainPage);
        return this;
    }

    public NewsPage findNewsDateOnMainPage() {
        log.info("Save news dates on Main page");
        newsDate = waitForElementVisibilityByWebElement(newsDateOnHomePage).getText();
        return this;
    }

    public NewsPage findNewsDatesOnNewsPage() {
        log.info("Save News dates on News page");
        datesList = getTextFromWebElements(newsDatesOnNewsPage);
        return this;
    }
}
