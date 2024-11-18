Feature: Test DedicatedProxy

  @dedicatedproxy
  Scenario Outline: Lambdatest DedicatedProxy test
    Given I launch Session on lambdatest with <dedicatedproxy>
    When I open google
    When I enter "2+2" in search textbox
    Then I should get result as "4"
    Then Open url and verify dedicated proxy
    Then Update the result
    Then Close the browser

    Examples:
      | dedicatedproxy |
      | dedicatedproxy |
