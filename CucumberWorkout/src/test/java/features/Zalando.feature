Feature: Zalando website workflow 

Scenario: To check the shopping in Zalando website and chatbot experience 

Given User goes to Zalando website 
And User gets the Alert text and print it
And User closes the Alert box and click on Zalando.uk
And User mouse hovers on FREE DELIVERY & RETURNS* get the text and print it
And User mouse hovers on Clothing and click Coat under WOMEN
And User chooses Material as cotton and Length as thigh-length
And User clicks the first result choose the color and size if available
And User gets the page title and match with the product brand, name, color and the page url
And User clicks Add to Bag and click on FREE DELIVERY & RETURNS* on the top of the page
And User clicks on Start chat in the Start chat and go to the new window
When User enters you first name and a dummy email and click Start Chat
Then User types Hi, click Send and print the reply message and close the chat window.