package steps;

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

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SheinSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	
	@Given("User launches browser for Shein")
	public void launchBrowserShein() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe"); 
	    System.setProperty("webdriver.chrome.silentOutput", "true"); 
	    
	    ChromeOptions options = new ChromeOptions(); 
	    options.addArguments("--disable-notifications"); 
	    
	    DesiredCapabilities cap = new DesiredCapabilities(); 
	    cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
	    options.merge(cap); 
	    
	    driver = new ChromeDriver(options); 
	}

	@And("User loads the application Shein")
	public void loadSheinApplication() {
	    driver.get("https://www.shein.in/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	    
	    try {
			driver.findElementByXPath("//div[@class='c-coupon-box']//i[@class='iconfont icon-close she-close']").click(); 
			System.out.println("Coupon box closed.");
		} catch (Exception e) {
			System.out.println("Coupon box not closed.");
			e.printStackTrace();
		}
	}

	@And("User mouse hovers on Clothing and click Jeans")
	public void hoverClothing() throws InterruptedException {
	    act = new Actions(driver); 
	    act.moveToElement(driver.findElementByXPath("//span[text()='CLOTHING']")).build().perform(); 
	    Thread.sleep(2000); 
	    act.click(driver.findElementByXPath("(//a[contains(text(),'Jeans')])[1]")).build().perform(); 
	    Thread.sleep(2000);
	}

	@And("User chooses Black under Jeans product count")
	public void chooseJeansProduct() throws InterruptedException {
	    driver.findElementByXPath("(//a[contains(text(),'Black')])[2]").click(); 
	    Thread.sleep(2000);
	}

	@And("User checks size as medium")
	public void checkSizeFilter() throws InterruptedException {
		js = (JavascriptExecutor) driver; 
	    WebElement sizeFilterElement = driver.findElementByXPath("//li[@class='filter-title leftnav-first-title']//span[text()='Size']"); 
	    js.executeScript("arguments[0].click()", sizeFilterElement); 
	    driver.findElementByXPath("(//div[@class='attr-group j-attr-group attr-group-size']//a)[4]").click(); 
	    Thread.sleep(3000);
	}

	@And("User clicks + in color")
	public void clickColour() throws InterruptedException {
	    String colourSelected = driver.findElementByXPath("//span[text()='Color']/ancestor::div//span[@class='attr-item-pic j-attr-item']//span").getText();
	    System.out.println("Colour filter applied."); 
	    Thread.sleep(3000);
	}

	@And("User checks whether the color is black")
	public void validateColour() {
		String colourSelected = driver.findElementByXPath("//span[text()='Color']/ancestor::div//span[@class='attr-item-pic j-attr-item']//span").getText();
		
		if (colourSelected.contains("Black")) {
			System.out.println("Selected Colour in the filter: " + colourSelected);
		} else { 
			System.out.println("Colour is not Black in the filter.");
		}
	}

	@And("User clicks first item to Add to Bag")
	public void clickFirstItemToBag() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("(//div[@class='c-goodsitem__absolute j-sc-goodsitem'])[1]")).build().perform(); 
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("(//button[contains(text(),'Add to Bag')])[1]")).build().perform();
	}

	@And("User clicks the size as M abd click Submit")
	public void clickSizeSubmit() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("(//div[@class='c-goodsitem__absolute j-sc-goodsitem'])[1]")).build().perform(); 
		Thread.sleep(2000);
		act.click(driver.findElementByXPath("//label[@class='opt-size j-quick-add-opt']//span[contains(text(),'M')]")).build().perform();
		act.click(driver.findElementByXPath("(//button[contains(text(),'Submit')])[1]")).build().perform();
	}

	@When("User clicks view Bag")
	public void clickViewBag() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//div[@class='header-right-dropdown-ctn header-right-no-relative fast-cart-wrap j-fast-cart']")).build().perform();
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("//a[text()='view bag']")).build().perform(); 
	    Thread.sleep(3000);
	}

	@Then("User checks the size is Medium or not and closes the browser")
	public void validateSizeAndCloseBrowser() throws InterruptedException {
	    String sizeDisplayed = driver.findElementByXPath("//span[@class='gd-size']//em").getText(); 
	    
	    if (sizeDisplayed.equalsIgnoreCase("M")) {
	    	System.out.println("Selected size is M."); 
	    } else { 
	    	System.out.println("Size is not M.");
	    }
	    
	    Thread.sleep(2000); 
	    driver.quit();
	}

}
