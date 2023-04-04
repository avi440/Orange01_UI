package com.orangehrm.runners;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pageObjects.Loginpage;
import com.orangehrm.runnersActions.OrangehrmLoginPageAction;
import com.orangehrm.testCases.BaseClass;

public class LoginPageValidation extends BaseClass  {
	
	
	
	String title = "OrangeHRM HR Software | Free & Open Source HR Software | HRMS | HRIS | OrangeHRM";
//	String title = "OrangeHRM";
	
	OrangehrmLoginPageAction test;
	@Test(priority=1)
	public void loginTest__01() throws IOException, NoSuchElementException, TimeoutException {
		
		 test = new OrangehrmLoginPageAction(driver);
       
	if (driver.getTitle().equalsIgnoreCase(title)) {
		Assert.assertTrue(true);
		captureScreenshot(driver,"logTest01");

	} else {
		captureScreenshot(driver,"logTest01");
		Assert.assertTrue(false);
	}
	logger.info("process was complited");
	
}
	
	@Test(priority=2)
	public void logTestPageElementValidation__02() throws IOException, NoSuchElementException, TimeoutException {
		
		test.pageElementDisplayedOrNot();
		 logger.info("logTestPageElementValidation__02 was pass");
	}
	
	@Test(priority=3)
	public void bookFreeDemoTitleValidation__03() throws IOException, NoSuchElementException, TimeoutException {
		test.verifyBookFreeDemoTitle();
		
		 logger.info("bookFreeDemoElementValidation__03 was pass");
	}

	@Test(priority=4)
	public void bookFreeDemoElementValidation__04() throws IOException, NoSuchElementException, TimeoutException {
		test.bookFreeDemoElementDisplayedOrNot();
		 logger.info("bookFreeDemoElementValidation__04 was pass");
	}

}
