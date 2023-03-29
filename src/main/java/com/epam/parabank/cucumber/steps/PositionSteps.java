package com.epam.parabank.cucumber.steps;

import com.epam.parabank.cucumber.service.PositionService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PositionSteps {

    private final PositionService positionService;
    private String customerId;
    private String accountId;
    private String positionId;

    public PositionSteps(){
        positionService = new PositionService();
    }

    @Given("I registered as a user and got my customer id")
    public void getCustomerId() {
        customerId = positionService.getCustomerId();
    }

    @Given("I got the id of my account from which I want to buy a position")
    public void getAccountId() {
        accountId = positionService.getAccountId(customerId);
    }

    @When("I buy a position using customerId, accountId, name = {string}, symbol = {string}, shares = {string}, pricePerShare = {string}")
    public void buyPosition(final String name, final String symbol, final String shares, final String pricePerShare) {
        positionService.buyPosition(customerId, accountId, name, symbol, shares, pricePerShare);
    }

    @When("I send a request to get position by id")
    public void getPositionById() {
        positionService.getPositionById(positionService.getPositionId());
    }

    @When("I get position id")
    public void getPositionId() {
        positionId = positionService.getPositionId();
    }

    @When("I send a request to sell position using position id, shares = {string}, pricePerShare = {string}")
    public void sellPosition(final String shares, final String pricePerShare) {
        positionService.sellPosition(customerId, accountId, positionId, shares, pricePerShare);
    }

    @Then("I get a response where name = {string}, symbol = {string}, shares = {string}, pricePerShare = {string}")
    public void validateResponseValues(final String name, final String symbol, final String shares, final String pricePerShare) {
        positionService.validateValues(name, symbol, shares, pricePerShare);
    }
}