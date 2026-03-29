@cart
Feature: Add to Cart
  As a customer
  I want to add products to my cart
  So that I can purchase them later

  Background:
    Given I am on the home page

  @store
  Scenario: Add product to cart from the Store page
    When I navigate to the store page
    And I add a product to the cart from the store
    Then the product should be visible in the cart
