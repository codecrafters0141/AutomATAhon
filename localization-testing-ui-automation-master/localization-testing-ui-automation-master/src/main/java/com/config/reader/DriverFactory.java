package com.config.reader;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.Assert;

import com.utility.LoggerMethods;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class DriverFactory {
	public WebDriver driver;

	public WebDriver initDriver(String browserName) throws URISyntaxException, IOException {

		if (browserName.equalsIgnoreCase("default browser")) {
			browserName = ConfigFileReader.getConfigPropery("defaultTestBrowser");
		}

		LoggerMethods.logInfo("DriverFactory", "initDriver", "Browser Name is : [" + browserName + "]");
		try {
			switch (browserName.toLowerCase()) {
			case "edge":
				driver = new EdgeDriver();
				break;
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox");
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("disable-infobars"); 
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-dev-shm-usage");
//				options.addArguments("--headless");
//				options.addArguments("window-size=1920,1200");
				driver = new ChromeDriver(options);
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				LoggerMethods.logInfo("DriverFactory", "initDriver",
						"Failure >>>>>>>>>>>>>>>>:" + "Invalid Browser Name");
				Assert.fail("Failure >>>>>>>>>>>>>>>>:" + "Invalid Browser Name");
				break;
			}
			return driver;
		} catch (Exception e) {
			LoggerMethods.logInfo("DriverFactory", "initDriver",
					"Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
			Assert.fail("Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
		}
		return driver;
	}

	public void launchApplication(String url) throws URISyntaxException {
		try {
			driver.get(url);
			driver.manage().window().maximize();
			 driver.navigate().refresh();
			LoggerMethods.logInfo("DriverFactory", "launchApplication", "Application is launched");
		} catch (Exception e) {
			LoggerMethods.logInfo("DriverFactory", "launchApplication",
					"Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
			Assert.fail("Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
		}

	}

	public void closeDriver() throws URISyntaxException {
		try {
			driver.quit();
			LoggerMethods.logInfo("DriverFactory", "closeDriver", "Browser is Closed");
		} catch (Exception e) {
			LoggerMethods.logInfo("DriverFactory", "closeDriver", e.toString() + " " + e.getMessage());
			Assert.fail("Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
		}
	}

}
