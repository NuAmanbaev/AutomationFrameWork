Feature: login to saucedemo


  @login
  Scenario: login to Sauce Demo and verify you logged in

    Given user is on log in page
    When user provides a valid passowrd
    And user provides a valid username
    And user clicks on login button
    Then verify user is loged in

