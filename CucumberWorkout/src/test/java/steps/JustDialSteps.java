package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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

	@And("User types Chennai and choose Chennai, IN - Chennai Airport (MAA) as Leaving From")
	public void selectDeparture() {
	    
	}

	@And("User types Toronto and select Toronto, CA - Toronto City Centre Airport (YTZ) as Going To")
	public void selectDestination() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User sets Departure as {int}, July {int}")
	public void userSetsDepartureAsJuly(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User adds Adult {int}, Children {int} click and Search")
	public void userAddsAdultChildrenClickAndSearch(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User selects Air Canada from multi-airline itineraries")
	public void userSelectsAirCanadaFromMultiAirlineItineraries() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User clicks on Price to sort the result")
	public void userClicksOnPriceToSortTheResult() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User clicks on +Details of first result under Price")
	public void userClicksOnDetailsOfFirstResultUnderPrice() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User captures the Flight Arrival times.")
	public void userCapturesTheFlightArrivalTimes() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@And("User captures the total price in a list and Click on Book")
	public void userCapturesTheTotalPriceInAListAndClickOnBook() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("User captures the Airport name base on the list of time")
	public void userCapturesTheAirportNameBaseOnTheListOfTime() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("User captures the total fare and print the difference amount from previous total price")
	public void userCapturesTheTotalFareAndPrintTheDifferenceAmountFromPreviousTotalPrice() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
