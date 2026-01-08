Feature: Login Functionality
  As a registered user
  I want to log into my account
  So that I can access my saved info

  Scenario: Login successful
    Given I am on the AskOmDch account page
    When I enter valid credentials
    |username|password|
    |Testing Web|testing@12|
    Then I should be on dashboard page
