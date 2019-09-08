package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.ElementActions;
import com.qa.hubspot.utils.JavaScriptUtil;

public class LoginPageByLocator {

	WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtil jsutil;
	
	//1.	Define Page objects with help of PageFactory or By Locator.
	//2.	Page actions/ Methods of the feature.
	
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUpLink = By.linkText("Sign Up");
	
	public LoginPageByLocator(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
		jsutil = new JavaScriptUtil(driver);
	}
	
	//2. Define your actions/methods
	
		public String getLoginPageTitle() {
			return driver.getTitle();
		}
		
		public boolean verifySignUpLink() {
			// return signUpLink.isDisplayed(); //[Error] isDisplayed() cannot be used as it is not an element, it is by Locator, need to convert to element first.
			return elementActions.getElement(signUpLink).isDisplayed();
		}
		
		public HomePage doLogin(String un, String pwd) {
			elementActions.sendKeysElement(username, un);
			elementActions.sendKeysElement(password, pwd);
			//elementActions.clickOnElement(loginBtn);
			jsutil.clickElementByJS(elementActions.getElement(signUpLink));

			
			return new HomePage(driver);
		}
	
	
}
