Feature: validation fire on TnC checkbox click in case user is doing payment via PayPalPPAD
 @regression
Scenario: Verify fire on TnC checkbox click in case user is doing payment via PayPal
            Given user is on Home Page   
          When User clicks on Buy Ticket CTA
           Then User pick a date
          When User clicks on Add to Cart button on general pass adult ticket
         Then user click on Check out button on mini cart
         And select terms and condition checkbox for paypalPPAD
         Then user able to see mandatory fields
         Then Close browser