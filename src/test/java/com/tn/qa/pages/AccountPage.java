package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	public WebDriver driver;
	
	//Objects
	@FindBy(linkText = "Logout")
	private WebElement logoutLink;
	
	//Initialization
	public AccountPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	public boolean logoutLinkisDisplayedOrNot() {
		boolean displayStatus = logoutLink.isDisplayed();
		return displayStatus;
	}
}
