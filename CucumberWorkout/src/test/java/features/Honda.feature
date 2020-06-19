Feature: Honda Workflow 

Scenario: To inquire the Price quotation for Honda scooter 

Given User goes to Honda two wheelers website 
And User clicks on scooters and click dio
And User clicks on Specifications and mouseover on Engine
And User puts all the details as key and value into Map
And User goes to Scooters and click Activa 125
And User puts All its Engine Specification into another Map same as like dio
And User compares Dio and Activa Maps and print the different values of the samekeys.
And User clicks FAQ from Menu and Click dio under Browse By Product
And User clicks  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit
And User clicks the price link,  Go to the new Window and select the state, city
And User prints the price and model 
When User clicks Product Enquiry and Fill all the * field except Mobile, check the terms and conditions box and click submit
Then User verifies the error message under the mobile number field.