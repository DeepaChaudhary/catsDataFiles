Feature: Yas Id link Scenarios 

Background: 
	Given user is on Home Page

Scenario: YPS-8545_Verify that anonymous user will able to register through YAS ID link on Order confirmation page and receives email to reset password
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
	And User Click on Yas Id link
	And verify user will able to register through YAS ID and receives email to reset password msg Should come
	Then Close browser
	
Scenario: YPS-8546_Verify that if anonymous user is already registered and clicks on YAS ID link then user should get pop up message that he/she is already registered
	When User clicks on Buy Ticket CTA 
	When User should see the empty state of the mini cart 
	When User clicks on Add to Cart button on general pass adult ticket 
	When user click on Check out button on mini cart 
	And user verifies the product amount listed on cart page 
	And user is able to enter yas id registered user details in guest form 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	And click on Pay Now button 
	Then user redirected to payment confirmation page
	And User Click on Yas Id link
	And verify user should get pop up message that he or she is already registered
	Then Close browser	


	

	
 

	