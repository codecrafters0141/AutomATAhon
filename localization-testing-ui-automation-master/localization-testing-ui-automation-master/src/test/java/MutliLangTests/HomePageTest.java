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

	@Test
	public void cartButtonLabelTest() throws InterruptedException, URISyntaxException, IOException {
		extentReport.createTestNode(extentTestCaseNumber + "Cart Label Test", "Verify Cart Button Label");
		extentReport.createTestStepNode("Verify Cart Label");
		homePage.verifyCartBtnExist(languageFileReader.getUnicodeKeyValueinLangFile("labelcartbutton"));
	}		

}
		
	












		
		
		
	
