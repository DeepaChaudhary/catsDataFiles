Feature: General Admission user able to edit guest count

			 
Scenario: Verify that guest count 
			Given user is on Home Page
           When User clicks on Buy Ticket CTA
         Then User add more junior ticket
         When User clicks on Add to Cart button on general pass adult ticket
         And User click on cart expand button
         Then User add more junior tickets and adult tickets
         And  User is able to edit guest count
         
  Scenario: Verify User is able to see two ticket opition
          And  User is able to see dated ticket option
          Then User is able to see Flexiable ticket option
          
 Scenario: Verify User is able to click flexible date and it should not be open calender overlay
          Then User is able to click flexible date
          And User is not able to see calender overlay
          Then Close browser
          

          