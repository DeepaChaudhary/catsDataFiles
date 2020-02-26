Feature: Verify that Logged in(with Email+)user is able to buy  15th day (current day + 15) 
         + General admission(Adult tickets + Junior Tickets) with 15% discount 
         + Extras: F&B Add Ons(Combo 1(Pepproni Pizza family meal+4 Reg Soft Drinks)

Scenario Outline:  user add General Admission Ticket and Extras
         Given user is on Home Page   
         When User clicks on MyProfile link
         And User enters emailid
         And User enters password
		 And user click on submit button
         When User clicks on Buy Ticket CTA
         Then User empty minicart if product were there
         And user add "2" adult and "2" junior
         Then user add ticket for <day>
         Then user add GA ticket to cart
         Then user expand minicart if not expanded
         Then User verify discount for <day>
         When user click on extras tab
         Then Add extras ticket to cart
         Then Close browser
Examples:
         |day|
         | 15 |
     
    
         
         
         
         
         




         
         