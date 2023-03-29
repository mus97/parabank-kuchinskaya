@registerUser
@closeBrowser
@cleanDatabase
Feature: Update user information
  As a user I can update my contact info on the web site

  Scenario Outline: Update contact information
    Given I am on Update contact info page
    And "Update Profile" is presented
    When I input new values into "<firstName>", "<lastName>", "<Address>", "<City>", "<State>", "<ZipCode>", "<Phone>" fields
    And I click Update profile button
    Then Message "Your updated address and phone number have been added to the system." is presented

    Examples:
      | firstName | lastName | Address  | City     | State   | ZipCode  | Phone     |
      | Bruno     | Falkone  | 303 Was  | Shinjuku | Japan   | 162-0041 | 955855431 |
      | Jolyne    | Freecss  | 6 Erekle | Tbilisi  | Georgia | 332-4004 | 332467952 |