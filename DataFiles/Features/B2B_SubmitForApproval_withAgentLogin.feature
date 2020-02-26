Feature: Login with Agent and Submit for Approval and Verify it 

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with Agent and Submit for approval purchase order and approve the order with partner login
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login with Agent without Purchase Rights
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B User click on Book Ticket CTA
    Then B2B User select dated ticket type
    And B2B User add single park ticket  
    And B2B User add Summer Offers Tickets
    Then B2B User check minicart is not empty
    And B2B User click on checkout button 
    Then B2B User add agent reference number
    And B2B User checks the terms and conditions checkbox for submitting request for approval
    Then B2B User click for payment checkout with Account Credit
    Then B2B User check the payment confirmation page for submit order request
    When B2B user logs out
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B user clicks on MyOrders button present under Profile icon
    Then B2B User checks the status of pending order
    Then B2B User fill credit card detail
    And B2B User checks the terms and conditions checkbox
    Then B2B User click for payment checkout
    Then B2B User check the payment confirmation page 
    When B2B user logs out
    Then Close browser
    
  Scenario: Login with Agent and Submit for approval purchase order and decline the order with partner login
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login with Agent without Purchase Rights
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B User click on Book Ticket CTA
    Then B2B User select dated ticket type
    And B2B User add single park ticket  
    Then B2B User check minicart is not empty
    And B2B User click on checkout button 
    Then B2B User add agent reference number
    And B2B User checks the terms and conditions checkbox for submitting request for approval
    Then B2B User click for payment checkout with Account Credit
    Then B2B User check the payment confirmation page for submit order request
    When B2B user logs out
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B user clicks on MyOrders button present under Profile icon
    Then B2B User checks the status of pending order
    And B2B User decline the order 
    Then B2B User check the order status in declined order
    Then Close browser
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    