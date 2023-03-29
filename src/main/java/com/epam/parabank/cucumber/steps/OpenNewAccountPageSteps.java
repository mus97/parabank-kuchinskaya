package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.OpenNewAccountService;

import com.epam.parabank.ui.pageobject.AccountDetailPage;
import com.epam.parabank.ui.pageobject.OpenNewAccountPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.apache.commons.lang3.StringUtils;

import org.testng.asserts.SoftAssert;

public class OpenNewAccountPageSteps {

    private final OpenNewAccountPage openNewAccountPage;
    private final OpenNewAccountService openNewAccountService;
    private final AccountDetailPage accountDetailPage;
    private final SoftAssert softAssert;

    public OpenNewAccountPageSteps() {
        openNewAccountService = new OpenNewAccountService();
        openNewAccountPage = new OpenNewAccountPage();
        accountDetailPage = new AccountDetailPage();
        softAssert = new SoftAssert();
    }

    @And("My account has ${float} and minimal deposit to transfer into new account is ${float}")
    public void openMyAccount(final float initialBalance, final float deposit) {
        openNewAccountService.setInitialAccountBalanceAndDeposit(initialBalance, deposit);
    }

    @Then("I click open new account button")
    public void openNewAccount() {
        openNewAccountPage.getAccountServicesComponent().clickOpenAccountBtn();
    }

    @Then("I choose {string} as new account type")
    public void chooseCheckingType(String accountType) {
        openNewAccountService.choseNewAccountType(accountType);
    }

    @Then("I choose existing account to transfer funds into the new account")
    public void chooseAccountToTransferDeposit() {
        openNewAccountPage.chooseFromAccount();
    }

    @Then("I click OPEN NEW ACCOUNT button")
    public void clickOpenNewAccount() {
        openNewAccountPage.clickOpenNewAccountButton();
        openNewAccountPage.getFirstAccountId();
    }

    @Then("Open Account detail page")
    public void openAccountDetailPage() {
        openNewAccountPage.getAccountDetailsPage();
    }

    @And("I see my new account type is {string} and {float}")
    public void checkAccountDetail(final String accountType, final float balance) {
        softAssert.assertTrue(accountDetailPage.isAccountDisplayed(accountType), "Account type not displayed : ");
        softAssert.assertEquals(
                Float.parseFloat(accountDetailPage.getBalance().replace("$",StringUtils.EMPTY)),
                balance,"Account balance doe's not match with expected : ");
        softAssert.assertAll();
    }
}
