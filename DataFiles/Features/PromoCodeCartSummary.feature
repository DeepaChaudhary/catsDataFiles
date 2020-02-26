Feature: Verify that anonymous user should be able to apply ADCD promo code on cart summary section and pays through ADCB Card then user should be able o proceed with payment

Scenario: Verify PromoCodeCart Summary
          
          Given user is on Home Page   
          When User clicks on Buy Ticket CTA
         Then User add more junior ticket
         When User clicks on Add to Cart button on general pass adult ticket
         When  user click on Check out button on mini cart
         Then user apply PromoCode
         And user is able getting promo discount 
        Then  user is able to enter details of guest form
        When user enter payment information with credit card
        And select terms and condition checkbox
        And click on Pay Now button
        Then user redirected to payment confirmation page
        Then Close browser