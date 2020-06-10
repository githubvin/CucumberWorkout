package steps;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BigBasketSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	
	@Given("User launches the browser for Big Basket")
	public void launchBrowserBigBasket() { 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe"); 
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications"); 
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(cap); 
		
		driver = new ChromeDriver(options);
	}
	
	@And("User loads the BigBasket application")
	public void loadApplication() {
	    driver.get("https://www.bigbasket.com/");
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User mouse hovers on  Shop by Category")
	public void hoverShopCategory() { 
		act = new Actions(driver);
		act.moveToElement(driver.findElementByXPath("//a[contains(text(),'Shop by')]")).build().perform(); 
	}

	@And("User goes to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS")
	public void hoverFoodGrainsRiceCategory() {
		act.moveToElement(driver.findElementByXPath("(//a[contains(text(),'Foodgrains, Oil & Masala')])[2]")).build().perform();
		act.moveToElement(driver.findElementByXPath("(//a[contains(text(),'Rice & Rice Products')])[2]")).build().perform();
	}

	@And("User clicks on BOILED & STEAM RICE")
	public void clickBoiledSteamRice() throws InterruptedException {
	    act.click(driver.findElementByLinkText("Boiled & Steam Rice")).build().perform(); 
	    Thread.sleep(3000);
	}

	@And("User gets the URL of the page and check it with site navigation link HOME > FOODGRAINS, OIL & MASALA> RICE & RICE PRODUCTS> BOILED & STEAM RICE")
	public void vaidateCurrentUrl() throws InterruptedException {
	    String navigatedUrl = driver.getCurrentUrl(); 
	    if (navigatedUrl.contains("/pc/foodgrains-oil-masala/rice-rice-products/boiled-steam-rice/?nc=bc")) {
	    	System.out.println("\nCurrent Url is landed correctly. " + navigatedUrl);
	    } else { 
	    	System.out.println("\nLanded on wrong page. " + navigatedUrl);
	    } 
	    Thread.sleep(2000);
	}

	@And("User chooses the Brand as bb Royal")
	public void chooseBrandBB() throws InterruptedException {
		driver.findElementByXPath("(//label//span[text()='bb Royal'])[1]").click(); 
		Thread.sleep(2000);
	}

	@And("User goes to Ponni Boiled Rice and select 10kg bag from Dropdown")
	public void selectPonniBoiledRice() throws InterruptedException { 
		driver.findElementByXPath("(//a[contains(text(),'Ponni Boiled Rice - Super')]/following::button[@type='button'])[1]").click(); 
	    driver.findElementByXPath("(//ul[@class='dropdown-menu drop-select']//span[text()='10 kg'])[3]").click();
	    Thread.sleep(2000);
	}

	@And("User clicks Add button")
	public void clickAddButton() throws InterruptedException {
		driver.findElementByXPath("//a[contains(text(),'Ponni Boiled Rice - Super Premium')]/following::button[@qa='add'][1]").click();
		Thread.sleep(2000);
	}

	@And("User goes to search box and type Dal")
	public void searchDal() throws InterruptedException {
		try {
			driver.findElementByLinkText("Continue").click();
		} catch (Exception e) {
			System.out.println("Location popup not displayed.");
			e.printStackTrace();
		} 
		
		driver.findElementByXPath("//input[@qa='searchBar']").sendKeys("Dal", Keys.ENTER);
		Thread.sleep(5000);
	}

	@And("User adds Toor\\/Arhar Dal 2kg and set Qty 2 from the list")
	public void addToorDal() throws InterruptedException {
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::span[2]").click();
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::span[2]/following::span[text()='2 kg'][1]").click();
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::input[@type='text'][1]").clear(); 
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::input[@type='text'][1]").sendKeys("2"); 
		driver.findElementByXPath("((//a[contains(text(),'Thuvaram Paruppu')])[1]/following::span[@class='discnt-price']//span/following::button)[1]").click();
		Thread.sleep(5000);
	}

	@And("User clicks Address")
	public void clickAddress() { 
		try {
			driver.findElementByClassName("toast-close-button").click();
			System.out.println("Success message closed.");
		} catch (Exception e) {
			System.out.println("Top success message not closed.");
			e.printStackTrace();
		}
		js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,-100)");
	    driver.findElementByXPath("//span[contains(@ng-bind, 'address_display_name')]").click();
	}

	@And("User selects Chennai as City, Alandur-600016,Chennai as Area  and click Continue")
	public void selectAddress() throws InterruptedException {
	    driver.findElementByXPath("(//span[text()='Select your city']/parent::span)[1]").click(); 
	    driver.findElementByXPath("//input[@placeholder='Select your city']").sendKeys("Chennai");
	    driver.findElementByXPath("//a[@class='ui-select-choices-row-inner']//span[text()='Chennai']").click(); 
	    driver.findElementByXPath("//input[@qa='areaInput']").sendKeys("Alandur"); 
	    Thread.sleep(2000);
	    driver.findElementByXPath("(//strong[text()='Alandur']/parent::a[text()='-600016,Chennai'])[5]").click();
	    driver.findElementByName("continue").click();
	}

	@And("User mouse hovers on My Basket take a screen shot")
	public void takeScreenshotMyBasket() throws IOException, InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).build().perform(); 
	    Thread.sleep(2000);  
	    //WebElement myBasket = driver.findElementByXPath("//ul[@ng-show='vm.basketDrop']");
	    File src = driver.getScreenshotAs(OutputType.FILE); 
	    File dest = new File("./screens/BigBasket_MyBasket.png"); 
	    FileUtils.copyFile(src, dest);
	    Thread.sleep(2000);
	}

	@When("clicks View Basket and Checkout")
	public void clickCheckout() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).build().perform();
		act.moveToElement(driver.findElementByXPath("//button[text()='View Basket & Checkout']")).build().perform();
		act.click(driver.findElementByXPath("//button[text()='View Basket & Checkout']")).build().perform();
	    Thread.sleep(3000);
	}

	@Then("clicks the close button and close the browser")
	public void closePopupAndBrowser() throws InterruptedException {
	    driver.findElementByXPath("//button[@ng-click='dismiss()']").click(); 
	    Thread.sleep(2000); 
	    driver.quit();
	}

}
