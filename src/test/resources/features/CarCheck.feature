Feature: Verify details of a vehicle
  Scenario: Verify details a vehicle given a list of vehicle registration numbers
    Given a set of files in Configured directory containing registration numbers
    When firefox browser is launched to open the Configured website
    Then for each vehicle registration number vehicle Tax details are retrieved