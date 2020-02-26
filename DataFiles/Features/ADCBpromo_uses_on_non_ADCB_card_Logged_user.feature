Feature: Verify that logged user is on payment page when he/she uses ADCB specific promo code and uses a non-ADCB card/PayPal/Apple Pay for payment then transaction should not proceed and user should see an error message on the payment section

Scenario: ADCBpromo code logged uses on nonADCB_card then got error massage
          Given user is on Home Page
           When User clicks on MyProfile link
           Then User enters emailid
           And User enters password
           Then user click on submit button   
          When User clicks on Buy Ticket CTA
         Then User add more junior ticket
         When User clicks on Add to Cart button on general pass adult ticket
         When  user click on Check out button on mini cart
         Then user apply ADBCPromoCode
        When user enter payment information with credit card
        And select terms and condition checkbox
        And click on Pay Now button
        Then User able to see ADCB error message
        Then Close browser
         
         