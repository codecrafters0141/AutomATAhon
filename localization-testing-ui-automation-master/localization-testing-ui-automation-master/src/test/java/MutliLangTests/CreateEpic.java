package MutliLangTests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.page.objects.CreateEpicPage;
import com.page.objects.LoginPage;

public class CreateEpic extends BaseTest {
	CreateEpicPage createEpic;
	LoginPage logInPage;


	@BeforeClass(alwaysRun = true)
	public void createInstance() throws URISyntaxException {
		createEpic = new CreateEpicPage(driver);
		logInPage = new LoginPage(driver);
	}

	@Test
	public void logInTest() throws InterruptedException, URISyntaxException, IOException {
		extentReport.createTestNode(extentTestCaseNumber + "Login Test", "Verify Login");
		extentReport.createTestStepNode("Verify Login"); //validateLogin
		logInPage.validateLogin();
		createEpic.clickIssueType();
	}	

}
