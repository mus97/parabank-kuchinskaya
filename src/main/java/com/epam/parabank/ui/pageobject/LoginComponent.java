package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.By;

import com.epam.parabank.ui.businessobject.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginComponent extends Component {
    private final By loginBtnLocator = By.cssSelector("div.login input.button");
    private final By userNameFieldLocator = By.cssSelector("input[name=username]");
    private final By passwordFieldLocator = By.cssSelector("input[name=password]");
    private final By registerLinkLocator = By.cssSelector("a[href*=register]");
    private final By customerLoginTitleLocator = By.cssSelector("div > h2");

    public LoginComponent(By self) {
        super(self);
    }

    public LoginComponent() {
        this(By.cssSelector("div#loginPanel"));
    }

    public boolean isUserNameFieldDisplayed() {
        return getSelf().findElement(userNameFieldLocator).isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return getSelf().findElement(passwordFieldLocator).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return getSelf().findElement(loginBtnLocator).isDisplayed();
    }

    public AccountOverviewPage loginDefaultUser(User user) {
        log.info("Entered Username: '" + user.getUsername() + "' and password: " + user.getPassword());
        getSelf().findElement(userNameFieldLocator).sendKeys(user.getUsername());
        getSelf().findElement(passwordFieldLocator).sendKeys(user.getPassword());
        getSelf().findElement(loginBtnLocator).click();
        return new AccountOverviewPage();
    }

    public LoginPage openLoginPageViaLoginButton() {
        log.info("Open Login page via LOG IN button.");
        getSelf().findElement(loginBtnLocator).click();
        return new LoginPage();
    }

    public AccountOverviewPage openAccountOverviewPageViaLoginButton() {
        log.info("Open Account Overview page via LOG IN button.");
        getSelf().findElement(loginBtnLocator).click();
        return new AccountOverviewPage();
    }

    public void enterUsername(String name) {
        log.info("Username field is filled in.");
        getSelf().findElement(userNameFieldLocator).sendKeys(name);
    }

    public void enterPassword(String password) {
        log.info("Password field is filled in.");
        getSelf().findElement(passwordFieldLocator).sendKeys(password);
    }

    public String getInputPasswordText() {
        return getSelf().findElement(passwordFieldLocator).getAttribute("value");
    }

    public String getInputUsernameField() {
        return getSelf().findElement(userNameFieldLocator).getAttribute("value");
    }

    public LoginPage loginManually(String name, String password) {
        log.info("Entered Username: '" + name + "' and password: " + password);
        getSelf().findElement(userNameFieldLocator).sendKeys(name);
        getSelf().findElement(passwordFieldLocator).sendKeys(password);
        getSelf().findElement(loginBtnLocator).click();
        return new LoginPage();
    }

    public RegisterPage openRegisterPage() {
        getSelf().findElement(registerLinkLocator).click();
        return new RegisterPage();
    }

    public String getCustomerLoginTitleText() {
        return (driver.findElement(customerLoginTitleLocator)).getText();
    }
}
