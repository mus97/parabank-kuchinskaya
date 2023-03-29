package com.epam.parabank.ui.test.requestloan;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.RegisterPage;
import com.epam.parabank.ui.test.AbstractTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class RequestLoanParentTest extends AbstractTest {

    protected static final String INITIAL_BALANCE = "500";
    protected static final String MINIMAL_BALANCE = "100";
    protected static final String LOAN_PROVIDER = "ws";
    protected static final String LOAN_PROCESSOR = "down";
    protected static final String THRESHOLD = "5";

    protected RegisterPage registerPage;

    @BeforeClass(alwaysRun = true)
    public void setTestEnvironment() {
        user = new UserManager().getUser();

        registerPage = new MainPage()
                .openRegisterPage()
                .fillRegistrationForm()
                .setUpUser(user)
                .getAccountServicesComponent()
                .openAdminPage()
                .cleanEndpoints()
                .setupApplication(INITIAL_BALANCE,MINIMAL_BALANCE,LOAN_PROVIDER,LOAN_PROCESSOR,THRESHOLD)
                .openRegisterPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void setSoftAssert() {
        softAssert =  new SoftAssert();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        new MainPage()
                .openAdminPage()
                .cleanDatabase();
        DriverSingleton.closeDriver();
    }
}
