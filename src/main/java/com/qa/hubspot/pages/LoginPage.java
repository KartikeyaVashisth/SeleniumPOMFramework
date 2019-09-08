package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage {

	WebDriver driver;
	//1.	Define Page objects with help of PageFactory or By Locator.
	//2.	Page actions/ Methods of the feature.

	//1. a:
	@FindBy(id="username")
	WebElement emailID;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="loginBtn")
	WebElement loginButton;
	
	@FindBy(linkText = "Sign up")
	WebElement signUpLink;
	
	//1.b: Now, create constructor of LoginPage Class and initialize all the page objects with driver.
	public LoginPage(WebDriver driver) {
		
		this.driver = driver; //here we have initialized global driver with local driver.
		PageFactory.initElements(driver, this); //We can either write LoginPage.class or just "this" only. "this" represents current class only.
	}
	
	//2. Define your actions/methods
	
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifySignUpLink() {
		return signUpLink.isDisplayed();
	}
	
	public HomePage doLogin(String un, String pwd) {
		emailID.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		return new HomePage(driver); //Every constructor of the page will accept driver.
	}
}
