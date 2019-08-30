Feature: Search on google

  Scenario Outline: User performs a search on Google and the results are optimal
    Given I open google homepage
    When I search for <search>
    Then I can see <result> in the results
    Examples:
      | search   | result   |
      | apple    | sarasa    |
      | orange   | orange   |
      | emiliano | emiliano |