package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.pageobject.AdminPage;

public class AdminPageService {

    private final AdminPage adminPage;

    public AdminPageService() {
        adminPage = new AdminPage();
    }

    public void cleanDatabase() {
        adminPage.cleanDatabase();
    }

    public String getDatabaseCleanedMessage() {
        return adminPage.getDatabaseCleanedMessage();
    }
}
