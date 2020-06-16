Feature: CarWale Workflow

Scenario: To Find Car with less KM usage 

Given User opens the Chrome browser 
And User launches the CarWale application 
And User maximises the browser 
And User clicks Used option 
And User selects city as Chennai 
And User selects budget min 8L and max 12L  
And User clicks Search  
And User selects Cars with Photos under Only Show Cars with from the search results 
And User selects Manufacturer as Hyundai and car as Creta 
And User selects Fuel type as Petrol 
And User sorts the results as KM: Low to High 
And User validates the Cars listed with KM Low to High and adds the least KM run car to Wishlist
And User goes to Wishlist and Click on More Details 
When User prints all the details under Overview in the Same way as displayed in application 
Then Close the browser. 