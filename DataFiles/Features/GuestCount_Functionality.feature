Feature: GuestCount Scenarios

Background: 
	Given user is on Home Page 

Scenario: 
	YPS-3962_Verify that for GENERAL ADMISSION tab, maximum count a user can select for a particular ticket is 14 after that counter will be disabled 
	When User clicks on main Menu Ticket item and select "GENERAL ADMISSION" 
	Then Verify user navigate to ticket page 
	When User click on Book Now Button of "Single Day Ticket" Feature 
	When User Increase the spinner count of Adult and junior if exceed 14 Group booking error message should display 
	Then Verify Add to cart button is disabled 
	Then Close browser 
	
	
Scenario: 
	YPS-5736_TC_02_Verify the changes for the visibility of the Quantity in the Mini cart for purchase journey 
	When User clicks on Buy Ticket CTA 
	When User should see the empty state of the mini cart 
	When User clicks on Add to Cart button on general pass adult ticket 
	Then User Open Mini cart details 
	And User Increase Guest Count in My cart by "2" 
	And Verify Guest Count value get increased in My cart by "2" 
	When user click on Check out button on mini cart 
	And Verify Guest Count value get increased in Cart Summary 
	Then Close browser 
	
Scenario:
YPS-8547_Verify that Logged-in user should be able to update cart (Update guest count/delete product) from Cart Summary section on Payment page 
	When User clicks on MyProfile link 
	And User enters emailid 
	And User enters password 
	And user click on submit button 
	When User clicks on Buy Ticket CTA 
	When User clicks on Add to Cart button on general pass adult ticket 
	Then User Open Mini cart details 
	When user click on Check out button on mini cart 
	And User expand cart summary on My payment page 
	And User Increase Guest Count in My cart by "2" 
	And Verify Guest Count value get increased in My cart by "2" 
	And User Delete item from the cart Summary 
	Then User should see the empty state of the mini cart 
	Then Close browser