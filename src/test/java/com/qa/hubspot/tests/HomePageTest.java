package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.CommonUtil;

public class HomePageTest {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_properties(); 
		driver = basePage.initialize_driver(prop); 
		driver.get(prop.getProperty("url")); 
		CommonUtil.mediumWait();
		loginPage = new LoginPage(driver); 
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		CommonUtil.mediumWait();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("Home page title is:"+title);
		Assert.assertEquals(title, Constants.HOMEPAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHomePageHeadsertest() {
		String header = homePage.getHomePageHeaderValue();
		System.out.println("HOme Page header is: "+header);
		Assert.assertEquals(header, Constants.HOMEPAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyLoggedInAccountNametest() {
		String accountName = homePage.getLoggedInAccountName();
		System.out.println("Loggedin account name"+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
