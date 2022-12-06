Feature: Validating Swapi API's

  Scenario: Verify in People resource that Edited date is after Created date
    Given GET People resource
    When user calls "getPeopleAPI" with "GET" http request and id is 1
    Then the API call is success with status code 200
    And the Edited Date is after Created Date

  Scenario Outline: Verify all People in People resource have Edited date is after Created date
    Given GET People resource
    When user calls "getPeopleAPI" with "GET" http request and id is "<id>"
    Then the API call is success with status code 200
    And the Edited Date is after Created Date
    Examples:
      | id |
      | 1  |
      | 82 |
      | 40 |
      | 10 |
      | 20 |
      | 30 |
      | 70 |
      | 60 |
      | 50 |

