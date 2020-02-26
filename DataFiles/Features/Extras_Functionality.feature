Feature: Extras Functionality Scenarios

Background: 
	Given user is on Home Page 

	#--------------------------------------Defect: After Clicking on book now button  Booking page is not coming (We have completed this scenario in Prod)-----------------------------------------------------
Scenario: 
	YPS-5261_TC_04_Extras_Verify that From PLP/PDP page if user click buy CTA for any Add on then they land on Extra Tab in booking journey and this checkbox will be selected 
	When User clicks on main Menu Ticket item and select "EXTRAS" 
	Then Verify user navigate to ticket page 
	When User click on Book Now Button of "The Flash Pass" Feature 
	Then Verify In Ticket page "The Flash Pass" Ticket is selected on Page 
	And User click on add to cart button on Extra ticket page 
	Then Close browser 
		
Scenario: 
	YPS-3967_Verify that for Extras tab, user will be able to see Product/Products added with correct price, date and guest count 
	When User clicks on Buy Ticket CTA 
	And User click on Extra Tab 
	And User Should add Guest Count 
	And User click on add to cart button on Extra ticket page 
	Then User Open Mini cart details 
	Then user Verify Products added with correct price, date and guest count in Mini cart 
	When user click on Check out button on mini cart 
	Then user Verify Products added with correct price, date and guest count in in Cart Summary 
	And user is able to enter details of guest form 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	And click on Pay Now button 
	Then user redirected to payment confirmation page 
	Then user Verify Products added with correct price, date and guest count in in Confirmation Page 
	Then Close browser