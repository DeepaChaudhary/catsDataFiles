Feature: Login with Agent and Credit Card Payment

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with Agent and do payment with Credit Card
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login with Agent 
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B User click on Book Ticket CTA
    Then B2B User select dated ticket type
    And B2B User add single park ticket  
    Then B2B User check minicart is not empty
    And B2B User click on checkout button 
    Then B2B User fill credit card detail
    And B2B User checks the terms and conditions checkbox
    Then B2B User add agent reference number
    Then B2B User click for payment checkout
    Then B2B User check the payment confirmation page 
    Then Close browser
    
    
    
    
    
    
    
    
    