package com.epam.parabank.api.test.cleandatabase;

import com.epam.parabank.api.test.ParentApiTest;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.epam.parabank.api.utils.CleanDataBase.cleanDataBase;
import static com.epam.parabank.ui.util.CleanDatabaseAndRegisterStep.cleanDatabaseAndRegister;

public class CleanDataBaseTest extends ParentApiTest {

    @Test(description = "EPMFARMATS-16749 | Clean data base")
    public void cleanDataBaseTest() {
        Response response = cleanDataBase();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT,"Response status code is wrong : ");
    }

    @AfterClass(alwaysRun = true)
    public void restoreDataBase() {
        user = new UserManager().getUser();
        cleanDatabaseAndRegister(user);
        DriverSingleton.closeDriver();
    }
}
