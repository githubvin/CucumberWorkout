package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BigBasketExamplesSteps extends HooksClass { 
	
	Actions act; 
	JavascriptExecutor js; 
	
	@Given("User goes to BigBasket application")
	public void launchBigBasket() {
	    driver.get("https://www.bigbasket.com/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User goes to Beverages and Fruit juices & Drinks")
	public void navigateBeveragesFruitJuices() throws InterruptedException { 
		act = new Actions(driver);
		act.moveToElement(driver.findElementByXPath("//a[contains(text(),'Shop by')]")).build().perform(); 
		Thread.sleep(2000);
		act.moveToElement(driver.findElementByXPath("//a[contains(text(),'Beverages')]")).build().perform(); 
		act.click(driver.findElementByXPath("//a[contains(text(),'Fruit Juices & Drinks')]")).build().perform();
		Thread.sleep(3000);
	   
	}

	@And("User clicks on JUICES")
	public void clickJuices() {
	    driver.findElementByXPath("(//span[text()='Juices'])[1]").click();
	}

	@And("User clicks Tropicana and Real under Brand")
	public void clickTropicanaBrand() throws InterruptedException {
	    driver.findElementByXPath("(//input[@placeholder='Search by Brand'])[1]").sendKeys("Tropicana"); 
	    act.click(driver.findElementByXPath("(//span[text()='Tropicana'])[1]")).build().perform(); 
	    Thread.sleep(2000); 
	    driver.findElementByXPath("(//input[@placeholder='Search by Brand'])[1]").clear();
	    driver.findElementByXPath("(//input[@placeholder='Search by Brand'])[1]").sendKeys("Real");
	    act.click(driver.findElementByXPath("(//span[text()='Real'])[1]")).build().perform(); 
	    Thread.sleep(2000);
	}

	@And("User checks count of the products from each Brands and total count")
	public void checkTotalCount() throws InterruptedException {
	    List<WebElement> prodList = driver.findElementsByXPath("//div[@qa='product_name']"); 
	    System.out.println("\nTotal number of Products listed: " + prodList.size());
	    
	    int realCount = 0; 
	    int tropicanaCount = 0; 
	    for (WebElement eachElement : prodList) {
			String prodName = eachElement.getText(); 
			if (prodName.equalsIgnoreCase("Tropicana")) {
				tropicanaCount++; 
			} else {
				realCount++; 
			}
		} 
	    
	    System.out.println("Brand Real count: " + realCount); 
	    System.out.println("Brand Tropicana: " + tropicanaCount); 
	    Thread.sleep(2000);
	}

	@And("User checks whether the products is availabe with Add button.")
	public void validateAddButton() throws InterruptedException {
		List<WebElement> notifyList = driver.findElementsByXPath("//button[@qa='NM']"); 
		try {
			System.out.println("Product without Add button: " + notifyList.size());
		} catch (Exception e) {
			System.out.println("Add Button available for all products.");
			e.printStackTrace();
		} 
		Thread.sleep(2000);
	}

	@And("User adds the First listed available product")
	public void addFirstProduct() {
	    driver.findElementByXPath("(//button[@qa='add'])[1]").click(); 
	    try {
			driver.findElementByClassName("toast-close-button").click();
			System.out.println("Success message closed.");
		} catch (Exception e) {
			System.out.println("Top success message not closed.");
			e.printStackTrace();
		}
	}

	@And("clicks on Change Address")
	public void clickChangeAddress() {
		js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,-100)"); 
		driver.findElementByXPath("//span[contains(@ng-bind, 'address_display_name')]").click();
	}

	@And("User selects (.*) as City, selects (.*) and click Continue")
	public void selectArea(String city, String area) throws InterruptedException {
		driver.findElementByXPath("(//span[text()='Select your city']/parent::span)[1]").click(); 
	    driver.findElementByXPath("//input[@placeholder='Select your city']").sendKeys(city); 
	    driver.findElementByXPath("//a[@class='ui-select-choices-row-inner']//span[text()='"+city+"']").click(); 
	    driver.findElementByXPath("//input[@qa='areaInput']").sendKeys(area); 
	    driver.findElementByXPath("(//strong[text()='"+area+"'])[1]").click();
	    driver.findElementByName("continue").click(); 
	    Thread.sleep(2000);
	}

	@And("User mouse hovers on My Basket print the product name count and price.")
	public void checkProductNamePrice() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).build().perform(); 
	    Thread.sleep(2000);
	    act.moveToElement(driver.findElementByXPath("//a[@qa='prodNameMB']")).build().perform(); 
	    String basketProduct = driver.findElementByXPath("//a[@qa='prodNameMB']").getText(); 
	    System.out.println("Product Name in the Basket: " + basketProduct);
	}

	@When("User clicks View Basket and Checkout")
	public void viewBasket() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("//button[text()='View Basket & Checkout']")).build().perform();
		act.click(driver.findElementByXPath("//button[text()='View Basket & Checkout']")).build().perform();
	    Thread.sleep(3000);
	}

	@Then("User clicks the close button")
	public void clickClosePopup() throws InterruptedException {
		driver.findElementByXPath("//button[@ng-click='dismiss()']").click(); 
	    Thread.sleep(2000);
	}

}