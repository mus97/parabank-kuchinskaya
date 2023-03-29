@closeBrowser
@cleanDatabase
Feature: Create transaction
  As a user I should be able to create transactions

  Scenario Outline: Create transactions by amount for account
    Given I am in the parabank app
    And My account has $<initial> and minimal deposit to transfer into new account is $<deposit>
    And I create new account "<type>"
    When I transfer $<amount> from my first account to second
    Then account balance is $<balance>

    Examples:
      | initial | deposit | amount | balance  | type     |
      | 500     | 100     | 50     | 350      | SAVINGS  |
      | 150     | 40      | 20     | 90       | CHECKING |