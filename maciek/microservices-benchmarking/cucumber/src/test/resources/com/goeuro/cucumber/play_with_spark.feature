Feature: Play with Spark HTTP Server a bit

  Scenario: call no delay
    Given I have spark consumer started
    And I have spark publisher started
    When I call publisher with 0 delay
    Then I get a response with transport time overhead measurement
