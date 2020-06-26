Feature: Naukri website exercise 

Scenario: To retrieve the company name from the popup coming when launching Naukri website 

Given User opens Naukri website 
And User gets the company names from each pop up window and close it 
When User now clicks on the upload cv button and upload some random image 
Then User gets the error message printed