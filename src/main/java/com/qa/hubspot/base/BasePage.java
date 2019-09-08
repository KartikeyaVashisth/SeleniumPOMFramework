package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.commons.Constants;

public class BasePage {

	WebDriver driver;
	Properties prop;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initialize_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");

		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\Selenium Jars\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Softwares\\Selenium Jars\\geckodriver-v0.24.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.out.println("No browser is defined.");
		}

		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.manage().deleteAllCookies();

		if(prop.getProperty("fullscreenmode").equals("yes")) {
			driver.manage().window().fullscreen();
		}		

		return driver;
	}
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public Properties initialize_properties() {
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\kartikeya.v\\eclipse-workspace4\\SeleniumFrameworkPractise"
					+ "\\src\\main\\java\\com\\qa\\hubspot\\configuration\\configuration.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
