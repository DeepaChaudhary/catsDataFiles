Feature: Verify that Logged-in user should be able to apply ADBC promo code on cart summary section and pays through ADCB Card then user should be able proceed with payment
Scenario: usingADBC_promo_andCARD_Withpayment
            
           Given user is on Home Page   
           When User clicks on MyProfile link
           Then User enters emailid
           And User enters password
           Then user click on submit button
           When User clicks on Buy Ticket CTA
          When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
         Then user apply ADBCPromoCode
		 Then user enter Debit or Credit card num and verify
         And user is able getting promo discount
         When user enter ADBC card Expaiy date and CVV num
         And select terms and condition checkbox
         And click on Pay Now button
         And user enter 3d secure pin code 
          Then user redirected to payment confirmation page		
         
