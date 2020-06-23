package steps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShikshaSteps {

	public RemoteWebDriver driver; 
	Actions act; 
	WebDriverWait wait; 
	JavascriptExecutor js; 
	
	public ShikshaSteps() {
		this.driver = HooksClass.getDriver(); 
	} 
	
	@Given("User goes to Shiksha Website")
	public void loadShikshaApplication() {
	    driver.get("https://studyabroad.shiksha.com/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User mouse hovers on Colleges and click MS in Computer Science &Engg under MS Colleges")
	public void selectCourse() throws InterruptedException { 
		
		try {
			driver.findElementByXPath("//div[@class='tar']//a[text()='OK']").click(); 
			System.out.println("Cookies notification clicked.");
		} catch (Exception e) {
			System.out.println("No click on Cookies notification.");
			e.printStackTrace();
		}
		
		act = new Actions(driver); 
		act.moveToElement(driver.findElementByXPath("//label[@class='menuTab-div fnt-wt melabel' and text()='Colleges ']")).build().perform(); 
		Thread.sleep(2000);  
		act.click(driver.findElementByLinkText("MS in Computer Science &Engg")).build().perform(); 
		Thread.sleep(5000); 
	}

	@And("User clicks Change course\\\\/country select box, choose course as BE\\\\/Btech and Choose specialization as Computer Science & Engineering")
	public void selectCountryAndSpecialization() throws InterruptedException {
	    driver.findElementByXPath("//a[contains(text(),'Change course')]").click(); 
	    Thread.sleep(2000);
	    
	    Select courseOptions = new Select(driver.findElementById("desiredCourse")); 
	    courseOptions.selectByVisibleText("BE/Btech"); 
	    Thread.sleep(2000);
	    
	    Select spOptions = new Select(driver.findElementById("subCatSelect")); 
	    spOptions.selectByVisibleText("Computer Science & Engineering"); 
	    Thread.sleep(2000);
	}

	@And("User selects Study destination as USA, UK, Canada and click Update")
	public void selectCountry() throws InterruptedException {
		driver.findElementByXPath("//div[@class='select-overlap']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//strong[text()='USA']").click(); 
		Thread.sleep(2000);

		driver.findElementByXPath("//strong[text()='UK']").click(); 
		Thread.sleep(2000);

		driver.findElementByXPath("//strong[text()='Canada']").click(); 
		Thread.sleep(2000);

		driver.findElementByXPath("(//a[text()='OK'])[1]").click(); 

		driver.findElementByXPath("//input[@value='Update']").click(); 
		Thread.sleep(3000);
	    
	}

	@And("User filters Select IELTS and score as 7.5 & Below in Exam Accepted")
	public void selectFilters() throws InterruptedException {
	    js = (JavascriptExecutor) driver; 
	    
	    js.executeScript("window.scrollBy(0,300)"); 
	    
	    driver.findElementByXPath("//p[text()='IELTS']").click(); 
	    Thread.sleep(5000); 
	    
	    WebElement elementScore = driver.findElementByXPath("(//select[@class='score-select-field'])[2]");
		Select scoreOptions = new Select(elementScore); 
		scoreOptions.selectByVisibleText("7.5 & below"); 
		Thread.sleep(3000); 
	}

	@And("User clicks Total Fees as Max 20L")
	public void selectMaxFees() throws InterruptedException {
		driver.findElementByXPath("//p[text()='Max 20 Lakhs']").click();
		Thread.sleep(2000);
	}

	
	Map<String, Double> collegeFeesMap; 
	@And("User captures the college Names and fees only if it is Engineering  course")
	public void captureEnggColleges() throws InterruptedException {
	    collegeFeesMap = new LinkedHashMap<String, Double>(); 
	    
	    List<WebElement> courseNameList = driver.findElementsByXPath("//div[@class='course-touple clearwidth']//p//a"); 
	    
	    List<WebElement> courseFeesList = driver.findElementsByXPath("//div[@class='course-touple clearwidth']//p//a/parent::p/following-sibling::div//div[@class='detail-col flLt'][1]//p"); 
	    
	    List<WebElement> collegeNameList = driver.findElementsByXPath("//div[@class='course-touple clearwidth']//p//a/parent::p/parent::div/preceding-sibling::div//p//a");
	    
	    for(int i = 0; i < courseNameList.size(); i++) { 
	    	
	    	// Here we are matching the text with the Engg keywords using RegEx  
	    	if (courseNameList.get(i).getText().contains("Bachelor of Engineering")||
	    			courseNameList.get(i).getText().contains("BEng")||
	    			courseNameList.get(i).getText().contains("B.Eng.")) {  
	    		
	    		String filteredColleges = collegeNameList.get(i).getText();
	    		String fees = courseFeesList.get(i).getText(); 
	    		double feesDouble = Double.parseDouble(fees.replaceAll("[\\s+a-zA-Z ]", ""));
	    		
	    		collegeFeesMap.put(filteredColleges, feesDouble); 
	    	}
	    }
	    
	    Thread.sleep(5000);
	}

	@And("User takes 20 colleges by Click Next button and go to next page.")
	public void clickNextPage() throws InterruptedException {
		
		js.executeScript("window.scrollBy(0,2500)");
		
		WebElement nextLink = driver.findElementByXPath("//div[contains(@class,'pagination')]/parent::div//li//a[text()='Next']"); 
		
		while (driver.findElementByXPath("//div[contains(@class,'pagination')]/parent::div//li//a[text()='Next']").isDisplayed()){
			
			//js.executeScript("arguments[0].click()", nextLink); 
			act.moveToElement(driver.findElementByXPath("//div[contains(@class,'pagination')]")).build().perform(); 
			
			try {
				act.moveToElement(nextLink).build().perform();
				act.click(nextLink).build().perform();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
				
			 
			Thread.sleep(3000);
			List<WebElement> courseNameList = driver.findElementsByXPath("//div[@class='course-touple clearwidth']//p//a"); 
		    
		    List<WebElement> courseFeesList = driver.findElementsByXPath("//div[@class='course-touple clearwidth']//p//a/parent::p/following-sibling::div//div[@class='detail-col flLt'][1]//p"); 
		    
		    List<WebElement> collegeNameList = driver.findElementsByXPath("//div[@class='course-touple clearwidth']//p//a/parent::p/parent::div/preceding-sibling::div//p//a");
		    
		    for(int i = 0; i < courseNameList.size(); i++) { 
		    	 
		    	if (courseNameList.get(i).getText().contains("Bachelor of Engineering")||
		    			courseNameList.get(i).getText().contains("BEng")||
		    			courseNameList.get(i).getText().contains("B.Eng.")) {  
		    		
		    		String filteredColleges = collegeNameList.get(i).getText();
		    		System.out.println(filteredColleges);
		    		String fees = courseFeesList.get(i).getText(); 
		    		double feesDouble = Double.parseDouble(fees.replaceAll("[\\s+a-zA-Z ]", ""));
		    		System.out.println(feesDouble); 
		    		
		    		collegeFeesMap.put(filteredColleges, feesDouble); 
		    		Thread.sleep(3000); 
		    		
		    	} 
		    	
		    }
		    
		} 
		
		    		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Next']")));
		    		// wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(nextLink)));
		    		// wait.until(ExpectedConditions.elementToBeClickable(nextLink));
		
		System.out.println(collegeFeesMap);
			
	    
	}

	@When("User searches the college name in the search box based on low fees")
	public void searchLowFeesCollege() {
	    
	}

	@Then("User matches the IELTS score, course Title and country from the University Page")
	public void validateIELTSCourseTitleCountry() {
	    
	}
	
}
