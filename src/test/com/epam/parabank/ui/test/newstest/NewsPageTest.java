package com.epam.parabank.ui.test.newstest;

import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.NewsPage;
import com.epam.parabank.ui.test.AbstractTest;

import lombok.extern.log4j.Log4j2;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class NewsPageTest extends AbstractTest {

    @BeforeMethod(alwaysRun = true)
    public void setSoftAssert() {
        softAssert =  new SoftAssert();
    }

    @Test(description = "EPMFARMATS-16611 | News sections content")
    public void newsDatePresentAndTheSame() {
        NewsPage newsPage = new MainPage()
                .openNewsPage()
                .findNewsDatesOnNewsPage()
                .openMainPage()
                .findNewsDateOnMainPage();

        softAssert.assertTrue(newsPage.isNewsDatePresent(), "A news date is not present");
        softAssert.assertEquals(newsPage.getNewsDate(),
                newsPage.getDatesList().get(0),
                "News dates don't the same : ");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16611 | News sections content")
    public void newsTitlesAreSame() {
        NewsPage newsPage = new MainPage()
                .openNewsPage()
                .findNewsTitlesOnNewsPage()
                .openMainPage()
                .findNewsTitlesOnMainPage();

        List<String> titlesOnHomePage = newsPage.getNewsTitlesOnHomePage();
        List<String> titlesOnNewsPage = newsPage.getNewsTitlesOnNewsPage();
        softAssert.assertTrue(titlesOnNewsPage.containsAll(titlesOnHomePage), "Titles on news page and on main page are not the same :");
        softAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
