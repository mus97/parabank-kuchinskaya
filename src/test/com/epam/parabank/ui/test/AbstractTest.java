package com.epam.parabank.ui.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.listener.TestListener;

import lombok.extern.slf4j.Slf4j;

@Listeners({TestListener.class})
@Slf4j
public abstract class AbstractTest {

    protected SoftAssert softAssert;
    protected User user;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        log.info("[Class] : Start Tests in: " + getClass().getSimpleName());
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("[Class] : End of Tests in: " + getClass().getSimpleName() + "\n");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodName(Method method) {
        log.info("===> START [Test: " + method.getName() + "]");
    }

    @AfterMethod(alwaysRun = true)
    public void logTestResult(ITestResult result) {
        String testResult = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "Passed";
            case ITestResult.FAILURE -> "Failed";
            case ITestResult.SKIP -> "Skipped";
            default -> Integer.toString(result.getStatus());
        };
        log.info("<===  END  [Test: " + result.getMethod().getMethodName() + "]: Result = " + testResult);
    }
}