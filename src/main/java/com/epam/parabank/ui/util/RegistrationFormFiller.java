package com.epam.parabank.ui.util;

import static java.lang.String.format;

import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.RegisterPage;

import lombok.extern.log4j.Log4j;

@Log4j
public class RegistrationFormFiller extends RegisterPage {

    public RegisterPage setUpUser(User user){
        log.info(format("Setup user %s", user.toString()));
        return new RegisterPage()
                .inputFirstName(user.getFirstName())
                .inputLastName(user.getLastName())
                .inputAddress(user.getAddress())
                .inputCity(user.getCity())
                .inputState(user.getState())
                .inputZipCode(user.getZipCode())
                .inputPhoneNumber(user.getPhoneNumber())
                .inputSsn(user.getSsn())
                .inputUsername(user.getUsername())
                .inputPassword(user.getPassword())
                .inputConfirm(user.getPassword())
                .clickRegisterButton();
    }

    public RegisterPage setUpInvalidUser(User user){
        log.info(format("Setup user %s", user.toString()));
        return new RegisterPage()
                .inputFirstName(user.getFirstName())
                .inputLastName(user.getLastName())
                .inputAddress(user.getAddress())
                .inputCity(user.getCity())
                .inputState(user.getState())
                .inputZipCode(user.getZipCode())
                .inputPhoneNumber(user.getPhoneNumber())
                .inputSsn(user.getSsn())
                .inputUsername(user.getUsername())
                .inputPassword(user.getPassword())
                .inputConfirm("invalid value")
                .clickRegisterButton();
    }
}