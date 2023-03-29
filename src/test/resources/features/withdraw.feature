@registerUser @cleanDatabase @closeBrowser
Feature: Withdraw
  As a user I should be able to withdraw funds

  Scenario Outline: I withdraw correct amount
  I can withdraw positive or negative amount of int or double type

    When I withdraw $<amount>
    And Withdraw 200 response code is received
    Then "Successfully withdrew" message is in response
    Examples:
      | amount   |
      | "42.00"  |
      | "42"     |
      | "-42"    |
      | "-42.00" |

  Scenario: I withdraw incorrect amount
    When I withdraw $"Incorrect_amount"
    Then Withdraw 404 response code is received

  Scenario: I withdraw empty amount
    When I withdraw $""
    Then Withdraw 500 response code is received