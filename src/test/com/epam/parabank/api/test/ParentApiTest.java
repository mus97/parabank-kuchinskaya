package com.epam.parabank.api.test;

import com.epam.parabank.api.model.Customer;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.driver.DriverSingleton;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import static com.epam.parabank.api.utils.CleanDataBase.cleanDataBase;
import static com.epam.parabank.api.utils.LogInUtil.logInWithUser;
import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;

public class ParentApiTest {

    protected User user;
    protected Customer customer;
    protected SoftAssert softAssert;

    protected static final String ID_REGEX = "\\d{5}";
    protected static final String BALANCE_REGEX = "\\d+\\.\\d{2}";
    protected static final String LOAN_PROVIDER_NAME = "Wealth Securities Dynamic Loans (WSDL)";
    protected static final String SAVINGS_TYPE = "SAVINGS";
    protected static final String CHECKING_TYPE = "CHECKING";
    protected static final String LOAN_TYPE = "LOAN";
    protected static final String CHOOSE_CHECKING_TYPE = "0";
    protected static final String CHOOSE_SAVINGS_TYPE = "1";
    protected static final String CHOOSE_LOAN_TYPE = "2";

    @BeforeSuite
    public void setUpUser() {
        user = new UserManager().getUser();
        cleanDatabaseAndRegister(user);
        DriverSingleton.closeDriver();
    }

    @BeforeClass(alwaysRun = true)
    public void setCustomer() {
        user = new UserManager().getUser();
        customer = logInWithUser(user.getUsername(), user.getPassword());
    }

    @BeforeMethod
    public void setSoftAssert() {
        softAssert = new SoftAssert();
    }

    @AfterSuite(alwaysRun = true)
    public void cleanDB() {
        cleanDataBase();
    }
}
