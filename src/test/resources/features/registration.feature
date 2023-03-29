@closeBrowser
@cleanDatabase
Feature: Registration
  As a user I should able to register on parabank web site

  Scenario: I register with valid credential
    When I open Register Page
    And I register new user
    Then "Your account was created successfully. You are now logged in." text is displayed