Feature: General Admission sign up and login while purchase
@Regression
Scenario: Verify that user is able to purchase by signup from the payment screen 
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
#	     When User should see the empty state of the mini cart	
         When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
#		 And user verifies the product amount listed on cart page
		 Then user click on login option
		 Then user click on Sign up option on registration page
         When User enter below valid details for registration
      	 And YWW User click on submit button for registration
#         And user is able to enter details of guest form 
         When user enter payment information with credit card
		 And select terms and condition checkbox
		# And click on Pay Now button
		 #Then user redirected to payment confirmation page
   	 Then Close browser  


@Regression
Scenario: Verify that user is able to purchase by Login with existing user from the payment screen
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
#	     When User should see the empty state of the mini cart	
         When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
#		 And user verifies the product amount listed on cart page
		 Then user click on login option
         Then User enters emailid
		 And User enters password
		 Then user click on submit button
		 When user enter payment information with credit card
		 And select terms and condition checkbox
		# And click on Pay Now button
		# Then user redirected to payment confirmation page
   	 Then Close browser 