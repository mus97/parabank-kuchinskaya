package com.epam.parabank.ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.log4j.Log4j;

@Log4j
public class RequestLoanPage extends AbstractPage {

    @FindBy(css = "#amount")
    private WebElement loanAmountField;
    @FindBy(css = "#downPayment")
    private WebElement downPaymentField;
    @FindBy(css = "input[value='Apply Now']")
    private WebElement applyNowButton;

    @Override
    protected void waitForPageLoad() {
        throw new UnsupportedOperationException();
    }

    public RequestLoanPage fillLoanAmountField(String amount) {
        log.info("Insert requested amount into amount field");
        loanAmountField.sendKeys(amount);
        return this;
    }

    public RequestLoanPage fillDownPaymentField(String amount) {
        log.info("Insert Down payment amount into amount field");
        downPaymentField.sendKeys(amount);
        return this;
    }

    public LoanProcessPage clickApplyNowButton() {
        log.info("Open Loan processed page");
        applyNowButton.click();
        return new LoanProcessPage();
    }
}
