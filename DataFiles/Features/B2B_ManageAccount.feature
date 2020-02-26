Feature: Create Operator and Agent with the login of Partner
       

  Background: 
       Given user is on B2B Home Page

  Scenario: Login with Partner and Create Operator
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B user clicks on Manage Account button present under Profile icon
    Then B2B user click on Add user button
    And B2B user fill all information to create an Operator
    Then B2B User click on Save button
    And B2B User verify new operator or agent is created
    Then B2B User logout
    Then Close browser
    
    Scenario: Login with Partner and Create Agent
    Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    Then B2B User verify dashboard
    When B2B user clicks on Manage Account button present under Profile icon
    Then B2B user click on Add user button
    And B2B user fill all information to create an Agent
    Then B2B User click on Save button
    And B2B User verify new operator or agent is created
    Then B2B User logout
    Then Close browser
     
     
     
     
     
     
     
     
     