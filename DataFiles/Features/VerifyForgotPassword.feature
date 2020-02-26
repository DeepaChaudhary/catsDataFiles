Feature: verify ForgotPassword

Background: User should be able to navigate home page
			 Given user is on Home Page
			 
Scenario:  Verify forgot password
          When User clicks on MyProfile link
          Then User click on forgot Password
          And User enters valid  emailid for resetpassword
          And user click on continue button
          Then user able to see reset comform mail send
          Then Close browser