Feature: Annual Pass with login at payment page and paypal payment

Scenario:  Verify that user is able to add tickets in cart and checkout with guest details
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
	     When User should see the empty state of the mini cart	
         When User clicks on Annual Passes
		 Then user add Annual Passes to cart
         When user click on Check out button on mini cart
		 And user verifies the product amount listed on cart page
		 Then user click on login option
		 Then user logs in
		 Then user fill information for another pass holder
		 And select terms and condition checkbox for paypal
   #      When user enter payment information with paypal
   #   	 Then user redirected to payment confirmation page
   	   Then Close browser
   	   
   	   
   	   