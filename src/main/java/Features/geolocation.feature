Feature: Test GeoLocation

  @geolocation
  Scenario Outline: Lambdatest GeoLocation test
    Given I launch Session on lambdatest with <geoLocation>
    When select First Item
    Then select second item
    Then add new item
    Then Open local browser and verify geolocation for country <country> and country code <countrycode>
    Then Update the result
    Then Close the browser

    Examples:
      | geoLocation    | country | countrycode |
      | geolocation:IT | Italy   | IT          |
      | geolocation:DE | Germany | DE          |
