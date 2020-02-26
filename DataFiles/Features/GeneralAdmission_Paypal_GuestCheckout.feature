Feature: user add General Admission Ticket and checkout with paypal payment with guest checkout

Scenario: Verify that user is able to add General Admission Ticket and checkout with paypal payment
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
         Then User empty minicart if product were there
         And user add "2" adult and "2" junior
        Then user add Flexible GA ticket
         When user click on Check out button on mini cart
         And user is able to enter details of guest form 
         And select terms and condition checkbox for paypal
         When user enter payment information with paypal
      	 Then user redirected to payment confirmation page
         Then Close browser
