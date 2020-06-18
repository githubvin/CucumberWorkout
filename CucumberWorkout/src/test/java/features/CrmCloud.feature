Feature: Demo CRM Cloud Workflow 

Scenario: To schedule a meeting for a Contact available in the CRM 

Given User goes to CRM Cloud application
And User gives username as admin and password as admin
And User chooses theme as Claro Theme and Login 
And User goes to Sales and Marketting and click Sales Home
And User clicks Create contact
And User selects Title and type First name, Last Name, Email and Phone Numbers
And User selects Lead Source as Public Relations and Business Roles
And User fills the Primary Address, City, State, Country and Postal Code and click Save
And User clicks create and Leads
And User fills First & Last name, Status as In Process, Title as Manager and Department as Sales
And User selects Lead as Public Relations and fill department, Email and Phone Number
And User clicks Save and View
And User mouse hovers on Today's Activities and click Meetings
And User types Subject as Project Status Status as Planned and Time as tomorrow 3 p.m
And User clicks Add paricipants, add your created Lead name and click Save
When User clicks contacts under Sales and Marketing, search the lead Name and click the name from the result
Then User checks whether the Meeting is assigned to the contact. 