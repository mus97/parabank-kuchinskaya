@closeBrowser
@cleanDatabase
Feature: Payment
  As a user I should be able to bill paying

  Scenario Outline:
    Given my account has $<initial>
    When I paid $<paying>
    Then account balance is $<final>

    Examples:
    | initial | paying | final |
    |500      |100     |400    |
    |450      |150     |300    |
    |150      |150     |0      |

  Scenario:
    Given my account has $150
    When I paid $550
    Then I get a message "The account doe's not have enough money"

  Scenario:
    Given my account has $200
    When I enter "letters" in the Amount field
    Then I get a message "Please inter a valid data"