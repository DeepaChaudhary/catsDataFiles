Feature: Product Listing Page scenarios


  Background: 
    Given user is on Home Page
#------------------------------------1-Drive---------------------------------------------------------
  Scenario: Verify PJA scenario
  #When click Buy Tickets Button on homepage
  When click on smart carousel image on homepage
  Then Verify User navigate to event listing page
  When User Select the date and time and click on buy ticket button on event listing page
  Then verify I understand Overlay should open and click on I understand Button
  Then Verify User navigate to buy ticket page
  When User choose any sector selection 
  And Click on radio button to select Ticket type
  And click on choose your seat button
  And Verify User Navigate to confirm seat page and click on confirm seat button
  And user verifies the ticket amount listed on cart
  And User Click on proceed to pay Button
  When user enter your details at my payment page
   When user enter payment information with credit card
#  When User enter payment information with credit card
  And select terms and condition checkbox
	And click on Pay Now button
	Then user redirected to payment confirmation page
#  Then verify redirected to payment confirmation page
  Then Close browser
  
  
  Scenario: Verify change link scenario
  #When click Buy Tickets Button on homepage
  When click on smart carousel image on homepage
  Then Verify User navigate to event listing page
  When User Select the date and time and click on buy ticket button on event listing page
  Then verify I understand Overlay should open and click on I understand Button
  Then Verify User navigate to buy ticket page
  When User choose any sector selection 
  And Click on radio button to select Ticket type
  And click on choose your seat button
  And Verify User Navigate to confirm seat page and click on confirm seat button
  And user verifies the ticket amount listed on cart
  Then Verify user is able to view and click Change link
   And To make changes user click on Yes Button
 # And User Click on proceed to pay Button
  #When User Click on arrow down to view ticket details
  Then Verify User navigate to buy ticket page
  When User choose any sector selection 
  And Click on radio button to select Ticket type
  And click on choose your seat button
  And Verify User Navigate to confirm seat page and click on confirm seat button
  And user verifies the ticket amount listed on cart
  And User Click on proceed to pay Button
  When user enter your details at my payment page
  When user enter payment information with credit card
  #When User enter payment information with credit card
  And select terms and condition checkbox
	And click on Pay Now button
		Then user redirected to payment confirmation page
 # Then verify redirected to payment confirmation page
  Then Close browser
  
 
  

  