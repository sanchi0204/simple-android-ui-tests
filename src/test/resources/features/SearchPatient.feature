Feature: Search for Patient information

  @search
  Scenario:Search for unregistered Patient
    Given User Registers New User through API
    And User Launches app
    And User taps on GetStarted button
    And User enters registered phone number
    And User enters registered security pin
    And User taps on Enter code link
    And User enters otp
    And User taps on Got It Button
    Then User searches for unregistered Patient

  @search1
  Scenario: Search for patient without BP info
    Given User Registers New User through API
    And User Registers New Patient through API
    And User Launches app
    And User taps on GetStarted button
    And User enters registered phone number
    And User enters registered security pin
    And User taps on Enter code link
    And User enters otp
    And User taps on Got It Button
    Then User searched for Registered Patient without BP info

  @search3
  Scenario: Search for patient with BP info
    Given User Registers New User through API
    And User Registers New Patient through API
    And User Registers New Bp record through API
    And User Launches app
    And User taps on GetStarted button
    And User enters registered phone number
    And User enters registered security pin
    And User taps on Enter code link
    And User enters otp
    And User taps on Got It Button
    Then User searched for Registered Patient without BP info