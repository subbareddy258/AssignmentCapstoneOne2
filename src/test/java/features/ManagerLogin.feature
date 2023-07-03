Feature: Manager Login


  Scenario Outline:Add Customer Details
    Given I am on the bank login page
    And  I click on Bank manger Login button
    When user fills the form from given sheetname "<SheetName>" and rownumber "<RowNumber>"
    Then click on customer and delete added customer

    Examples:
      |SheetName|RowNumber|
      |Sheet1|0|



  Scenario:Open Customer Validation
    Given I am on the bank login page
    And  I click on Bank manger Login button
    When I click on open account the open account page lauched
    And I Select the customer details and currency from dropdown
    And click on process
    Then Verify alert message displayed

