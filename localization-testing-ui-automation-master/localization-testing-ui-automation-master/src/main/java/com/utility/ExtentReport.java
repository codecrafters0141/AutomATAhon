package com.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.config.reader.ConfigFileReader;

public class ExtentReport {

	ExtentSparkReporter htmlReporter1;
	ExtentSparkReporter htmlReporter2;
	ExtentReports extent1;
	ExtentReports extent2;
	ExtentTest test1;
	ExtentTest test2;
	ExtentTest testStep1;
	ExtentTest testStep2;
	String toLangCode;
	String fromLangCode = "en";
	String reportPath = System.getProperty("user.dir") + "/Reports/";
	String documnetTitle = "Localization Automation Report";
	String reportName = "Test Report";
	boolean multiLangReport;

	public void startReport() throws IOException {
		htmlReporter1 = new ExtentSparkReporter(reportPath + "testReport_eng.html");
		extent1 = new ExtentReports();
		createReportInstance(htmlReporter1, extent1, documnetTitle, reportName);
		multiLangReport = Boolean.valueOf(ConfigFileReader.getConfigPropery("multiLanguageReport"));
		if (multiLangReport) {
			toLangCode = System.getProperty("languageCode");
			if (toLangCode.equalsIgnoreCase(fromLangCode)) {
				multiLangReport = false;
			}
		}
		if (multiLangReport) {
			htmlReporter2 = new ExtentSparkReporter(
					reportPath + "testReport_" + System.getProperty("language") + ".html");
			extent2 = new ExtentReports();
			documnetTitle = Translator.translate(fromLangCode, toLangCode, documnetTitle);
			reportName = Translator.translate(fromLangCode, toLangCode, reportName);
			createReportInstance(htmlReporter2, extent2, documnetTitle, reportName);
		}
	}

	public void createReportInstance(ExtentSparkReporter htmlReporter, ExtentReports extent, String docTitle,
			String reportName) throws IOException {
		htmlReporter.config().setDocumentTitle(docTitle);
	//	htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version", System.getProperty("os.version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Localization Testing Language", System.getProperty("language"));
		extent.setSystemInfo("Environment", "Testing");
		
	//	htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/resources/Reports/"+"ExtentReport_" + sdf.format(now) + ".html");

		htmlReporter.loadXMLConfig(new File("extentconfig2.xml"));
		extent.attachReporter(htmlReporter);

		String customCSSContent = readCustomCSSFile(System.getProperty("user.dir")+"/Style.css");
		htmlReporter.config().setCss(customCSSContent);

		String logoPath = System.getProperty("user.dir")+"/images.png";
		String reportNameNew = String.format("<div><img src='%s' style='max-width: 50px; max-height: 50px;' /> ATA automation report</div>", logoPath);
		htmlReporter.config().setReportName(reportNameNew);
		
	}

	public void createTestNode(String testName, String testDescription) {
		test1 = extent1.createTest(testName, testDescription);
		if (multiLangReport) {
			testName = Translator.translate(fromLangCode, toLangCode, testName);
			testDescription = Translator.translate(fromLangCode, toLangCode, testDescription);
			test2 = extent2.createTest(testName, testDescription);
		}
	}

	public void createTestStepNode(String testStepName) {
		testStep1 = test1.createNode(testStepName);
		testStep1.log(Status.INFO, testStepName);
		if (multiLangReport) {
			testStepName = Translator.translate(fromLangCode, toLangCode, testStepName);
			testStep2 = test2.createNode(testStepName);
			testStep2.log(Status.INFO, testStepName);
		}
	}

	public void addResult(ITestResult result, WebDriver driver) throws Exception {
		addResultInstance(test1, testStep1, result, driver);
		if (multiLangReport) {
			addResultInstance(test2, testStep2, result, driver);
		}
	}

	public void addResultInstance(ExtentTest test, ExtentTest testStep, ITestResult result, WebDriver driver)
			throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			testStep.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getTestName());
		} else {
			test.log(Status.SKIP, result.getTestName());
		}
		String screenshotPath = ScreenshotUtility.getScreenshot(driver, result.getName());
		test.addScreenCaptureFromPath(screenshotPath);
	}

	private static String readCustomCSSFile(String filePath) throws IOException {
	    return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	public void flushReport() {
		extent1.flush();
		if (multiLangReport) {
			extent2.flush();
		}

	}
}