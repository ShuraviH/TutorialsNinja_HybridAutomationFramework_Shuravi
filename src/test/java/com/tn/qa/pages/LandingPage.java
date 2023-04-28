package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;	//instance variable

	//Objects
	
	@FindBy(linkText = "My Account")
	private WebElement myAccountLink;
	
	@FindBy(linkText = "Register")
	private WebElement registerAccountLink;
	
	@FindBy(linkText = "Login")
	private WebElement logInLink;
	
	
	
	public LandingPage(WebDriver driver) {		//local variable
		this.driver = driver;
		//PageFactory.initElements(driver, LandingPage.class);
		PageFactory.initElements(driver, this);	//This takes care of the StaleElementReferenceException
	}
	
	//Actions
	
	public void clickOnmyAccountLink() {
		myAccountLink.click();	
	}
	public void clickOnregisterAccountLink() {
		registerAccountLink.click();	
	}
	public void clickOnlogInLink() {
		logInLink.click();	
	}
	

}
