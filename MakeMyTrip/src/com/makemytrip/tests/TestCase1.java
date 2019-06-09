package com.makemytrip.tests;

import org.testng.annotations.Test;

import com.makemytrip.GenericClass;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class TestCase1 {
	
	WebDriver driver;
	GenericClass gc;
	
/*
 * Testcase to test if we have reached the correct page after click on "search" flight
 * */	
  @Test(priority=0)
  public void f() {
  
	  gc.searchFlight(driver, "Mumbai", "Delhi", "June 2019", "17", "2", "0", "1", "Economy");
	  assertTrue(driver.findElement(By.xpath("//div[@id='search-widget']")).isDisplayed());
  
  }
  
  /*
   * Testcase to validate if highest price is same as expected
   * */
  @Test(priority=1)
  public void price()
  {
	  int rate=gc.calculateHighestFlightRate(driver);
	  assertEquals(rate, 13757);
	  
  }
  
  @BeforeTest
  public void beforeTest() {
  
	  gc=new GenericClass();
	  driver=gc.initiateDriver();
	  gc.openUrl(driver, "https://www.makemytrip.com/international-flights/");
  
  }

  @AfterTest
  public void afterTest() {
	  
	  try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
  }

}
