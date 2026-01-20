@checkout
Feature: Checkout functionality
  As a customer
  I want to checkout with products in my cart
  So that I can place an order

  Background:
    Given I am on the store page
    And I add a product to the cart for checkout
    And I proceed to checkout

  @validCheckout
  Scenario: Successful checkout with required billing details
    When I fill the billing form with:
      | firstName | lastName | country | streetAddress | city   | state  | zipcode | email            |
      | Adia      | Uwase    | Rwanda  | KG 123 Street | Kigali | Kigali | 00000   | adia@example.com |
    And I submit the checkout form
    Then the order should be placed successfully

  @invalidCheckout
  Scenario Outline: Checkout fails with missing required billing details
    When I enter billing first name "<firstName>"
    And I enter billing last name "<lastName>"
    And I select billing country "<country>"
    And I enter billing street address "<streetAddress>"
    And I enter billing city "<city>"
    And I select billing state "<state>"
    And I enter billing zipcode "<zipcode>"
    And I enter billing email "<email>"
    And I submit the checkout form
    Then I should see a checkout error message "<errorMessage>"

    Examples:
      | firstName | lastName | country | streetAddress | city   | state  | zipcode | email            | errorMessage                               |
      |           | Uwase    | Rwanda  | KG 123 Street | Kigali | Kigali | 00000   | adia@example.com | Error: First name is a required field.     |
      | Adia      |          | Rwanda  | KG 123 Street | Kigali | Kigali | 00000   | adia@example.com | Error: Last name is a required field.      |
      | Adia      | Uwase    |         | KG 123 Street | Kigali | Kigali | 00000   | adia@example.com | Error: Country is a required field.        |
      | Adia      | Uwase    | Rwanda  |               | Kigali | Kigali | 00000   | adia@example.com | Error: Street address is a required field. |
      | Adia      | Uwase    | Rwanda  | KG 123 Street |        | Kigali | 00000   | adia@example.com | Error: Town / City is a required field.    |
      | Adia      | Uwase    | Rwanda  | KG 123 Street | Kigali |        | 00000   | adia@example.com | Error: State is a required field.          |
      | Adia      | Uwase    | Rwanda  | KG 123 Street | Kigali | Kigali |         | adia@example.com | Error: Postcode / ZIP is a required field. |
      | Adia      | Uwase    | Rwanda  | KG 123 Street | Kigali | Kigali | 00000   |                  | Error: Email address is a required field.  |
