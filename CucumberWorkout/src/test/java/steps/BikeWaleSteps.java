package steps;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BikeWaleSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	WebDriverWait wait; 
	
	public BikeWaleSteps() {
		this.driver = HooksClass.getDriver(); 
	}

	@Given("User goes to BikeWale website")
	public void loadBikeWaleApplication() {
	    driver.get("https://www.bikewale.com/");
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User goes to menu and click new bikes")
	public void navigateNewBikes() {
	    driver.findElementByXPath("//div[@class='leftfloat']//span").click(); 
	    driver.findElementByXPath("//span[text()='New Bikes']").click();
	}

	@And("User clicks New Bikes Then compare bikes")
	public void clickCompareBikes() throws InterruptedException {
	    driver.findElementByXPath("//a[text()='Compare Bikes']").click(); 
	    Thread.sleep(3000);
	}

	@And("User adds first bike as Royal Enfield and model as Thunderbird 350")
	public void addFirstBike() throws InterruptedException {
	    driver.findElementByXPath("(//span[@class='box-label'])[1]//span[1]").click(); 
	    Thread.sleep(4000); 
	    
	    driver.findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[1]").click(); 
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[1]").sendKeys("Royal Enfield"); 
	    driver.findElementByXPath("//em[text()='Royal Enfield']").click(); 
	    
	    driver.findElementByXPath("(//select[@data-title='Select model']/parent::div//div)[1]").click();
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[2]").sendKeys("Thunderbird 350");
	    driver.findElementByXPath("(//em[text()='Thunderbird 350'])[1]").click();
	}

	@And("User adds second bike Jawa, model as 42 and version Dual Channel ABS - BS VI")
	public void addSecondBike() throws InterruptedException {
		driver.findElementByXPath("(//span[@class='box-label'])[2]//span[1]").click(); 
	    Thread.sleep(2000); 
	    
	    driver.findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[4]").click();
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[4]").sendKeys("Jawa");
	    driver.findElementByXPath("//em[text()='Jawa']").click(); 
	    
	    driver.findElementByXPath("((//select[@data-title='Select model'])[2]/parent::div//div)[1]").click(); 
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[5]").sendKeys("42"); 
	    driver.findElementByXPath("//em[text()='42']").click(); 
	    
	    driver.findElementByXPath("((//select[@data-title='Select version'])[2]/parent::div//div)[1]").click(); 
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[6]").sendKeys("Dual Channel ABS - BS VI"); 
	    driver.findElementByXPath("//em[text()='Dual Channel ABS - BS VI']").click();
	}

	@And("User adds bike brand Kawasaki model as Ninja 300")
	public void addThirdBike() throws InterruptedException {
	    driver.findElementByXPath("(//span[@class='box-label'])[3]//span[1]").click(); 
	    Thread.sleep(2000); 
	    
	    driver.findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[7]").click(); 
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[7]").sendKeys("Kawasaki");
	    driver.findElementByXPath("//em[text()='Kawasaki']").click(); 
	    
	    driver.findElementByXPath("(//select[@data-title='Select model'])[3]/following-sibling::div").click(); 
	    driver.findElementByXPath("(//div[@class='chosen-search']//input[@type='text'])[8]").sendKeys("Ninja 300"); 
	    driver.findElementByXPath("//em[text()='Ninja 300']").click(); 
	    Thread.sleep(2000);
	}

	@When("User clicks compare")
	public void clickCompare() throws InterruptedException {
	    driver.findElementById("btnCompare").click();
	    Thread.sleep(3000);
	}

	@Then("User finds and print the maximum overall rating of all the bikes and find the max")
	public void printMaxOverallRatingBike() throws InterruptedException {
	    
		js = (JavascriptExecutor) driver; 
		
		js.executeScript("window.scrollBy(0, 100)");
		
		driver.findElementByXPath("//div[@id='overall-tabs']//li[text()='Reviews']").click(); 
		Thread.sleep(2000); 
		
		js.executeScript("window.scrollBy(0 , -50)"); 
		
		Map<String, Double> bikeRatingMap = new LinkedHashMap<String, Double>(); 
		
		List<WebElement> bikeList = driver.findElementsByXPath("//div[@class='bike-details-block']//a[@class='item-target-link underline-none ']");
		
		List<WebElement> ratingList = driver.findElementsByXPath("//td[text()='Overall rating']/parent::tr//span[@class='font20 font-bold']");
		
		for(int i = 0; i < bikeList.size(); i++) { 
			String bikeName = bikeList.get(i).getAttribute("title"); 
			String ratingStr = ratingList.get(i).getText(); 
			double rating = Double.parseDouble(ratingStr); 
			
			bikeRatingMap.put(bikeName, rating); 
		}
		
		System.out.println("\nSelected Bikes and their overall ratings:"); 
		
		// Printing the Map in Lambda expression 
		bikeRatingMap.entrySet().forEach(each -> {System.out.println(each.getKey() + " --- " + each.getValue());});
		
		// Sorting the Map here using Stream 
		LinkedHashMap<String, Double> sortedMap = bikeRatingMap.entrySet()
		.stream()
		.sorted(Map.Entry.comparingByValue())
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); 
		
		System.out.println("\nMap after sorting:");
		for (Map.Entry<String, Double> each : sortedMap.entrySet()) {
			System.out.println(each.getKey() + " " + each.getValue());
		} 
		
		
		// Getting the Max value of Map using Stream 
		Optional<Entry<String, Double>> maxValue = sortedMap.entrySet() 
		.stream() 
		.max(Comparator.comparing(Map.Entry::getValue));
		
		System.out.println("\nMaximum rating of all bikes: " + maxValue);
		System.out.println("Bike having maximum rating: " + maxValue.get().getKey());
		
		
	}
	
}
