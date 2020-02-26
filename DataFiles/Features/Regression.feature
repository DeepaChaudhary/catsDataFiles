Feature: WBW Regression scenarios 


Background: 
	Given user is on Home Page 
	#------------------------------------sce-1---Defect------------------------------------------------------
Scenario: 
	YPS-7211_Verify Multipark tickets are available on general addmission PLP and navigating to booking page after clicking 
	When User clicks on main Menu Ticket item and select "GENERAL ADMISSION" 
	Then Verify user navigate to ticket page 
	When User click on Book Now Button of "2 Day Any 2 Parks" Feature 
	Then Verify In Ticket page "2 Day Any 2 Parks" Ticket is selected 
	And User click on add to cart button on General addmission page 
	Then Close browser
		
Scenario: #--------------------------------------Defect: Valid upto six month is not in UAT-----------------------------------------------------
	YPS-4121_Verify that for General Admission tab, flexible ticket will be valid for 6 months 
	##	When User clicks on main Menu Ticket item and select "GENERAL ADMISSION"
	##	Then Verify user navigate to ticket page
	##	When User click on Book Now Button of "Single Day Ticket" Feature 
	When User clicks on Buy Ticket CTA 
	When User clicks on Add to Cart button on general pass adult ticket 
	Then User Open Mini cart details 
	And Verify Ticket Valid upto six month from current date on mini cart 
	When user click on Check out button on mini cart 
	And Verify Ticket Valid upto six month from current date in Cart Summary 
	And user is able to enter details of guest form 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	And click on Pay Now button 
	Then user redirected to payment confirmation page 
	And Verify Ticket Valid upto six month from current date in Confirmation Page 
	Then Close browser 
	
Scenario: 
	YPS-3957_Verify that user should be able to add multiple tickets from different tabs 
	When User clicks on Buy Ticket CTA 
	When User should see the empty state of the mini cart 
	When User clicks on Add to Cart button on general pass adult ticket 
	When User clicks on Annual Passes 
	Then user add Annual Passes to cart 
	And User click on Extra Tab 
	And User Should add Guest Count 
	And User click on add to cart button on Extra ticket page 
	Then User Open Mini cart details 
	Then Verify that multiple tickets get added from different tabs 
	Then Close browser 
	
Scenario: 
	YPS-6273_TC_05_GA_Verify that multi park tickets will be displayed in carousel form 
	When User clicks on Buy Ticket CTA 
	Then Verify In General Admission multi park tickets will be displayed in carousel form 
	Then Close browser 
	
Scenario: 
	YPS-5285TC_05_GA_ Verify that Add to cart CTA will be moved inside the collapsible ticket section. Once user click on icon to expand these tickets ‚Äì Add to cart CTA will be displayed. Default state of this CTA is greyed out. 
	When User clicks on Buy Ticket CTA 
	When User should see the empty state of the mini cart 
	When User clicks on Annual Passes 
	Then Verify Add to cart is not appearing 
	When User click on any Annual pass ticket checkbox 
	Then Verify Add to cart is appearing 
	And User decrease Guest Count in My cart by "2" 
	Then Verify Add to cart button is greyed out 
	Then Close browser
	
Scenario:
YPS-8549_Verify that for Logged-in user all validations will fire on PAY CTA click in case user is doing payment via Payfort 
	When User clicks on MyProfile link 
	And User enters emailid 
	And User enters password 
	And user click on submit button 
	When User clicks on Buy Ticket CTA 
	When User clicks on Add to Cart button on general pass adult ticket 
	Then User Open Mini cart details 
	When user click on Check out button on mini cart 
	And click on Pay Now button 
	Then User validate mandatory field error message
	Then Close browser 
	




   	   
	
