Feature: Product Listing Page scenarios


  Background: 
    Given user is on Home Page
#------------------------------------1-Drive---------------------------------------------------------
  Scenario: Verify that user is able to add experiences-drive via PDP with Guest user
    When User clicks on main Menu Experiences item and select "Drive"
    When User navigate to PLP throught experience option for selecting date to Book product
    And User select country of issue
    And User click on Terms and condition checkbox
    And User click on Add to cart Button on select Date Overlay
    And Verify user Navigate to Booking Page and Minicart is present
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
    #And click on Confirm order button
    #Then user redirected to payment confirmation page
    #When user logs out
    Then Close browser
    
#------------------------------------2-Drag---------------------------------------------------------

Scenario: Verify that user is able to add experiences-Drag via PDP with Guest user
    When User clicks on main Menu Experiences item and select "Drag"
    When User navigate to PLP throught experience option for selecting date to Book product
    And User select country of issue
    And User click on Terms and condition checkbox
    And User click on Add to cart Button on select Date Overlay
    And Verify user Navigate to Booking Page and Minicart is present
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
   # When user logs out
    Then Close browser
    
 #--------------------------------3-Drift-----------------------------------------------------   
 Scenario: Verify that user is able to add experiences-Drift via PDP with Guest user thru Proceed to checkout flow
    When User clicks on main Menu Experiences item and select "Drift"
   When User navigate to PLP throught experience option for selecting date to Book product
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
    #Then user redirected to payment confirmation page
    #When user logs out 
    Then Close browser
 
#-------------------------------------------4-Ride-------------------------------------------   

    Scenario: Verify that user is able to add experiences-Ride via PDP with Guest user thru Proceed to checkout flow
    When User clicks on main Menu Experiences item and select "Ride"
    When User navigate to PLP throught experience option for selecting date to Book product
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
    #And click on Confirm order button
    #Then user redirected to payment confirmation page
    #When user logs out
    Then Close browser
    
#  ------------------------------------5-Recommendations ---------------------
  Scenario: Verify that user is able to add experiences-drive via PDP with Guest user and check recommendation tab
    When User clicks on main Menu Experiences item and select "Drive"
    And User select feature "YAS CAR TRACK DAY" and click on book now button.
    And User select country of issue
    And User click on Terms and condition checkbox
    And User click on Add to cart Button on select Date Overlay
    And Verify user Navigate to Booking Page and Minicart is present
    And Verify recommended tab should come
    And User should verify recommended list and select recommended list option
    Then Verify chose your ticket Overlay should open
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
    And click on Confirm order button
   # Then user redirected to payment confirmation page
   # When user logs out
    Then Close browser
    
   