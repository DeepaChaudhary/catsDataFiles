Feature: User Login and Password change

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with partner and update its details and verify it
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login for password Change
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    When B2B user clicks on My Profile button present under Profile icon
    When B2B User clicks on Change Password tab
    When B2B User enters new password 
    And B2B User clicks on submit button to save new password
    When B2B user logs out
    Then B2B user logs in again
 #   When B2B User close the notification on Homepage
    When B2B user clicks on My Profile button present under Profile icon
    When B2B User clicks on Change Password tab
    When B2B User reset password 
    And B2B User clicks on submit button to save new password
   Then Close browser
   
   
   
   
    
    
    
    
    
    
    
   
   
    
    