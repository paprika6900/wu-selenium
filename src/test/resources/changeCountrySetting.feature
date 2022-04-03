Feature: Does the page change correctly according to country setting?
  Scenario: Lithuania to USA
    Given location is Lithuania
    When changing location settings
    Then location should be UnitedStates