Feature: Verify that Logged in(with Email+)user is able to buy 15th day (current day + 15) 
           General admission 2 Days 2 Parks (YWW, FWAD)  (Adult tickets + Junior Tickets) with 15% discount 
        +  10th day (current day + 9) General admission 1 Days 2 Parks


Scenario Outline:  user add two types of General Admission Ticket
         Given user is on Home Page   
         When User clicks on MyProfile link
         And User enters emailid
         And User enters password
		 And user click on submit button
         When User clicks on Buy Ticket CTA
         Then User empty minicart if product were there
         And user add "2" adult and "2" junior
         Then user add ticket for <day>
         Then User select the <type1> of General Admission ticket
         Then user expand minicart if not expanded
         Then user verify the details in MiniCart for <MiniCartTwoDay> for <day>
         And user add "2" adult and "2" junior
         Then user add ticket for <day1>
         Then User select the <type2> of General Admission ticket
         Then user verify the details in MiniCart for <MiniCartOneDay> for <day1>
          Then user verify total price in minicart
          Then Close browser
Examples:
         |day|day1|type1|type2|MiniCartOneDay|MiniCartTwoDay|
         | 15 |10|2 Day Any 2 Parks|1 Day Any 2 Parks|1 Day Any 2 Parks|2 Days Any 2 Parks|
       
Scenario Outline:  user add MutipPark General Admission Ticket and Annual Pass Ticket
         Given user is on Home Page   
         When User clicks on MyProfile link
         And User enters emailid
         And User enters password
		 And user click on submit button
         When User clicks on Buy Ticket CTA
         Then User empty minicart if product were there
         And user add "2" adult and "2" junior
         Then user add ticket for <day>
         Then User select the <type> of General Admission ticket
         Then user expand minicart if not expanded
         Then user verify the details in MiniCart for <MiniCartOneDay> for <day>
         When User clicks on Annual Passes
		 Then User select the <AnnualType1> of Annual Pass ticket
		 Then user add "2" Annual Passes to cart
		 Then user validate the price in product itself
         Then user add Annual Pass ticket to cart
         Then user expand minicart if not expanded
         Then user verify details for <MiniCartGiftVoucher> for annual Pass in Minicart 
         Then User select the <AnnualType2> of Annual Pass ticket
		 Then user add "1" Annual Passes to cart
		 Then user validate the price in product itself
         Then user add Annual Pass ticket to cart
         Then user expand minicart if not expanded
         Then user verify details for <MiniCartPlatinumPass> for annual Pass in Minicart         
         Then Close browser
Examples:
         |day|type|AnnualType1|AnnualType2|MiniCartOneDay|MiniCartGiftVoucher|MiniCartPlatinumPass|
         | 15 |1 Day Any 2 Parks|Yas Park Annual Pass Gift Voucher|Platinum|1 Day Any 2 Parks|Yas Parks Annual Pass Gift Voucher|Yas Parks Annual Platinum Pass|
         | 4 |1 Day Any 2 Parks|Yas Park Annual Pass Gift Voucher|Platinum|1 Day Any 2 Parks|Yas Parks Annual Pass Gift Voucher|Yas Parks Annual Platinum Pass|
    
    
    Scenario Outline:  user add MutipPark General Admission Ticket, Flexible GA Ticket and Annual Pass Ticket
         Given user is on Home Page   
         When User clicks on MyProfile link
         And User enters emailid
         And User enters password
		 And user click on submit button
         When User clicks on Buy Ticket CTA
         Then User empty minicart if product were there
         And user only add "2" adults only
         Then user add Flexible GA ticket
         Then user expand minicart if not expanded 
         Then User verify discount for <dayForFlexibleTicket>
         And user add "2" adult and "2" junior
         Then user add ticket for <day>
         Then User select the <type> of General Admission ticket
         Then user expand minicart if not expanded 
         Then user verify the details in MiniCart for <MiniCartTwoDay> for <day>
         When User clicks on Annual Passes
		 Then User select the <AnnualType1> of Annual Pass ticket
		 Then user add "2" Annual Passes to cart
         Then user add Annual Pass ticket to cart
         Then user expand minicart if not expanded 
         Then user verify details for <MiniCartGiftVoucher> for annual Pass in Minicart
         Then User select the <AnnualType2> of Annual Pass ticket
		 Then user add "1" Annual Passes to cart
         Then user add Annual Pass ticket to cart
         Then user expand minicart if not expanded 
         Then user verify details for <MiniCartPlatinumPass> for annual Pass in Minicart
         Then Close browser
Examples:
         |day|type|AnnualType1|AnnualType2|dayForFlexibleTicket|MiniCartTwoDay|MiniCartGiftVoucher|MiniCartPlatinumPass|
         | 4 |2 Day Any 2 Parks|Yas Park Annual Pass Gift Voucher|Platinum|0|2 Days Any 2 Parks|Yas Parks Annual Pass Gift Voucher|Yas Parks Annual Platinum Pass|
       
        
       
       
    
    
         
         
         
         
         
         
         




         
         