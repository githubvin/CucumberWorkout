Feature: Lenskart Workflow 

Scenario: To check the price of a particular brand in Lenskart website 

Given User goes to Lenskart application 
And User mouse hovers on Contact Lenses 
And User clicks on Monthly under Explore By Disposability
And User selects brand as Aqualens
And User clicks on the first product
And User clicks Buy Now
And User selects No of boxes as 2 and Power as -1 for both eyes.
And User types name in User's name 
When User clicks Save and continue
Then User prints total amount and click Proceed to Checkout 