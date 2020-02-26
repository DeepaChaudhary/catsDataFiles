Feature: Wishlist_verify
Background: User should be able to navigate home page
			 Given user is on Home Page
			 
Scenario:  Verify Wishlist item
          Given User, click on wishlist icon
          And   User verify wishlist is empty
		  Then User click on hotels navigation link
		  And User click on hotels wishlist icon
		  And User verify wishlist on wishlist page
		  Then Close browser