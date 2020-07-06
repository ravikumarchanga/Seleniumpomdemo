package org.sce.chatbot;

	
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sce.commons.ReusableMethods;
import org.sce.utils.TestUtil;
import org.testng.Assert;
import org.testng.ITestResult;
	import org.testng.SkipException;


public class ChatBotTest  extends ChatBotSuiteBase {
	
	
	


		static int count = 1;
		static boolean pass = false;
		static boolean fail = false;
		static boolean skip = false;
		static boolean isTestPass = true;

		public static Logger logger = Logger.getLogger(new RuntimeException().getStackTrace()[1].getClassName());

		@BeforeTest
		public void checkTestSkip() {
			

			if (!TestUtil.verifyTestCaseExecutable(botSuiteExcel, this.getClass().getSimpleName())) {
				logger.info("Skipping execution of" + this.getClass().getSimpleName()
						+ " because runmode of test case set to NO ");
				throw new SkipException("Skipping execution of" + this.getClass().getSimpleName()
						+ " because runmode of test case set to NO ");
			
			
			}
			
			
			

			
			
			
		
		


		}

		@Test(dataProvider="getTestData")
		

		public void verifyChatBotContent(String xpath, String data, String expValue) {
			
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			test = extent.createTest("verifyChatBotContent");
			
		
			System.out.println("Executing " + this.getClass().getSimpleName() + ".....");
			logger.debug("Executing " + this.getClass().getSimpleName() + ".....");

			try {
				
				ReusableMethods.enterText("chatInput", data);
				
				ReusableMethods.click("chatSendButton");
				System.out.println(xpath);
				
				Thread.sleep(5000);
				WebElement element=wDriver.findElement(By.xpath(xpath));
				
				
				String actual = element.getText();
				
				Assert.assertEquals(actual, expValue);
				Thread.sleep(1000);
				logger.info("Actual is : :" + actual + "Expected is : : " + expValue);
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
					
					
					
				
					
		
	
		
			} catch (Throwable t) {
				logger.error(t.getMessage());

			}

		}
		
		@AfterMethod
		public void test1(ITestResult result){
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "TestCase Failed", ExtentColor.RED));
				test.fail(result.getThrowable());

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "TestCase Pass", ExtentColor.GREEN));

			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "TestCase Skipped", ExtentColor.YELLOW));
				result.getThrowable();
			}

		}

		@AfterTest
		public void reportTestResult() {
			

			if (isTestPass) {
				TestUtil.reportTestResult(botSuiteExcel, this.getClass().getSimpleName(),
						TestUtil.getRowNum(botSuiteExcel, this.getClass().getSimpleName()), "Pass");
			} else {
				TestUtil.reportTestResult(botSuiteExcel, this.getClass().getSimpleName(),
						TestUtil.getRowNum(botSuiteExcel, this.getClass().getSimpleName()), "Fail");

			}
			//extent.flush();
			
		}
		
		

		@DataProvider

		public Object[][] getTestData() {
			return TestUtil.getdata(botSuiteExcel, this.getClass().getSimpleName());

		}

	}



