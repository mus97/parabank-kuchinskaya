package com.epam.parabank.ui.test.findtransaction;

import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.test.AbstractTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.format.DateTimeFormatter;

public class FindTransactionParentTest extends AbstractTest {

    static final String DATE_PATTERN = "MM-dd-yyyy";
    static final String EXPECTED_AMOUNT_BASE = "$%s";

    DateTimeFormatter dateTimeFormatter;

    @BeforeMethod(alwaysRun = true)
    public void setSoftAssert() {
        softAssert =  new SoftAssert();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}