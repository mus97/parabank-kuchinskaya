package com.epam.parabank.ui.pageobject;

import com.epam.parabank.ui.driver.DriverSingleton;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public abstract class Component {
    protected WebDriver driver;
    private final WebElement self;

    protected Component(By self) {
        this.driver = DriverSingleton.getDriver();
        this.self = driver.findElement(self);
    }

    public boolean isComponentDisplayed() {
        return getSelf().isDisplayed();
    }
}