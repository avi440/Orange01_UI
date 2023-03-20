package com.orangehrm.testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {
	
	WebDriver driver;
	WebDriverWait wait; 
	int timeout; 
	
	public SeleniumWait(WebDriver driver, int timeout) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout; 
//			this.driver = driver;
//			this.wait = wait; 
		}
	
//	 Returns webElement found by the locator if element is visible 
//	@param locator  @return 
	 public WebElement getWhenVisible(By locator) { 
		 WebElement element; 
		 element = (WebElement) wait.until(ExpectedConditions .visibilityOfElementLocated(locator)); 
		 return element;
	 }
	 
	 
	 public WebElement getWhenClickable(By locator) {
		 WebElement element; element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		 return element; 
	 }
 
 public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
	 return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null; 
 }
  
 public boolean waitForPageTitleToContain(String expectedPagetitle) { 
	 return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
 }
 
 public WebElement waitForElementToBeVisible(WebElement element) {
	 return (WebElement) wait.until(ExpectedConditions.visibilityOf(element)); 
 } 
 
 public void waitForFrameToBeAvailableAndSwitchToIt(By locator) { 
	 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator)); 
 }
 
 public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
	 return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements)); 
 }
 
 public boolean waitForElementToBeInVisible(By locator) { 
	 return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null; 
 }
 public WebElement waitForElementToBeClickable(WebElement element) {
	 return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element)); 
 }
 
 public void clickWhenReady(By locator) { 
	 WebElement element = (WebElement) wait.until(ExpectedConditions .elementToBeClickable(locator)); 
	 element.click(); 
 } 
 public void waitForPageTitleToAppearCompletely(String txtPageTitle) { 
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 wait.until(ExpectedConditions.titleIs(txtPageTitle)); 
 }
 
 public boolean waitUntilNewWindowIsOpen(int numOfWindows) { 
	 return wait.until(ExpectedConditions.numberOfWindowsToBe(numOfWindows)); 
 }
 public void waitForMsgToastToDisappear() {
	 int i = 0; resetImplicitTimeout(1); 
	 try {
		 while (driver.findElement(By.className("toast-message")).isDisplayed() && i <= timeout) {
			 hardWait(1);
			 i++; 
 }
  } catch (Exception e) { 
 } resetImplicitTimeout(timeout); 
 }
 
 public void waitForElementToDisappear(WebElement element) { 
	 int i = 0;
	 resetImplicitTimeout(2);
	 try { 
		 while (element.isDisplayed() && i <= timeout) { 
			 hardWait(1); 
  }
   } catch (Exception e) {
 }
	 resetImplicitTimeout(timeout); 
}

 public void resetImplicitTimeout(int newTimeOut) { 
	 try { 
		 driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS); 
	 }
	 catch (Exception e) { } 
 }
	 
 // TODO Implement Wait for page load for page synchronizations 
	


 
 
 public void waitForElementToAppear(WebElement element) {
	 int i = 0; 
	 System.out.println("waiting For Element To Appear");
	 System.out.println("element.isDisplayed()==="+ element.isDisplayed());
	 resetImplicitTimeout(2); 
//	  hardWait(1);
	 try { 
		 System.out.println("try");
		 while (!(element.isDisplayed()) && i <= timeout) {
			 System.out.println("while");
			 hardWait(1); i++; 
			 System.out.println(i + " Seconds"); }
	 }
		 catch (StaleElementReferenceException e) { 
			 System.out.println("exception thrown"); 
			 } 
	 System.out.println("exitig loop"); 
	 resetImplicitTimeout(timeout); 
	 System.out.println("exiting function"); 
 }
 

   public int getTimeout() {
	// TODO Auto-generated method stub
	 return timeout; 
}	

 public void waitForPageToLoadCompletely() {
	// TODO Auto-generated method stub
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"))); 
}

void hardWait(int seconds) {
	// TODO Auto-generated method stub
	 try { 
		 Thread.sleep(seconds * 1000); 
	 }catch (InterruptedException ex) {
		 ex.printStackTrace(); 
  }
	
} 



	

}
