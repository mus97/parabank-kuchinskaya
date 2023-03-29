package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.By;

public class AccountServicesComponent extends Component {

    private final By openAccountOverviewPageLocator = By.cssSelector("a[href*='overview']");
    private final By openAccountBtnLocator = By.cssSelector("a[href*='openaccount']");
    private final By logOutBtnLocator = By.cssSelector("a[href*=logout]");
    private final By openBillPayPage = By.cssSelector("a[href*=billpay]");
    private final By adminLink = By.xpath("//div[@id='headerPanel']//a[contains (text(),'Admin Page')]");
    private final By openFindTransactionPage = By.cssSelector("a[href*='findtrans']");

    public AccountServicesComponent() {
        this(By.cssSelector("#leftPanel"));
    }

    public AccountServicesComponent(By self) {
        super(self);
    }

    public MainPage openMainPageViaLogOutBtn() {
        getSelf().findElement(logOutBtnLocator).click();
        return new MainPage();
    }

    public OpenNewAccountPage clickOpenAccountBtn() {
        getSelf().findElement(openAccountBtnLocator).click();
        return new OpenNewAccountPage();
    }

    public AccountOverviewPage openAccountOverviewPage() {
        getSelf().findElement(openAccountOverviewPageLocator).click();
        return new AccountOverviewPage();
    }

    public BillPayPage openBillPayPage() {

        getSelf().findElement(openBillPayPage).click();
        return new BillPayPage();
    }

    public AdminPage openAdminPage() {
        getSelf().findElement(adminLink).click();
        return new AdminPage();
    }

    public FindTransactionsPage openFindTransactionPage() {
        getSelf().findElement(openFindTransactionPage).click();
        return new FindTransactionsPage();
    }
}
