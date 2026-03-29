@browseCategory
Feature: Browse by categories on the store page
  As a customer
  I want to filter products by category
  So that I only see items from the selected category

  Background:
    Given I am on the store page

  Scenario Outline: Filter products by category
    When I select the "<category>" category
    Then Only the products from "<category>" should be displayed

    Examples:
      | category     |
      | men          |
      | women        |
      | accessories  |
