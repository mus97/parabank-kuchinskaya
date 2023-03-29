@registerUser @cleanDatabase @closeBrowser
Feature: Bill pay via UI
  As a user I should be able to create a bill pay via UI

  Background:
    When I open Bill Pay Page

  Scenario Outline: I create bill pay with valid data
    When I create a bill pay to account "123" in amount of <amount>
    Then "Bill Payment Complete" message is displayed on Bill Pay Page
    Examples:
      | amount |
      | "100"  |
      | "1.00" |

  Scenario: I create bill pay with wrong data
    When I create a bill pay to account "Incorrect_account" in amount of "100"
    Then "Please enter a valid number." warning message is displayed on Bill Pay Page
