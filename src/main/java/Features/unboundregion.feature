Feature: Test Unbound Region

  @unboundregion
  Scenario Outline: Lambdatest Unbound Region test
    Given I launch Session on lambdatest with <unboundRegion>
    When select First Item
    Then select second item
    Then add new item
    Then Open local browser and verify ip <ip> for unboundregion
    Then Update the result
    Then Close the browser

    Examples:
      | unboundRegion                     | ip            |
      | unboundregion:ProductionUS2-PFRC  | 40.66.60.219  |
      | unboundregion:ProductionEU2-PUSE2 | 52.252.22.110 |
      | unboundregion:ProductionEU2-PFRC  | 40.66.60.232  |
      | unboundregion:ProductionUS2-PUSE2 | 52.177.110.52 |