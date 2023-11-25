package com.page.objects;

import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.CommomMethods;
import com.utility.LoggerMethods;

public class CreateEpicPage extends CommomMethods{
	@FindBy(xpath = "//a[@id='create_link']")
	WebElement CreateIssueType;
	
	@FindBy(xpath = "//input[@id='customfield_10102']")
	WebElement EpicName;
	
	@FindBy(xpath = "//input[@id='issuetype-field']")
	WebElement issueTypeDropDown;
	
	@FindBy(xpath = "//input[@id='summary']")
	WebElement SummaryField;
	
	@FindBy(xpath = "//button[normalize-space()='Text']")
	WebElement DescriptionText;
	
	
	public CreateEpicPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickIssueType() throws InterruptedException, URISyntaxException {
		try {
			clickElement(CreateIssueType);
			LoggerMethods.logInfo("CreateIssueType", "Submit", "Click Submit");
			
			clickElement(EpicName);
			enterText(EpicName, "Sample Project");
			LoggerMethods.logInfo("SelectE", "Submit", "Click Submit");
			
		} catch (Exception e) {
			exceptionClosure("HomePage", "Login unseccusful", e);
		}

	}
}
