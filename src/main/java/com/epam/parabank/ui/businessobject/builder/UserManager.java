package com.epam.parabank.ui.businessobject.builder;

import static java.lang.String.format;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.util.PropertyReader;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Log4j
public class UserManager {

    public User getUser() {
        try (Reader reader = new FileReader(PropertyReader.getInstance()
                                                          .getProperty(PropertyReader.ProjectPropertyName.USER_PATH.getPropertyName()))) {
            return new Gson().fromJson(reader, User.class);
        } catch (IOException e) {
            log.warn(format("Exception in user builder %s", e.getMessage()));
        }
        return null;
    }

    public String serializeUser(User user) {
        return new Gson().toJson(user);
    }

    public User deserializeUser(String userJsonText) {
        return new Gson().fromJson(userJsonText, User.class);
    }
}