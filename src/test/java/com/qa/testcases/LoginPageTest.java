/*
 * @autor : Rahul Kauldhar
 * 
 */
package com.qa.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.pages.SearchPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	SearchPage searchPage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		//Validate the login page title
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Online Shopping Canada: Everyday Low Prices at Walmart.ca!");
	}
	
	@Test(priority=2)
	public void loginPageAllDepartments() throws InterruptedException{
		List<WebElement> allDepartments = loginPage.validateNumDepartments();
		
		String[] departments = {"Rollback & Clearance","Grocery","Outdoor Living","Outdoor Living","All Electronics","TVs","Home","Furniture","Toys","Baby","All Clothing, Shoes & Accessories","Women's Clothing","Shoes","Girls' Clothing","Boys' Clothing","Men's Clothing","Appliances","Health, Beauty & Pharmacy","Sports & Rec","Sports & Rec","Video Games","Gifts","Pantry, Household & Pets","Automotive"};
		
		//Validate all the departments
		for(int i=1; i<=allDepartments.size(); i++) {
			String departmentName = driver.findElement(By.xpath("//div[@class='tileGenV2_wrapper tenCol']/div[@class='tile desktop8 tablet6 mobile3 categoryTile regular_image']["+i+"]/a/article/h3")).getText();
			System.out.println("The department name is: "+departmentName);
			Assert.assertEquals(departmentName, departments[i-1]);
		}
	}
	@Test(priority=3)
	public void loginPageBrokenLinkTest() throws MalformedURLException, IOException{
		//Validate the login page title
		String finalResponse = loginPage.validateBrokenLinks();
		Assert.assertEquals(finalResponse, "OK");
		
		System.out.println("All links on this login page are active & response code is 200: OK");
	}
	 @Test(priority=4) 
	  public void logintoHomepage(){ 
		  searchPage = loginPage.movetoSearchPage();
	  }	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
