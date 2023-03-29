package com.epam.parabank.ui.util;

import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.RegisterPage;

public class CleanDatabaseAndRegisterStep {

    public static RegisterPage cleanDatabaseAndRegister(User user){
        return new MainPage().openAdminPage().cleanDatabase().getLoginComponent()
                .openRegisterPage().fillRegistrationForm().setUpUser(user);
    }
}
