Feature: Recent patient list

  @recentPatient @smoke
  Scenario: User  verifies recent patient list
    Given User registers new user from api
    And User registers new patient with bp from api
    And User LogsIn in app
    And User on Patient tab verifies patient info should show up on top of recent patient section

  @recentPatient  @regression
  Scenario: User verifies SEE ALL Button
    Given User registers new user from api
    And User registers new patient with bp from api
    And User LogsIn in app
    And User on Patient tab verifies SEEALL button

  @recentPatient  @regression
  Scenario:upon skiping appointment, verify patient name should not show up in recent patient list
    Given User registers new user from api
    And User registers new patient with bp from api
    And User LogsIn in app
    And User on Patient tab select any patient from recent patient list
    And User on Patient summary page taps on save Button
    And  User on Patient summary page taps on appointment not now button
    And User on Patient tab verifies patient info should not show up on top of recent patient section