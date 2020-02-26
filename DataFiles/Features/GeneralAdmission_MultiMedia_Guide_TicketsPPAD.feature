Feature: General Admission add MultiMedia Tickets

@regrestion
Scenario: Verify that guest count 
         Given user is on Home Page   
         When User clicks on Buy Ticket CTA
         Then User add more junior ticket
         Then User pick a date
         When User clicks on Add to Cart button on general pass adult ticket
         And User click on cart expand button
         Then User add more junior tickets and adult tickets
         And  User is able to edit guest count
         Then Close browser

          