package com.epam.parabank.ui.test.register;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.driver.DriverSingleton;
import com.epam.parabank.ui.pageobject.MainPage;
import com.epam.parabank.ui.pageobject.RegisterPage;
import com.epam.parabank.ui.test.AbstractTest;
import com.epam.parabank.ui.util.RegistrationFormFiller;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class RegisterPageTest extends AbstractTest {

    @BeforeMethod(alwaysRun = true)
    public void setSoftAssert() {
        softAssert =  new SoftAssert();
        user = new UserManager().getUser();
    }

    @Test(description = "EPMFARMATS-16613 | Checking the registration form for processable data types")
    public void registerNewUser(){
        RegisterPage registerPage = new MainPage()
                .openRegisterPage();
        new RegistrationFormFiller()
                .setUpUser(user);

        String expectedMessage = "Your account was created successfully. You are now logged in.";
        Assert.assertEquals(registerPage.getRegistrationMessage(), expectedMessage, "the message after " +
                "registration does not match, or is not found");
    }

    @Test(description = "EPMFARMATS-16614 | Check the comparison of 'Password' and 'Confirm' fields")
    public void checkComparisonOfPasswordAndConfirmFields(){
        RegisterPage registerPage = new MainPage()
                .openRegisterPage();
        new RegistrationFormFiller()
                .setUpInvalidUser(user);

        String expectedMessage = "Passwords did not match.";
        Assert.assertEquals(registerPage.getInvalidConfirmFieldMessage(),expectedMessage, "the message about " +
                "invalid confirm field does not match, or is not found");
    }

    @Test(description = "EPMFARMATS-16615 | Checking the necessity of entering values in all field of the form")
    public void checkNecessityOfEnteringValuesInAllFields(){
        RegisterPage registerPage = new MainPage()
                .openRegisterPage()
                .inputFirstName(user.getFirstName())
                .inputLastName(user.getLastName())
                .inputZipCode(user.getZipCode())
                .inputUsername(user.getUsername())
                .inputPassword(user.getPassword())
                .inputConfirm(user.getPassword())
                .clickRegisterButton();

        String addressExpectedMessage = "Address is required.";
        String cityExpectedMessage = "City is required.";
        String ssnExpectedMessage = "Social Security Number is required.";

        softAssert.assertEquals(registerPage.getEmptyAddressFieldMessage(),addressExpectedMessage);
        softAssert.assertEquals(registerPage.getEmptyCityFieldMessage(),cityExpectedMessage);
        softAssert.assertEquals(registerPage.getEmptySsnFieldMessage(),ssnExpectedMessage);
        softAssert.assertAll("the message about empty fields does not match, or is not found");
    }

    @AfterMethod(alwaysRun = true)
    public void dbClean(){
        new MainPage()
                .openAdminPage()
                .cleanDatabase();
    }

    @AfterClass(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}