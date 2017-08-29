package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		PageFactory.initElements(driver, this);
		//Populates all our web elements. It does it in a eager fetching manner)
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
