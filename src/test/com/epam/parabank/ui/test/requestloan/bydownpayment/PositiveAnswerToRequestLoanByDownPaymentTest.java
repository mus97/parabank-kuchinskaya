package com.epam.parabank.ui.test.requestloan.bydownpayment;

import com.epam.parabank.ui.pageobject.AccountDetailPage;
import com.epam.parabank.ui.pageobject.LoanProcessPage;
import com.epam.parabank.ui.test.requestloan.RequestLoanParentTest;

import org.testng.annotations.*;

public class PositiveAnswerToRequestLoanByDownPaymentTest extends RequestLoanParentTest {

    private static final String LOAN_STATUS_TEXT = "Approved";
    private static final String LOAN_RESPONSE_TEXT = "Congratulations, your loan has been approved.";
    private static final String LOAN_TYPE = "LOAN";
    private static final String BALANCE_AMOUNT_PATTERN = "$%s";

    @Test(description = "EPMFARMATS-16607 | Positive answer to Request loan by Down Payment", dataProvider = "loan_data")
    public void downPaymentLessThanBalanceTest(String loanAmount, String downPayment) {
        String id;

        LoanProcessPage loanProcessedPage = registerPage
                .openRequestLoanPage()
                .fillLoanAmountField(loanAmount)
                .fillDownPaymentField(downPayment)
                .clickApplyNowButton();

        softAssert.assertEquals(loanProcessedPage.getStatusText(), LOAN_STATUS_TEXT, "Loan status text not the same with expected :");
        softAssert.assertEquals(loanProcessedPage.getResponseText(),LOAN_RESPONSE_TEXT, "Loan response text not match with expected : ");
        softAssert.assertTrue(loanProcessedPage.isNewAccountIdDisplayed(),"Account ID is not displayed : ");

        id = loanProcessedPage.saveNewAccountId();

        AccountDetailPage accountDetails  = loanProcessedPage
                .openAccountDetails();

        softAssert.assertTrue(accountDetails.isAccountIdDisplayed(id), "Account ID is not displayed : ");
        softAssert.assertEquals(accountDetails.getBalance(),String.format(BALANCE_AMOUNT_PATTERN, loanAmount),"Account balance does not match with requested loan amount : ");
        softAssert.assertEquals(accountDetails.getType(), LOAN_TYPE, "Loan type does not match with expected : ");
        softAssert.assertAll();
    }

    @DataProvider(name = "loan_data")
    private Object[][] getLoanData() {
        return new Object[][]
                {{"123.00","50.50"},
                {"500.10","170.00"},
                {"120.00","17.10"},
                {"50.20","20.50"},
                {"40.15","10.25"}};
    }
}
