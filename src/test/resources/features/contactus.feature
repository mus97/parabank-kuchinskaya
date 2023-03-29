@closeBrowser
Feature: Contact Us
  As a guest I should be able to send message to Customer Care

  Scenario: I send message to Customer Care
    Given I open Contact Us Page
    When I fill out the contact details
    And Write a message "Parabank is the best!"
    And Send the message to Customer Care
    Then "A Customer Care Representative will be contacting you." message is displayed on Contact Us Page
