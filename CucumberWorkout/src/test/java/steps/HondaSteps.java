package steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HondaSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	Map<String, String> dioEngineMap; 
	Map<String, String> activaEngineMap; 
	
	public HondaSteps() {
		this.driver = HooksClass.getDriver(); 
	} 
	
	@Given("User goes to Honda two wheelers website")
	public void loadHondaApplication() {
	    driver.get("https://www.honda2wheelersindia.com/");
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	    
	    try {
			driver.findElementByXPath("//button[@data-dismiss='modal']").click();
		} catch (Exception e) {
			System.out.println("Lockdown popup not displayed.");
			e.printStackTrace();
		}
	}

	@And("User clicks on scooters and click dio")
	public void clickDio() throws InterruptedException {
		driver.findElementByLinkText("Scooter").click(); 
		Thread.sleep(2000);
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click(); 
		Thread.sleep(3000);
	}

	@And("User clicks on Specifications and mouseover on Engine")
	public void getSpecificationAndEngineDetails() throws InterruptedException {
		driver.findElementByLinkText("Specifications").click(); 
		Thread.sleep(3000); 

		act = new Actions(driver); 

		act.moveToElement(driver.findElementByLinkText("ENGINE")).build().perform(); 
	}

	@And("User puts all the details as key and value into Map")
	public void storeDioEngineDetailsMap() {
	    
		dioEngineMap = new LinkedHashMap<String, String>(); 
		
		List<WebElement> engineAttRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[1]");
	    
		List<WebElement> engineValuesRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[2]");
		
	    // For Engine Attributes and its values the index is not matching to get the values. So fetching values separately. 
	    
	    List<String> engineAttributes = new ArrayList<String>();  
	    for(int i = 1; i < engineAttRaw.size(); i++) { 
	    	engineAttributes.add(engineAttRaw.get(i).getText()); 
	    } 
	    
	    List<String> engineValues = new ArrayList<String>(); 
	    for(int j = 0; j < engineValuesRaw.size(); j++) {
	    	engineValues.add(engineValuesRaw.get(j).getText()); 
	    } 
	    
	    // Merging two lists into Map 
	    
	    for(int i = 0; i < engineAttributes.size(); i++) {
	    	dioEngineMap.put(engineAttributes.get(i), engineValues.get(i)); 
	    }
	    
	    System.out.println("\nScooter Dio Engine Specifications:");
	    for (Entry<String, String> eachEntry : dioEngineMap.entrySet()) {
			System.out.println(eachEntry.getKey() + "---" + eachEntry.getValue());
		}
	    
	    // Merging two lists into Map using Iterator 
		/*
		 * Iterator<String> it1 = engineAttributes.iterator(); 
		 * Iterator<String> it2 = engineValues.iterator();
		 * 
		 * while (it1.hasNext() || it2.hasNext()) 
		 * dioEngineMap.put(it1.next(), it2.next()); 
		 * if (it1.hasNext() || it2.hasNext()) 
		 * throw new IllegalArgumentException("Keys and values collections have not the same size");
		 * 
		 * 
		 */
	    
	    
	}

	@And("User goes to Scooters and click Activa 125")
	public void clickActiva() throws InterruptedException {
		driver.findElementByLinkText("Scooter").click(); 
		Thread.sleep(3000);
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click(); 
		Thread.sleep(5000);
	}

	@And("User puts All its Engine Specification into another Map same as like dio")
	public void storeActivaEngineDetailsMap() throws InterruptedException { 
		
		driver.findElementByLinkText("Specifications").click(); 
		Thread.sleep(2000); 

		act = new Actions(driver); 

		act.moveToElement(driver.findElementByLinkText("ENGINE")).build().perform(); 

		activaEngineMap = new LinkedHashMap<String, String>(); 

		List<WebElement> engineAttRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[1]");

		List<WebElement> engineValuesRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[2]");

		// For Engine Attributes and its values the index is not matching to get the values. So fetching values separately. 

		List<String> engineAttributes = new ArrayList<String>();  
		for(int i = 1; i < engineAttRaw.size(); i++) { 
			engineAttributes.add(engineAttRaw.get(i).getText()); 
		} 

		List<String> engineValues = new ArrayList<String>(); 
		for(int j = 0; j < engineValuesRaw.size(); j++) {
			engineValues.add(engineValuesRaw.get(j).getText()); 
		} 

		// Merging two lists into Map 

		for(int i = 0; i < engineAttributes.size(); i++) {
			activaEngineMap.put(engineAttributes.get(i), engineValues.get(i)); 
		}

		System.out.println("\nScooter Activa 125 Engine Specifications:");
		for (Entry<String, String> eachEntry : activaEngineMap.entrySet()) {
			System.out.println(eachEntry.getKey() + "---" + eachEntry.getValue());
		}
	    
		
	}

	@And("User compares Dio and Activa Maps and print the different values of the samekeys.")
	public void compareDioActiva() {
	    
		MapDifference<String, String> difference = Maps.difference(dioEngineMap, activaEngineMap); 
		
		// This line is to get the difference of Keys 
		//Set<String> diffKey = difference.entriesDiffering().keySet(); 
		
		// This line is to get the difference of Values 
		//Collection<ValueDifference<String>> diffValues = difference.entriesDiffering().values(); 
		
		// Printing difference of both key and values
		Map<String, ValueDifference<String>> differing = difference.entriesDiffering();
		
		System.out.println("\nDifference in two specifications:");
		// Printing the difference in Lambda 
		differing.entrySet().forEach(each -> {System.out.println(each.getKey() + "---" + each.getValue());});
		
	}

	@And("User clicks FAQ from Menu and Click dio under Browse By Product")
	public void navigateDioFAQ() throws InterruptedException {
	    driver.findElementByLinkText("FAQ").click();
	    Thread.sleep(2000);
	    driver.findElementByXPath("//a[text()='Dio BS-VI']").click(); 
	    Thread.sleep(2000);
	}

	@And("User clicks  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit")
	public void checkVehiclePrice() {
	    driver.findElementByXPath("//a[contains(text(),'Vehicle Price')]").click(); 
	    
	    WebElement vehicleElement = driver.findElementByName("SegmentID"); 
	    Select vehicleOptions = new Select(vehicleElement); 
	    vehicleOptions.selectByVisibleText("Scooter"); 
	    
	    WebElement modelElement = driver.findElementByName("ModelID"); 
	    Select modelOptions = new Select(modelElement); 
	    modelOptions.selectByVisibleText("Dio BS-VI");
	    
	    driver.findElementByXPath("//button[@id='submit6']").click();
	}

	@And("User clicks the price link,  Go to the new Window and select the state, city")
	public void selectCity() throws InterruptedException {
	    driver.findElementByXPath("//a[contains(text(),'Click here to know the price')]").click();
	    Thread.sleep(3000); 
	    
	    Set<String> winSet = driver.getWindowHandles(); 
	    List<String> winList = new ArrayList<String>(winSet); 
	    int size = winList.size(); 
	    driver.switchTo().window(winList.get(size-1)); 
	    
	    WebElement stateElement = driver.findElementByName("StateID"); 
		Select stateOptions = new Select(stateElement); 
		stateOptions.selectByValue("28"); 
		
		WebElement cityElement = driver.findElementByName("CityID"); 
		Select cityOptions = new Select(cityElement); 
		cityOptions.selectByValue("1568"); 
	    
		driver.findElementByXPath("//button[text()='Search']").click(); 
		Thread.sleep(3000);
		
	}

	@And("User prints the price and model")
	public void printPriceModel() {
	    
		System.out.println("\nAvailable Model and Price details:");
		
		String dioModel1 = driver.findElementByXPath("(//table[@id='gvshow']//td[2])[1]").getText(); 
		String dioModel1Price = driver.findElementByXPath("//table[@id='gvshow']//td[3]").getText(); 
		System.out.println(dioModel1 + "-" + dioModel1Price);
		
		String dioModel2 = driver.findElementByXPath("(//table[@id='gvshow']//td[1])[2]").getText(); 
		String dioModel2Price = driver.findElementByXPath("(//table[@id='gvshow']//td[2])[2]").getText(); 
		System.out.println(dioModel2 + "-" + dioModel2Price); 
	}

	@When("User clicks Product Enquiry and Fill all the * field except Mobile, check the terms and conditions box and click submit")
	public void submitProductEnquiry() throws InterruptedException {
	    driver.findElementByXPath("//a//span[text()='Product Enquiry ']").click(); 
	    Thread.sleep(2000); 
	    
	    WebElement modelElement = driver.findElementById("ModelID"); 
	    Select modelOptions = new Select(modelElement); 
	    modelOptions.selectByValue("2"); 
	    
	    WebElement stateElement = driver.findElementById("StateID"); 
		Select stateOptions = new Select(stateElement); 
		stateOptions.selectByValue("28"); 
	    
		WebElement cityElement = driver.findElementById("CityID"); 
		Select cityOptions = new Select(cityElement); 
		cityOptions.selectByValue("1568"); 
		
		Select title = new Select(driver.findElementById("TitleID")); 
		title.selectByVisibleText("Mr."); 
		
		driver.findElementById("Name").sendKeys("Vinothkumar"); 
		driver.findElementById("Email").sendKeys("test@test.com");
		driver.findElementById("TermsAndConditions").click(); 
		driver.findElementById("submit").click();
		
	}

	@Then("User verifies the error message under the mobile number field.")
	public void verifyError() {
	    String expMobileError = "Please enter mobile no"; 
	    
	    String actualMobileError = driver.findElementByXPath("//span[@for='MobileNo']").getText(); 
	    
	    if (actualMobileError.contains(expMobileError)) {
	    	System.out.println("\nError message is correct.");
	    } else {
	    	System.out.println("\nNot displaying expected error.");
	    }
	}


}
