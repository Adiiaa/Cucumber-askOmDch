@register
Feature: User Registration
  As a customer
  I want to register an account on the website
  So that I can save my personal information

  Background:
    Given the user is on the account page

  @valid
  Scenario: Successful registration with valid credentials
    When the user fills the registration form with:
      | username | email             | password |
      | user2    | user2@example.com | user2!   |
    And the user submits the registration form
    Then the account should be created successfully

  @invalid
  Scenario Outline: Registration fails with invalid credentials
    When the user enters username "<username>"
    And the user enters email "<email>"
    And the user enters password "<password>"
    And the user submits the registration form
    Then the user should see an error message "<errorMessage>"

    Examples:
      | username    | email             | password   | errorMessage                                                                    |
      | Testing Web | testing@Web.com   | testing123 | Error: An account is already registered with your email address. Please log in. |
      |             | test@example.com  | testing123 | Error: Please enter a valid account username.                                  |
      | user1       |                   | user1!     | Error: Please provide a valid email address.                                    |
      | user2       | user2.com         | user2!     | Error: Please provide a valid email address.                                    |
      | user3       | user3@example.com |            | Error: Please enter an account password.                                        |


