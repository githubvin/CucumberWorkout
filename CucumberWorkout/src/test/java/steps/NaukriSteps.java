package steps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NaukriSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	Robot rb; 
	
	public NaukriSteps() {
		this.driver = HooksClass.getDriver(); 
	} 
	
	@Given("User opens Naukri website")
	public void launchNaukriApplication() {
	    driver.get("https://www.naukri.com/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User gets the company names from each pop up window and close it")
	public void getCompanyNames() {
	    Set<String> winSet = driver.getWindowHandles(); 
	    List<String> winList = new ArrayList<String>(winSet); 
	    int size = winList.size(); 
	    
	    driver.switchTo().window(winList.get(size-1)); 
	    String company1 = driver.findElementByXPath("//a[@target='_blank']//img").getAttribute("alt"); 
	    driver.close();
	    
	    driver.switchTo().window(winList.get(size-2)); 
	    String company2 = driver.findElementByXPath("//a[@target='_blank']//img").getAttribute("alt"); 
	    driver.close(); 
	    
	    System.out.println("\nCompanies displayed in the popup: " + company1 + ", " + company2); 
	    
	    driver.switchTo().window(winList.get(size-3)); 
	    
	}

	@When("User now clicks on the upload cv button and upload some random image")
	public void uploadRandomImageOnCV() throws InterruptedException, AWTException {
		
	    String filePath = "C:\\Users\\vinvi\\Desktop\\VideoLength_Qset3.png"; 
	    
	    // Here using send keys to pass the file name directly to the element. 
	    // This send keys will work only when the element locator is under input tag and it must have attribute type as 'file'  
	    // driver.findElementById("file_upload").sendKeys(filePath);
	    // Thread.sleep(3000); 
	    
	    // For elements under other tags we can use Robot class as below. 
	    rb = new Robot(); 
	    driver.findElementById("wdgt-file-upload").click();
	    
	    rb.setAutoDelay(2000);
	    
	    StringSelection sc = new StringSelection(filePath); 
	    
	    // This line is similar to copying the file into clipboard 
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sc, null); 
	    
	    rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V); 
		
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_V); 
		
		rb.keyPress(KeyEvent.VK_ENTER); 
		rb.keyRelease(KeyEvent.VK_ENTER);
	    
	}

	@Then("User gets the error message printed")
	public void printErrorMessage() { 
		
		String errorMsg = driver.findElementByXPath("//div[@class='error-header-desc error']").getText(); 
		
		System.out.println("\nDisplayed Error: " + errorMsg);
	    
	}

}
