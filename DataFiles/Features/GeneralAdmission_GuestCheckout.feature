Feature: General Admission Guest Checkout with credit card payment

Scenario: Verify that user is able to add tickets in cart and checkout with guest details
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
	     When User should see the empty state of the mini cart	
         When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
		 And user verifies the product amount listed on cart page
         And user is able to enter details of guest form 
         When user enter payment information with credit card
		 And select terms and condition checkbox
		 And click on Pay Now button
		 Then user redirected to payment confirmation page
		 Then user click on Download ticket button
   	 Then Close browser  
   	   