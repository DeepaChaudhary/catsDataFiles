Feature: User Login, update and verify my profile changes
  - Verify user details on profile page

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with partner and update its details and verify it
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    When B2B user clicks on My Profile button present under Profile icon
    When B2B User update all the details present in profile info page with valid details
    When B2B user logs out
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B user clicks on My Profile button present under Profile icon
    Then B2B user validate information
    When B2B User reset all the details present in profile info page with valid details
   Then Close browser
   
    
    
    
    
    
    
    
    
   
   
    
    