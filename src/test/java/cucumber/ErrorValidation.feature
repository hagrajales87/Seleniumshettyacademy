Feature: Error Validation
@ErrorValidation
  Scenario Outline: Error Validation example
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
    |name|password|
    |hagrajales97@gmail.com| Test123|