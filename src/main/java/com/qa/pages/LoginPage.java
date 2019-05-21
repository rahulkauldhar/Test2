package com.qa.pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath="//span[contains(@class,'sign-in-message')]")
	WebElement myAccount;
	
	@FindBy(xpath="//button[contains(@id,'sign-in-button')]")
	WebElement signIn;
	
	@FindBy(xpath="//div[@class='tileGenV2_wrapper tenCol']/div[@class='tile desktop8 tablet6 mobile3 categoryTile regular_image']")
	List<WebElement> numberOfDepartments;
	
	@FindBy(xpath="//div[contains(@class,'tileGenV2_wrapper tenCol')]")
	WebElement allDeptContainer;
	
	@FindBy(tagName = "a")
	List<WebElement> linksList;
	
	@FindBy(xpath="//input[contains(@id,'global-search')]")
	WebElement searchBar;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public SearchPage movetoSearchPage(){
		searchBar.sendKeys(prop.getProperty("searchText"));
		searchBar.sendKeys(Keys.ENTER);
		
		return new SearchPage();
	}
	
	public List<WebElement> validateNumDepartments() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(allDeptContainer));
		
		System.out.println("List size:"+numberOfDepartments.size());
		
		for(int i=0; i< numberOfDepartments.size(); i++) {
			   while (!isDisplayed(numberOfDepartments.get(i))) 
			    {
			        Thread.sleep(3000);
			        System.out.println("Element is not visible yet");
			    }
			    return numberOfDepartments;

			    }
		return numberOfDepartments;
			  
		}
		
	
public static boolean isDisplayed(WebElement element) {
    try {
        if(element.isDisplayed())
            return element.isDisplayed();
        }catch (NoSuchElementException ex) {
        return false;
    }
    return false;
}

	public String validateBrokenLinks() throws MalformedURLException, IOException{
		
		linksList.addAll(driver.findElements(By.tagName("img")));
		
		List<WebElement> activeLinks = new ArrayList<WebElement>();
		
		for(int i=0; i<linksList.size(); i++) {
			//pick only valid anchor tags or images
			if(linksList.get(i).getAttribute("href")!=null && !linksList.get(i).getAttribute("href").startsWith("javascript") ) {
				activeLinks.add(linksList.get(i));
			}
		}
		String response = null;
		for(int i=0; i<activeLinks.size(); i++) {
			response = TestUtil.checkBrokenLinks(activeLinks.get(i));
			System.out.println(activeLinks.get(i).getAttribute("href")+" response---> "+response);
		}
		
		return response;
		    	
	}
	
}
