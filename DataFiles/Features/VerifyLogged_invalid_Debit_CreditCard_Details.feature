Feature: Verify when Logged-in user adds incorrect data in any of the Payfort Card details fields(Card Number, CVV, Expiry) then after clicking Pay user should get error on the payment page 

Scenario: logged user Verify with fill invalid  card details 
            Given user is on Home Page   
            When User clicks on MyProfile link
           Then User enters emailid
           And User enters password
           Then user click on submit button
           When User clicks on Buy Ticket CTA
          When User clicks on Add to Cart button on general pass adult ticket
          When user click on Check out button on mini cart
          Then user enter wrong Debit Or Credit card details
          And select terms and condition checkbox
          And click on Pay Now button
          Then user verify error mgs for invalid input
          
          