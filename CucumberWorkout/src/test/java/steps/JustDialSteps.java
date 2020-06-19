package steps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JustDialSteps { 
	
	public RemoteWebDriver driver; 
	Actions act; 
	JavascriptExecutor js; 
	
	int totalTicketFare;
	
	public JustDialSteps() {
		this.driver = HooksClass.getDriver(); 
	}

	@Given("User launches the JustDial website")
	public void loadJustDial() {
	    driver.get("https://www.justdial.com/"); 
	    driver.manage().window().maximize(); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}

	@And("User clicks on Air Tickets")
	public void clickAirTickets() {
	    driver.findElementByXPath("//span[text()='Air Tickets']").click();
	}

	@And("User types Chennai and choose Chennai, IN - Chennai Airport \\(MAA) as Leaving From")
	public void selectDeparture() throws InterruptedException {
	    driver.findElementById("departure").sendKeys("Chennai");
	    Thread.sleep(1000);
	    driver.findElementByXPath("//li[text()='Chennai, IN - Chennai Airport (MAA)']").click();
	}

	@And("User types Toronto and select Toronto, CA - Toronto City Centre Airport \\(YTZ) as Going To")
	public void selectDestination() throws InterruptedException {
		driver.findElementById("arrival").sendKeys("Toronto");
		Thread.sleep(1000);
		driver.findElementByXPath("//li[text()='Toronto, CA - Toronto City Centre Airport (YTZ)']").click();
	}

	@And("User sets Departure as 2020, July 22")
	public void setDepartureDate() {
	    driver.findElementById("departDate").click();
	    driver.findElementByXPath("//a[@title='Next']").click(); 
	    driver.findElementByXPath("//a[text()='22']").click();
	}

	@And("User adds Adult 2, Children 1 click and Search")
	public void setPassengers() throws InterruptedException {
	    driver.findElementByXPath("//span[@id='dAdultsCountSelector']//span[@class='plus']").click();
	    driver.findElementByXPath("//span[@id='dChildrenCountSelector']//span[@class='plus']").click(); 
	    driver.findElementByXPath("//input[@value='SEARCH']").click(); 
	    Thread.sleep(3000);
	}

	@And("User selects Air Canada from multi-airline itineraries")
	public void selectItineraries() {
	    driver.findElementByXPath("//label[text()='Air Canada(AC)']").click();
	}

	@And("User clicks on Price to sort the result")
	public void sortResult() {
	    driver.findElementByXPath("//a[contains(text(),'Price')]").click();
	}

	@And("User clicks on +Details of first result under Price")
	public void clickFirstDetails() throws InterruptedException {
	    driver.findElementById("resTD1").click(); 
	    Thread.sleep(2000);
	}

	@And("User captures the Flight Arrival times.")
	public void getFlightArrivalTime() throws InterruptedException {
	    
		HashMap<String, String> flightTimeMap = new HashMap<String, String>(); 
		
		List<WebElement> arrivalTimes = driver.findElementsByXPath("//td[@id='result1td1']//td[6]"); 
		List<WebElement> arrivalDates = driver.findElementsByXPath("//td[@id='result1td1']//tr[@class='childText']//td[4]");
		
		for (int i = 0; i < arrivalTimes.size(); i++) {
			flightTimeMap.put(arrivalTimes.get(i).getText(), arrivalDates.get(i).getText()); 
		} 
		
		System.out.println("\nArrival Time with Dates: "); 
		
		// Printing the Map with Lambda 
		flightTimeMap.entrySet().forEach(entry->{System.out.println(entry.getKey() + "---" + entry.getValue());});
		
		// Conventional for each 
		/*
		 * for (Entry<String, String> eachEntry : flightTimeMap.entrySet()) {
		 * System.out.println(eachEntry.getKey() + "---" + eachEntry.getValue()); }
		 */
		
	    Thread.sleep(3000);
	}

	@And("User captures the total price in a list and Click on Book")
	public void getPriceAndBook() throws InterruptedException {
	    String totalTicketStr = driver.findElementByXPath("//span[text()='Total']/parent::li//div//span").getText(); 
	    
	    totalTicketFare = Integer.parseInt(totalTicketStr.replaceAll("\\D", "")); 
	    System.out.println("\nTotal fare of the ticket: " + totalTicketFare);
	    
	    driver.findElementByXPath("(//a[@class='bookButton'])[1]").click(); 
	    Thread.sleep(3000);
	}

	@When("User captures the Airport name base on the list of time")
	public void getAirportName() throws InterruptedException {
	    
		LinkedHashMap<String, String> airportNameMap = new LinkedHashMap<String, String>();
		
		List<WebElement> depAirportNames = driver.findElementsByXPath("//table[@id='innerDiv']//tr[@class='childText']//td[2]");
	    
	    List<WebElement> destAirtportNames = driver.findElementsByXPath("//table[@id='innerDiv']//tr[@class='childText']//td[4]"); 
	    
	    for (int i = 0; i < depAirportNames.size(); i++) {
	    	airportNameMap.put(depAirportNames.get(i).getText(), destAirtportNames.get(i).getText());
	    } 
	    
	    System.out.println("\nAirport Names of Departure and Arrival:");
	    // Printing Map in conventional way 
	    for (Entry<String, String> eachEntry : airportNameMap.entrySet()) {
			System.out.println(eachEntry.getKey() + "---" + eachEntry.getValue());
		} 
	    
	    Thread.sleep(2000);
	}

	@Then("User captures the total fare and print the difference amount from previous total price")
	public void checkNetFare() {
	    String finalFareStr = driver.findElementById("totalFare").getText(); 
	    
	    int finalFare = Integer.parseInt(finalFareStr.replaceAll("\\D", "")); 
	    
	    System.out.println("\nFinal fare: " + finalFare);
	    
	    System.out.println("\nNet Fare difference: " + (finalFare-totalTicketFare));
	    
	}
}
