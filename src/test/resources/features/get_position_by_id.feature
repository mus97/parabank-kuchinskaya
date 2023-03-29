@closeBrowser
@cleanDatabase
@registerUser
Feature: Get position by id
  As a user I should be able to get a position by id through the API

  Background: I buy the position via API
    Given I registered as a user and got my customer id
    And I got the id of my account from which I want to buy a position
    When I buy a position using customerId, accountId, name = "vase", symbol = "s", shares = "50", pricePerShare = "25.0000"
    And I get position id

    Scenario: I get position by id
      When I send a request to get position by id
      Then I get a response where name = "vase", symbol = "s", shares = "50", pricePerShare = "25.0000"