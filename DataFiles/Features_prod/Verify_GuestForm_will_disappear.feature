Feature: Verify that anonymous user clicks on Login/signup option then after logging in user will land on payment page and Guest form will disappear

Scenario: Verify_GuestForm_will_disappear
         Given user is on Home Page   
         When  User clicks on Buy Ticket CTA
         When  User clicks on Add to Cart button on general pass adult ticket
         When  user click on Check out button on mini cart
		 And   user verifies the product amount listed on cart page
		 Then  user click on login on payment page
		  And User enters emailid
         And User enters password
	     And user click on submit button
	     Then User is able to see Guest form will disappear
	     Then Close browser
		 