Feature: My Purchase History Scenarios

Background: 
	Given user is on Home Page 

Scenario: YPS-YPS-7103_Verify that if register user book ticket on site then his/her purchases will be available in my purchase and annual pass section on profile section
	When User clicks on Buy Ticket CTA 
	When User should see the empty state of the mini cart 
	When User clicks on Annual Passes 
	Then user add "3" Annual Passes to cart 
	When user click on Check out button on mini cart 
	And user verifies the product amount listed on cart page 
	Then user click on login option 
	And User login 
	Then user fill information for another pass holder 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	And click on Pay Now button 
	Then user redirected to payment confirmation page 
	And User Verify the orderId on payment confirmation page 
	 When user clicks on My Profile button present under Profile icon
	And User click on my purchase tab 
	Then Verify purchase order id is available in my purchase tab
	Then Close browser 
	
	
Scenario: YPS-3324_Verify that clicking on Annual Pass tab, user will land on Annual Pass tab on Booking page
    When User clicks on Buy Ticket CTA 
	When User should see the empty state of the mini cart 
	When User clicks on Annual Passes 
	Then user add "3" Annual Passes to cart 
	When user click on Check out button on mini cart 
	And user verifies the product amount listed on cart page 
	Then user click on login option 
	Then user logs in 
	Then user fill information for another pass holder 
	And select terms and condition checkbox for paypal 
	When user enter payment information with paypal 
	Then user redirected to payment confirmation page 
	Then Close browser