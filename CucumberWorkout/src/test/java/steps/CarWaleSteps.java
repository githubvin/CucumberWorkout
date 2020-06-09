package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Ordering;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarWaleSteps {

	public RemoteWebDriver driver; 
	
	public JavascriptExecutor js;
	
	
	@Given("User opens the Chrome browser")
	public void openBrowser() { 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe"); 
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(cap);
		
		driver = new ChromeDriver(options); 
	} 
	
	@And("User launches the CarWale application")
	public void loadApplication() {
	    driver.get("https://www.carwale.com");
	}

	@And("User maximises the browser")
	public void maximiseBrowser() {
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User clicks Used option")
	public void clickUsedTab() {
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click(); 
	}

	@And("User selects city as Chennai")
	public void selectCity() throws InterruptedException {
		driver.findElementById("usedCarsList").sendKeys("Chennai"); 
		Thread.sleep(3000); 
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click(); 
	}

	@And("User selects budget min 8L and max 12L")
	public void selectBudget() {
		driver.findElementById("minInput").sendKeys("8"); 
		driver.findElementById("maxInput").sendKeys("12"); 
	}

	@And("User clicks Search")
	public void clickSearch() throws InterruptedException {
		driver.findElementById("btnFindCar").click(); 
		Thread.sleep(5000); 
		
		// Closing the additional popups 
		try {
			driver.findElementByLinkText("Don't show anymore tips").click();
		} catch (Exception e) {
			System.out.println("Popup not displayed.");
			e.printStackTrace();
		}
		
	}

	@And("User selects Cars with Photos under Only Show Cars with from the search results")
	public void clickCarWithPhotos() throws InterruptedException {
		driver.findElementByXPath("//span[text()='Cars with Photos']").click(); 
		Thread.sleep(5000); 
	}

	@And("User selects Manufacturer as Hyundai and car as Creta")
	public void selectCar() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		driver.findElementByXPath("//span[@class='filterText' and contains(text(),'Hyundai')]").click(); 
		Thread.sleep(2000); 
		driver.findElementByXPath("//span[@class='model-txt' and contains(text(),'Creta')]").click(); 
		Thread.sleep(3000);
	}

	@And("User selects Fuel type as Petrol")
	public void selectFuelType() throws InterruptedException {

		js.executeScript("window.scrollBy(0, 400)");
		driver.findElementByXPath("//h3[contains(text(),'Fuel Type')]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[@class='filterText' and text()='Petrol']").click(); 
		Thread.sleep(3000);
	}

	@And("User sorts the results as KM: Low to High")
	public void sortKM() throws InterruptedException {
		WebElement sortElement = driver.findElementById("sort"); 
		Select sortOptions = new Select(sortElement); 
		sortOptions.selectByValue("2"); 
		Thread.sleep(5000); 
	}

	@And("User validates the Cars listed with KM Low to High and adds the least KM run car to Wishlist")
	public void validateSorting() throws InterruptedException {
		List<WebElement> kmElement = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']"); 
		int size = kmElement.size(); 
		
		List<Integer> kmList = new ArrayList<Integer>(); 
		
		for (int i = 0; i < size; i++) {
			
			String text = kmElement.get(i).getText(); 
			System.out.println(text); 
			int kmValues = Integer.parseInt(text.replaceAll("\\D", "")); 
			kmList.add(kmValues); 
			
		}
		
		// Validating the Integer List is sorted or not 
		boolean sorted = Ordering.natural().isOrdered(kmList); 
		
		if (sorted == true) {
			System.out.println("Cars are sorted correctly.");
		} else { 
			System.out.println("Sorting is incorrect.");
		} 
		
		// Sorting the List 
		Collections.sort(kmList); 
		System.out.println("*****************************"); 
		System.out.println(kmList); 

		Integer lowestKm = kmList.get(0); 

		// Converting this integer value to String to pass it in the below Xpath 
		String str = Integer.toString(lowestKm);  
		str = new StringBuilder(str).insert(str.length()-3, ",").toString(); 
		System.out.println(str);

		// Selecting the lowest KMs car  
		Thread.sleep(3000); 

		WebElement favCar = driver.findElementByXPath("(//span[contains(text(),'"+str+"')]//ancestor::div[@class='stock-detail']//span[@class='shortlist-icon--inactive shortlist'])[1]");
		//js.executeScript("window.scrollBy(0, 500)");
		//js.executeScript("window.scrollIntoView(true)", favCar);  
		favCar.click();
	}

	@And("User goes to Wishlist and Click on More Details")
	public void clickWishlist() throws InterruptedException {
		driver.findElementByXPath("//li[@data-cat='UsedCarSearch']").click(); 
		driver.findElementByLinkText("More details »").click(); 
		
		//Navigating to new tab after clicking more details in Wishlist 
		Set<String> winSet = driver.getWindowHandles(); 
		List<String> winList = new ArrayList<String>(winSet); 
		int size = winList.size(); 
		driver.switchTo().window(winList.get(size-1)); 
		Thread.sleep(5000);
		
	}

	@When("User prints all the details under Overview in the Same way as displayed in application")
	public void printCarOverview() {
		List<WebElement> eleAttribute = driver.findElementsByXPath("//div[@id='overview']//li//div[1]"); 
		List<WebElement> eleValue = driver.findElementsByXPath("//div[@id='overview']//li//div[2]");
		
		Map<String, String> carDetails = new LinkedHashMap<String, String>(); 
		
		for (int i=0; i < eleAttribute.size(); i++) { 
			String attributeStr = eleAttribute.get(i).getText(); 
			String valueStr = eleValue.get(i).getText(); 
			carDetails.put(attributeStr, valueStr); 
		} 
		
		System.out.println("\nDetails of the Favourite Car");
		for (Map.Entry<String, String> eachEntry : carDetails.entrySet()) { 
			System.out.println(eachEntry.getKey() + " ------- " + eachEntry.getValue());
		}
	}

	@Then("Close the browser.")
	public void closeBrowser() {
	    driver.quit();
	}
	
	
}
