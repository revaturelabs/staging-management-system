package com.revature.gluecode;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssociateLogin {
	private WebDriver driver;
	
	@FindBy(xpath="//*[@id='body']/div/div/div/div/form[2]/fieldset/div[2]/input")
	WebElement username;
	@FindBy(xpath="//*[@id='body']/div/div/div/div/form[2]/fieldset/div[3]/input")
	WebElement password;
	@FindBy(xpath="//button[@id='loginBtn']")
	WebElement login;
	
	public AssociateLogin(WebDriver driver){
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
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
	public void driverLogIntoAssociate(String username, String password){
		this.inputUsername(username);
		this.inputPassword(password);
		this.submitLogin();
	}
	
}
