package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(features = "src/test/java/features/CarWale.feature", glue = "steps", 
monochrome = true, snippets = SnippetType.CAMELCASE) 
public class CarWaleRunner extends AbstractTestNGCucumberTests { 
	

}
