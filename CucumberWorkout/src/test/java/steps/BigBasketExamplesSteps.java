package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BigBasketExamplesSteps { 
	
	@Given("User goes to BigBasket application")
	public void launchBigBasket() {
	    driver.get
	}

	@And("User goes to Beverages and Fruit juices & Drinks")
	public void navigateBeveragesFruitJuices() { 
		
	   
	}

	@And("User clicks on JUICES")
	public void clickJuices() {
	    
	}

	@And("User clicks Tropicana and Real under Brand")
	public void clickTropicanaBrand() {
	    
	}

	@And("User checks count of the products from each Brands and total count")
	public void checkTotalCount() {
	    
	}

	@And("User checks whether the products is availabe with Add button.")
	public void validateAddButton() {
	    
	}

	@And("User adds the First listed available product")
	public void addFirstProduct() {
	    
	}

	@And("clicks on Change Address")
	public void clickChangeAddress() {
	    
	}

	@And("User selects Chennai as City, types (.*) and selects (.*) as Area  and click Continue")
	public void selectArea(String area, String areaName) {
	    
	}

	@And("User mouse hovers on My Basket print the product name count and price.")
	public void checkProductNamePrice() {
	    
	}

	@When("User clicks View Basket and Checkout")
	public void viewBasket() {
	    
	}

	@Then("User clicks the close button")
	public void clickCloseButton() {
	    
	}

}
