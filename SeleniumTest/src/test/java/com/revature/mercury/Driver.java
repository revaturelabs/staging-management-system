package com.revature.mercury;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	static WebDriver driver;
	static String url = "http://newtours.demoaut.com/";
	

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);	// This launches the driver to a specified URL
		
		
		//FIRST PAGE
		
		//Login
		driver.findElement(By.name("userName")).sendKeys("bobbert");
		driver.findElement(By.name("password")).sendKeys("bobbert");
		driver.findElement(By.name("login")).click();
		
		
		//SECOND PAGE
		
		//Flight Details
		
		//Type
		for(int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("//font/input["+i+"]")).click();
		}
		
		//Passengers
		for(int i = 1; i <= 4; i++) {
			driver.findElement(By.xpath("//tr[3]/td[2]/b/select/option["+i+"]")).click();
		}
		
		//Departing From
		for(int i = 1; i <= 10; i++) {
			driver.findElement(By.xpath("//tr[4]/td[2]/select/option["+i+"]")).click();
		}
		
		//On Month
		for(int i = 1; i <= 12; i++) {
			driver.findElement(By.xpath("//tr[5]/td[2]/select[1]/option["+i+"]")).click();	
		}		
		
		//On Day
		for(int i = 1; i <= 31; i++) {
			driver.findElement(By.xpath("//tr[5]/td[2]/select[2]/option["+i+"]")).click();
		}
		
		//Arriving In
		for(int i = 1; i <= 10; i++) {
			driver.findElement(By.xpath("//tr[6]/td[2]/select/option["+i+"]")).click();
		}
		
		//Returning Month
		for(int i = 1; i <= 12; i++) {
			driver.findElement(By.xpath("//tr[7]/td[2]/select[1]/option["+i+"]")).click();
		}
		
		//Returning Day
		for(int i = 1; i <= 31; i++) {
			driver.findElement(By.xpath("//tr[7]/td[2]/select[2]/option["+i+"]")).click();
		}
		
		//Preferences
		
		//Service Class
		
		driver.findElement(By.xpath("//tr[9]/td[2]/font/input")).click();
		
		for(int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("//tr[9]/td[2]/font/font/input["+i+"]")).click();
		}
		
		//Airline
		for(int i = 1; i <= 4; i++) {
			driver.findElement(By.xpath("//tr[10]/td[2]/select/option["+i+"]")).click();
		}
		
		//Continue to next page
		driver.findElement(By.name("findFlights")).click();
		

		//THIRD PAGE
		
		//Depart
		for(int i = 3; i <= 9; i+=2) {
			driver.findElement(By.xpath("//table[1]/tbody/tr["+i+"]/td[1]/input")).click();
		}
		
		//Return
		for(int i = 3; i <= 9; i+=2) {
			driver.findElement(By.xpath("//table[2]/tbody/tr["+i+"]/td[1]/input")).click();
		}

		//Continue to next page
		driver.findElement(By.name("reserveFlights")).submit();
		
		//FOURTH PAGE
		
		//Passengers
		
		//First name and last name
		for(int i = 0; i <= 3; i++) {
			driver.findElement(By.name("passFirst"+i)).sendKeys("SomeRandomFirstName");
			driver.findElement(By.name("passLast"+i)).sendKeys("someRandomLastName");
		}
		
		//Meal
		for(int j = 4; j <= 7; j++) {
			for(int k = 1; k <= 10; k++) {
				driver.findElement(By.xpath("//tbody/tr["+j+"]/td/table/tbody/tr[2]/td[3]/select/option["+k+"]")).click();
			}
		}
		
		//CreditCard
		
		//CardType
		for(int i = 1; i <= 6; i++) {
			driver.findElement(By.xpath("//tr[9]/td/table/tbody/tr[2]/td[1]/select/option["+i+"]")).click();
		}
		
		//Number
		driver.findElement(By.name("creditnumber")).sendKeys("111111111111");
		
		//Expiration Month
		for(int i = 1; i <= 12; i++) {
			driver.findElement(By.xpath("//tr[2]/td[3]/select[1]/option["+i+"]")).click();
			//
		}
		
		//Expiration Year
		for(int i = 1; i <= 12; i++) {
			driver.findElement(By.xpath("//tr[2]/td[3]/select[2]/option["+i+"]")).click();
			if(i == 5)
				driver.switchTo().alert().accept();
		}
		
		//FirstName
		driver.findElement(By.name("cc_frst_name")).sendKeys("SomeFirstName");
		//MiddleName
		driver.findElement(By.name("cc_mid_name")).sendKeys("SomeMiddleName");
		//LastName
		driver.findElement(By.name("cc_last_name")).sendKeys("SomeLastName");
		//Ticketless Travel checkbox
		driver.findElement(By.name("ticketLess")).click();
		
		//Billing address
		
		//Address1
		driver.findElement(By.name("billAddress1")).sendKeys("SomeRandomAddressOne");
		//Address2
		driver.findElement(By.name("billAddress2")).sendKeys("SomeRandomAddressTwo");
		//City
		driver.findElement(By.name("billCity")).sendKeys("SomeCity");
		//State
		driver.findElement(By.name("billState")).sendKeys("SomeCity");
		//PostalCode
		driver.findElement(By.name("billZip")).sendKeys("123456");
		//Country
		for(int i = 1; i <= 266; i++) {
			driver.findElement(By.xpath("//tr[16]/td[2]/select/option["+i+"]")).click();
		}
		//Billing address checkbox
		driver.findElement(By.xpath("//tr[17]/td[2]/input")).click();
		
		//Delivery address
		
		//Delivery Address1
		driver.findElement(By.name("delAddress1")).sendKeys("SomeRandomAddressOne");
		//Delivery Address2
		driver.findElement(By.name("delAddress2")).sendKeys("SomeRandomAddressTwo");
		//Delivery City
		driver.findElement(By.name("delCity")).sendKeys("SomeCity");
		//Delivery State
		driver.findElement(By.name("delState")).sendKeys("SomeCity");
		//Delivery PostalCode
		driver.findElement(By.name("delZip")).sendKeys("123456");
		
		for(int i = 1; i <= 266; i++) {
			driver.findElement(By.xpath("//tr[22]/td[2]/select/option["+i+"]")).click();
		}
		
		driver.findElement(By.name("buyFlights")).submit();
		driver.switchTo().alert().accept();
	}

}
