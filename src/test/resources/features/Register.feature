Feature: Registration functionality
  As a customer
  I want to register on the website
  So that I can  save my personal info

  Scenario: Registering successful in the website
    Given I am on the AskOmDch account page
    When I register with valid credentials
    Then My account should be created successfully