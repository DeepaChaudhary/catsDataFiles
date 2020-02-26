Feature: Experiences via PDP with logged in user

Scenario:  Verify that user is able to add experiences via PDP with logged in user
         Given user is on Home Page   
         When User clicks on MyProfile link
         And User enters emailid
         And User enters password
		 And user click on submit button
         When User navigate to PLP with Experiences
         When User clicks on Buy Ticket CTA via PDP
         When User should see the empty state of the mini cart
         Then Add ticket to cart for Experiences or Tour
         When user click on Check out button on mini cart
		 And user verifies the product amount listed on cart page
         When user enter payment information with credit card
		 And select terms and condition checkbox
		# And click on Pay Now button
		# Then user redirected to payment confirmation page
		 Then Close browser
		 
		 
		 
		 
		
	  
	  