package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage {

	public WebDriver driver;
	
	//Objects:

	
	@FindBy(name = "search")
	private WebElement searchTextBox;
	
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	
	//Initialization:
	public SearchPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	//Actions:
	
	public void clickOnSearchTextBox() {
		searchTextBox.click();
	}
	
	public void enterSearch(String searchText) {
		searchTextBox.sendKeys(searchText);
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
}
