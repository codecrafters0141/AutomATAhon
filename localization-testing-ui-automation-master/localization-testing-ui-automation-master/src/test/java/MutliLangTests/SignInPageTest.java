package MutliLangTests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.page.objects.SignInPage;

public class SignInPageTest extends BaseTest {
	SignInPage singInPage;

	@BeforeClass(alwaysRun = true)
	public void createInstance() {
		singInPage = new SignInPage(driver);
	}

	@Test(priority = 1, groups = {"Arabic","Chinese","Hindi","English","French","Regression"})
	public void signInEmailLabelTest() throws InterruptedException, URISyntaxException, IOException {
		extentReport.createTestNode(extentTestCaseNumber + "Sign In Email Label Test",
				"Verify Email Label in Sign in Page");
		extentReport.createTestStepNode("Click Sign In Button");
		singInPage.clickSignInBtn();
		extentReport.createTestStepNode("Verify Email ID Label");
		singInPage.verifyEmailIdLabel(languageFileReader.getUnicodeKeyValueinLangFile("emaillabel"));

	}

}
