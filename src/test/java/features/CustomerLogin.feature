Feature: Customer Login

  @deposit
  Scenario Outline: Customer Amount Validation
    Given I am on the login page
    When I click on login button
    Then Dropdown display with Customer Names "<amount>" and "<type>"

    Examples:
      | amount | type      |
      | 10000  | Deposit   |
      | -5     | Deposit   |
      | 0      | Deposit   |
      | 100    | Withdrawl |
      | -5     | Withdrawl |
      | 0      | Withdrawl |

  @deposit
  Scenario:Deposit Amount Validation
    Given I am on the login page
    When I click on login button
    And click on transcations
    Then click on LogoutButton



