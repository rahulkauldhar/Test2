/*
 * @autor : Rahul Kauldhar
 * 
 */
package com.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CheckoutPage;
import com.qa.pages.LoginPage;
import com.qa.pages.SearchPage;
import com.qa.util.TestUtil;

public class SearchPageTest extends TestBase {
	LoginPage loginPage;
	SearchPage searchPage;
	TestUtil testUtil;
	CheckoutPage checkoutPage;

	public SearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		checkoutPage = new CheckoutPage();
		loginPage = new LoginPage();
		searchPage = loginPage.movetoSearchPage();
	}
	
	
	@Test(priority=1)
	public void validateSearchPageTitleTest(){
		String homePageTitle = searchPage.verifySearchPageTitle();
		Assert.assertEquals(homePageTitle, "Search Results for 'baby food' at Walmart.ca");
	}
	
	@Test(priority=2)
	public void validateSearchResults(){
		//verify if the search text is appearing in search results
		Assert.assertEquals(prop.get("searchText"), searchPage.verifySearchResults());
		System.out.println("Correct text is being searched on Search Page");
	}
	@Test(priority=3)
	public void validateAddtoCart(){

		checkoutPage = searchPage.verifyAddtoCart();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	

}
