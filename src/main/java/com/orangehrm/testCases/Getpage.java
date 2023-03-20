package com.orangehrm.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.core.StringContains.containsString;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import static com.orangehrm.testCases.ObjectFileReader.getELementFromFile; 
import static com.orangehrm.testCases.BaseClass.readConfig;


//import junit.framework.Assert;

public class Getpage extends BaseUI   {
	
	
	
	
	protected WebDriver webdriver;
	String pageName;
	LayoutValidation layouttest; 
	private long start; 

	public Getpage(WebDriver driver, String pageName) {
		// TODO Auto-generated constructor stub
		 super(driver, pageName); 
		 setStart(System.currentTimeMillis());// find this 
		 this.webdriver = driver; 
		 this.pageName = pageName; 
		 layouttest = new LayoutValidation(driver, pageName);
	}

	public void testPageLayout(List<String> tagsToBeTested) {
		layouttest.checklayout(tagsToBeTested); 
	}
	
	public void testPageLayout(String tagToBeTested) { 
		testPageLayout(Arrays.asList(tagToBeTested)); 
	}
	
	public void testPageLayout() { 
		testPageLayout(Arrays.asList(readConfig.getBrowser())); 
	} 

	// TODO: put this in right place, create dedicated class for frame and // windov, handlers protected void switchToNestedFrames(String frameNames) { switchToDefaultContent(); String[] frameIdentifiers = frameNames.split(":"); for (String frameId : frameIdentifiers) { wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId .trim())); 
	protected WebElement element(String elementToken) throws NoSuchElementException, TimeoutException {
		hardWaitForlEBrowser(2); 
		return element(elementToken,""); 
	} 
	
	protected WebElement nthElement(String elementToken, int index) throws NoSuchElementException, TimeoutException {
		hardWaitForlEBrowser(2);
		return element(elementToken, "", index);  
	}
	

	
	protected WebElement element(String elementToken, String replacementl, String replacement2) throws NoSuchElementException {
		WebElement elem = null; 
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacementl, replacement2)));
		}
		   catch(StaleElementReferenceException exi) {
			   wait.hardWait(1); 
			   elem = wait.waitForElementToBeVisible(webdriver .findElement(getLocator(elementToken, replacementl, replacement2))); 
			   logMessage("Find Element " + elementToken + " after catching Stale Element Exception"); 
			 }
		    catch(NoSuchElementException excp){ 
		    	fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!"); 
			} 
		return elem; 
			} 
			
	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException, TimeoutException {
	    return element(elementToken, replacement, 0); 
			} 

	protected WebElement element(String elementToken, String replacement, int index)
			throws NoSuchElementException, TimeoutException { 
		WebElement elem = null; 
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement, index))); 
			} catch(StaleElementReferenceException ex1) {
				wait.hardWait(1);
				elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement, index))); 
				logMessage("Find Element " + elementToken + " after catching Stale Element Exception"); 
				}
		   catch (NoSuchElementException excp) { 
			   fail("FAILED: Element " + elementToken + "' not found on the " + this.pageName + " !!!");
			  }
	 
		return elem; 

	} 

	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement))); 
	}
	
	protected List<WebElement> elements(String elementToken, String replacementl, String replacement2) {
		return webdriver.findElements(getLocator(elementToken, replacementl, replacement2)); 
	}
	
	protected List<WebElement> elements(String elementToken) {
		hardWaitForlEBrowser(2); return elements(elementToken, ""); 
	} 
	protected void waitForElementToAppear(String elementToken) throws NoSuchElementException, TimeoutException {
		wait.waitForElementToAppear(element(elementToken)); 
	} 
	
	protected void _waitForElementToDisappear(String elementToken, String replacement) {
		 int i = 0;
		 int initTimeout = wait.getTimeout();
		 wait.resetImplicitTimeout(20);
		 int count;
		 while (i <= 20) {
			 if (replacement.isEmpty()) 
				 count = elements(elementToken).size(); 
	   else 
	   count = elements(elementToken, replacement).size(); 
	   if (count == 0) 
		break; 
	     i += 2; 
	    wait.resetImplicitTimeout(initTimeout);
		 }
	}
		 
		 public void waitForElementToDisappear(String elementToken1){
			 _waitForElementToDisappear(elementToken1, ""); 
		 }
		 
		 protected void waitForElementToDisappear(String elementToken11, String replacement1) {
			 _waitForElementToDisappear(elementToken11, replacement1); 
		 }
		 
		 protected void isStringMatching(String actual, String expected) {
			 logMessage("Comparing 2 strings"); 
			 logMessage("EXPECTED STRING :" + expected); 
			 logMessage("ACTUAL STRING :" + actual); 
			 Assert.assertEquals(actual, expected, "The strings does not match!!!");
			 logMessage("ASSERTION PASSED: String compare matched."); 
		 } 

		  boolean isElementDisplayed(String elementName,String elementTextReplace) throws NoSuchElementException, TimeoutException{ 
			 //wait.hardWait(2); 
			 wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
			 boolean result = element(elementName, elementTextReplace).isDisplayed(); 
			 assertTrue(result, "ASSERTION FAILED: element '" + elementName + "with text " 
			                               + elementTextReplace + "' is not displayed.");
			 logMessage("ASSERTION PASSED: element " + elementName +
					 " with text " + elementTextReplace + " is displayed.");
			 return result; 
		 }
		 
		 boolean isElementNotDisplayed(String elementName1, String elementTextReplace1) throws NoSuchElementException, TimeoutException {
			 wait.waitForElementToBeVisible(element(elementName1, elementTextReplace1));
			 boolean result = element(elementName1, elementTextReplace1).isDisplayed(); 
			 assertTrue(!result, "ASSERTION FAILED: element '" + elementName1 + "with text " 
			                                       + elementTextReplace1 + "' is displayed."); 
			 logMessage("ASSERTION PASSED: element " + elementName1 + " with text "
			                                        + elementTextReplace1 + " is not displayed."); 
			 return result; 
	} 
	
	protected void verifyElementText(String elementName11, String expectedText) throws NoSuchElementException, TimeoutException{ 
		wait.waitForElementToBeVisible(element(elementName11));
		assertEquals(element(elementName11).getText().trim(), expectedText, "ASSERTION FAILED: element '"
		                                                   + elementName11 + "' Text is not as expected: "); 
		logMessage("ASSERTION PASSED: element " + elementName11 + " is visible and Text is " + expectedText); 
	}

	
	protected void verifyElementTextContains(String elementName, String expectedText) throws NoSuchElementException, TimeoutException {
		wait.waitForElementToBeVisible(element(elementName));
		assertThat("ASSERTION FAILED: element '" + elementName + "' Text is not as expected: ",
				element(elementName).getText().trim(),containsString(expectedText));
		logMessage("ASSERTION PASSED: element " + elementName + " is visible and Text is " + expectedText); 
	}

	protected boolean isElementDisplayed(WebElement elem, String assertionText) { 
		wait.waitForElementToBeVisible(elem);
		boolean result = elem.isDisplayed(); 
         assertTrue(result, "ASSERTION FAILED: '" + assertionText + "' is not displayed."); 
         logMessage("ASSERTION PASSED: element '" + assertionText + "' is displayed."); 
         return result; 
	}

	protected boolean isElementDisplayed(String elementName) throws NoSuchElementException, TimeoutException { 
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed(); 
		assertTrue(result, "ASSERTION FAILED: element '' "+ elementName +
				                                        "' is not displayed."); 
		logMessage("ASSERTION PASSED: element " + elementName + " is displayed.");
		return result; 
	}
	protected boolean isElementNotDisplayed(String elementName) {
		boolean result; 
		try { 
			wait.waitForPageToLoadCompletely();
			driver.findElement(getLocator(elementName)); 
			result = false; 
			} catch (NoSuchElementException excp) {
				result = true; 
		}
	 assertTrue(result, "ASSERTION FAILED: element '" + elementName + "' is displayed."); 
	 logMessage("ASSERTION PASSED: element " + elementName + " is not displayed as expected!!!"); 
	 return result; 
	} 
	protected void doHoverOverElement(WebElement element) {
		Actions builder = new Actions(driver); 
		builder.moveToElement(element).build().perform(); 
	}
	
	protected boolean isElementEnabled(String elementName, boolean expected) throws NoSuchElementException, TimeoutException {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = expected && element(elementName).isEnabled();
		assertTrue(result, "ASSERTION FAILED: element '" + elementName + "' is ENABLED :- " + !expected);
		logMessage("ASSERTION PASSED: element " + elementName + " is enabled :- " + expected); 
		return result; 
	}
	protected By getLocator(String elementToken) { 
		return getLocator(elementToken, ""); 
	} 
	protected By getLocator(String elementToken, String replacement) {
		return getLocator(elementToken, replacement, 0); 
	} 

	protected By getLocator(String elementToken, String replacement, int index) { 
		String[] locator = getELementFromFile(this.pageName, elementToken);
		if(index<0){ 
			logMessage("elementToken="+elementToken+"; replacement="+replacement+"; index="+index);
			logMessage("locator[1]="+locator[1]+"; locator[2]="+locator[2]); 
	} 
	 if(index>0 && replacement.length()==0)
		 locator[2] = locator[2].replace("(n)","("+index+")"); 
	 else 
		 locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement); 
	 if(index<0) logMessage("locator[2]="+locator[2]); 
	 return getBy(locator[1].trim(), locator[2].trim()); 
	}
	
	protected By  getLocator(String elementToken, String replacement1, String replacement2) {
		 String[] locator = getELementFromFile(this.pageName, elementToken); 
		 locator[2] = locator[2].replaceFirst("\\S\\{.+?\\}", replacement1); 
		 locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement2);
		 return getBy(locator[1].trim(), locator[2].trim()); 
	} 
	
	public void clickOnFirstLinkBasedOnProvidedText(String elementToken, String linkText) throws NoSuchElementException, TimeoutException {
		element(elementToken, linkText).click(); 
	} 
	public boolean matchGivenPatternWithProvidedText(String pattern, String text) {
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		return matcher.matches(); 
	}
	
	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)){ 
		case id: 
			return By.id(locatorValue); 
	    case xpath:
	    	return By.xpath(locatorValue); 
	    case css:
	    	return By.cssSelector(locatorValue); 
	    case name:
	    	return By.name(locatorValue); 
	    case classname:
	    	return By.className(locatorValue); 
	    case linkText: 
	    	return By.linkText(locatorValue); 
	    case portialLinkText: 
	    	return By.partialLinkText(locatorValue); 
	    default:
	    	return By.id(locatorValue); 
		}
	}
	    	public long getStart() {
	    	return start; 
	    	}
	    	
	    	public void setStart(long start) { 
	    		this.start = start; 
	    	}
	    	
	    	public void reportTime() {
	    		System.out.println("\n  Total elapsed time for test: " + (System.currentTimeMillis()-getStart()) + 
	    				" ms (with demoWaitSeconds set to " + getDemoWaitSeconds() + ") *****\n"); 
	    	} 
	    	
	    	
	    	protected WebElement elementWithouthait(String elementToken) throws NoSuchElementException { 
	    		WebElement elem = null;
	    		try {
	    			elem = driver.findElement(getLocator(elementToken));
	    			} catch (StaleElementReferenceException exl) {
	    				logMessage("Find Element " + elementToken + " after catching Stale Element Exception"); 
	    				} catch (NoSuchElementException excp) { 
	    					fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!"); } 
	    		      return elem; 
	    			}

}
