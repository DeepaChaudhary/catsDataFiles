Feature: FQA verify

Background: User should be able to navigate home page
			 Given user is on Home Page
			 
Scenario:  Verify Fqa senario
#          When User clicks on MyProfile link
#          And User enters emailid
#         And User enters password
#         Then user click on submit button
#         And User clicks on MyProfile link
#         Then User click on logout button
         And User click on Faq link
         Then User click on FAQ section 
         And User verify FAQ section present 
         Then Close browser