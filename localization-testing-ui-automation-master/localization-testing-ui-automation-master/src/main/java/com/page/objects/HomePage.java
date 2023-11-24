package com.page.objects;

import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.CommomMethods;
import com.utility.LoggerMethods;

public class HomePage extends CommomMethods {
	@FindBy(xpath = "//*[@id='nav-cart-text-container']")
	WebElement cartBtn;

	@FindBy(xpath = "//*[@id='twotabsearchtextbox']")
	WebElement searchBox;

	@FindBy(xpath = "((//*[contains(@data-cel-widget,'search_result_')]//*[contains(@class,'TOP_BANNER_MESSAGE')])[1]//span)[2]")
	WebElement resultLabel;

	@FindBy(xpath = "//a[contains(@class,'a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal')]/span")
	List<WebElement> resultMobile;
	
	@FindBy(xpath = "//*[@id='add-to-cart-button']")
	WebElement addtoCartBtn;
	
	@FindBy(xpath = "//*[contains(@id,'MSG_SUCCESS')]")
	WebElement successMessage;
	
	@FindBy(xpath = "//*[@id='sw-gtc']")
	WebElement gotoCartBtn;
	
	@FindBy(xpath = "//span[@class='a-truncate-cut']")
	WebElement itemNameLable;
	
	@FindBy(xpath = "//span[@id='sc-subtotal-label-activecart']")
	WebElement itemCountLable;
	
	@FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']")
	WebElement itemAmoutLable;
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyCartBtnExist(String expBtnName) throws InterruptedException, URISyntaxException {
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
	
	public void searchMobiles(String mobileName) throws InterruptedException, URISyntaxException {
		try {
			enterText(searchBox, mobileName);
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
	}
}
