@login
Feature: User Login
  As a registered user
  I want to log in to my account
  So that I can access my saved information

  Background:
    Given the user is on the account page

  @validLogin
  Scenario: Successful login with valid credentials
    When the user fills the login form with:
      | username | password |
      | user2    | user2!   |
    And the user submit the login form
    Then the user should be logged in successfully

  @invalidLogin
  Scenario Outline: Login fails with invalid credentials
    When the user enters login username "<username>"
    And the user enters login password "<password>"
    And the user submit the login form
    Then the user should see a login error message "<errorMessage>"

    Examples:
      | username | password  | errorMessage                                                                                                                    |
      | user2    | wrongPass | Error: The password you entered for the username user2 is incorrect. Lost your password?                                        |
      |          | user2!    | Error: Username is required.                                                                                                    |
      | user2    |           | Error: The password field is empty.                                                                                             |
      | unknown  | user2!    | Error: The username unknown is not registered on this site. If you are unsure of your username, try your email address instead. |
