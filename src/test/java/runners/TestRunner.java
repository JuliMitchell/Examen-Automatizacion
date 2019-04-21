package runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;


import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature",
		glue= {"stepDefinitions"},
		plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		tags = {"@TestReady"}
 )
public class TestRunner {
	@AfterClass
	 public static void writeExtentReport() {
	    String path = new File("configs/extent-config.xml").getAbsolutePath();
	    Reporter.loadXMLConfig(path);
	 }
}