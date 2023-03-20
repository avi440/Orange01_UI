package com.orangehrm.runners;


import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orangehrm.RequiredTest.RequiredTest;
import com.orangehrm.pageObjects.Loginpage;
import com.orangehrm.testCases.BaseClass;
import com.orangehrm.testCases.Getpage;
//import com.orangrhrm.utilities.Extentreports;
import com.orangrhrm.utilities.Reporting;

public class TC_LoginTest_001 extends BaseClass  {

	


	String title = "OrangeHRM HR Software | Free & Open Source HR Software | HRMS | HRIS | OrangeHRM";
//	String title = "OrangeHRM";
	
	
	Loginpage lp ;
	RequiredTest test ;
	
	

	@BeforeTest
	public void requiredData() {
//		lp = new Loginpage(driver);
		 test = new RequiredTest();
//		 lp = new Loginpage(driver);
	}
	
	@Test(priority=1)
	public void logTest() throws IOException, NoSuchElementException, TimeoutException {
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		
		 lp = new Loginpage(driver);
		
		logger.info("Expected URL is opened");

              String aa = driver.getTitle();
              
		if (driver.getTitle().equals(title)) {
			Assert.assertTrue(true);

		} else {
//			captureScreenshot(driver,"logTest01");
			Assert.assertTrue(false);
		}
		logger.info("process was complited");
		
	}
	
	
	@Test(priority=2)
	public void comper() throws IOException{
		boolean flag = test.displayed(lp.PlatformElement);
//		
//		
		if(flag == true) {
			logger.info("PlatformElement webelement was Displayed t");
		}else
		{
			logger.info("PlatformElement webelemen was not Displayed");
		}
		
		
	}
	
	@AfterTest
	public void closerequiredData() {
		logger.info("####################################");
	}
	
}
