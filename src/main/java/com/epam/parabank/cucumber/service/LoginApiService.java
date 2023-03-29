package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.service.RestCustomerService;
import com.epam.parabank.api.service.RestLoginService;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import io.restassured.response.Response;
import lombok.Getter;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginApiService implements ResponseValidator {

    private static LoginApiService loginApiService;
    private final RestLoginService restLoginService;
    private final RestCustomerService restCustomerService;
    private final SoftAssert softAssert;
    private final User user;
    @Getter
    private Response loginResponse;

    private LoginApiService() {
        restLoginService = new RestLoginService();
        restCustomerService = new RestCustomerService();
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
    }

    public static LoginApiService getLoginApiService() {
        if (loginApiService == null) {
            loginApiService = new LoginApiService();
        }
        return loginApiService;
    }

    public void loginUser() {
        loginResponse = restLoginService.login(user.getUsername(), user.getPassword());
    }

    @Override
    public void verifyResponse(final int responseCode) {
        Assert.assertEquals(loginResponse.getStatusCode(), responseCode, "Wrong code!");
    }

    public void verifyCustomerCredential() {
        Customer customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        softAssert.assertEquals(customer.getFirstName(), user.getFirstName(), "First name doesn't match.");
        softAssert.assertEquals(customer.getLastName(), user.getLastName(), "Last name doesn't match.");
        softAssert.assertAll();
    }
}
