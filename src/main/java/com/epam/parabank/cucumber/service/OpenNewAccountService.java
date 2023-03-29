package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.OpenNewAccountPage;

public class OpenNewAccountService {

    User user;
    private final OpenNewAccountPage openNewAccountPage;

    public OpenNewAccountService() {
        user = new UserManager().getUser();
        openNewAccountPage = new OpenNewAccountPage();
    }

    public void setInitialAccountBalanceAndDeposit(float balance,float deposit) {
        new MainPage()
                .openAdminPage()
                .cleanEndpoints()
                .setupInitialBalanceAndDeposit(balance, deposit)
                .openMainPage()
                .openRegisterPage()
                .fillRegistrationForm()
                .setUpUser(user);
    }

    public void choseNewAccountType(String accountType) {
        switch (accountType) {
            case "SAVINGS" -> openNewAccountPage.choseSavingType();
            case "CHECKING" -> openNewAccountPage.choseCheckingType();
        }
    }
}
