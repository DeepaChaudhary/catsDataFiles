Feature: UpSell Scenarios


Background: 
	Given user is on Home Page


Scenario: YPS-3651_YPS-3652_Verify that when user clicks on cross icon then upsell pop up will close and orignal product will be added in the cart
#         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
	     When User should see the empty state of the mini cart	
         When User clicks on Add to Cart button on general pass adult ticket
         Then User Open Mini cart details
         And Verify that orignal product will be added in the cart
          Then Close browser
         
Scenario: YPS-3648_YPS-6717_Verify that mini cart will be update with the selected product from the upsell and Verify that whenever user adds a product from the booking page then he should be presented with an Upsell option everytime and user is able to purchase the Upsell product
         When User clicks on Buy Ticket CTA
	     When User should see the empty state of the mini cart	
         Then User Click on Add to cart button and Upgrade My ticket OverLay should Open  
         And User will select ticket " 3 Day All 3 Parks" in Upgrade My ticket Overlays      
         Then User Open Mini cart details
         And Verify that Upgrade ticket get added in the cart
         When user click on Check out button on mini cart
		 And user verifies the product amount listed on cart page
         And user is able to enter details of guest form 
         When user enter payment information with credit card
		 And select terms and condition checkbox
		 And click on Pay Now button
		 Then user redirected to payment confirmation page
		 Then Close browser
         
       