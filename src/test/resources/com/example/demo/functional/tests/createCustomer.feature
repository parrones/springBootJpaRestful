Feature: Create a new customer

  Scenario Outline: The user "<email>" wants to signup
    Given an "<email>" and a "<password>"
    When there is a request to signup resource
    Then the response is "<result>"
    
    Examples:
      | email             | password  | result       |
      | email@domain.com  | 123456789 | OK           |
      | email@domain.com  | 123456789 | CONFLICT     |