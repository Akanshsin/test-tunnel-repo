Feature: Add new item to ToDO list

  @tunnel
  Scenario: Lambdatest ToDO test
    Given I launch Session on lambdatest with tunnel
    Then Open local browser and verify tunnel
    When select First Item
    Then select second item
    Then Update the result
    Then Close the browser
