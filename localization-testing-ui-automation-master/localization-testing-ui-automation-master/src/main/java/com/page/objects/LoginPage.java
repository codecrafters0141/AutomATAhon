package com.page.objects;

import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.CommomMethods;
import com.utility.LoggerMethods;

public class LoginPage extends CommomMethods {
	
	@FindBy(xpath = "//input[@id='login-form-username']")
	WebElement Username;

	@FindBy(xpath = "//input[@id='login-form-password']")
	WebElement Password;

	@FindBy(id = "login")
	WebElement Submit;

	@FindBy(xpath = "h1[normalize-space()='System Dashboard']")
	WebElement systemDashboard;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void validateLogin() throws InterruptedException, URISyntaxException {

		enterText(Username, "ata");
		LoggerMethods.logInfo("HomePage", "UserName", "Enter User name");

		enterText(Password, "ata@123");
		LoggerMethods.logInfo("HomePage", "Password", "Enter password");

		clickElement(Submit);

		LoggerMethods.logInfo("HomePage", "Submit", "Click Submit");

		waitForVisibilityOfElement(systemDashboard);
		if (verifyElementDisplyed(systemDashboard))
			LoggerMethods.logInfo("HomePage", "verifyResultLabel", "System Dashboard is present");
		else {
			assertionClosure("HomePage", "verifyResultLabel", "System Dashboard is NOT present");
		}
	}
}
