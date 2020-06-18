package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CrmCloudSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	
	public CrmCloudSteps() {
		this.driver = HooksClass.getDriver(); 
	}
	
	@Given("User goes to CRM Cloud application")
	public void launchCloudApplication() {
	    driver.get("https://demo.1crmcloud.com/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User gives username as admin and password as admin")
	public void loginCloudApplication() {
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin"); 
	}

	@And("User chooses theme as Claro Theme and Login")
	public void applyTheme() throws InterruptedException {
		WebElement elementTheme = driver.findElementById("login_theme"); 
		Select themeOptions = new Select(elementTheme); 
		themeOptions.selectByVisibleText("Claro Theme"); 
		driver.findElementById("login_button").click(); 
		Thread.sleep(5000);
	}

	@And("User goes to Sales and Marketting and click Sales Home")
	public void navigateSalesHome() throws InterruptedException { 
		act = new Actions(driver); 
		act.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).build().perform();
		Thread.sleep(2000); 
		act.click(driver.findElementByXPath("//div[text()='Sales Home']")).build().perform(); 
		Thread.sleep(2000);
	}

	@And("User clicks Create contact")
	public void createContact() throws InterruptedException {
		driver.findElementByXPath("//div[@id='page-shortcuts']//div[text()='Create Contact']").click(); 
		Thread.sleep(3000);
	}

	@And("User selects Title and type First name, Last Name, Email and Phone Numbers")
	public void enterContactDetails() throws InterruptedException {
		act.moveToElement(driver.findElementById("DetailFormsalutation-input")).build().perform();
		Thread.sleep(2000);
		act.click(driver.findElementById("DetailFormsalutation-input")).build().perform(); 
		
		//act.click(driver.findElementByXPath("//div[@id='DetailFormsalutation-input-label' and text()='Mr.']")).build().perform(); 

		act.click(driver.findElementByXPath("//div[text()='Mr.']")).build().perform(); 

		driver.findElementById("DetailFormfirst_name-input").sendKeys("Kimberly"); 
		driver.findElementById("DetailFormlast_name-input").sendKeys("Brown"); 
		driver.findElementById("DetailFormemail1-input").sendKeys("KimberlyPBrown@teleworm.us");
		driver.findElementById("DetailFormphone_work-input").sendKeys("1234567890");
	}

	@And("User selects Lead Source as Public Relations and Business Roles")
	public void enterDepartmentDetails() {
		driver.findElementById("DetailFormlead_source-input").click(); 
		act.click(driver.findElementByXPath("//div[text()='Public Relations']")).build().perform(); 
		
		driver.findElementById("DetailFormbusiness_role-input").click(); 
		act.click(driver.findElementByXPath("//div[text()='Sales']")).build().perform(); 
	}

	@And("User fills the Primary Address, City, State, Country and Postal Code and click Save")
	public void enterAddressDetails() throws InterruptedException {
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("4054 Westfall Avenue");
		driver.findElementById("DetailFormalt_address_city-input").sendKeys("Detroit");
		driver.findElementById("DetailFormalt_address_state-input").sendKeys("Michigan"); 
		driver.findElementById("DetailFormalt_address_country-input").sendKeys("USA"); 
		driver.findElementById("DetailFormalt_address_postalcode-input").sendKeys("48219"); 
		driver.findElementById("DetailForm_save2").click(); 
		Thread.sleep(3000); 
	}

	@And("User clicks create and Leads")
	public void clickCreateLeads() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).build().perform();
		Thread.sleep(2000); 
		act.click(driver.findElementByXPath("//div[text()='Leads']")).build().perform(); 
		Thread.sleep(2000); 
		driver.findElementByXPath("//div[text()='Create Lead']").click(); 
		Thread.sleep(2000);
	}

	@And("User fills First & Last name, Status as In Process, Title as Manager and Department as Sales")
	public void enterLeadContactDetails() {
		act.click(driver.findElementById("DetailFormsalutation-input")).build().perform();  
		act.click(driver.findElementByXPath("//div[text()='Mr.']")).build().perform(); 
		
		driver.findElementById("DetailFormfirst_name-input").sendKeys("Vinothkumar"); 
		driver.findElementById("DetailFormlast_name-input").sendKeys("S"); 
		driver.findElementById("DetailFormtitle-input").sendKeys("Manager"); 
		
		driver.findElementById("DetailFormstatus-input").click();
		act.click(driver.findElementByXPath("//div[text()='In Process']")).build().perform();  
		driver.findElementById("DetailFormdepartment-input").sendKeys("Sales"); 
		
	}

	@And("User selects Lead as Public Relations and fill department, Email and Phone Number")
	public void enterLeadAddressDetails() {
		driver.findElementById("DetailFormlead_source-input").click(); 
		act.click(driver.findElementByXPath("//div[text()='Public Relations']")).build().perform(); 
		
		driver.findElementById("DetailFormemail1-input").sendKeys("test@email.com");
		driver.findElementById("DetailFormphone_work-input").sendKeys("3698521478");
		
	}

	@And("User clicks Save and View")
	public void clickSaveView() throws InterruptedException {
	    driver.findElementById("DetailForm_save2-label").click(); 
	    Thread.sleep(2000);
	}

	@And("User mouse hovers on Today's Activities and click Meetings")
	public void navigateTodayActivities() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("//div[(contains(text(),'Today'))]")).build().perform();  
		Thread.sleep(1000);
		act.click(driver.findElementByXPath("//div[(contains(text(),'Meetings'))]")).build().perform();
		//driver.findElementByXPath("//div[(contains(text(),'Meetings'))]").click();
		Thread.sleep(5000); 
	}

	@And("User types Subject as Project Status Status as Planned and Time as tomorrow 3 p.m")
	public void enterMeetingDetails() throws InterruptedException {
	    driver.findElementByXPath("//div[text()='Schedule Meeting']").click(); 
	    Thread.sleep(2000);
	    driver.findElementById("DetailFormname-input").sendKeys("Project Status"); 
	    
	    driver.findElementById("DetailFormdate_start-input").click(); 
		driver.findElementByXPath("//div[contains(@class, 'current selected responsive')]/following-sibling::div[1]").click(); 
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']").clear();
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']")
		.sendKeys("3:00pm", Keys.ENTER); 
	}

	@And("User clicks Add paricipants, add your created Lead name and click Save")
	public void addMeetingParticipants() throws InterruptedException {
		// Adding Participants 
		driver.findElementByXPath("//span[contains(text(),'Add Participants')]").click(); 
		driver.findElementByXPath("//div[@id='app-search-text']//input[@class='input-text']").sendKeys("Vinothkumar");
		act.click(driver.findElementByXPath("//div[@id='app-search-list']//div[(contains(text(),'Vinothkumar'))]")).build().perform();
		
		driver.findElementById("DetailForm_save2-label").click(); 
		Thread.sleep(5000);
	}

	@When("User clicks contacts under Sales and Marketing, search the lead Name and click the name from the result")
	public void navigateLeadContact() throws InterruptedException {
		act.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).build().perform();
		Thread.sleep(2000); 
		act.click(driver.findElementByXPath("//div[text()='Contacts']")).build().perform(); 
		Thread.sleep(2000); 
		
		driver.findElementById("filter_text").sendKeys("Vinothkumar"); 
		Thread.sleep(2000);
		act.click(driver.findElementByXPath("//div[text()='Mr. Vinothkumar S']")).build().perform();
		Thread.sleep(2000);
	}

	@Then("User checks whether the Meeting is assigned to the contact.")
	public void checkAssignedMeeting() {
	    WebElement anyMeeting = driver.findElementByXPath("//span[@id='subpanel-activities']/ancestor::div[@class='subpanel-entry']/descendant::span[@class='detailLink']"); 
	    
	    if (anyMeeting.isDisplayed()) { 
	    	System.out.println("Meeting is assigned to the Lead.");
	    } else { 
	    	System.out.println("No Meeting assigned.");
	    } 
	}


}
