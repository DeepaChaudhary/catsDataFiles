Feature: Booking Via Cart

  Background: 
    Given user is on Home Page
    
  #  ----------------------------------giftvoucher-------6-------------------
    Scenario: Verify select gift voucher and book product with Guest user
    When User click on add to cart icon on home page
    Then verify user navigate to my Payment page and click on Buy Experiences button
    And Verify user Navigate to Booking Page and Minicart is present
    And Verify and select "GIFT VOUCHER" Voucher tab is present on Booking page
    When User select item in gift vouher list option
    Then Verify chose your ticket Overlay should open
     And User click on Terms and condition checkbox
    And User click on Proceed to checkout Button on select Date Overlay
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
    When user logs out
    Then Close browser
    
 #//---------------------------motor------------------------------------------------  
    Scenario: Verify select motorSport and book product with Guest user
    When User click on add to cart icon on home page
    Then verify user navigate to my Payment page and click on Buy Experiences button
    And Verify user Navigate to Booking Page and Minicart is present
    And Verify and select "MOTORSPORTS" Voucher tab is present on Booking page
    When User select item in MOTORSPORTS list option
    Then Verify chose your ticket Overlay should open
    Then User should select available date and time Slot
    And User click on Terms and condition checkbox
    And User click on Proceed to checkout Button on select Date Overlay
    Then Verfy User Navigate to Mypayment page and invoice Summary is present
    And User click on proceed to payment Button
    Then verify user navigate to login page
    And User enters emailid
    And User enters password
    And user click on submit button
    And verify user Navigate to payment page
    When user enter payment information with credit card
    And select terms and condition checkbox
    And click on Confirm order button
    Then user redirected to payment confirmation page 
    When user logs out
    Then Close browser

    
    
    
    
