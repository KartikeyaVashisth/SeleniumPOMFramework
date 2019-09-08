package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.CommonUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	
	//WebElements
	@FindBy(xpath="//h1[@class='private-page__title']")
	WebElement homePageHeader;
	
	@FindBy(xpath="//span[@class='account-name ']")
	WebElement accountName;
	
	@FindBy(id="nav-primary-contacts-branch")
	WebElement contactMainLink;
	
	@FindBy(id="nav-secondary-contacts")
	WebElement contactSubLink;
	
	//Constructor
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Methods
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	public String getHomePageHeaderValue() {
		return homePageHeader.getText();
	}
	
	public String getLoggedInAccountName() {
		return accountName.getText();
	}
	
	public ContactsPage goToContactsPage() {
		contactMainLink.click();
		//CommonUtil.shortWait();
		contactSubLink.click();
		return new ContactsPage(driver);
	}
}
