Feature: Explore Options scenario

Background: 
	Given user is on Home Page

Scenario: YPS-6777_Verify that user will be able to navigate to Dining Detail page via Dining Listing page
	When User clicks on main Menu explore item and select "DINING"
	Then Verify user navigate to dinning listing page and click on Read more Button
	And Verify user navigate to Dining Detail page
	Then Close browser 
	
	
Scenario: YPS-6776_Verify that user will be able to navigate to ride and attraction Detail page via Ride and attraction Listing page	
	When User clicks on main Menu explore item and select "RIDES & ATTRACTIONS"
	Then Verify user navigate to Ride And Attraction listing page and click on Read more Button
	And Verify user navigate to Ride And Attraction Detail page
	Then Close browser
	
