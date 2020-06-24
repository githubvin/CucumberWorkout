package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ZalandoSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	WebDriverWait wait; 
	
	public ZalandoSteps() {
		this.driver = HooksClass.getDriver(); 
	} 
	
	@Given("User goes to Zalando website")
	public void loadZalandoApplication() {
	    driver.get("https://www.zalando.com/");
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User gets the Alert text and print it")
	public void printAlertText() throws InterruptedException { 
		Thread.sleep(5000);
		String initialAlertText = driver.switchTo().alert().getText(); 
		System.out.println("Initial Alert: " + initialAlertText); 
	}

	@And("User closes the Alert box and click on Zalando.uk")
	public void closeAlertNavigateUK() throws InterruptedException {
		driver.switchTo().alert().accept(); 
		driver.switchTo().defaultContent(); 
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
		Thread.sleep(8000);  
		
		try {
			driver.findElementById("uc-btn-accept-banner").click();
		} catch (Exception e) {
			System.out.println("Cookies block not displayed.");
			e.printStackTrace();
		}
		
	}

	@And("User mouse hovers on FREE DELIVERY & RETURNS* get the text and print it")
	public void printFreeDeliveryReturnsText() {
		String toolTipText = driver.findElementByXPath("//a[text()='Free delivery & returns*']/parent::span").getAttribute("title"); 
		System.out.println("Tooltip message in Free Delivery and Returns: " + toolTipText); 
	}

	@And("User mouse hovers on Clothing and click Coat under WOMEN")
	public void navigateWomenClothing() throws InterruptedException {
	    act = new Actions(driver); 
	    
	    act.moveToElement(driver.findElementByXPath("//span[text()='Clothing']")).build().perform(); 
	    Thread.sleep(2000);
	    act.click(driver.findElementByXPath("//span[text()='Coats']")).build().perform();
	    Thread.sleep(3000);
	}

	@And("User chooses Material as cotton and Length as thigh-length")
	public void chooseMaterialLength() throws InterruptedException {
		driver.findElementByXPath("//span[text()='Material']").click(); 
		driver.findElementByXPath("//span[text()='cotton (100%)']").click(); 
		driver.findElementByXPath("//button[text()='Save']").click(); 
		Thread.sleep(3000); 
		
		driver.findElementByXPath("//span[text()='Length']").click(); 
		driver.findElementByXPath("//span[text()='thigh-length']").click(); 
		driver.findElementByXPath("//button[text()='Save']").click(); 
		Thread.sleep(3000); 
	}

	@And("User clicks the first result choose the color and size if available")
	public void clickFirstItem() throws InterruptedException {
	    driver.findElementByXPath("(//a[@class='cat_infoDetail--ePcG'])[1]").click();
	    Thread.sleep(3000); 
	    driver.findElementById("picker-trigger").click(); 
		driver.findElementByXPath("//span[text()='14']").click(); 
	}

	@And("User gets the page title and match with the product brand, name, color and the page url")
	public void validatePageTitleWithBrandName() {
	   
		String pageTitle = driver.getTitle(); 
	    
	    String brandName = driver.findElementByXPath("//span[text()='Colour']/ancestor::div[3]//a//span").getText(); 
	    
	    String prodName = driver.findElementByXPath("//span[text()='Colour']/ancestor::div[3]//h1").getText();
		
	    String colour = driver.findElementByXPath("//span[text()='Colour']/following-sibling::span").getText(); 
	    
	    System.out.println("Page Title contains Brand: " + pageTitle.matches("(.*)" + brandName + "(.*)")); 
	    System.out.println("Page Title contains Product: " + pageTitle.matches("(.*)" + prodName + "(.*)")); 
	    System.out.println("Page Title contains colour: " + pageTitle.matches("(.*)" + colour + "(.*)")); 
	    System.out.println("Page Title contains current page URL: " + pageTitle.matches("(.*)zalando.co.uk(.*)"));
	    
	}

	@And("User clicks Add to Bag and click on FREE DELIVERY & RETURNS* on the top of the page")
	public void addToBagPrintClickFreeDeliveryLink() throws InterruptedException {
	    driver.findElementByXPath("//span[text()='Add to bag']").click(); 
	    driver.findElementByXPath("//a[text()='Free delivery & returns*']").click();
	    Thread.sleep(5000);
	}

	@And("User clicks on Start chat in the Start chat and go to the new window")
	public void clickStartChatNavigateChatWindow() throws InterruptedException {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Start chat']/parent::button")));
		driver.findElementByXPath("//span[text()='Start chat']/parent::button").click();  
		System.out.println("Chat clicked.");
		Thread.sleep(5000); 
		
		Set<String> winSet = driver.getWindowHandles(); 
		List<String> winList = new ArrayList<String>(winSet); 
		int size = winList.size(); 
		driver.switchTo().window(winList.get(size-1)); 
		Thread.sleep(3000);
	}

	@When("User enters you first name and a dummy email and click Start Chat")
	public void enterDetailsChat() throws InterruptedException {
		driver.findElementById("prechat_customer_name_id").sendKeys("Vinothkumar"); 
		driver.findElementById("prechat_customer_email_id").sendKeys("test@test.com"); 
		driver.findElementByXPath("//span[text()='Start Chat']/parent::button").click(); 
		Thread.sleep(8000);
	}

	@Then("User types Hi, click Send and print the reply message and close the chat window.")
	public void typeMsgPrintReply() {
		driver.findElementById("liveAgentChatTextArea").sendKeys("Hi"); 
		driver.findElementByXPath("//button[@title='Send']").click(); 
		
		String botResponse = driver.findElementByXPath("//span[@class='client']//following-sibling::span[@class='operator']//span[@class='messageText']").getText(); 
		System.out.println("ChatBot response: " + botResponse);
	}

}
