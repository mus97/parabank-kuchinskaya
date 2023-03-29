package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.DatabaseApiService;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.epam.parabank.cucumber.service.DatabaseApiService.getDatabaseApiService;

public class Hooks {

    private final DatabaseApiService databaseApiService;
    private final User user;

    public Hooks() {
        databaseApiService = getDatabaseApiService();
        user = new UserManager().getUser();
    }

    @Before("@registerUser")
    public void registerUser() {
        new MainPage().openRegisterPage().fillRegistrationForm().setUpUser(user);
    }

    @After("@cleanDatabase")
    public void cleanDatabase() {
        databaseApiService.sendCleanDatabaseRequest();
    }

    @After("@closeBrowser")
    public void closeDriver() {
        DriverSingleton.closeDriver();
    }
}
