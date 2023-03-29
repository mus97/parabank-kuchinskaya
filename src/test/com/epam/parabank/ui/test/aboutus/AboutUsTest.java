package com.epam.parabank.ui.test.aboutus;

import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.TextFileReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.AboutUsPage;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.util.PropertyReader;

import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARASOFT_URL;
import static com.epam.parabank.ui.util.TextFileReader.FilePath.ABOUT_US_TEXT_FILE_PATH;

public class AboutUsTest extends AbstractTest {

    private AboutUsPage aboutUsPage;

    @BeforeMethod(alwaysRun = true)
    public void setAboutUsPage() {
        aboutUsPage = new MainPage().openAboutUsPage();
    }

    @Test(description = "EPMFARMATS-16610 | About us section content")
    public void aboutUsTextIsCorrect() {
        String expectedAboutUsText = TextFileReader.readTextFromFile(ABOUT_US_TEXT_FILE_PATH.getFilePath());

        String text = aboutUsPage
                .getPageText();
        Assert.assertEquals(text, expectedAboutUsText, "The text on the page is incorrect: ");
    }

    @Test (description = "EPMFARMATS-16610 | About us section content")
    public void parasoftLinkIsNavigable() {
        String parasoftUrl =
                PropertyReader.getInstance().getProperty(PARASOFT_URL.getPropertyName());

        String title = aboutUsPage
                .clickParasoftLink()
                .getPageUrl();
        Assert.assertEquals(title, parasoftUrl, "The link does not go to the parasoft.com: ");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
