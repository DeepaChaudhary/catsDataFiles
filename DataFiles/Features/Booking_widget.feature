Feature: Booking_widget

Background: User should be able to navigate home page
			 Given user is on Home Page
			 

Scenario: Verify the hotel booking widget
       #  Given Closing cookie header if it is there
        When User select checkin and checkout date
        Then user select "2" rooms
        Then user add "2" adult and "1" child
		And user click on Get Packages
	    And user is redirected to Package page and verify information
		Then Close browser
		
		
		
		