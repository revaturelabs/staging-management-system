package com.revature.gluecode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SMSLogin {
	public static WebDriver driver;
	
	@Given("^I am at the login screen for Staging Management System$")
	public void i_am_at_the_login_screen_for_Staging_Management_System() throws Throwable {
	    System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("https://34.209.40.60");
	}

	@When("^I input my \"([^\"]*)\" and \"([^\"]*)\" and click login$")
	public void i_input_my_and_and_click_login(String username, String password) throws Throwable {
		AssociateLogin asscLogin = new AssociateLogin(driver);
		asscLogin.driverLogIntoAssociate(username, password);
	}

	@Then("^I shall be redirected to the associate profile page\\.$")
	public void i_shall_be_redirected_to_the_associate_profile_page() throws Throwable {
	   driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
	   driver.quit();
	}
}
