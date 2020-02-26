Feature: Verify payment page & selected credit/debit card as payment method when user starts typing ADCB card number and enter 4 digits of the card then user should get a notified that he/she is using a ADCB card

Scenario: non logged VerifyType_of_Debit_CreditCard
          Given user is on Home Page   
          When User clicks on Buy Ticket CTA
          Then User pick a date
          When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
		 Then user enter Debit or Credit card num and verify 
		 Then Close browser


