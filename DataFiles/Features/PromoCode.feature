Feature: PromoCode Scenarios

Background: 
	Given user is on Home Page 

Scenario:
YPS-8530_Verify that anonymous user should be able to apply coupon code on Cart Summary section and see discount applied on the total price 
	When User clicks on Buy Ticket CTA 
	When User clicks on Annual Passes 
	Then user add Annual Passes to cart 
	When user click on Check out button on mini cart 
	Then user apply PromoCode 
	And user is able getting promo discount 
	Then Close browser
	
	
Scenario: YPS-4033_Verify that for Annual Pass tab, when user removes the valid coupon code then discounted price will change to original price 
	When User clicks on Buy Ticket CTA 
	When User clicks on Annual Passes 
	Then user add Annual Passes to cart 
	When user click on Check out button on mini cart 
	Then user apply PromoCode 
	And Verify When user removes the valid coupon code then discounted price will change to original price 
	Then Close browser 
	