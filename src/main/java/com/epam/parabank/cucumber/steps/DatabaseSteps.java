package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.AdminPageService;
import com.epam.parabank.cucumber.service.DatabaseApiService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.epam.parabank.cucumber.service.DatabaseApiService.*;

public class DatabaseSteps {

    private final DatabaseApiService databaseApiService;
    private final AdminPageService adminPageService;

    public DatabaseSteps() {
        this.databaseApiService = getDatabaseApiService();
        this.adminPageService = new AdminPageService();
    }

    @When("^I send clean database api request$")
    public void databaseIsCleanedApi() {
        databaseApiService.sendCleanDatabaseRequest();
    }

    @When("^I clean database on Admin Page$")
    public void cleanDatabaseOnAdminPage() {
        adminPageService.cleanDatabase();
    }

    @And("{string} message is displayed on Admin Page")
    public void getDatabaseCleanedMessage(final String databaseText) {
        Assert.assertEquals(adminPageService.getDatabaseCleanedMessage(), databaseText, "Wrong text!");
    }
}
