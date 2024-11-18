@general_test
Feature: Check addition in Google calculator
   In order to verify that google calculator work correctly
   As a user of google
   I should be able to get correct addition result

   @Addition
   Scenario: Addition
   Given I launch Session on lambdatest with nothing
   Then open url from context json
   When select First Item
   Then select second item
   Then add new item
   Then Update the result
   Then Close the browser

   @Subtraction
   Scenario: Subtraction
   Given I launch Session on lambdatest with nothing
   Then open url from context json
   When select First Item
   Then select second item
   Then add new item
   #Then I should get internet speed and sample todo check
   Then Update the result
   Then Close the browser

   @Multiplication
   Scenario: Multiplication
   Given I launch Session on lambdatest with nothing
   Then open url from context json
   When select First Item
   Then select second item
   Then add new item
   #Then I should get internet speed and sample todo check
   Then Close the browser

   @Division
   Scenario: Division
   Given I launch Session on lambdatest with nothing
   Then open url from context json
   When select First Item
   Then select second item
   Then add new item
   #Then I should get internet speed and sample todo check
   Then Update the result
   Then Close the browser

   @Modulus
   Scenario: Modulus
   Given I launch Session on lambdatest with nothing
   Then open url from context json
   When select First Item
   Then select second item
   Then add new item
   #Then I should get internet speed and sample todo check
   Then Update the result
   Then Close the browser

   @Data
   Scenario: Small test
   Given I launch Session on lambdatest with nothing
   Then open url from context json
   When select First Item
   Then select second item
   Then add new item
   Then Close the browser
