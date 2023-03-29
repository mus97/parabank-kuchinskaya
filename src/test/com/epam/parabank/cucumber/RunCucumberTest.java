package com.epam.parabank.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty",
                "json:build/cucumber-reports/cucumber.json",
                "json:target/cucumber-json-report/cucumber.json"},
        features = "src/test/resources/features",
        glue = "com.epam.parabank.cucumber.steps")
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider()
    public Object[][] scenarios() {
        return super.scenarios();
    }
}