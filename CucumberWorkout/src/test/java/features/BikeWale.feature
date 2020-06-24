Feature: BikeWale Workflow 

Scenario: To compare the bikes and find the maximum rating from them 

Given User goes to BikeWale website 
And User goes to menu and click new bikes
And User clicks New Bikes Then compare bikes
And User adds first bike as Royal Enfield and model as Thunderbird 350
And User adds second bike Jawa, model as 42 and version Dual Channel ABS - BS VI
And User adds bike brand Kawasaki model as Ninja 300
When User clicks compare
Then User finds and print the maximum overall rating of all the bikes and find the max 