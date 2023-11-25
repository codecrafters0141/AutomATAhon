package MutliLangTests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.config.reader.ConfigFileReader;
import com.config.reader.DriverFactory;
import com.config.reader.LanguageFileReader;
import com.utility.ExtentReport;
import com.utility.LoggerMethods;

public class BaseTest {
	static WebDriver driver;
	static LanguageFileReader languageFileReader = new LanguageFileReader();
	DriverFactory driverFactory = new DriverFactory();
	static ExtentReport extentReport = new ExtentReport();
	static int testCaseNumber = 0;
	String extentTestCaseNumber;

	@Parameters({ "browser" })
	@BeforeSuite(alwaysRun = true)
	public void initializationBeforeSuite(@Optional("Default Browser") String browserName)
			throws URISyntaxException, IOException {
		LoggerMethods.initializeLog();
		ConfigFileReader.initializeConfigFile();
		driver = driverFactory.initDriver(browserName);
		LoggerMethods.logInfo("Base Test", "initializationBeforeSuite", "Initalized log, report, browser instance");
	}

	@Parameters({ "language" })
		@BeforeTest(alwaysRun = true)
		public void setupBeforeTest(@Optional("Default Language") String languageName)
				throws IOException, URISyntaxException {
			languageFileReader.setLangCodeProperty(languageName);
			languageFileReader.initLangFile();
			extentReport.startReport();
			driverFactory.launchApplication(ConfigFileReader.getConfigPropery("URL"));
			LoggerMethods.logInfo("Base Test", "setupBeforeTest", "Property file loaded");
		}

	@BeforeMethod(alwaysRun = true)
	public void setupBeforeMethod(Method testMethod) throws Exception {
		testCaseNumber++;
		LoggerMethods.steps = 1;
		extentTestCaseNumber = "Test Case " + testCaseNumber + ": ";
		LoggerMethods.logInfo(testMethod.getDeclaringClass().getSimpleName(), testMethod.getName(),
				"Test Case " + testCaseNumber + ": STARTED");
	}

	@AfterMethod(alwaysRun = true)
	public void addTestResult(ITestResult result) throws Exception {
		extentReport.addResult(result, driver);
		LoggerMethods.logInfo("Base Test", "addTestResult", "Test Case FINISHED");
		LoggerMethods.steps = 1;
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownAfterSuite() throws URISyntaxException {
	//	driverFactory.closeDriver();
		extentReport.flushReport();
		LoggerMethods.logInfo("Base Test", "tearDownAfterSuite", "Tear down activity finished");
	}

}
