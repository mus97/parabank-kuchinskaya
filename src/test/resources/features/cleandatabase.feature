@registerUser
@closeBrowser
Feature: Clean Database
  As a user I should able to clean database via UI and API

  Scenario: I clean database via API
    When I send clean database api request
    And "Clean database" 204 response code is received
    When I login via API
    Then "Login" 400 response code is received

  Scenario: I clean database via UI
    Given Browser is closed after registration
    When I open Login Page
    And I open Admin Page
    When I clean database on Admin Page
    And "Database Cleaned" message is displayed on Admin Page
    And I open Login Page
    When I login on Login Page
    Then Access is denied. "Error" text is displayed