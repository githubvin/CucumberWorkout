package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LenskartSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	WebDriverWait wait; 
	
	public LenskartSteps() {
		this.driver = HooksClass.getDriver(); 
	} 
	
	@Given("User goes to Lenskart application")
	public void loadLenskartApplication() {
	    driver.get("https://www.lenskart.com/");
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User mouse hovers on Contact Lenses")
	public void hoverContactLenses() throws InterruptedException {
	    act = new Actions(driver); 
	    act.moveToElement(driver.findElementByXPath("//a[text()='Contact Lenses']")).build().perform();
	    Thread.sleep(2000);
	}

	@And("User clicks on Monthly under Explore By Disposability")
	public void clickMonthlyDisposability() throws InterruptedException {
	    driver.findElementByXPath("//span[text()='Monthly']").click(); 
	    Thread.sleep(3000);
	}

	@And("User selects brand as Aqualens")
	public void selectBrand() throws InterruptedException {
	    driver.findElementByXPath("(//span[contains(text(),'Aqualens')])[2]").click();
	    Thread.sleep(2000);
	}

	@And("User clicks on the first product")
	public void clickFirstProduct() throws InterruptedException {
	    driver.findElementByXPath("(//div[contains(@class,'product-info')])[1]").click();
	    Thread.sleep(3000);
	}

	@And("User clicks Buy Now")
	public void clickBuyNow() throws InterruptedException { 
		act.moveToElement(driver.findElementByXPath("//span[text()='Sign Up']")).build().perform();
	    driver.findElementByXPath("//button[text()='BUY NOW']").click(); 
	    Thread.sleep(2000);
	}

	@And("User selects No of boxes as 2 and Power as -1 for both eyes.")
	public void selectBoxPower() {
	    Select boxOptions1 = new Select(driver.findElementByXPath("(//select[@name='boxes'])[1]")); 
	    boxOptions1.selectByValue("2"); 
	    
	    driver.findElementByXPath("(//span[text()='Please Select'])[1]").click(); 
	    driver.findElementByXPath("//li//div[text()='-1.00']").click(); 
	    
	    Select boxOptions2 = new Select(driver.findElementByXPath("(//select[@name='boxes'])[2]")); 
	    boxOptions2.selectByValue("2"); 
	    
	    driver.findElementByXPath("(//span[text()='Please Select'])[1]").click(); 
	    driver.findElementByXPath("//li//div[text()='-1.00']").click(); 
	}

	@And("User types name in User's name")
	public void enterName() {
	    driver.findElementById("example-text-input").sendKeys("Vinothkumar");
	}

	@When("User clicks Save and continue")
	public void clickSaveContinue() throws InterruptedException {
	    driver.findElementByXPath("(//button[text()='SAVE & CONTINUE'])[1]").click(); 
	    Thread.sleep(3000);
	}

	@Then("User prints total amount and click Proceed to Checkout")
	public void printTotalAndCheckout() throws InterruptedException {
	    String total = driver.findElementByXPath("//span[text()='Order Total :']/..//span[2]").getText(); 
	    System.out.println("Total Amount for the Lenses: Rs." + total); 
	    
	    driver.findElementByXPath("//span[text()='Proceed To Checkout']").click(); 
	    Thread.sleep(3000);
	}


}
