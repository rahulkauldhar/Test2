/*
 * @autor : Rahul Kauldhar
 * 
 */
package com.qa.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.base.TestBase;
import com.qa.pages.CheckoutPage;
import com.qa.pages.LoginPage;
import com.qa.pages.SearchPage;
import com.qa.util.TestUtil;

public class CheckoutPageTest extends TestBase{

	LoginPage loginPage;
	SearchPage searchPage;
	TestUtil testUtil;
	CheckoutPage checkoutPage;
	   
	public CheckoutPageTest(){
			super();		
	}
		
	@BeforeMethod
	public void setUp() {
		
		initialization();
		testUtil = new TestUtil();
		checkoutPage = new CheckoutPage();
		loginPage = new LoginPage();
		searchPage = loginPage.movetoSearchPage();
		checkoutPage = searchPage.verifyAddtoCart();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void validateCheckoutPageTitle(){
		String checkoutPageTitle = checkoutPage.verifyCheckoutPageTitle();
		System.out.println("checkoutPageTitle: "+checkoutPageTitle);
		Assert.assertEquals(checkoutPageTitle, "Walmart Canada");
	}
	
	@Test(priority=2)
	public void validateCartDetails(){
		String checkoutPageProductName = checkoutPage.verifyProductInCheckoutPage();
		Assert.assertEquals(checkoutPageProductName, searchPage.productName);
		System.out.println("Correct Product is added to checkoutPage with productName as: "+checkoutPageProductName);
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}	
	
}
