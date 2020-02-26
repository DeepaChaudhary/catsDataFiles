Feature: Verify that anonymous user able to Update guest add /delete product from Cart Summary section on Payment page

Scenario: Verify Update GuestCountDeleteProduct_FromCartSummarySection
          
          Given user is on Home Page   
          When User clicks on Buy Ticket CTA
         Then User add more junior ticket
         When User clicks on Add to Cart button on general pass adult ticket
         When  user click on Check out button on mini cart
         Then User click on grid expand button
         Then User addition more junior tickets and adult tickets
         And  User is able to edit guest count
         And User Delete item from the cart
        Then Close browser