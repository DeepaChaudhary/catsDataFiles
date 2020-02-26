Feature: PPAD Ticket Purchase

Background: User should be able to navigate home page
			 Given user is on Home Page
	@ppad		 
  Scenario: Verify that user is able to add tickets in cart and checkout with guest details
    When User clicks on Buy Ticket CTA
    When User should see the empty state of the mini cart
    Then User pick a date
    When User clicks on Add to Cart button on general pass adult ticket
    Then user click on Check out button on mini cart
    And user verifies the product amount listed on cart page
    Then user is able to enter details of guest form
    When user enter payment information with credit card
    And select terms and condition checkbox
   # And click on Pay Now button
   # Then user redirected to payment confirmation page
    Then Close browser  

  Scenario: Verify that user is able to add tickets from PLP Page in cart and checkout with guest details
    When User navigates to PLP for booking
    When User should see the empty state of the mini cart
    Then User pick a date
    When User clicks on Add to Cart button on general pass adult ticket
    Then user click on Check out button on mini cart
    And user verifies the product amount listed on cart page
    Then user is able to enter details of guest form
    When user enter payment information with credit card
    And select terms and condition checkbox
   # And click on Pay Now button
   # Then user redirected to payment confirmation page
   Then Close browser  

  Scenario: Verify that user is able to add Tour tickets in cart and checkout with guest details
    When User clicks on Buy Ticket CTA
    Then User click on Tours
    When User should see the empty state of the mini cart
    Then Add ticket to cart for Experiences or Tour
    When user click on Check out button on mini cart
    And user verifies the product amount listed on cart page
    Then user is able to enter details of guest form
    And select terms and condition checkbox for paypal
  #  When user enter payment information with paypal
   # Then user redirected to payment confirmation page
   Then Close browser  
