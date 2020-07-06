package org.sce.commons;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import com.aventstack.extentreports.MediaEntityBuilder;



public class ReusableMethods extends TestBase {
	 
		static String destFile;
	
	static String path;

	public static Logger logger = Logger.getLogger(new RuntimeException().getStackTrace()[1].getClassName());
    
	public static void implicitlyWait() {
		wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void HighLightElement(WebElement element) {
		wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor jse = (JavascriptExecutor) wDriver;
		jse.executeScript("arguments[0].style.border='3px solid blue'", element);
	}

	
	
	public static void clear(String object) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed()) {
				//HighLightElement(element);
				Thread.sleep(1000);
				element.clear();
				logger.info("Cleared Input Field");
			} else {
				logger.error("Unable to Clear Input Field");

			}
		} catch (Exception e) {
			logger.error(e);

		}

	}
	
	
    public static void scrollToElement(String object){
    	WebElement element =getElement(wDriver, objectProp.getProperty(object));
    	((JavascriptExecutor) wDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
	public static void enterText(String object, String data) {
    	
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed()) {
				
				element.sendKeys(data);
				Thread.sleep(1000);
			     path=takeScreenshot(object);
			     test.pass("Entered Data in : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				
				logger.info("Entered Data in Input Field");
			} else {
				test.fail("Unable to Enter Data in : : " + object,MediaEntityBuilder.createScreenCaptureFromPath(path).build());

				logger.error("Unable to Enter Data in Input Field");
			}

		} catch (Exception e) {
			logger.error(e);

		}

	}
	public static void performDoubleClick(String object){
		try {
			
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			
			
			if (element.isDisplayed()) {
				
				Actions action=new Actions(wDriver);
				action.doubleClick(element).build().perform();
				//HighLightElement(element);
				path=takeScreenshot(object);
			
			test.pass("DoubleClick Performed : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			logger.info("DoubleClick Performed"); 
		} else {
			test.fail("Unable to Perfrom DoubleClick", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			logger.error("Unable to Perform DoubleClick");
		}
	} catch (Exception e) {
		logger.error(e);
	}
	}
	
	
	
	
	public static void performRightClick(String object){
		try {
			
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			
			
			if (element.isDisplayed()) {
				
				Actions action=new Actions(wDriver);
				action.contextClick(element).build().perform();
				//HighLightElement(element);
				path=takeScreenshot(object);
			
			test.pass("RightClick Performed : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			logger.info("RightClick Performed"); 
		} else {
			test.fail("Unable to Perfrom RightClick", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			logger.error("Unable to Perform RightClick");
		}
	} catch (Exception e) {
		logger.error(e);
	}
	}
	
	
	
	
	
	
	public static void performMouseover(String object){
		try {
			
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			
			
			if (element.isDisplayed()) {
				
				Actions action=new Actions(wDriver);
				action.moveToElement(element).build().perform();
				//HighLightElement(element);
				path=takeScreenshot(object);
			
			test.pass("Mouseover Performed : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			logger.info("Mouseover Performed"); 
		} else {
			test.fail("Unable to Perfrom Mouseover", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			logger.error("Unable to Perform Mouseover");
		}
	} catch (Exception e) {
		logger.error(e);
	}
	}
	
	public static void performDragAndDrop(String dragElement, String dropElement){
		try {
		
		wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement dragelement = getElement(wDriver, objectProp.getProperty(dragElement));
		WebElement dropelement = getElement(wDriver, objectProp.getProperty(dropElement));
		
		if (dragelement.isDisplayed()) {
			
			Actions action=new Actions(wDriver);
			action.dragAndDrop(dragelement, dropelement).build().perform();
			//HighLightElement(element);
			path=takeScreenshot(dropElement);
		
		test.pass("Drag & Drop Performed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		logger.info("Drag & Drop Performed"); 
	} else {
		test.fail("Unable to Perfrom Drag & Drop", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		logger.error("Unable to Perform Drag & Drop");
	}
} catch (Exception e) {
	logger.error(e);
}
	}

	public static void click(String object) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed() && element.isEnabled()) {
				//HighLightElement(element);
				path=takeScreenshot(object);
				Thread.sleep(2000);
				element.click();
				test.pass("Clicked  : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.info("Clicked  : : " + object ); 
			} else {
				test.fail("Unable to Click : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.error("Unable to Click : : " + object);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void selectRadioButton(String object) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed() && element.isEnabled()) {
				//HighLightElement(element);
				path=takeScreenshot(object);
				Thread.sleep(1000);
				element.click();
				test.pass("Selected  : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.info("Selected :: " + object);
			} else {
				test.fail("Unable to Select : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.error("Unable to Select : : " + object);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void selectCheckBox(String object) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed() && element.isEnabled()) {
				//HighLightElement(element);
				path=takeScreenshot(object);
				element.click();
				Thread.sleep(1000);

				test.pass("Selected  : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.info("Selected :: " + object);
			} else {
				test.fail("Unable to Select : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.error("Unable to Select : : " + object);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void selectDropdownByIndex(String object, int option) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed()) {
				//HighLightElement(element);
				path=takeScreenshot(object);
				Select select = new Select(element);
			
				select.selectByIndex(option);
				Thread.sleep(1000);
				test.pass("Selected Option from :: " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.info("Selected Option from : : " + object);
			} else {
				test.fail("Unable to Select : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.error("Unable to Select Option from :: "+ object);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void selectDropdownByValue(String object, String option) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed()) {
				//HighLightElement(element);
				path=takeScreenshot(object);
			
				Select select = new Select(element);
				select.selectByValue(option);
				Thread.sleep(1000);
				test.pass("Selected Option from :: " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.info("Selected Option from : : " + object);
			} else {
				test.fail("Unable to Select : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.error("Unable to Select Option from :: "+ object);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void selectDropdownByVisibleText(String object, String option) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));

			if (element.isDisplayed()) {
				//HighLightElement(element);
				path=takeScreenshot(object);
				
				Select select = new Select(element);
				select.selectByVisibleText(option);
				Thread.sleep(1000);

				test.pass("Selected Option from :: " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.info("Selected Option from : : " + object);
			} else {
				test.fail("Unable to Select : : " + object, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				logger.error("Unable to Select Option from :: "+ object);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void verifyInputField(String object, String expected) {
		try {
			
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed()) {
				//HighLightElement(element);
				String actual = element.getAttribute("value");
				Thread.sleep(1000);
				Assert.assertEquals(actual, expected);
				logger.info("Actual is : :" + actual + "Expected is : : " + expected);

			} else {
				logger.error("Actual is Not Equal to Expected");

			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void verifyText(String object, String expected) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			if (element.isDisplayed()) {
				//HighLightElement(element);
				String actual = element.getText();
		
				Assert.assertEquals(actual, expected);
				Thread.sleep(1000);
				logger.info("Actual is : :" + actual + "Expected is : : " + expected);

			} else {
				logger.error("Actual is Not Equal to Expected");

			}

		} catch (Exception e) {
			logger.error(e);
		}

	}
	
	
	public static void switchToFrame(String object) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
			WebElement element = getElement(wDriver, objectProp.getProperty(object));
			    wDriver.switchTo().frame(element);
		} catch (Exception e) {
			logger.error(e);
		}

	}
	
	public static void verifyAlertText(String actual, String expected) {
		try {
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			    Alert alert=wDriver.switchTo().alert();
		       
			    actual=	alert.getText();
			    Assert.assertEquals(actual, expected);
				Thread.sleep(1000);
				logger.info("Actual is : :" + actual + "Expected is : : " + expected);


		} catch (Exception e) {
			logger.error(e);
		}

	}

	public static String takeScreenshot(String object) {
		
		File scrFile = ((TakesScreenshot) wDriver).getScreenshotAs(OutputType.FILE);

		try {
			destFile =System.getProperty("user.dir")+"/Screenshots/"+object + System.currentTimeMillis()+".png";
			FileUtils.copyFile(scrFile, new File(destFile));
			
		} catch (IOException ex) {
			
		}
		return destFile ;
	

	}
	
	public static void verifyRadioButtonSelected() {

	}

	public static void verifyCheckBoxSelected() {

	}
	
	public static void alertAccept(){
		Alert alert=wDriver.switchTo().alert();
		try {
			Thread.sleep(2000);
			alert.accept();
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
				
	}
    
	public static void alertDismiss(){
		Alert alert=wDriver.switchTo().alert();
		try {
			Thread.sleep(2000);
			alert.dismiss();;
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}
	}

