Feature: Add all tabs product in FWAD

Scenario Outline: User add one ticket from each tab 
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
        When User clicks on Annual Passes
		 Then User select the <AnnualType1> of Annual Pass ticket
		 Then user add "1" Annual Passes to cart
         Then user add Annual Pass ticket to cart
         When user click on extras tab
         Then Add extras ticket to cart
         When user click on experiences tab 
         Then Add ticket to cart for Experiences or Tour
         When user click on offers tab 
         Then User select the Offers ticket  
		 Then user add "2" Offers Ticket to cart
         Then user add date and ticket to cart
          When user click on Check out button on mini cart
         When user enter payment information with credit card
         And user is able to enter details of guest form 
        When user enter payment information with credit card
		 And select terms and condition checkbox
		 And click on Pay Now button
		 Then user redirected to payment confirmation page
         Then Close browser
Examples:
         |day|AnnualType1|MiniCartGiftVoucher|
         | 0 |Yas Park Annual Pass Gift Voucher|Yas Parks Annual Pass Gift Voucher|
       
         
         	        
		 
   	   
   	   
   	   
   	   
   	   
   	   
   	   
   	   