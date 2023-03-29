package com.epam.parabank.api.test.initializedb;

import com.epam.parabank.api.test.ParentApiTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.epam.parabank.api.utils.CleanDataBase.initializeDatabase;
import static org.testng.Assert.assertEquals;

public class InitializeDatabaseTest extends ParentApiTest {

    @Test(description = "EPMFARMATS-16720 | Implement 5 API TC for Uladzimir | Initialize Database")
    public void initializeDataBaseTest() {
        Response initializeDatabaseResponse = initializeDatabase();
        assertEquals(initializeDatabaseResponse.getStatusCode(),
                HttpStatus.SC_NO_CONTENT,
                "Initialize database api response code is invalid");
    }
}
