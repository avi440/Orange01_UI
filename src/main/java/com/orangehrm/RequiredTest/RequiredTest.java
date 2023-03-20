package com.orangehrm.RequiredTest;

import org.openqa.selenium.WebElement;

public class RequiredTest {
	
	public boolean displayed(WebElement element) {
		boolean flag = element.isDisplayed();
		return flag;
	}

}
