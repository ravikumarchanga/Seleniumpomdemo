package org.sce.chatbot;




import org.apache.log4j.Logger;
import org.sce.commons.TestBase;
import org.sce.utils.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;







public class ChatBotSuiteBase extends TestBase {


	
	
	public static Logger logger = Logger.getLogger(new RuntimeException().getStackTrace()[1].getClassName());

	@BeforeSuite
	public void checkSuiteSkip() {
		
		initialization();
		invokeBrowser();
		baseUrl();
	
		
		
		html=new ExtentHtmlReporter("../SCE_Chat/Reports/extent.html");
		
		//html=new ExtentHtmlReporter("extent.html");
		//html=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\extent.html");
		//html = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
		extent.setSystemInfo("OS", "WINDOWS");
		extent.setSystemInfo("Environment", "QA");
		html.config().setDocumentTitle("Automation Testing Reports");
		html.config().setTestViewChartLocation(ChartLocation.TOP);
		html.config().setTheme(Theme.DARK);
		
		

		logger.debug("Checking Runmode of masterSuiteExcel");
		if (!TestUtil.verifyModuleExecutable(masterSuiteExcel, "ChatBotSuite")) {

			// System.out.println(Smoketestsuitexls.getRowCount("testcase"));
			logger.debug("Skipping the execution of ChatBotSuite as the runmode of the suite was set to NO");
			throw new SkipException("RunMode of Module1Suite  is set to No, therefore skipping all test cases in ChatBotSuite");
		}

	}

	@AfterSuite
	public void tearDown() {
		
		extent.flush();
		wDriver.quit();
	
	}

}
