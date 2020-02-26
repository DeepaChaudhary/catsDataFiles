Feature: VerifyCookies
Background: User should be able to navigate home page
			 Given user is on Home Page
			 
Scenario:  Verify Cookies present only on home page
          #When User, click on accepts cookie button
          And   User click on Offer page 
		  Then User verify no cookies present in Offer page 
		  And User click on Contact Us page
		  Then User verify no cookies present in Contact Us page
		  Then Close browser