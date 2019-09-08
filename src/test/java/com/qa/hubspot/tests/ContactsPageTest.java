package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.CommonUtil;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

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
		contactsPage = homePage.goToContactsPage(); //We will navigate onto contacts Page.
	}
	
	@DataProvider(name="getContactsData")
	public Object[][] getContactsData() {
		Object[][] contactsData = ExcelUtil.getTestData("contacts");
		return contactsData;
	}
	
	@Test(dataProvider = "getContactsData") //To test with hardcoded values first we can remove dataProvider and just write @Test
	public void createContactsTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
