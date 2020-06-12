Feature: Shein Workflow 

Scenario: To do online shopping in the Shein Website 

Given User launches browser for Shein 
And User loads the application Shein 
And User mouse hovers on Clothing and click Jeans
And User chooses Black under Jeans product count 
And User checks size as medium 
And User clicks + in color 
And User checks whether the color is black 
And User clicks first item to Add to Bag 
And User clicks the size as M abd click Submit 
When User clicks view Bag 
Then User checks the size is Medium or not and closes the browser