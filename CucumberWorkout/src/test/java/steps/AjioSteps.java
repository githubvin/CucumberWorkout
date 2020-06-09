package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AjioSteps {  
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	
	@Given("User opens the browser") 
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
	
	@And("User loads the Ajio application")
	public void loadApplication() {
	    driver.get("https://www.ajio.com/shop/sale"); 
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User hovers the mouse on Women, CATEGORIES and click on Kurtas")
	public void mouseHoverCategory() throws InterruptedException {
	    act = new Actions(driver); 
	    
	    act.moveToElement(driver.findElementByLinkText("WOMEN")).build().perform();
	    act.click(driver.findElementByLinkText("Kurtas")).build().perform(); 
	    Thread.sleep(3000);
	}

	@And("User clicks on Brands and choose Ajio")
	public void chooseBrand() throws InterruptedException {
	    driver.findElementByXPath("//span[text()='brands']").click(); 
	    driver.findElementByXPath("//label[@for='AJIO']").click(); 
	    Thread.sleep(2000);
	}

	@And("User checks all the results are Ajio")
	public void validateBrandName() {
	    List<WebElement> brandList = driver.findElementsByXPath("//div[@class='brand']"); 
	    
	    String brandText = ""; 
	    
	    for (WebElement eachBrand : brandList) {
			brandText = eachBrand.getText(); 
		} 
	    
	    if (brandText.equalsIgnoreCase("Ajio")) { 
			System.out.println("\nAjio brands are displayed.");
		} else { 
			System.out.println("\nOther brands are displayed.");
		}
	}

	@And("User sets Sort by the result as Discount")
	public void sortResults() throws InterruptedException {
	    WebElement sortElement = driver.findElementByXPath("//div[@class='filter-dropdown']//select"); 
	    Select sortOptions = new Select(sortElement); 
	    sortOptions.selectByVisibleText("Discount"); 
	    Thread.sleep(5000);
	}

	@And("User selects the first Kurta")
	public void selectFirstItem() throws InterruptedException {
	    driver.findElementByXPath("(//div[@class='contentHolder'])[1]").click(); 
	    Thread.sleep(5000);
	}

	@And("User selects any Color and click ADD TO BAG")
	public void selectColourAndAddBag() throws InterruptedException {
	    Set<String> winSet = driver.getWindowHandles(); 
	    List<String> winList = new ArrayList<String>(winSet); 
	    int size = winList.size(); 
	    
	    driver.switchTo().window(winList.get(size-1)); 
	    
	    driver.findElementByXPath("(//div[@class='color-swatch'])[2]").click(); 
	    Thread.sleep(3000);
	}

	@And("User verifies the error message Select your size to know your estimated delivery date")
	public void verifyEstimatedDeliveryError() {
	    String sizeError = driver.findElementByXPath("//span[@class='edd-pincode-msg-details']").getText(); 
	    
	    if (sizeError.equalsIgnoreCase("Select your size to know your estimated delivery date.")) {
	    	System.out.println("\nEstimate Delivery date error is displayed.");
	    } else { 
	    	System.out.println("\nEstimate Delivery error not displayed.");
	    }
	}

	@And("User selects size and click ADD TO BAG")
	public void selectSizeAndAddToBag() throws InterruptedException {
	    driver.findElementByXPath("(//div[contains(@class, 'size-instock')])[1]").click(); 
	    driver.findElementByXPath("//span[text()='ADD TO BAG']").click(); 
	    Thread.sleep(3000);
	}

	@And("User clicks on Enter pin-code to know estimated delivery date")
	public void clickEnterPincode() throws InterruptedException { 
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 50)"); 
		
	    driver.findElementByXPath("//span[contains(@class, 'edd-pincode-msg-details')]").click(); 
	    Thread.sleep(3000);
	}

	@And("User enters the pincode as 603103 and click Confirm pincode")
	public void enterPincode() {
		driver.findElementByName("pincode").sendKeys("603103"); 
	    driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click(); 
	}

	@When("User prints the message and click Go to  Bag")
	public void printDeliveryMessage() throws InterruptedException { 
		
		String EstimatedDeliveryMessage = driver.findElementByXPath("//ul[@class='edd-message-success-details']").getText(); 
		System.out.println(EstimatedDeliveryMessage);
	    
	    driver.findElementByXPath("//span[text()='GO TO BAG']").click(); 
	    Thread.sleep(3000);
	   
	}

	@Then("User clicks on Proceed to Shipping and clode the browser")
	public void clickProceedShipping() throws InterruptedException {
	    driver.findElementByXPath("//button[text()='Proceed to shipping']").click();
	    Thread.sleep(3000); 
	    driver.quit();
	}

}
