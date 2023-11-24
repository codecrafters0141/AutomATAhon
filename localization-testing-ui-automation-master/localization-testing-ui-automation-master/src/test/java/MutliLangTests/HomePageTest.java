package MutliLangTests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.page.objects.HomePage;

public class HomePageTest extends BaseTest {
	HomePage homePage;

	@BeforeClass(alwaysRun = true)
	public void createInstance() throws URISyntaxException {
		homePage = new HomePage(driver);
	}

	@Test(priority = 1, groups = { "Arabic","Chinese","Hindi","English","French","Regression" })
	public void cartButtonLabelTest() throws InterruptedException, URISyntaxException, IOException {
		extentReport.createTestNode(extentTestCaseNumber + "Cart Label Test", "Verify Cart Button Label");
		extentReport.createTestStepNode("Verify Cart Label");
		homePage.verifyCartBtnExist(languageFileReader.getUnicodeKeyValueinLangFile("labelcartbutton"));
	}

	@Test(priority = 2, groups = { "Smoke" })
	public void searchMobileTest() throws InterruptedException, URISyntaxException, IOException {
		extentReport.createTestNode(extentTestCaseNumber + "Search Mobile Test", "Verify Mobile Search Result text");
		extentReport.createTestStepNode("Enter Mobiles and Search");
		homePage.searchMobiles();
		extentReport.createTestStepNode("Verify Result Label");
		homePage.verifyResultLabel(languageFileReader.getUnicodeKeyValueinLangFile("resultlabel"));
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test(priority = 2, groups = { "Arabic" })
//	public void searchMobileTest() throws InterruptedException, URISyntaxException, IOException {
//		extentReport.createTestNode(extentTestCaseNumber + "Search Mobile Test", "Verify Mobile Search Result text");
//		String nameMobile = languageFileReader.getUnicodeKeyValueinLangFile("mobilename");
//		String cartMessage = languageFileReader.getUnicodeKeyValueinLangFile("cartsuccessmessage");
//		String amount = languageFileReader.getUnicodeKeyValueinLangFile("amount");
//		extentReport.createTestStepNode("Enter Mobiles and Search");
//		homePage.searchMobiles(nameMobile);
//		extentReport.createTestStepNode("Select Mobile");
//		homePage.selectMobile(nameMobile);
//		extentReport.createTestStepNode("Click on Add to Cart and Verify Success Message");
//		homePage.clickAddToCart();
//		homePage.verifyAddedToCartMessage(cartMessage);
//		extentReport.createTestStepNode("Go to Cart And Verify Cart Details");
//		homePage.goToCart();
//		homePage.verifyCartDetails(nameMobile,amount);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
		
	












		
		
		
	
