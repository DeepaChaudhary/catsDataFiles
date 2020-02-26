Feature: Flash Sale scenarios 


Background: 
	Given user is on Home Page 
	
Scenario: YPS-3591_Dated Products Verify that if user already has some products in cart then that will be merged with flash sale product
	 When User clicks on Buy Ticket CTA
	 When User should see the empty state of the mini cart	
	 When User clicks on Add to Cart button on general pass adult ticket
	 When User click on Header logo and Navigate to home page
	 And User Click on Add to cart button on Flash Sale 
	 And verify user Navigate to payment page
	 Then Verify that flash sale product get merged with already existing products
	 Then Close browser
 
Scenario: YPS-3582_Verify Details in flash sale
	When Verify User is on home Page
	Then Verify Product name and product description is present on Flash Sale
	Then Verify Product Time is present on Flash Sale
	Then Verify Discount amount, Saving amount and Price is present on Flash Sale
	Then Verify Quantity selection is present and Working on Flash Sale
	Then Close browser