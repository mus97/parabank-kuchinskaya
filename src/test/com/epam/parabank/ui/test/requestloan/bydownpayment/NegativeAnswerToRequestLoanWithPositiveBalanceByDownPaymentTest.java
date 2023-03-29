package com.epam.parabank.ui.test.requestloan.bydownpayment;

import com.epam.parabank.ui.pageobject.LoanProcessPage;
import com.epam.parabank.ui.test.requestloan.RequestLoanParentTest;

import org.testng.annotations.*;

public class NegativeAnswerToRequestLoanWithPositiveBalanceByDownPaymentTest extends RequestLoanParentTest {

    private static final String LOAN_STATUS_TEXT = "Denied";
    private static final String LOAN_RESPONSE_TEXT = "We cannot grant a loan in that amount with the given down payment.";

    @Test(description = "EPMFARMATS-16608 | Negative answer to Request loan with positive balance by Down Payment", dataProvider = "loan_data")
    public void downPaymentLessThanBalanceTest(String loanAmount, String downPayment) {
        LoanProcessPage loanProcessedPage = registerPage
                .openRequestLoanPage()
                .fillLoanAmountField(loanAmount)
                .fillDownPaymentField(downPayment)
                .clickApplyNowButton();

        softAssert.assertEquals(loanProcessedPage.getStatusText(), LOAN_STATUS_TEXT,"Loan status text not the same with expected :");
        softAssert.assertEquals(loanProcessedPage.getResponseText(),LOAN_RESPONSE_TEXT, "Loan response text not match with expected : ");
        softAssert.assertAll();
    }

    @DataProvider(name = "loan_data")
    private Object[][] getLoanData() {
        return new Object[][]
                {{"100500","198"},
                {"10000","100"},
                {"15000","1"},
                {"12000","480"},
                {"40000","150"}};
    }
}
