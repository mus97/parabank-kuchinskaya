@closeBrowser
@cleanDatabase
@registerUser
Feature: Find transaction
  As a user I should be able to find transactions by different parameters

  Scenario Outline: Find transaction by date
    Given I am in the parabank app
    And I have already existed transaction with <amount>
    When I open Find transaction page
    And I insert date of transaction
    Then I see transaction <amount>
    And I see transaction date

    Examples:
      |amount|
      |50.00 |
      |14.00 |

  Scenario Outline: Find transaction by amount
    Given I am in the parabank app
    And I have already existed transaction with <amount>
    When I open Find transaction page
    And I insert <amount> of transaction
    Then I see transaction <amount>
    And I see transaction date

    Examples:
      |amount|
      |60.00 |
      |20.00 |