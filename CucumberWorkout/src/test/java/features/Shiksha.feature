Feature: Shiksha Workflow 

Scenario: To check the Engineering colleges with lowest fees 

Given User goes to Shiksha Website 
And User mouse hovers on Colleges and click MS in Computer Science &Engg under MS Colleges
And User clicks Change course/country select box, choose course as BE/Btech and Choose specialization as Computer Science & Engineering
And User selects Study destination as USA, UK, Canada and click Update
And User filters Select IELTS and score as 7.5 & Below in Exam Accepted
And User clicks Total Fees as Max 20L
And User captures the college Names and fees only if it is Engineering  course 
And User takes 20 colleges by Click Next button and go to next page.
When User searches the college name in the search box based on low fees
Then User matches the IELTS score, course Title and country from the University Page