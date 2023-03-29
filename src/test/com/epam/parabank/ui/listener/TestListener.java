package com.epam.parabank.ui.listener;
import com.epam.parabank.ui.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IConfigurationListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener extends TestListenerAdapter implements IConfigurationListener {
    private static final String FILE_PATH = ".//target/screenshots/'%s'.png";
    private static final String TIME_PATTERN = "uuuu-MM-dd_HH-mm-ss";
    private final Logger logger = LogManager.getRootLogger ();

    @Override
    public void onTestFailure(ITestResult tr) {
        saveScreenshot();
        logger.info("Test failed.");
    }

    @Override
    public void onConfigurationFailure(ITestResult tr) {
        saveScreenshot();
        logger.info("Configuration prepare failed.");
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton.getDriver())
                .getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenCapture, new File(String.format(FILE_PATH, getCurrentTimeAsString())));
            logger.error("Screenshot was saved.");
        }
        catch (IOException ioException){
            logger.error("Failed to save screenshot: " + ioException.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return ZonedDateTime.now().format(dateTimeFormatter);
    }
}
