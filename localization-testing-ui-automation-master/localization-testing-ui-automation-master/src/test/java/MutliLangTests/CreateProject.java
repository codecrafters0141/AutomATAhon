package MutliLangTests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.page.objects.CreateProjectPage;
import com.page.objects.LoginPage;
import com.page.objects.SystemDashboard;

public class CreateProject extends BaseTest {
	SystemDashboard systemDashboard;
	CreateProjectPage createProjectPage;

	@BeforeClass(alwaysRun = true)
	public void createInstance() throws URISyntaxException {
		systemDashboard = new SystemDashboard(driver);
		createProjectPage = new CreateProjectPage(driver);
	}

	@Test
	public void CreateProject() throws InterruptedException, URISyntaxException, IOException {
		extentReport.createTestNode(extentTestCaseNumber + "Create Project", "Create Project");
		extentReport.createTestStepNode("Verify Login");
		systemDashboard.CreateProject();
		createProjectPage.CreateProject();
	}		

}
		
	









		
		
		
	

