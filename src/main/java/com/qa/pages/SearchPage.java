/*
 * @autor : Rahul Kauldhar
 * 
 */
package com.qa.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class SearchPage extends TestBase {

	@FindBy(xpath = "//div[@class='results search-lvl1']/b")
	WebElement searchResults;
	
	//article[contains(@class,'standard-thumb')]/div/button
	@FindBy(xpath = "//article[contains(@class,'standard-thumb')]/div/button/span")
	List<WebElement> articles;
	
	@FindBy(xpath = "//article[contains(@class,'standard-thumb')]/a/div[@class='product-details-container']/div/div/h2")
	List<WebElement> productDetails;
	
	@FindBy(xpath = "//a[contains(text(),'Check out')]")
	WebElement checkoutBttn;
	
	public static String productName = null;
	
	// Initializing the Page Objects:
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifySearchPageTitle(){
		return driver.getTitle();
	}
	
	
	public String verifySearchResults(){
		String searchResltText = searchResults.getText();
		return searchResltText;
	}

	public CheckoutPage verifyAddtoCart() {
		for(int k=0; k<articles.size(); k++) {
	
			if(articles.get(k).getText().equals("Add to cart")) {
				
				productName = productDetails.get(k-1).getText();
			    System.out.println("Name of this product is: "+productName);
				articles.get(k).click();
				
				System.out.println("Item successfully added to cart");
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", checkoutBttn);

				System.out.println("Item added to checkout Page");
				break;
			}
			else {
				System.out.println("Item is either out of stock or view details");
			}
		}
		
		return new CheckoutPage();
	}
	

}
