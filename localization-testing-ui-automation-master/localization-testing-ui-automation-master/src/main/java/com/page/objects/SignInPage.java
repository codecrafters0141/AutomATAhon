package com.page.objects;

import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.CommomMethods;
import com.utility.LoggerMethods;

public class SignInPage extends CommomMethods {
	@FindBy(xpath = "//a[@id='nav-link-accountList']")
	WebElement signInBtn;
	@FindBy(xpath = "//label[@for='ap_email']")
	WebElement emailLabel;

	public SignInPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickSignInBtn() throws InterruptedException, URISyntaxException {
		try {
			clickElement(signInBtn);
			LoggerMethods.logInfo("SignInPage", "clickSignInBtn", "Clicked on Sign In Button");
		} catch (Exception e) {
			exceptionClosure("SignInPage", "clickSignInBtn", e);
		}
	}

	public void verifyEmailIdLabel(String expLabel) throws InterruptedException, URISyntaxException {
		try {

			if (verifyElementDisplyed(emailLabel)) {
				String actualLabel = getElementText(emailLabel);
				if (actualLabel.equalsIgnoreCase(expLabel))
					LoggerMethods.logInfo("SignInPage", "verifyEmailIdLabel", "Verify actual email text: ["
							+ actualLabel + "] with expected email text: [" + expLabel + "] is present");
				else {
					assertionClosure("SignInPage", "verifyEmailIdLabel", "Verify actual email text: [" + actualLabel
							+ "] with expected email text: [" + expLabel + "] is not present");
				}
			} else {
				assertionClosure("SignInPage", "verifyEmailIdLabel", "Email label not diplayed");
			}
		} catch (Exception e) {
			exceptionClosure("SignInPage", "verifyEmailIdLabel", e);
		}
	}

}
