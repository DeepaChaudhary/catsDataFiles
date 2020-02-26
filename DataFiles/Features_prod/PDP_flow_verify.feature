Feature: PDP Verify
  Background: 
     Given user is on Home Page
     

  Scenario: Verify PDP Drive 
    When User clicks on main Menu Experiences item and select "Drive"
    And User click on discover More button
    And User select country of issue
    And User click on Terms and condition checkbox
    And User click on Add to cart Button on select Date Overlay
    And user verifies the product amount listed on cart
    When user click on Check out button of mini cart
   Then Verfy User Navigate to Mypayment page and invoice Summary is present
   And User click on proceed to payment Button
    Then verify user navigate to login page
    And User enters emailid
    And User enters password
    And user click on submit button
    And verify user Navigate to payment page
    When user enter payment information with credit card
   And select terms and condition checkbox
  #  And click on Confirm order button
  # Then user redirected to payment confirmation page
  # And user logs out
    Then Close browser

 Scenario: Verify PDP Drag 
    When User clicks on main Menu Experiences item and select "Drag"
    And User click on discover More button
    And User select country of issue
    And User click on Terms and condition checkbox
    And User click on Add to cart Button on select Date Overlay
    And user verifies the product amount listed on cart
    When user click on Check out button of mini cart
    Then Verfy User Navigate to Mypayment page and invoice Summary is present
    And User click on proceed to payment Button
    Then verify user navigate to login page
    And User enters emailid
    And User enters password
    And user click on submit button
    And verify user Navigate to payment page
    When user enter payment information with credit card
    And select terms and condition checkbox
   # And click on Confirm order button
   # Then user redirected to payment confirmation page
   # And user logs out
		Then Close browser
 







