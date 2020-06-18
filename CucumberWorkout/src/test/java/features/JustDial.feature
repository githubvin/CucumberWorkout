Feature: JustDial Workflow 

Scenario: To check the Air Tickets fare details in JustDial website 

Given User launches the JustDial website 
And User clicks on Air Tickets
And User types Chennai and choose Chennai, IN - Chennai Airport (MAA) as Leaving From 
And User types Toronto and select Toronto, CA - Toronto City Centre Airport (YTZ) as Going To
And User sets Departure as 2020, July 22
And User adds Adult 2, Children 1 click and Search
And User selects Air Canada from multi-airline itineraries
And User clicks on Price to sort the result
And User clicks on +Details of first result under Price
And User captures the Flight Arrival times.
And User captures the total price in a list and Click on Book
When User captures the Airport name base on the list of time
Then User captures the total fare and print the difference amount from previous total price