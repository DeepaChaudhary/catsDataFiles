Feature: Social Icon Scenarios

Background: 
	Given user is on Home Page

#Scenario: YPS-6879_Verify that login with social id's ie Google+
#When User clicks on MyProfile link
#Then verify User redirects to Login page successfully
#Then User clicks on GoogleIcon on login page and it redirects to respective social site page
#When User enter valid credential for Google
#Then verify User Logged in
#Then Close browser 

Scenario: YPS-6801_Verify that login with social id's ie Facebook
When User clicks on MyProfile link
Then verify User redirects to Login page successfully
Then User clicks on facebookIcon on login page and it redirects to respective social site page
When User enter valid credential for facebook login
Then verify User Logged in
Then Close browser 