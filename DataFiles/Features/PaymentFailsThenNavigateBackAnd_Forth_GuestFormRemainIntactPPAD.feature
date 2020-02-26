Feature: when user fills the guest form and updates cart navigate back and forth to payment page payment fails then guest form details should remain intact
Scenario: Verify Payment Fails Then Navigate Back And Forth to GuestForm Remain Intact
         Given user is on Home Page   
        When User clicks on Buy Ticket CTA
        Then User pick a date
        When User clicks on Add to Cart button on general pass adult ticket
         When user click on Check out button on mini cart
         Then user is able to enter guest details form
         And user navigate back and forth to payment page
         Then user validate guest Form Remain intact
         Then Close browser
         