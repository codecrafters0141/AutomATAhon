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

public class CreateProjectPage extends CommomMethods {
	
	@FindBy(xpath = "//div[@title='Kanban software development']]")
	WebElement Kanban;

	@FindBy(xpath = "//button[@class='create-project-dialog-create-button pt-submit-button aui-button aui-button-primary']")
	WebElement NextBtn;

	@FindBy(xpath = "//div[@class='workflow kanban-workflow-screenshot']")
	WebElement Workflow;
	
	@FindBy(xpath = "//button[@class='template-info-dialog-create-button pt-submit-button aui-button aui-button-primary']")
	WebElement Select;
	
	@FindBy(xpath = "//input[@id='name']")
	WebElement Name;
	
	@FindBy(xpath = "//button[@class='add-project-dialog-create-button pt-submit-button aui-button aui-button-primary']")
	WebElement Submit;
	

	public CreateProjectPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void CreateProject() throws InterruptedException, URISyntaxException {
		
		waitForVisibilityOfElement(Kanban);
		
		clickElement(Kanban);
		LoggerMethods.logInfo("SystemDashboard", "Projects", "Click projects");
		
		clickElement(NextBtn);
		LoggerMethods.logInfo("SystemDashboard", "Click Projects", "Click create project");	
		
		waitForVisibilityOfElement(Workflow);
		
		clickElement(Select);
		LoggerMethods.logInfo("SystemDashboard", "Projects", "Select");
		
		enterText(Name, "Sample Test Project");
		LoggerMethods.logInfo("SystemDashboard", "Name", "Enter name");
		
		clickElement(Submit);
		LoggerMethods.logInfo("SystemDashboard", "Submit", "Submit");
				
		
		}
	}
