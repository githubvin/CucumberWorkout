package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(features = "src/test/java/features/Trivago.feature", glue = "steps", monochrome = true, 
snippets = SnippetType.CAMELCASE)
public class TrivagoRunner extends AbstractTestNGCucumberTests{

}
