Feature: Registration
 

  Background: 
     Given user is on Home Page

  Scenario: Verify user is able to register 
        When YWW User clicks on REGISTER NOW link
        Then User redirects to registration page
        When User enter below valid details for registration
        And YWW User click on submit button for registration
