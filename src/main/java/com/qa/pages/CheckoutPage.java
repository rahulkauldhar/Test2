package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

public class CheckoutPage extends TestBase {
	
	//@FindBy(xpath = "//a[@class='cart-product-title']")
	@FindBy(xpath = "//ul/li[@class='product'][1]/div[@class='product-content']/div/div/h4/a[@class='cart-product-title']")
	WebElement productName;
	
	
	// Initializing the Page Objects:
	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public String verifyCheckoutPageTitle(){
		return driver.getTitle();
	}
	
	
	public String verifyProductInCheckoutPage(){
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].scrollIntoView()", productName); 
		
		String name = productName.getText();
		
		return name;
	}	

}
