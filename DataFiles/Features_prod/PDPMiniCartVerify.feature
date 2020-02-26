Feature: PDP mini cart Verify 

  Background: 
     Given user is on Home Page
     

Scenario: Verify PDP drift booking throgh minicart 
	Given user is on Home Page 
	When User clicks on main Menu Experiences item and select "Drift" 
	And User click on discover More button 
	And User click on Terms and condition checkbox 
	And User click on Add to cart Button on select Date Overlay 
	And Verify user Navigate to Booking Page and Minicart is present 
	And user verifies the product amount listed on cart 
	When user click on Check out button of mini cart 
	Then Verfy User Navigate to Mypayment page and invoice Summary is present 
	And User click on proceed to payment Button 
	Then verify user navigate to login page 
	And User enters emailid 
	And User enters password 
	And user click on submit button 
	And verify user Navigate to payment page 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	#And click on Confirm order button 
	#Then user redirected to payment confirmation page 
	#And user logs out 
	 Then Close browser
	
Scenario: Verify PDP Ride booking throgh minicart 

	When User clicks on main Menu Experiences item and select "Ride" 
	And User click on discover More button 
	And User click on Terms and condition checkbox 
	And User click on Add to cart Button on select Date Overlay 
	And Verify user Navigate to Booking Page and Minicart is present 
	And user verifies the product amount listed on cart 
	When user click on Check out button of mini cart 
	Then Verfy User Navigate to Mypayment page and invoice Summary is present 
	And User click on proceed to payment Button 
	Then verify user navigate to login page 
	And User enters emailid 
	And User enters password 
	And user click on submit button 
	And verify user Navigate to payment page 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	#And click on Confirm order button 
	#Then user redirected to payment confirmation page 
	#And user logs out 
	 Then Close browser
	
Scenario: Verify PDP Tour booking throgh minicart 
	When User clicks on main Menu Experiences item and select "Tour" 
	And User click on bookNow button 
	Then User select quantity 
	And User click on Terms and condition checkbox 
	And User click on Add to cart Button on select Date Overlay 
	And Verify user Navigate to Booking Page and Minicart is present 
	And user verifies the product amount listed on cart 
	When user click on Check out button of mini cart 
	Then Verfy User Navigate to Mypayment page and invoice Summary is present 
	And User click on proceed to payment Button 
	Then verify user navigate to login page 
	And User enters emailid 
	And User enters password 
	And user click on submit button 
	And verify user Navigate to payment page 
	When user enter payment information with credit card 
	And select terms and condition checkbox 
	#And click on Confirm order button 
	#Then user redirected to payment confirmation page 
	#And user logs out 
	Then Close browser

