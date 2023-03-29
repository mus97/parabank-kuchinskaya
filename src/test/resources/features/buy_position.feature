@closeBrowser
@cleanDatabase
@registerUser
Feature: Buy position
  As a user I should be able to buy a position through the API

  Scenario: I buy the position via API
    Given I registered as a user and got my customer id
    And I got the id of my account from which I want to buy a position
    When I buy a position using customerId, accountId, name = "vase", symbol = "s", shares = "50", pricePerShare = "25.0000"
    Then I get a response where name = "vase", symbol = "s", shares = "50", pricePerShare = "25.0000"