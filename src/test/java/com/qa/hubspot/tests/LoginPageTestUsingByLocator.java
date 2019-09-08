package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.LoginPageByLocator;
import com.qa.hubspot.utils.CommonUtil;

public class LoginPageTestUsingByLocator {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPageByLocator loginPageByLocator;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_properties(); //It will return Properties, so we will create a prop instance at global level only.
		driver = basePage.initialize_driver(prop); //It will return WebDriver, so we will create a driver instance at global level only.
		
		driver.get(prop.getProperty("url")); //Since we wanted to get URL property.
		CommonUtil.mediumWait();
		loginPageByLocator = new LoginPageByLocator(driver);//creating object of LoginPage so that we can hv access of methods present in LoginPage class 
		//Constructor of LoginPage with driver as an argument will be called.
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPageByLocator.getLoginPageTitle();
		System.out.println("Login Page title is :"+title);
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE, "LoginPage title is not correct");
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPageByLocator.verifySignUpLink(), "Sign up link is not visible");
	}
	
	@Test(priority=3)
	public void loginTest() {
		loginPageByLocator.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
