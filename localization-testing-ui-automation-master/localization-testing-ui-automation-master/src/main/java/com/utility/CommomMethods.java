package com.utility;

import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommomMethods {
	WebDriver driver;
	public WebDriverWait wait;
	public long waitTime = 40;

	public CommomMethods(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitForVisibilityOfElement(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean verifyElementDisplyed(WebElement element) {
		return element.isDisplayed();
	}

	public void clickElement(WebElement element) {
		element.click();
	}

	public String getElementText(WebElement element) {
		waitForVisibilityOfElement(element);
		return element.getText().trim();
	}

	public void enterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text, Keys.ENTER);
	}

	public void assertionClosure(String className, String methodName, String infoMessage) throws URISyntaxException {
		LoggerMethods.logInfo(className, methodName, "Failure >>>>>>>>>:" + infoMessage);
		Assert.fail("Failure >>>>>>>>>:" + infoMessage);
	}

	public void exceptionClosure(String className, String methodName, Exception e) throws URISyntaxException {
		LoggerMethods.logInfo(className, methodName, "Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
		Assert.fail("Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
	}
}
