@cleanDatabase
@closeBrowser
Feature: Open new account with correct credentials

  Scenario Outline: Open new account with registered user
    Given I am in the parabank app
    And My account has $<INIT> and minimal deposit to transfer into new account is $<DEPOSIT>
    Then I click open new account button
    Then I choose "<TYPE>" as new account type
    Then I choose existing account to transfer funds into the new account
    Then I click OPEN NEW ACCOUNT button
    Then Open Account detail page
    And I see my new account type is "<TYPE>" and <BALANCE>

    Examples:
    |INIT   |DEPOSIT   |BALANCE  | TYPE   |
    |500.00 |100.50    |100.50   |CHECKING|
    |200.00 |50.50     |50.50    |SAVINGS |