Feature: Verify that for General Admission tab,
       -if user selects date 0-3 days from current date then there will be no discount, 
       -if user selects 4-14 days then there will be 10% discount 
       -if user selects date more than 15 days then there will be 15% discount app


Scenario Outline:  Verify that if user selects date from current date then there will be discount aplied according to date 
         Given user is on Home Page   
         When User clicks on MyProfile link
         And User enters emailid
         And User enters password
		 And user click on submit button
         When User clicks on Buy Ticket CTA
         Then User empty minicart if product were there
         Then user add ticket for <day>
         Then user add GA ticket to cart
         Then user expand minicart if not expanded
         Then User verify discount for <day>
         Then Close browser
Examples:
         |day|
         | 0 |
         | 2 |
         | 3 |
         | 14 |
         | 15 |
    
    
         


         
         