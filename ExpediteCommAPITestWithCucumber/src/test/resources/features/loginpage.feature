Feature: User Registration and Support Case Creation
  Scenario Outline: Register, Login, and Create Support Case
    Given a user with username "<username>" and password "<password>"
    When the user registers
    Then the registration should be successful
    When the user logs in
    Then the login should be successful and an auth token is returned
    When the user creates a support case with title "Test Support Case" and description "This is a test support case"
    Then the support case creation should be successful

    Examples:
      | username | password |
      | eve.holt | pistol   |
      | satyan   | password |
      | SatyanP  | test123  |