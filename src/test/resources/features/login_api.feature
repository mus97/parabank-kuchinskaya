@registerUser
@cleanDatabase
@closeBrowser
Feature: Login
  As a user I should able to login on parabank via API

  Scenario: I login on parabank via API
    When I login via API
    And "Login" 200 response code is received
    Then Logged in API credentials are correct


