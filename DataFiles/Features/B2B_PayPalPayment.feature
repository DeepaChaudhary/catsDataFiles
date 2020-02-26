Feature: Login with Operator and PayPal Payment

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with Operator and do payment with PayPal
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login with Operator 
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B User click on Book Ticket CTA
    Then B2B User select Non dated ticket type
    And B2B User add MultiPark ticket  
    Then B2B User check minicart is not empty
    And B2B User click on checkout button 
    Then B2B User add agent reference number
    And B2B User checks the terms and conditions checkbox for Paypal
    Then B2B User pay with PayPal
    Then B2B User check the payment confirmation page 
    Then Close browser
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    