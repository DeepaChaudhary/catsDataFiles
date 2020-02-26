Feature: Login with Partner and Account Credit Payment

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with Partner and do payment with Account Credit
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B User click on Book Ticket CTA
    Then B2B User select Non dated ticket type
    And B2B User add single park ticket  
    And B2B User add AddOn Tickets
    Then B2B User check minicart is not empty
    And B2B User click on checkout button 
    Then B2B User add agent reference number
    And B2B User checks the terms and conditions checkbox for Account Credit Payment
    Then B2B User click for payment checkout with Account Credit
    Then B2B User check the payment confirmation page
    Then Close browser
    
    
    
    
    
   
    
    
    
    
    