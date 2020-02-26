Feature: Verify when Non_Logged-in user adds incorrect data in any of the Payfort Card details fields(Card Number, CVV, Expiry) then after clicking Pay user should get error on the payment page 
 
 @regression
Scenario: Verify Non logged user fill invalid  card details 
            Given user is on Home Page   
           When User clicks on Buy Ticket CTA
          Then User pick a date
          When User clicks on Add to Cart button on general pass adult ticket
         Then user click on Check out button on mini cart
         And user verifies the product amount listed on cart page
        Then user is able to enter details of guest form
        Then user enter wrong Debit Or Credit card details
        And select terms and condition checkbox
        And click on Pay Now button
       Then user verify error mgs for invalid input
       Then Close browser