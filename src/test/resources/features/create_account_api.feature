@registerUser
@closeBrowser
@cleanDatabase
Feature: Create account
  As a user I should be able to create new account via API

  Scenario Outline: I create new account via API
    When I create new "<account type>" account via API
    And "Create account" 200 response code is received
    Then New account API credentials are correct

    Examples:
      | account type |
      | CHECKING     |
      | SAVINGS      |
      | LOAN         |