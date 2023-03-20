package com.orangrhrm.utilities;

//listener class used to generate report

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
   public  ExtentHtmlReporter htmlReporter;
    
   public ExtentReports extent;
    //helps to generate the logs in test report.
   public ExtentTest test;
   

    
	public void delectFile() {
	File folder = new File(System.getProperty("user.dir")+"/test-output/Extentreports/");
	if(!folder.isFile()) {
	File[] listOfFiles = folder.listFiles();
	String fileName = null;
	for (File file : listOfFiles) {
	    if (file.isFile()) {
	    	fileName = file.getName();
	    }
	}
	File file = new File(System.getProperty("user.dir")+"/test-output/Extentreports/"+fileName); //creates a file instance  
	file.deleteOnExit();
	}
}
	public static String captureScreenshot(WebDriver driver) throws IOException {
		 TakesScreenshot ts = (TakesScreenshot)driver;
	     File Source = ts.getScreenshotAs(OutputType.FILE);
	     File destination = new File(System.getProperty("user.dir")+"/Screenshots/"+System.currentTimeMillis()+".png");
	     String filePath = destination.getAbsolutePath();
	     FileUtils.copyFile(Source, destination);
	     return filePath;
//	     System.out.println("Take screenshot: " + tname);
	  }
   
    public void onStart(ITestContext testcontest){
    	
    	delectFile();
    	
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time Stamp
    	String repName = "Test-Report-"+timeStamp+".html";
    	
    	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/Extentreports/"+repName);//generate report location
    	htmlReporter.loadXMLConfig("./extent-config.xml");
    	
    	extent = new ExtentReports();
    	
    	
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "windows");
        extent.setSystemInfo("user", "Avi");
        extent.setSystemInfo("environemnt", "QA");
        
//      htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Education Report");//Title of report
        htmlReporter.config().setReportName("OrangeHRM Report");//name of the report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
        htmlReporter.config().setTheme(Theme.DARK);
//        htmlReporter.config().setAutoCreateRelativePathMedia(true);
    }
    
    //for Success one 
        public void onTestSuccess(ITestResult tr){
        	test = extent.createTest(tr.getMethod().getMethodName());
        	test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));	
        }
        
        //for Failed one
        public void onTestFailure(ITestResult tr ){
        	test = extent.createTest(tr.getMethod().getMethodName());
        	test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
        	String screenshotpath = System.getProperty("user.dir")+"/Screenshots/"+tr.getMethod().getMethodName()+System.currentTimeMillis()+".jpg";
        	File file = new File(screenshotpath);
//        	String filePath = file.getAbsolutePath();
//        	FileUtils.copyFile(file, file);
        	System.out.println("##########################: "+ file);
//        	if(file.exists()) {
        		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ "+ file);
//        	String filePath = screenshotpath.getAbsolutePath();
        		try {
        			test.fail("Screenshort is below:" + test.addScreenCaptureFromPath(screenshotpath));
        		}catch(IOException e){
        			e.printStackTrace();
        		}
//        	}
        	test.fail(tr.getThrowable());
        }
        
        //for Skipping one
        public void onTestSkipped(ITestResult tr) {
        	test = extent.createTest(tr.getMethod().getMethodName());
        	test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
        }
        
      
        public void onFinish(ITestContext testContext){
        		
    	  extent.flush();
      }
    	
    }


