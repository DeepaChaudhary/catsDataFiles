Feature: Verify that after ticking TnC checkbox of payment method and then updates cart promo code guest form then clicks on pay then all the updated info should come on order confirmation page

Scenario: PayThenAllThe_fill updated_should come on_confirmation page
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
         When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
          Then user apply PromoCode
         And user is able getting promo discount 
        Then  user is able to enter details of guest form
         When user enter payment information with credit card
		 And select terms and condition checkbox
		 And click on Pay Now button
		 Then user redirected to payment confirmation page
		 Then verify filled data should be display confirmation page
		 