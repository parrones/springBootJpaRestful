Feature: Delete a customer

  Scenario Outline: The client wants to delete his user
    Given the id "<id>" of a customer
    When there is a request to delete resource
    Then the response is "<result>"
    
    Examples:
      | id | result    |
      | 3  | OK        |
      | 99 | NOT_FOUND |