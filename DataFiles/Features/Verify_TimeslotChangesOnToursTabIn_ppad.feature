Feature: Verify Time slot changes on tours tab in ppad 
@regression
Scenario: Verify Time slot changes on tours tab in ppad
            Given user is on Home Page   
            When User clicks on Buy Ticket CTA
            Then User click on Tours
            Then Add ticket to cart for Experiences or Tour
            Then Close browser
            
            