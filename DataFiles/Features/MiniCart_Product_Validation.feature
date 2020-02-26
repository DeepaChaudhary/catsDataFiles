Feature: Maximum ticket count per user that can be added to mini cart is 90


Scenario: Verify that maximum ticket count per user that can be added to mini cart is 90
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
	     When User should see the empty state of the mini cart
	     When User add General Admission Ticket
	     Then user expand minicart if not expanded
	     And user verify the error message in minicart
	     Then Close browser
	     