package com.epam.parabank.api.test.account;

import com.epam.parabank.api.model.Account;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.test.ParentApiTest;
import com.epam.parabank.ui.util.TextFileReader;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.EndpointFormatUtil.*;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_ACCOUNTS_ENDPOINT;
import static com.epam.parabank.ui.util.TextFileReader.FilePath.TRANSFER_FUNDS_MESSAGE;

public class TransferFundsApiTest extends ParentApiTest {

    private Response response;
    private String mainAccountId;
    private String toAccountId;
    private final String amount = "150.00";

    @BeforeClass
    public void preconditions() {
        response = makeRequestAndGetResponse(String.format(PARABANK_ACCOUNTS_ENDPOINT.getPropertyName(), customer.getId()));
        customer.setAccounts(createJaxbModel(response.asString(), Accounts.class));
        mainAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();

        response = createNewAccount(customer, CHOOSE_CHECKING_TYPE, mainAccountId);
        toAccountId = createJaxbModel(response.asString(), Account.class).getAccountId();
    }

    @Test
    public void transferFundsBetweenTwoAccounts() {
        response = transferFunds(mainAccountId, toAccountId, amount);

        String expectedTransferFundsMessage = String.format(
                TextFileReader.readTextFromFile(TRANSFER_FUNDS_MESSAGE.getFilePath()),
                amount,
                mainAccountId,
                toAccountId);

        softAssert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Response status code is wrong : ");
        softAssert.assertEquals(response.asString(), expectedTransferFundsMessage,
                "Message about successful transfer fund doesn't match");
        softAssert.assertAll();
    }
}