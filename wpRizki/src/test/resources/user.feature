Feature: As User creating a end-to-end (e2e) tests for creating a gist
  Scenario: As a user, I want to create a public gist
    Given User access Gist
    And User Login
    And User Add New Public Gist
    Then Verify That Gist Exist

  Scenario: As a user, I want to edit an existing gist
    Given User Edit an Existing Gist
    Then Verify That Gist Exist

  Scenario: As a user, I want to delete an existing gist
    Given User Delete an Existing Gist
    Then Verify That Gist Deleted

  Scenario: As a user, I want to see my list of gists
    Given When User Click See All of Your Gists