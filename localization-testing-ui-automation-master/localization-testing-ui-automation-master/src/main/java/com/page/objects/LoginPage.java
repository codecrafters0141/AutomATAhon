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
/*	@FindBy(id = "login-form-username")
	WebElement Username;
	@FindBy(id = "login-form-username")
	WebElement Username; */
	

	@FindBy(xpath = "//input[@id='login-form-username']")
	WebElement Username;
	

	@FindBy(xpath = "//input[@id='login-form-password']")
	WebElement Password;
	
	@FindBy(id = "login")  ////h1[normalize-space()='System Dashboard']
	WebElement Submit;
	
	//input[@id='login']

	@FindBy(xpath = "h1[normalize-space()='System Dashboard']")
	WebElement systemDashboard;

	//input[@id='login-form-username']
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	public void validateLogin() throws InterruptedException, URISyntaxException {
		
			enterText(Username, "ata");
			LoggerMethods.logInfo("HomePage", "Login", "Enter User name");
			enterText(Password, "ata@123");
			LoggerMethods.logInfo("HomePage", "Login", "Enter password");
			
			Password.sendKeys(Keys.ENTER);
		//	clickElement(Submit);
			
			LoggerMethods.logInfo("HomePage", "Submit", "Click Submit");
			
			
			
			waitForVisibilityOfElement(systemDashboard);
			if (verifyElementDisplyed(systemDashboard))
				LoggerMethods.logInfo("HomePage", "verifyResultLabel", "System Dashboard is present");
			else {
				assertionClosure("HomePage", "verifyResultLabel", "System Dashboard is NOT present");
				
						
		} 
	}
	
	
	/*public void verifyCartBtnExist(String expBtnName) throws InterruptedException, URISyntaxException {
		try {
			waitForVisibilityOfElement(cartBtn);
			if (verifyElementDisplyed(cartBtn)) {
				String actualBtnName = getElementText(cartBtn);
				if (actualBtnName.equalsIgnoreCase(expBtnName))
					LoggerMethods.logInfo("HomePage", "verifyCartBtnExist", "Verify actual cart name: [" + actualBtnName
							+ "] with expected cart name: [" + expBtnName + "] is present");
				else {
					assertionClosure("HomePage", "verifyCartBtnExist", "Verify actual cart name: [" + actualBtnName
							+ "] with expected cart name: [" + expBtnName + "] is not present");
				}
			} else {
				assertionClosure("HomePage", "verifyCartBtnExist", "Cart button not displayed");
			}
		} catch (Exception e) {
			exceptionClosure("HomePage", "verifyCartBtnExist", e);
		}
	}
	
	

	public void searchMobiles() throws InterruptedException, URISyntaxException {
		try {
			enterText(searchBox, "Mobile");
			LoggerMethods.logInfo("HomePage", "searchMobiles", "Enter Mobile and Search");
		} catch (Exception e) {
			exceptionClosure("HomePage", "searchMobiles", e);
		}
	}
	
	

	public void verifyResultLabel(String expLabel) throws InterruptedException, URISyntaxException {
		try {
			waitForVisibilityOfElement(resultLabel);
			if (verifyElementDisplyed(resultLabel)) {
				String actualLabel = getElementText(resultLabel);
				if (actualLabel.equalsIgnoreCase(expLabel))
					LoggerMethods.logInfo("HomePage", "verifyResultLabel", "Verify actual result text: [" + actualLabel
							+ "] with expected result text: [" + expLabel + "] is present");
				else {
					assertionClosure("HomePage", "verifyResultLabel", "Verify actual result text: [" + actualLabel
							+ "] with expected result text: [" + expLabel + "] is not present");
				}
			} else {
				assertionClosure("HomePage", "verifyResultLabel", "Result label not displayed");
			}
		} catch (Exception e) {
			exceptionClosure("HomePage", "verifyResultLabel", e);
		}
	}
	
	public void selectMobile() throws InterruptedException, URISyntaxException {
		try {
			clickElement(resultMobile.get(0));
		} catch (Exception e) {
			exceptionClosure("HomePage", "SelectMobile", e);
		}
	}
	
	public void selectMobile(String mobileName) throws InterruptedException, URISyntaxException {
		boolean mobileFound =false;
		try {
				for (int result=0; result<resultMobile.size(); result++) {
					if(resultMobile.get(result).getText().contains(mobileName)) {
						mobileFound = true;
						clickElement(resultMobile.get(result));
						LoggerMethods.logInfo("HomePage", "SelectMobile", "Select Mobile from results");
						break;
					}
				}
				if (mobileFound) {
					LoggerMethods.logInfo("HomePage", "SelectMobile", "Mobile found [" +mobileName+ "]");
				}
				else {
					assertionClosure("HomePage", "selectMobile","Mobile not found [" +mobileName+ "]");
				}
				
		} catch (Exception e) {
			exceptionClosure("HomePage", "SelectMobile", e);
		}
	}
	
	public void clickAddToCart() throws InterruptedException, URISyntaxException {
		try {
			clickElement(addtoCartBtn);
			LoggerMethods.logInfo("HomePage", "clickAddToCart", "Click on Add to Cart button");
		} catch (Exception e) {
			exceptionClosure("HomePage", "clickAddToCart", e);
		}
	}
	
	public void verifyAddedToCartMessage(String expMessage) throws InterruptedException, URISyntaxException {
		try {
			waitForVisibilityOfElement(successMessage);
			if (verifyElementDisplyed(successMessage)) {
				String actualMessage = getElementText(successMessage);
				if (actualMessage.equalsIgnoreCase(expMessage))
					LoggerMethods.logInfo("HomePage", "verifyAddedToCartMessage", "Verify actual cart success message: [" + actualMessage
							+ "] with expected cart success message: [" + expMessage + "] is present");
				else {
					assertionClosure("HomePage", "verifyAddedToCartMessage", "Verify actual cart success message: [" + actualMessage
							+ "] with expected cart success message: [" + expMessage + "] is not present");
				}
			} else {
				assertionClosure("HomePage", "verifyAddedToCartMessage", "Cart success message not present ");
			}
		} catch (Exception e) {
			exceptionClosure("HomePage", "verifyAddedToCartMessage", e);
		}
	}
	
	public void goToCart() throws InterruptedException, URISyntaxException {
		try {
			clickElement(gotoCartBtn);
			LoggerMethods.logInfo("HomePage", "goToCart", "Click on Go to Cart button");
		} catch (Exception e) {
			exceptionClosure("HomePage", "goToCart", e);
		}
	}
	
	public void verifyCartDetails(String expName , String expAmount) throws InterruptedException, URISyntaxException {
		try {
			waitForVisibilityOfElement(itemNameLable);
			if (verifyElementDisplyed(itemNameLable)) {
				String actualName = getElementText(itemNameLable);
				if (actualName.contains(expName))
					LoggerMethods.logInfo("HomePage", "verifyCartDetails", "Verify actual cart deatils: [" + actualName
							+ "] with expected cart deatils: [" + expName + "] is present");
				else {
					assertionClosure("HomePage", "verifyAddedToCartMessage", "Verify actual cart deatils: [" + actualName
							+ "] with expected cart deatils: [" + expName + "] is not present");
				}
			} else {
				assertionClosure("HomePage", "verifyCartDetails", "Cart deatils not present ");
			}
			
			waitForVisibilityOfElement(itemAmoutLable);
			if (verifyElementDisplyed(itemAmoutLable)) {
				String actualAmount= getElementText(itemAmoutLable);
				if (actualAmount.contains(expAmount))
					LoggerMethods.logInfo("HomePage", "verifyCartDetails", "Verify actual cart deatils: [" + actualAmount
							+ "] with expected cart deatils: [" + expAmount + "] is present");
				else {
					assertionClosure("HomePage", "verifyAddedToCartMessage", "Verify actual cart deatils: [" + actualAmount
							+ "] with expected cart deatils: [" + expAmount + "] is not present");
				}
			} else {
				assertionClosure("HomePage", "verifyCartDetails", "Cart deatils not present ");
			}
			
		} catch (Exception e) {
			exceptionClosure("HomePage", "verifyCartDetails", e);
		} 
	} */
}

