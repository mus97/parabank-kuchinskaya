package com.epam.parabank.api.test.login;

import com.epam.parabank.api.test.ParentApiTest;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateEndPoint.createComplexEndpoint;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_LOGIN_ENDPOINT;

public class LoginApiTest extends ParentApiTest {
    private static final String INCORRECT_USER_NAME = "Incorrect_name";
    private static final String INCORRECT_PASSWORD = "Incorrect_password";
    private static final String invalidLoginMessage = "Invalid username and/or password";
    Response response;

    @AfterClass(alwaysRun = true)
    public void restoreDataBase() {
        user = new UserManager().getUser();
        cleanDatabaseAndRegister(user);
        DriverSingleton.closeDriver();
    }

    @Test(description = "EPMFARMATS-16756 | Logging in with correct credentials API")
    public void testLoginWithCorrectDataApi() {
        response = makeRequestAndGetResponse(createComplexEndpoint(PARABANK_LOGIN_ENDPOINT.getPropertyName(),
                user.getUsername(), user.getPassword()));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match.");

        softAssert.assertEquals(customer.getFirstName(), user.getFirstName(), "First name doesn't match.");
        softAssert.assertEquals(customer.getLastName(), user.getLastName(), "Last name doesn't match.");
        softAssert.assertAll();
    }

    @Test(description = "EPMFARMATS-16757 | Logging in with incorrect credentials API")
    public void testLoginWithIncorrectDataApi() {
        cleanDB();
        response = makeRequestAndGetResponse(createComplexEndpoint(PARABANK_LOGIN_ENDPOINT.getPropertyName(),
                INCORRECT_USER_NAME, INCORRECT_PASSWORD));

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, "Status code doesn't match.");
        Assert.assertEquals(response.asString(), invalidLoginMessage, "Error message is absent.");
    }

    @Test(description = "EPMFARMATS-16758 | Logging in with empty request API")
    public void testLoginWithEmptyDataApi() {
        cleanDB();
        response = makeRequestAndGetResponse(PARABANK_LOGIN_ENDPOINT.getPropertyName());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code doesn't match.");
        Assert.assertTrue(response.asString().isEmpty(), "Response is not empty.");
    }
}
