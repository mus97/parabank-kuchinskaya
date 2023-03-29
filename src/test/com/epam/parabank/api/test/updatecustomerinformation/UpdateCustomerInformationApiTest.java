package com.epam.parabank.api.test.updatecustomerinformation;

import com.epam.parabank.api.test.ParentApiTest;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.parabank.api.utils.GetRequestUtil.makePostRequest;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_CUSTOMER_INFORMATION_ENDPOINT;

public class UpdateCustomerInformationApiTest extends ParentApiTest {

    private static final String NEW_FIRSTNAME = "Natalie";
    private static final String NEW_LASTNAME = "Smith";
    private static final String NEW_ID = "15567";
    private static final String NEW_USERNAME = "Ponnappa";
    private static final String NEW_PASSWORD = "newPassword";

    @Test(description = "EPMFARMATS-16737 | Update a customer's information by changing the FirstName and the LastName")
    public void updateCustomerInformationByChangingFirstNameAndLastNameTest() {
        Response response = makePostRequest(createCustomerInformationEndpoint(customer.getId(), NEW_FIRSTNAME,NEW_LASTNAME, user.getUsername(), user.getPassword()));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");
    }

    @Test(description = "EPMFARMATS-16738 | Update user information by changing the UserID")
    public void updateUserInformationByChangingTheUserIdTest() {
        Response response = makePostRequest(createCustomerInformationEndpoint(NEW_ID, customer.getFirstName(), customer.getLastName(), user.getUsername(), user.getPassword()));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN, "Response status code is wrong : ");
    }

    @Test(description = "EPMFARMATS-16739 | Update user information by changing the UserName and Password")
    public void updateUserInformationByChangingTheUserNameAndPasswordTest() {
        Response response = makePostRequest(createCustomerInformationEndpoint(customer.getId(), customer.getFirstName(), customer.getLastName(), NEW_USERNAME, NEW_PASSWORD));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Response status code is wrong : ");
    }

    public String createCustomerInformationEndpoint(String id, String firstName, String lastName, String userName, String password) {
        return new StringBuilder(PARABANK_CUSTOMER_INFORMATION_ENDPOINT.getPropertyName())
                .append(id)
                .append("?firstName=")
                .append(firstName)
                .append("&lastName=")
                .append(lastName)
                .append("&street=")
                .append(customer.getAddress().getStreet())
                .append("&city=")
                .append(customer.getAddress().getCity())
                .append("&state=")
                .append(customer.getAddress().getState())
                .append("&zipCode=")
                .append(customer.getAddress().getZipCode())
                .append("&phoneNumber=")
                .append(customer.getPhoneNumber())
                .append("&ssn=")
                .append(customer.getSsn())
                .append("&username=")
                .append(userName)
                .append("&password=")
                .append(password)
                .toString();
    }
}
