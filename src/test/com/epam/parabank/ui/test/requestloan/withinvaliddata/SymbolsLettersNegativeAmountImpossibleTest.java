package com.epam.parabank.ui.test.requestloan.withinvaliddata;

import com.epam.parabank.ui.pageobject.LoanProcessPage;
import com.epam.parabank.ui.test.requestloan.RequestLoanParentTest;

import org.testng.Assert;
import org.testng.annotations.*;

public class SymbolsLettersNegativeAmountImpossibleTest extends RequestLoanParentTest {

    private static final String ERROR_MESSAGE = "Please enter a valid amount";

    @Test(description = "EPMFARMATS-16607 | Positive answer to Request loan by Down Payment", dataProvider = "loan_data")
    public void downPaymentLessThanBalanceTest(String loanAmount, String downPayment) {
        LoanProcessPage loanProcessedPage = registerPage
                .openRequestLoanPage()
                .fillLoanAmountField(loanAmount)
                .fillDownPaymentField(downPayment)
                .clickApplyNowButton();

        Assert.assertEquals(loanProcessedPage.getErrorMessage(), ERROR_MESSAGE, "Error message not match with expected error message : ");
    }

    @DataProvider(name = "loan_data")
    private Object[][] getLoanData() {
        return new Object[][]{{"maximus","volutpat"},
                        {"@(**&%","0"},
                        {"-120.00","-50"},
                        {"0","-5"},
                        {"*(^&","::|~@#"}};
    }
}
