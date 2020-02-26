Feature: Verify_non_logged_Guest_my_purchase

Scenario: Verify Guest purchase
         Given user is on Home Page   
        When User clicks on Buy Ticket CTA
         When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
		 And user verifies the product amount listed on cart page
         Then user is able to enter guest details form
         When user enter payment information with credit card
		 And select terms and condition checkbox
		 And click on Pay Now button
		 Then user redirected to payment confirmation page
		 And  User clicks on MyProfile link
         And User enters emailid
         And User enters password
	     And user click on submit button
	     Then User Hover and Click on my profile 
	     And click on purchases and verify booking id not present
	     