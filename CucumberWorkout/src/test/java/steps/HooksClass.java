package steps;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksClass { 
	
	private static RemoteWebDriver driver; 
	
	@Before
	public void setUp() { 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe"); 
	    System.setProperty("webdriver.chrome.silentOutput", "true"); 
	    
	    ChromeOptions options = new ChromeOptions(); 
	    options.addArguments("--disable-notifications"); 
	    
	    DesiredCapabilities cap = new DesiredCapabilities(); 
	    cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
	    options.merge(cap); 
	    
	    driver = new ChromeDriver(options); 
	} 
	
	@After 
	public void tearDown() { 
		driver.quit();
	} 
	
	public static RemoteWebDriver getDriver() {
		return driver; 
	}

}
