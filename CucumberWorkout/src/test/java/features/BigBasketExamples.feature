Feature: BigBasket Workflow with Examples 

Background: 
Given User goes to BigBasket application
And User mouse hovers on  Shop by Category   
And User goes to Beverages and Fruit juices & Drinks 
And User clicks on JUICES
And User clicks Tropicana and Real under Brand
And User checks count of the products from each Brands and total count
And User checks whether the products is availabe with Add button.
And User adds the First listed available product 

Scenario Outline: To check the shopping in BigBasket with different locations. 

Given User clicks on Change Address 
And User selects Chennai as City, types <area> and selects <areaName> as Area  and click Continue
And User mouse hovers on My Basket print the product name count and price.
When User clicks View Basket and Checkout
Then User clicks the close button 

Examples: 
|area		|areaName								| 
|Mambalam	|Mambalam High Road-600017,Chennai		|
|CIT Nagar	|CIT Nagar East-600035,Chennai			| 
|Chromepet	|Ganapathipuram-Chromepet-600044,Chennai|