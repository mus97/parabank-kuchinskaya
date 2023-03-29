package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.model.*;
import com.epam.parabank.api.service.RestAccountService;
import com.epam.parabank.api.service.RestCustomerService;
import com.epam.parabank.api.service.RestPositionService;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import static com.epam.parabank.api.service.CreateModel.createJaxbModel;

public class PositionService {

    private final RestPositionService restPositionService;
    private final RestCustomerService restCustomerService;
    private final RestAccountService restAccountService;
    private final SoftAssert softAssert;
    private final User user;
    private Customer customer;
    private Response response;
    private Position position;

    public PositionService() {
        softAssert = new SoftAssert();
        user = new UserManager().getUser();
        restPositionService = new RestPositionService();
        restCustomerService = new RestCustomerService();
        restAccountService = new RestAccountService();
    }

    public String getCustomerId() {
        customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        return customer.getId();
    }

    public String getAccountId(String customerId) {
        response = restAccountService.getCustomerAccounts(customerId);
        return new XmlPath(response.getBody().asString()).getString("accounts.account.id");
    }

    public void buyPosition(String customerId, String accountId, String name, String symbol, String shares, String pricePerShare) {
        response = restPositionService.buyPosition(customerId, accountId, name, symbol, shares, pricePerShare);
        customer.setPositions(createJaxbModel(response.asString(), Positions.class));
        position = customer.getPositions().getPositionsList().get(0);
    }

    public void validateValues(String name, String symbol, String shares, String pricePerShare) {
        softAssert.assertEquals(position.getName(), name, "Name of Position doesn't match");
        softAssert.assertEquals(position.getSymbol(), symbol, "Symbol of Position doesn't match");
        softAssert.assertEquals(position.getShares(), shares, "Shares of Position doesn't match");
        softAssert.assertEquals(position.getPurchasePrice(), pricePerShare, "Price per share of Position doesn't match");
        softAssert.assertAll();
    }

    public void getPositionById(String positionId) {
        response = restPositionService.getPositionById(positionId);
        position = createJaxbModel(response.asString(), Position.class);
    }

    public String getPositionId() {
        return new XmlPath(response.getBody().asString()).getString("positions.position.positionId");
    }

    public void sellPosition(String customerId, String accountId, String positionId, String shares, String pricePerShare) {
        response = restPositionService.sellPosition(customerId, accountId, positionId, shares, pricePerShare);
        customer.setPositions(createJaxbModel(response.asString(), Positions.class));
        position = customer.getPositions().getPositionsList().get(0);
    }
}