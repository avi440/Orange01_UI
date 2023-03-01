package com.orangehrm.testCases;


import java.io.IOException;
import java.util.logging.Level;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pageObjects.Loginpage;
//import com.orangrhrm.utilities.Extentreports;
import com.orangrhrm.utilities.Reporting;

public class TC_LoginTest_001 extends BaseClass  {

	String title = "OrangeHRM HR Software | Free & Open Source HR Software | HRMS | HRIS | OrangeHRM";
//	String title = "OrangeHRM";

	@Test
	public void logTest() throws IOException {

		Loginpage lp = new Loginpage(driver);

//		driver.get("https://www.orangehrm.com");
		
		
		logger.info("Expected URL is opened");
//		String aaa = driver.getTitle();
//		System.out.println(aaa);

		if (driver.getTitle().equals(title)) {
			Assert.assertTrue(true);

		} else {
//			captureScreenshot(driver,"logTest01");
			Assert.assertTrue(false);
		}
		logger.info("process was complited");
		
		
		

	}
}
