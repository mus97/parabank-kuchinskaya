package com.epam.parabank.cucumber.service;

import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import com.epam.parabank.ui.pageobject.BillPayPage;
import com.epam.parabank.ui.pageobject.MainPage;
import org.testng.Assert;

public class BillPayUiService {

    private final User user = new UserManager().getUser();
    private BillPayPage billPay;

    public void openBillpayPage() {
        billPay = new MainPage().getAccountServicesComponent().openBillPayPage();
    }

    public void sendBillpay(String account, String amount) {
        billPay.sendPayment(user, account, amount);
    }

    public void verifyResultMessage(String message) {
        Assert.assertTrue(billPay.getResultMessage().contains(message), "Wrong message");
    }

    public void verifyErrorMessage(String message) {
        Assert.assertTrue(billPay.getWarningMessage().contains(message), "Wrong message");
    }

}

