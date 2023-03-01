package com.orangehrm.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.orangrhrm.utilities.ReadConfig;


public class BaseClass {
	public static int PAGE_LOAD_TIMEOUT = 20;
	public static int IMPLICIT_WAIT = 20;
	
	ReadConfig readConfig = new ReadConfig();
	public String baseUrl = readConfig.getApplicationURL();
	public String eng = readConfig.getTire();
	public static WebDriver driver;
	public static Logger logger;
	public  static EventFiringWebDriver e_driver;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br ) {
		logger =  Logger.getLogger("orange01");
		 PropertyConfigurator.configure("./Configuration/log4j.properties");
		 
		 if(br.equals("chrome")) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		 }
		 else if(br.equals("firefox")) {
			 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver.exe");
				driver = new FirefoxDriver();
			 
		 }
         else if(br.equals("ie")) {
        	 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/driver/IEDriverServer.exe");
     		driver = new InternetExplorerDriver();
     		
		 }
		
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();

			
			driver.get(baseUrl);


	}
	 
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	public void captureScreenshot(WebDriver driver,String tname) throws IOException {
	 TakesScreenshot ts = (TakesScreenshot)driver;
     File Source = ts.getScreenshotAs(OutputType.FILE);
     File destination = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
//     String filePath = destination.getAbsolutePath();
     FileUtils.copyFile(Source, destination);
//     return filePath;
     System.out.println("Take screenshot: " + tname);
  }


}
