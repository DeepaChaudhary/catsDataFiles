Feature: progress bar ovr the Purchase journey page  Scenarios

Background: 
	Given user is on Home Page 

Scenario: 
	YPS-5739_Verify the visiblity of the progress bar ovr the Purchase journey page 
	When User clicks on Buy Ticket CTA 
	Then Verify user Navigate to Booking Page 
	When User Click on payment Tab from header 
	Then verify user Navigate to payment page 
	Then Close browser 