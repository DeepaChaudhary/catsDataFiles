 Feature: ADCB card through payment PPAD 
# PROD NEED FOR Then User click on general Admission btn
 Scenario: Verify that user is able to pay through ADCB card 
    Given user is on Home Page
    When User clicks on Buy Ticket CTA
    When User should see the empty state of the mini cart
    Then User pick a date
    When User clicks on Add to Cart button on general pass adult ticket
    Then user click on Check out button on mini cart
    And user verifies the product amount listed on cart page
    Then user is able to enter details of guest form
    Then user enter Debit or Credit card num and verify
    When user enter ADBC card Expaiy date and CVV num
     And select terms and condition checkbox
     And click on Pay Now button
     And user enter 3d secure pin code 
     Then user redirected to payment confirmation page
     Then Close browser 
	
     