Feature: Trivago Workflow 

Scenario: To book a Hotel in Agra good Recommendation and Rating 4  

Given User launches the browser 
And User loads the application Url 
And User types Agra as destination 
And User selects the checkin date as June 15 and checkout date as June 30 
And User selects Family Room 
And User selects Adults Children numbers and Age 
And User clicks confirm button and click search 
And User selects Accommodation type as Hotels and choose 4 stars 
And User selects Guest rating as Very Good 
And User sets Hotel Location as Agra Fort and click Done 
And User selects Air Conditioning, Restaurant and WiFi in Filters and click Done 
And User sorts result as Rating and Recommended 
And User prints the Hotel name, Rating, Number of Reviews and Clicks View Deal 
And User prints the URL of the page 
When User prints the price of the Room and clicks Choose your Room 
Then User clicks Reserve and I'll Reserve and closes the Browser  