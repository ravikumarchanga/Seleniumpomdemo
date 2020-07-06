package org.sce.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.sce.utils.ExcelHelper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;




public class TestBase {
	
	public static File configFile;
	public static FileInputStream configFis;
	public static Properties configProp;

	public static File objectFile;
	public static FileInputStream objectFis;
	public static Properties objectProp;

	public static File log4jFile;
	public static FileInputStream log4jFis;

	public static WebDriver wDriver;
	
	public static ExcelHelper masterSuiteExcel;
	public static ExcelHelper botSuiteExcel;

	static boolean isinitialized = false;
	
	public static WebElement element;
	public static By by;
	
	
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest test;
	

	public static Logger logger = Logger.getLogger(new RuntimeException().getStackTrace()[1].getClassName());

	public static void loadProjectConfigProperties() {
		
	configFile=new File(System.getProperty("user.dir")+"/src/test/java/org/sce/resources/config.properties");
		logger.info("Project Config Property File Loaded");
		try {
			configFis = new FileInputStream(configFile);
			configProp = new Properties();
			configProp.load(configFis);
		} catch (FileNotFoundException e) {
			logger.error("Project Config Property FIle Not Loaded" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void loadObjectRepositoryProperties() {
		
		objectFile=new File(System.getProperty("user.dir")+"/src/test/java/org/sce/resources/objectRepository.properties");
	
		logger.info("Object Repository Property File Loaded");
		try {
			objectFis = new FileInputStream(objectFile);
			objectProp = new Properties();
			objectProp.load(objectFis);
		} catch (FileNotFoundException e) {
			logger.error("Object Repository Property FIle Not Loaded" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void loadLog4jProperties() {
		
		logger.info("Log4j Property File Loaded");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/java/org/sce/resources/log4j.properties");
	}

	public static void invokeBrowser() {

			String browser=configProp.getProperty("browser");

		logger.info("Browser is : : " + browser);
		if (browser.equalsIgnoreCase("Firefox")) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
		    System.setProperty("webdriver.gecko.driver", "../SCE_Chat/Browsers/geckodriver.exe");
			wDriver = new FirefoxDriver();
			wDriver.manage().window().maximize();
			logger.info("Firefox Browser Invoked");

		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "../SCE_Chat/Browsers/chromedriver.exe");
			wDriver = new ChromeDriver();
			wDriver.manage().window().maximize();
			logger.info("Chrome Browser Invoked");
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "../SCE_Chat/Browsers/IEDriverServer32.exe");
			wDriver = new InternetExplorerDriver();
			wDriver.manage().window().maximize();
			logger.info("Internet Explorer Browser Invoked");
			

		} else if (browser.equalsIgnoreCase("Safari")) {
			wDriver = new SafariDriver();
			wDriver.manage().window().maximize();
			logger.info("Safari Browser Invoked");

		} else {
			logger.error("Browser Not Initialized");
		}
		//wDriver.manage().window().maximize();
		logger.info("Window Maximized");
	}

	public static void initialization() {
		loadProjectConfigProperties();
		loadObjectRepositoryProperties();
		loadLog4jProperties();

		masterSuiteExcel = new ExcelHelper("../SCE_Chat/TestInputs/MasterSuite.xls");
		logger.info("Master Suite Excel FIle Loaded");

	
		
		botSuiteExcel = new ExcelHelper("../SCE_Chat/TestInputs/ChatBotSuite.xls");
		logger.info("Chat Bot Suite Excel FIle Loaded");
	
		

	}
	
	public static void baseUrl(){
		String url=configProp.getProperty("baseUrl");
		logger.info("Entered Url : : " + url);
		
		wDriver.get(url);
	}
	
	

	public static WebElement getElement(WebDriver wDriver, String locator) {
		String[] objects = locator.split("-->");

		String locatorType = objects[0];
		String locatorValue = objects[1];
		WebElement element = null;
		By by = null;

		if (locatorType.equalsIgnoreCase("className")) {
			
			by = By.className(locatorValue);
			
			logger.info("Element Identified with ClassName : : " + by);
		} else if (locatorType.equalsIgnoreCase("cssSel")) {
			by = By.cssSelector(locatorValue);
			logger.info("Element Identified : : " + by);
		} else if (locatorType.equalsIgnoreCase("id")) {
			by = By.id(locatorValue);
			logger.info("Element Identified : : " + by);
		} else if (locatorType.equalsIgnoreCase("linkTxt")) {
			by = By.linkText(locatorValue);

			logger.info("Element Identified : : " + by);
		} else if (locatorType.equalsIgnoreCase("name")) {
			by = By.name(locatorValue);
			logger.info("Element Identified : : " + by);
		} else if (locatorType.equalsIgnoreCase("plLinkTxt")) {
			by = By.partialLinkText(locatorValue);
			logger.info("Element Identified : : " + by);
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			by = By.tagName(locatorValue);
			logger.info("Element Identified : : " + by);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			by = By.xpath(locatorValue);
			logger.info("Element Identified : : " + by);
		} else {
			logger.error("Element not Identified");
		}

		if (wDriver.findElements(by).size() > 0) {
			element = wDriver.findElement(by);

		}

		return element;
	}

}
