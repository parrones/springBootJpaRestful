Feature: Retrieve a customer by id
  
  Scenario Outline: The user "<email>" wants to retrieve his data
    Given the email "<email>" and the id "<id>" of a customer
    When there is a request to retrieve customer resource
    Then the response is "<result>"
    And the email returned is "<email>"
    
    Examples:
      | email             | id | result    |
      | prueba@domain.com | 1  | OK        |
      | test@domain.com   | 2  | OK        |
      |                   | 99 | NOT_FOUND |