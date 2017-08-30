package com.revature.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageFactory {
	
	//variable for driver
	private WebDriver driver;
	
	//variable for locators
	@FindBy(xpath = ("//input[@name='userName']"))
	WebElement username;
	@FindBy(xpath = ("//input[@name='password']"))	
	WebElement password;
	@FindBy(xpath = ("//input[@name='login']"))
	WebElement login;
			
	
	/*
	 * The above is creating something called an object repository.
	 * The object repository serves to be a hub that offers a test script
	 * any element it may need to interact with. This makes the pages class a
	 * single point of access for any test script, this, decoupling the code entirely
	 */
	
	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		
		/*
		 * Three kinds of of wait in Selenium.
		 * -A wait is the duration of time Selenium waits before determining a failed test case
		 * -Implicit waits, explicit waits, and fluent waits.
		 */
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*
		 * A configuration:
		 * -The driver will wait a total of 10 seconds for any element
		 * it can't find right away before determining the test is a failure
		 * -Is applied to all actions in Selenium
		 */
		
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 20); //Default 20 is in seconds
		
		//Populates all our web elements. It does it in a eager fetching manner)
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	public void inputUsername(String username) {
		this.username.sendKeys(username);
	}
	
	public void inputPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void submitLogin() {
		this.login.click();
	}
	
	//Full driver scripts
	public void driverLogIntoMercury(String username, String password) {
		this.inputUsername(username);
		this.inputPassword(password);
		this.submitLogin();
	}
}
