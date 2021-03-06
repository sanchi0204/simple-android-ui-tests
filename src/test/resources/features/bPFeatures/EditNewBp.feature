Feature: Edit BP info

  @bp @smoke1 @all
  Scenario: Edit BP info with today's date to already registered patient
    Given User registers new user from api
    And User registers new patient with bp from api
    And User LogsIn in app
    And User on Patient tab taps on search text box
    Then User on Search page searched for Registered Patient
    And User on Search page selects patient form search list
    And User on Patient summary page taps on Edit Bp Link
    And User on Patient summary page updates Bp Info
    And User on Patient summary page selects done button
    And User on Patient summary page verifies days information
    And User on Patient summary page taps on save Button
    And User on Patient summary page taps on appointment done button
    Then User on Patient tab verifies patient info should show up on top of recent patient section

  @bp @regression @all
  Scenario: Edit BP info to already registered patient who has more than one recorded bp.
    Given User registers new user from api
    And User registers a patient with multiple Bps
    And User LogsIn in app
    And User on Patient tab taps on search text box
    Then User on Search page searched for Registered Patient
    And User on Search page selects patient form search list
    And User on Patient summary page taps on Edit Bp Link
    And User on Patient summary page updates Bp Info
    And User on Patient summary page selects done button
    And User on Patient summary page verifies days information
    And User on Patient summary page taps on save Button
    And User on Patient summary page taps on appointment done button
    And User on Patient tab verifies patient info should show up on top of recent patient section

  @bp @regression @all
  Scenario: End to End flow for Edit Bp and verify reflected changes in patient summary screen[without clicking on sync link]
    Given User registers new user from api
    And  User registers new patient with bp from api
    And User LogsIn in app
    And User on Patient tab taps on search text box
    Then User on Search page searched for Registered Patient
    And User on Search page selects patient form search list
    And User on Patient summary page taps on Edit Bp Link
    And User on Patient summary page updates Bp Info
    And User on Patient summary page selects done button
    And User on Patient summary page taps on save Button
    And User on Patient summary page taps on appointment done button
    And User on Patient tab verifies patient info should show up on top of recent patient section
    And User on Patient tab select patient from recent patient list
    And User on Patient summary page verifies days information










