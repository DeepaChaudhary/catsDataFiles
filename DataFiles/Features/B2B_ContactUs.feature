Feature: Contact Us for logged in User
  

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with partner and update its details and verify it
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    When B2B User click on ContactUs
    Then User select the contactUs reason
    And User write message for reason
    When B2B User click on Submit button
    Then B2B User get confirmation message
    Then Close browser
    
    
    
    
    
    
   
   
   