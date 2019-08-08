@employeeMstestFeature
Feature: employeeMstestFeature

  Scenario: Employee MS test Feature
    Given I want to generate the access token with user "Alexa" and "password"
    Then I will call the "list_all_employee" with generated access token

      
