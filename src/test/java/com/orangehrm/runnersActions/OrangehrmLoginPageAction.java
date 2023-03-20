package com.orangehrm.runnersActions;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;

import com.orangehrm.testCases.Getpage;

public class OrangehrmLoginPageAction extends Getpage{
	
	
	WebDriver driver ;
	public OrangehrmLoginPageAction(WebDriver driver) {
		super(driver, "loginPage");
    	this.driver = driver; 
	}
	
	public void m1() throws NoSuchElementException, TimeoutException {
	isElementDisplayed("PlatformElement");
	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
	
}
