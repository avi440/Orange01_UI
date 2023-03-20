package com.orangehrm.testCases;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.Reporter;

import static com.orangehrm.testCases.ObjectFileReader.getPageTitleFromFile; 
import static com.orangehrm.testCases.BaseClass.readConfig;


//import junit.framework.Assert;

public class BaseUI  {
	
	public String browser = readConfig.getBrowser();
	
	 WebDriver driver; 
	protected SeleniumWait wait; 
	private String pageName ;
	private int demoWaitSeconds = 0; 
//	ObjectFileReader object = new ObjectFileReader();

	public BaseUI(WebDriver driver, String pageName) {
		// TODO Auto-generated constructor stub
			PageFactory.initElements(driver, this); 
			this.driver = driver;
			this.pageName = pageName; 
			this.wait = new SeleniumWait(driver, Integer.parseInt(readConfig.getExplicitWait())); 
		}
	
		protected String getPageTitle() {
			return driver.getTitle(); 
		} 
		
		protected void logMessage(String message) {
			Reporter.log(message, true); 
		}
		
		protected String getCurrentURL() {
			return driver.getCurrentUrl(); 
		}
		protected void verifyPageTitleExact() {
			String pageTitle = getPageTitleFromFile(pageName); 
			verifyPageTitleExact(pageTitle); 
		}
		
		protected void verifyPageTitleExact(String expectedPagetitle) { 
			if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle .isEmpty()))
					&& (readConfig.getBrowser().equalsIgnoreCase("chrome"))) {
				expectedPagetitle = getCurrentURL(); 
			}
		 try {
			wait.waitForPageTitleToBeExact(expectedPagetitle); 
			logMessage("TEST PASSED: PageTitle for '" + pageName + "' is exactly: '" + expectedPagetitle + "'"); 
		}
			catch (TimeoutException ex) { 
				 Assert.fail("TEST FAILED: PageTitle for " + pageName + " is not exactly: '" +
			        expectedPagetitle + "'!!!1\n instead it is :- " + driver.getTitle()); 
		  }
		}
		
		// verification of the page title with the title text provided in the page object repository
		
		protected void verifyPageTitleContains() { 
			String expectedPagetitle = getPageTitleFromFile(pageName).trim();
			verifyPageTitleContains(expectedPagetitle); 
		}
		protected void assertPageTitle() {
			String expectedPagetitle = getPageTitleFromFile(pageName).trim(); 
			String actualPageTitle = getPageTitle().trim();
			Assert.assertTrue(actualPageTitle.contains(expectedPagetitle+"xyz"),"[ASSERTION FAILED]:: User Is not on ACS Parature Landing page");
			logMessage("[ASSERTION PASSED]: Verified User Is On ACS Parature Landing page");
		}
		
		
		// this method will get page title of current window and match with partially with the param provided
		
		protected void verifyPageTitleContains(String expectedPagetitle) { 
			if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle .isEmpty()))
					&& (readConfig.getBrowser().equalsIgnoreCase("chrome"))) {
				expectedPagetitle = getCurrentURL(); 
			}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
			String actualPageTitle = getPageTitle().trim(); 
			logMessage("ASSERTION PASSED: PageTitle for '" + actualPageTitle + "' contains: '" + expectedPagetitle + "'." );
		     } catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("ASSERTION FAILED: As actual Page Title: '" + actualPageTitle +
					          "' does not contain expected Page Title : '" + expectedPagetitle + "'.");
		   }
		}

		protected WebElement getElementByIndex(List<WebElement> elementlist, int index) {
			return elementlist.get(index); 
		} 
		
		protected WebElement getElementByExactText(List<WebElement> elementlist,
				String elementtext) {
			WebElement element = null; 
				for (WebElement elem : elementlist) {
					if (elem.getText().equalsIgnoreCase(elementtext.trim())) { 
						element = elem; 
				}
					
		    }
				// FIXME: handle if no element with the text is found in list No element // exception 
				if (element == null) {
					 }
				return element; 
		} 
		
		protected WebElement getElementByContainsText(List<WebElement> elementlist, String elementtext) { 
			WebElement element = null; 
			for (WebElement elem : elementlist) {
				if (elem.getText().contains(elementtext.trim())) {
					element = elem; 
			}
		}
		 // FIXME: handle if no element with the text is found in list 
          if (element == null) {
        	  } 
          return element; 
          }
		
		protected void switchToFrame(WebElement element) {
			wait.waitForElementToBeVisible(element);
			driver.switchTo().frame(element); 
			hardWaitForDemo(); 
		}
		
		public void switchToFrame(int i) { 
			driver.switchTo().frame(i); 
		}

		public void switchToFrame(String id) { 
			driver.switchTo().frame(id); 
		}
         
		public void switchToDefaultContent() { 
			driver.switchTo().defaultContent(); 
		}
		
		protected Object executeJavascript(String script) {
			return ((JavascriptExecutor) driver).executeScript(script); 
		}
		
		protected String executeJavascriptl(String script) { 
			return (String) ((JavascriptExecutor) driver).executeScript(script); 
			} 
		
		protected void handleAlert() {
			try {
				Alert alert = switchToAlert(); 
				hardWaitForDemo(); 
				alert. accept();
				logMessage("Alert handled.."); driver.switchTo().defaultContent(); 
			}
				catch(Exception e) {
					System.out.println("No Alert window appeared..."); } 
		}
		
		private Alert switchToAlert() { 
			WebDriverWait wait = new WebDriverWait(driver, 1); 
			return wait.until(ExpectedConditions.alertIsPresent()); 
		}
		
		protected void selectProvidedTextFromDropDown(WebElement e1, String text) {
			try { wait.waitForElementToBeVisible(e1);
			scrollDown(e1); 
			Select sel = new Select(e1); 
			sel.selectByVisibleText(text); 
			hardWaitForDemo(); 
			} catch(StaleElementReferenceException exl){ 
				wait.hardWait(2);
				wait.waitForElementToBeVisible(e1); 
				scrollDown(e1); Select sel = new Select(e1); 
				sel.selectByVisibleText(text); 
				logMessage("Selected Element " + e1 + 
						" after catching Stale Element Exception"); 
			}
		}
		 
		protected void selectProvidedValueFromDropDown(WebElement el, String value) { 
			try { 
				wait.waitForElementToBeVisible(el); 
				scrollDown(el); 
				Select sel = new Select(el); 
				sel.selectByValue(value); } 
			catch(StaleElementReferenceException exl) {
				wait.hardWait(1);
				wait.waitForElementToBeVisible(el); 
				scrollDown(el);
				Select sel = new Select(el); 
				sel.selectByValue(value); 
				logMessage("Selected Element " + el +
						"after catching stale Element Expection");
			}catch(Exception ex2) {
				logMessage("Element "+ el + "could not be selected!"+
						ex2.getMessage());
			}
		}
		protected void selectValueIndexFromDropDown(WebElement el, int index) {
			try {
				wait.waitForElementToBeVisible(el);
				scrollDown(el);
				Select sel = new Select(el);
				sel.selectByIndex(index);
				}catch (StaleElementReferenceException exi) {
					wait.hardWait(1);
					wait.waitForElementToBeVisible(el);
					 scrollDown(el);
					 Select sel = new Select(el);
					 sel.selectByIndex(index);
					 logMessage("Selected Element " + el + " after catching Stale Element Exception"); 
					 } catch (Exception ex2) {
						 logMessage("Element " + el + " could not be Selected! " + ex2.getMessage()); 
			}
		}
		
		protected String getFirstSelectedOptionFromDropdown(WebElement el) {
			wait. waitForElementToBeVisible(el); 
			scrollDown(el); 
			Select sel = new Select(el); 
			return sel.getFirstSelectedOption().getText(); 
		}
		 
		protected void scrollDown(WebElement element) { 
			((JavascriptExecutor)driver).executeScript( "arguments[0].scrollIntoView(true);", element); 
			hardWaitForDemo(); 
		} 
		protected void scrollDown() {
			((JavascriptExecutor) driver).executeScript( "window.scrollBy(0,10000)"); 
		} 
		protected void scrollUp() { 
			((JavascriptExecutor) driver).executeScript( "window.scrollBy(0,-10000)"); 
		}
		
		protected void hover(WebElement element) {
			Actions hoverOver = new Actions(driver);
			hoverOver.moveToElement(element).build().perform(); 
		} 
	protected String hoverAndGetHTML(WebElement element) {
		String a = new String(); 
		//a[1]=element.getAttribute("title");
		Actions hoverOver = new Actions(driver); 
		hoverOver.moveToElement(element).build().perform(); 
		//a[1]=element.getAttribute("outerHTML"); 
		a = element.getText();
		return a; 
	} 
	public void hoverAndClick(WebElement element) {
		Actions hoverClick = new Actions(driver); 
		hoverClick.moveToElement(element).click().build().perform(); 
	} 
	public void hoverOnMainAndClickSubiink(WebElement mainElement, WebElement subElement) {
		Actions actions = new Actions(driver);
		Actions builder = actions.moveToElement(mainElement); 
		Action b = builder.build();
		b.perform();
//		actions.moveToElement(mainElement).build().perform();
//		wait.hardWait(1); 
//		actions.moveToElement(subElement);
//		actions.click().build().perform(); 
	}
	
	protected void click(WebElement element) {
		try { 
			wait.waitForElementToBeVisible(element);
			scrollDown(element); 
			element. click();
			hardWaitForDemo(); 
			} catch (StaleElementReferenceException exi) { 
			 wait.hardWait(3); 
			 wait.waitForElementToBeVisible(element);
			 scrollDown(element); 
			 element.click(); 
			 logMessage("Clicked Element " + element + " after catching Stale Element Exception"); 
			}catch (Exception ex2) {
				logMessage("Element " + element + " could not be clicked! " + ex2.getMessage());
	 }
	}	
		public void mouseHoverJScript(WebElement HoverElement) {
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent(iMouseEvents');" +
		               "evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} "+
					    "else if(document.createEventObject) { arguments[0].fireEvent(ionmouseover');)"; 
			            ((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement); 
		}
		public void changeWindow(int i) { 
			String ieBrowser = browser;
			//Adding block of code to resolve Window Switching issue in 1E-11 
			if(ieBrowser.equalsIgnoreCase("ie")&&(i>1)){ 
				wait.waitUntilNewWindowIsOpen(i+1);
				System.out.println("Inside If block"); 
				String currentWindowHandle = driver.getWindowHandle(); 
				Set<String> handles = driver.getWindowHandles(); 
				for(String handle : handles){ 
			}
		}

			 Set<String> windows = driver.getWindowHandles(); 
			 System.out.println("Window Size::"+windows.size()); 
			 String wins[] = windows.toArray(new String[windows.size()]); 
			 driver.switchTo().window(wins[i]); 
			if(browser.equalsIgnoreCase("ie")) 
				wait.hardWait(2); 
			    else hardWaitForDemo(); 
			} 
		public void closeWindow(int i) {
				Set<String> windows = driver.getWindowHandles();
				System.out.println("Window Size:-"+windows.size()); 
				String wins [] = windows.toArray(new String[windows.size()]);
				driver.switchTo().window(wins[i]); 
				hardWaitForDemo(); 
				driver. close(); 
			}
			
	    public void closeChildWindows() { 
				Set<String> windows = driver.getWindowHandles();
				System.out.println("Window Size::"+windows.size());
				String wins[] = windows.toArray(new String[windows.size()]);
				for(int i=wins.length; i>1; i--){ 
					driver.switchTo().window(wins[i-1]);
					driver. close(); 
					} 
				changeWindow(0); 
			}
	    public void sendText(WebElement e,String text){
	    	scrollDown(e);
	    	e.clear(); 
	    	e.sendKeys(text); 
	    	hardWaitForDemo(); 
	    } 

	    public void sendFilePath(WebElement e,String filePath){ 
	    	//e.clear(); 
	    	e.sendKeys(filePath); 
	    } 

	    public boolean isAlertPresent(){
	    	try{ 
	    		driver.switchTo().alert(); return true; 
	    		}catch (NoAlertPresentException e){
	    			return false;
	    			} 
	    	} 
	    
	    public String getAlertText(){ 
	    	try{
	    		Alert alert = driver.switchTo().alert();
	    		String message = alert.getText();
	    		alert. accept();
	    		return message; 
	    		}catch (NoAlertPresentException e){
	    			return "No Alert is present"; 
	    			} 
	    }

	    public void waitForAlert(){
	    	int i=0; while(i++<20) { 
	    		try{ driver.switchTo().alert();
	    		break; 
	    		} catch(NoAlertPresentException e) {
	    			wait.hardWait(1);
	    			continue; 
	    	}
	       }
	    }
	    
	    public void acceptAlertPopUp(){ 
	    	try{
	    		Alert alert = driver.switchTo().alert();
	    		alert. accept(); 
	    		}catch (NoAlertPresentException e){
	    			System.out.println("No Alert is present"); 
	      } 
	    } 
	    
	    public void hardWaitForlEBrowser(int seconds) {
	    	if (readConfig.getBrowser().equalsIgnoreCase( "IE")||
	    		readConfig.getBrowser().equalsIgnoreCase("ie")||
	    		readConfig.getBrowser().equalsIgnoreCase("internetexplorer")) { 
	    		wait.hardWait(seconds); 
	    } 
	    } 
	    
	    public void hardWaitForDemo() { 
	    	hardWaitForDemo(getDemoWaitSeconds());
	    	} 
	    
	    public void hardWaitForDemo(int seconds) { 
	    	if (getDemoWaitSeconds()!=0) 
	    		wait.hardWait(seconds); 
	    	}
	    public int getDemoWaitSeconds() {
	    	return demoWaitSeconds; 
	    }
	    public void setDemoWaitSeconds(int demoWaitSeconds) {
	    	this.demoWaitSeconds = demoWaitSeconds; 
	    }

}
