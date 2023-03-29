package com.epam.parabank.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = initDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    private static WebDriver initDriver() {
        WebDriver webDriver;
        switch (Driver.getByDriverType(System.getProperty("browser"))) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            }
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }
}