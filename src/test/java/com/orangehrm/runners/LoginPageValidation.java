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
	
	OrangehrmLoginPageAction a;
	@Test(priority=1)
	public void logTest() throws IOException, NoSuchElementException, TimeoutException {
		
		 a = new OrangehrmLoginPageAction(driver);
		 a.m1();
		
		
	  System.out.println(driver.getTitle());
		
	}

}
