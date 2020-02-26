Feature: Confirmation page Scenarios

Background: 
	Given user is on Home Page 

Scenario:
YPS-6288_Verify that after successful transaction user is not able to access confirmation page 2nd time By Going to browser history and clicking on confirmation page URL there 
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
	When User refresh the confirmation page 
	Then Verify User is not able to access confirmation page 2nd time 
	Then Close browser