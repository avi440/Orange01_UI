package com.orangehrm.pageObjects;


import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver ldriver;

	public Loginpage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//li[@class='nav-item']/a[text()='Platform']")
	@CacheLookup
	public WebElement PlatformElement;

	@FindBy(xpath = "//li[@class='nav-item']/a[text()='Why OrangeHRM']")
	@CacheLookup
	WebElement WhyOrangeHRMElement;

	@FindBy(xpath = "//li[@class='nav-item']/a[text()='Resources']")
	@CacheLookup
	WebElement ResourcesElement;

	@FindBy(xpath = "//li[@class='nav-item']/a[text()='Company']")
	@CacheLookup
	WebElement CompanyElement;

	@FindBy(xpath = "//div[@class='d-flex web-menu-btn']//button[text()='Book a Free Demo']")
	@CacheLookup
	WebElement bookFreeDemoElement;

	@FindBy(xpath = "//div[@class='d-flex web-menu-btn']//button[text()='Contact Sales']")
	@CacheLookup
	WebElement contactSalesElement;

	@FindBy(xpath = "//div[@class='btn-toolbar']/input[@value='Try it for Free']")
	@CacheLookup
	WebElement tryItFreeElement;

	@FindBy(xpath = "//div[@class='homepage-product-content ']/h2[text()='People Management']")
	@CacheLookup
	WebElement PeopleManagementElement;

	@FindBy(xpath = "//div[@class='homepage-product-content ']/h2[text()='Culture']")
	@CacheLookup
	WebElement cultureElement;

	@FindBy(xpath = "//div[@class='homepage-product-content ']/h2[text()='Talent Management']")
	@CacheLookup
	WebElement talentManagementElement;

	@FindBy(xpath = "//div[@class='homepage-product-content ']/h2[text()='Compensation']")
	@CacheLookup
	WebElement compensationElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='hr administration']")
	@CacheLookup
	WebElement hradministrationElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='Employee managment']")
	@CacheLookup
	WebElement EmployeemanagmentElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='Reporting']")
	@CacheLookup
	WebElement reportingElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='performance 1']")
	@CacheLookup
	WebElement performanceElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='Career Developemet']")
	@CacheLookup
	WebElement CareerDevelopemetElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='Traning']")
	@CacheLookup
	WebElement traningElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='Recruitment']")
	@CacheLookup
	WebElement RecruitmentElement;

	@FindBy(xpath = "//div[@class='homepage-product-sub']//img[@alt='onboarding'] ")
	@CacheLookup
	WebElement onboardingElement;
	
//	List<WebElement> list = new ArrayList<>(); 
	public boolean displayed(WebElement element) {
		boolean flag = element.isDisplayed();
		return flag;
	}
	
}
