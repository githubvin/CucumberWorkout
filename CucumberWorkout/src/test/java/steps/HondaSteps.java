package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

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
		Thread.sleep(2000); 

		act = new Actions(driver); 

		act.moveToElement(driver.findElementByLinkText("ENGINE")).build().perform(); 
	}

	@And("User puts all the details as key and value into Map")
	public void storeDioEngineDetailsMap() {
	    
		List<WebElement> engineAttRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[1]");
	    
	    // For Engine Attributes and its values the index is not matching to get the values. So fetching values separately. 
	    
	    List<String> engineAttributes = new ArrayList<String>();  
	    for(int i = 1; i < engineAttRaw.size(); i++) {
	    	engineAttributes.add(engineAttRaw.get(i).getText()); 
	    }
	    
	    List<WebElement> engineValuesRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[2]");
	    
	    List<String> engineValues = new ArrayList<String>(); 
	    for(int j = 0; j < engineValuesRaw.size(); j++) {
	    	engineValues.add(engineValuesRaw.get(j).getText()); 
	    } 
	    
	    dioEngineMap.put(engineAttributes.toString(), engineValues.toString()); 
	    
	    
	    
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

		List<WebElement> engineAttRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[1]");

		// For Engine Attributes and its values the index is not matching to get the values. So fetching values separately. 

		List<String> engineAttributes = new ArrayList<String>();  
		for(int i = 1; i < engineAttRaw.size(); i++) {
			engineAttributes.add(engineAttRaw.get(i).getText()); 
		}

		List<WebElement> engineValuesRaw = driver.findElementsByXPath("//div[contains(@class,'engine')]//li//span[2]");

		List<String> engineValues = new ArrayList<String>(); 
		for(int j = 0; j < engineValuesRaw.size(); j++) {
			engineValues.add(engineValuesRaw.get(j).getText()); 
		} 

		activaEngineMap.put(engineAttributes.toString(), engineValues.toString()); 
		
	}

	@And("User compares Dio and Activa Maps and print the different values of the samekeys.")
	public void compareDioActiva() {
	    
	}

	@And("User clicks FAQ from Menu and Click dio under Browse By Product")
	public void navigateDioFAQ() {
	    
	}

	@And("User clicks  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit")
	public void checkVehiclePrice() {
	    
	}

	@And("User clicks the price link,  Go to the new Window and select the state, city")
	public void selectCity() {
	    
	}

	@And("User prints the price and model")
	public void printPriceModel() {
	    
	}

	@When("User clicks Product Enquiry and Fill all the * field except Mobile, check the terms and conditions box and click submit")
	public void submitProductEnquiry() {
	    
	}

	@Then("User verifies the error message under the mobile number field.")
	public void verifyError() {
	    
	}


}
