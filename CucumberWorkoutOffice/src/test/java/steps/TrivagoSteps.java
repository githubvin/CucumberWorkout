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

public class TrivagoSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	
	@Given("User launches the browser")
	public void launchBrowser() {
	    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe"); 
	    System.setProperty("webdriver.chrome.silentOutput", "true"); 
	    
	    ChromeOptions options = new ChromeOptions(); 
	    options.addArguments("--disable-notifications"); 
	    
	    DesiredCapabilities cap = new DesiredCapabilities(); 
	    cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
	    options.merge(cap); 
	    
	    driver = new ChromeDriver(options);
	}

	@And("User loads the application Url")
	public void loadApplication() {
	    driver.get("https://www.trivago.com/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User types Agra as destination")
	public void typeDestination() throws InterruptedException { 
		js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
	    driver.findElementByXPath("//input[@type='search']").sendKeys("Agra"); 
	    Thread.sleep(3000); 
	    act = new Actions(driver);
	    act.moveToElement(driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']")).build().perform();
	    act.click(driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']")).build().perform();
	}

	@And("User selects the checkin date as June 15 and checkout date as June 30")
	public void selectCheckinCheckoutDate() throws InterruptedException { 
		Thread.sleep(2000); 
		act.click(driver.findElementByXPath("//time//span[text()='15']")).build().perform();
		Thread.sleep(2000);
	    
		act.click(driver.findElementByXPath("//time//span[text()='30']")).build().perform();
		Thread.sleep(2000);
	    
	}

	@And("User selects Family Room")
	public void selectRoomType() throws InterruptedException {
		act.click(driver.findElementByXPath("//span[text()='Family rooms']")).build().perform();
	    Thread.sleep(2000);
	}

	@And("User selects Adults Children numbers and Age")
	public void selectAdultChildrenAge() {
	    WebElement adultElement = driver.findElementById("select-num-adults-2"); 
	    Select adultOptions = new Select(adultElement); 
	    adultOptions.selectByValue("2");
	    
	    WebElement childrenElement = driver.findElementById("select-num-children-2"); 
	    Select childrenOptions = new Select(childrenElement); 
	    childrenOptions.selectByValue("1"); 
	    
	    WebElement childAgeElement = driver.findElementById("select-ages-children-2-3"); 
	    Select childAgeOptions = new Select(childAgeElement); 
	    childAgeOptions.selectByValue("4");
	}

	@And("User clicks confirm button and click search")
	public void clickConfirmSearch() throws InterruptedException {
		driver.findElementByXPath("//span[text()='Confirm']").click(); 
		Thread.sleep(3000);
	}

	@And("User selects Accommodation type as Hotels and choose 4 stars")
	public void selectAccommodationHotel() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//li[@data-qa='stars-filter']")).build().perform(); 
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("//label[text()='Hotels only']")).build().perform(); 
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("//span[text()='4-star hotels']")).build().perform(); 
	    driver.findElementById("filter-popover-done-button").click(); 
	    Thread.sleep(3000);
	}

	@And("User selects Guest rating as Very Good")
	public void selectGuestRating() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//li[@data-qa='rating-filter']")).build().perform(); 
	    act.click(driver.findElementByXPath("//span[text()='Very good']")).build().perform(); 
	    Thread.sleep(3000);
	}

	@And("User sets Hotel Location as Agra Fort and click Done")
	public void selectHotelLocation() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//li[@data-qa='location-filter']")).build().perform(); 
	    
	    WebElement hotelLocElement = driver.findElementById("pois"); 
	    Select hotelLocOptions = new Select(hotelLocElement); 
	    hotelLocOptions.selectByVisibleText("Agra Fort"); 
	    
	    driver.findElementById("filter-popover-done-button").click(); 
	    Thread.sleep(3000);
	}

	@And("User selects Air Conditioning, Restaurant and WiFi in Filters and click Done")
	public void applyMoreFilters() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//li[@data-qa='more-filter']")).build().perform(); 
	    act.click(driver.findElementByXPath("//label[text()='Air conditioning']")).build().perform(); 
	    act.click(driver.findElementByXPath("//label[text()='WiFi']")).build().perform(); 
	    act.click(driver.findElementByXPath("//label[text()='Restaurant']")).build().perform(); 
	    act.click(driver.findElementById("filter-popover-done-button")).build().perform(); 
	    Thread.sleep(3000);
	}

	@And("User sorts result as Rating and Recommended")
	public void sortResults() throws InterruptedException {
	    WebElement sortElement = driver.findElementById("mf-select-sortby"); 
	    Select sortOptions = new Select(sortElement); 
	    sortOptions.selectByValue("7"); 
	    Thread.sleep(4000);
	}

	@And("User prints the Hotel name, Rating, Number of Reviews and Clicks View Deal")
	public void printFirstHotelDetailsAndClick() throws InterruptedException {
	    String hotelName = driver.findElementByXPath("(//span[@data-qa='item-name'])[1]").getText(); 
	    System.out.println("Hotel Name: " + hotelName); 
	   
	    String hotelRating = driver.findElementByXPath("(//div[@itemprop='starRating']//meta)[1]").getAttribute("content"); 
	    System.out.println("Hotel Rating: " + hotelRating); 
	    
	    String hotelReviews = driver.findElementByXPath("(//span[@class='details-paragraph details-paragraph--rating'])[1]").getText(); 
	    System.out.println("Number of Hotel Reviews: " + hotelReviews); 
	    
	    driver.findElementByXPath("(//span[text()='View Deal'])[1]").click(); 
	    Thread.sleep(3000);
	}

	@And("User prints the URL of the page")
	public void printHotelDetailUrl() throws InterruptedException {
	    Set<String> winSet = driver.getWindowHandles(); 
	    List<String> winList = new ArrayList<String>(winSet); 
	    int size = winList.size(); 
	    
	    driver.switchTo().window(winList.get(size-1));
	    Thread.sleep(2000); 
	    
	    String hotelUrl = driver.getCurrentUrl(); 
	    System.out.println("Url of the Hotel: " + hotelUrl);
	}

	@When("User prints the price of the Room and clicks Choose your Room")
	public void printRoomPriceAndSelect() throws InterruptedException {
	    String hotelPrice = driver.findElementByXPath("//div[@class='price-start ']//strong").getText(); 
	    System.out.println("Hotel Price: " + hotelPrice); 
	    
	    driver.findElementByLinkText("BOOK NOW").click(); 
	    driver.findElementByXPath("//button[@class='gotobook']").click();
	    Thread.sleep(5000);
	}

	@Then("User clicks Reserve and I'll Reserve and closes the Browser")
	public void clickReserveAndClose() { 
		// Reserve button not available at the time this script execution. 
	    driver.quit();
	}

}
