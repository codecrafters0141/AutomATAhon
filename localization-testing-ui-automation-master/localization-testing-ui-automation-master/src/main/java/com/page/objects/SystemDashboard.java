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

public class SystemDashboard extends CommomMethods {
	
	@FindBy(xpath = "//a[@id='browse_link']")
	WebElement Projects;

	@FindBy(xpath = "//a[@id='project_template_create_link_lnk']")
	WebElement CreateProject;


	public SystemDashboard(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void CreateProject() throws InterruptedException, URISyntaxException {
		
		clickElement(Projects);
		LoggerMethods.logInfo("SystemDashboard", "Projects", "Click projects");

		waitForVisibilityOfElement(CreateProject);
		
		clickElement(CreateProject);
		LoggerMethods.logInfo("SystemDashboard", "Click Projects", "Click create project");
		
		
		
		
		}
	}
