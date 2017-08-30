package com.revature.testsuites;

import org.testng.annotations.Test;

import com.revature.pages.LoginPage;
import com.revature.pages.LoginPageFactory;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class MercuryLogin {
	
	//Variable for driver
	
	private WebDriver driver;
	
	//Variable for URL
	
//	private String url = "http://newtours.demoaut.com/";
//	private String username = "bobbert";
//	private String password = "bobbert";
//	private String mercuryHomepage = "Welcome: Mercury Tours";
//	private String findFlightPage = "Find a Flight: Mercury Tours:";
	
	
	
	@Test(priority = 0)
	@Parameters({"homepage"})
	public void validateLandingPage(String homepage) {
		Assert.assertEquals(driver.getTitle(), homepage);
	}

	@Test(priority = 1)
	@Parameters({"username", "password", "findflightpage"})
	public void logIntoMercury(String username, String password, String findflightpage) {
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.driverLogIntoMercury(username, password);
//		
		LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
		loginPageFactory.driverLogIntoMercury(username, password);
		
//		driver.findElement(By.name("userName")).sendKeys(username);
//		driver.findElement(By.name("password")).sendKeys(password);
//		driver.findElement(By.name("login")).click();
		
		Assert.assertEquals(driver.getTitle(), findflightpage);
		
	}
	
	@Test(priority = 2)
	@Parameters({"homepage"})
	public void logout(String homepage) {
		driver.findElement(By.xpath("//a[contains(text(), 'Home')]")).click();
		Assert.assertEquals(driver.getTitle(), homepage);
		//Use contains() as a simple string function to see if text exists
		//Use text() as a parameter of contains for looking at innerHTML
	}
	
	@Test(priority = 3, dataProvider="provideAccountDetails")
	public void dataProviderExample(String username, String password) {
		LoginPageFactory loginPage = new LoginPageFactory(driver);
		loginPage.driverLogIntoMercury(username, password);
		driver.findElement(By.xpath("//a[contains(text(), 'Home')]")).click();
	}

	
	@BeforeTest
	@Parameters({"url"})
	public void beforeTest(String url) {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@DataProvider // Note you can NAME the provider if you don't it defaults to method name
	public Object[][] provideAccountDetails() {
		return new Object[][] {
			{"bobbert", "bobbert"},
			{"testguy123", "testguy123"}
		};
	}
	
}
