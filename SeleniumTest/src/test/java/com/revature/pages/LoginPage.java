package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	//variable for driver
	private WebDriver driver;
	
	//variable for locators
	By username = By.xpath("//input[@name='userName']");
	By password = By.xpath("//input[@name='password']");
	By login = By.xpath("//input[@name='login']");
	
	/*
	 * The above is creating something called an object repository.
	 * The object repository serves to be a hub that offers a test script
	 * any element it may need to interact with. This makes the pages class a
	 * single point of access for any test script, this, decoupling the code entirely
	 */
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputUsername(String username) {
		driver.findElement(this.username).sendKeys(username);
	}
	
	public void inputPassword(String password) {
		driver.findElement(this.password).sendKeys(password);
	}
	
	public void submitLogin() {
		driver.findElement(this.login).click();
	}
	
	//Full driver scripts
	public void driverLogIntoMercury(String username, String password) {
		this.inputUsername(username);
		this.inputPassword(password);
		this.submitLogin();
	}
}
