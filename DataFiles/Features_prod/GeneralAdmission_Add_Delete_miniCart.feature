Feature: General Admission verify addition and deletion

Scenario: Verify that user is able to add tickets and Delete tickets successfully 
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
         Then User add more junior ticket
         When User clicks on Add to Cart button on general pass adult ticket
         And User click on cart expand button
         Then User add more junior tickets and adult tickets
         And User Delete item from the cart
        Then User should see the empty state of the mini cart
        Then Close browser

