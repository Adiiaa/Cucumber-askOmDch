Feature: Add To Cart Functionality
  As a user
  I want to add a product to cart
  So that I can purchase it later

  Scenario: Adding product to cart
    Given I am on the store page
    When I click add to product button on the product
    Then then the product should be visible to cart