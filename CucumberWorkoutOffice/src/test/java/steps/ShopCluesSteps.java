package steps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopCluesSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	List<WebElement> allProducts; 
	List<WebElement> allPrice; 
	Map<String, String> itemMap; 
	
	@Given("User launches the Browser for ShopClues")
	public void launchBrowserForShopClues() {
	    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe"); 
	    System.setProperty("webdriver.chrome.silentOutput", "true"); 
	    
	    ChromeOptions options = new ChromeOptions(); 
	    options.addArguments("--disable-notifications"); 
	    
	    DesiredCapabilities cap = new DesiredCapabilities(); 
	    cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
	    options.merge(cap); 
	    
	    driver = new ChromeDriver(options); 
	}

	@And("User loads the ShopClues application")
	public void loadShopCluesApplication() {
	    driver.get("https://www.shopclues.com/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	    
	    try {
			driver.findElementByXPath("//button[contains(text(),'t Allow')]").click();
			System.out.println("Cookies closed.");
		} catch (Exception e) {
			System.out.println("Cookies not closed.");
			e.printStackTrace();
		}
	}

	@And("User mouse hovers on women and clicks Casual Shoes")
	public void hoverWomenCategory() throws InterruptedException {
	    act = new Actions(driver); 
	    act.moveToElement(driver.findElementByLinkText("WOMEN")).build().perform(); 
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("//a[text()='Casual Shoes']")).build().perform();
	    Thread.sleep(5000);
	}

	@And("User selects colour as Black")
	public void selectColour() throws InterruptedException {
	    Set<String> winSet = driver.getWindowHandles(); 
	    List<String> winList = new ArrayList<String>(winSet); 
	    int size = winList.size(); 
	    driver.switchTo().window(winList.get(size-1)); 
	    
	    js = (JavascriptExecutor) driver; 
	    js.executeScript("window.scrollBy(0,1000)"); 
	    WebElement colourElement = driver.findElementByXPath("//label[@for='Black']"); 
	    js.executeScript("arguments[0].click()", colourElement); 
	    Thread.sleep(3000);
	}

	@And("User checks whether the product name contains the word Black")
	public void validateProductNameWithColour() {
	    allProducts = driver.findElementsByXPath("//span[contains(@class,'prod_name')]"); 
	    
	    allPrice = driver.findElementsByXPath("//span[@class='p_price']");
	}

	@And("If so user adds the product name and price to Map")
	public void addProductWordColourMap() {
	    itemMap = new LinkedHashMap<String, String>(); 
	    String prodName = "";
	    String prodPrice = "";
	    for (WebElement eachElement : allProducts) {
			if (eachElement.getText().contains("Black")) {
				prodName = eachElement.getText();
				for (WebElement eachPrice : allPrice) {
					prodPrice = eachPrice.getText();
				}
				itemMap.put(prodName, prodPrice);
			}
		} 
	    
	    System.out.println("\nList of Products from the page:");
	    for (Entry<String, String> eachEntry : itemMap.entrySet()) {
			System.out.println(eachEntry.getKey() + "--------" + eachEntry.getValue());
		}
	    
	}

	@And("User checks display the count of shoes which are display more than 500 Rupees")
	public void checkPrice500() {
	    int fiveHunderPlus = 0; 
	    for (WebElement eachPrice : allPrice) {
			String priceStr = eachPrice.getText();
			int priceNumber = Integer.parseInt(priceStr.replaceAll("\\D", "")); 
			if (priceNumber > 500) {
				fiveHunderPlus++; 
			}
		} 
	    
	    System.out.println("Number of Products with Price more than 500:" + fiveHunderPlus);
	}

	@And("User clicks the highest price of the Shoe")
	public void viewHighestPriceProduct() throws InterruptedException {
	    driver.findElementByLinkText("High Price").click();
	    Thread.sleep(2000); 
	    driver.findElementByXPath("(//span[contains(@class,'prod_name')])[1]").click(); 
	    Thread.sleep(5000);
	}

	@And("User gets the current page Url and checks with the product ID")
	public void getCurrentPageUrlProductID() throws InterruptedException {
		Set<String> winSet = driver.getWindowHandles(); 
	    List<String> winList = new ArrayList<String>(winSet); 
	    int size = winList.size(); 
	    driver.switchTo().window(winList.get(size-1)); 
	    
	    System.out.println("\nCurrent Page Url: " + driver.getCurrentUrl());
	    
	    String prodId = driver.findElementByXPath("//span[@class='pID']").getText(); 
	    prodId = prodId.replaceAll("\\D", ""); 
	    System.out.println("\nProduct ID: " + prodId); 
	    
	    if (driver.getCurrentUrl().contains(prodId)) {
	    	System.out.println("Current Url contains the Product ID.");
	    } else { 
	    	System.out.println("Url does not have Product ID.");
	    }
	    
	    Thread.sleep(3000);
	}

	@And("User copies the offer code")
	public void copyOfferCode() throws InterruptedException {
	    driver.findElementByXPath("(//div[@class='coupons_code']//span)[1]").click();
	    System.out.println("\nOffer Code copied."); 
	    Thread.sleep(2000);
	}

	@And("User selects the size, colour and clicks Add to Cart")
	public void selectColourSize() throws InterruptedException {
	    driver.findElementByXPath("//span[contains(@style,'background')]").click(); 
	    driver.findElementByXPath("//span[@value='36 (EUR)']").click(); 
	    driver.findElementById("add_cart").click(); 
	    Thread.sleep(2000);
	}

	@And("User mouse hovers on Shopping Cart and clicks View Cart")
	public void hoveViewCart() throws InterruptedException {
	    act.moveToElement(driver.findElementByXPath("//a[@class='cart_ic']")).build().perform(); 
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("//a[text()='View Cart']")).build().perform(); 
	    Thread.sleep(3000);
	}

	@When("User types Pincode as 600016 clicks Submit and Place Order")
	public void submitOrder() throws InterruptedException {
	    driver.findElementById("pin_code_popup").sendKeys("600016");
	    driver.findElementById("get_pincode_popup").click(); 
	    Thread.sleep(2000);
	    driver.findElementByXPath("//div[text()='Place Order']").click();
	    Thread.sleep(3000);
	}

	@Then("User closes the Browser")
	public void userClosesTheBrowser() {
	    driver.quit();
	}
	

}
