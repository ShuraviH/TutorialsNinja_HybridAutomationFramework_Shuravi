package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

		public WebDriver driver;
		
//Objects:

		@FindBy(css = "input#input-email")
		private WebElement usernameTextBox;
		
		@FindBy(css = "input#input-password")
		private WebElement passwordTextbox;
		
		@FindBy(css = "input.btn.btn-primary")
		private WebElement signinButton;
	
		@FindBy(css = "div.alert.alert-danger.alert-dismissible")
		private WebElement tempraryIssueMessageLocator;
		
		
		public LoginPage(WebDriver driver) {
			 this.driver = driver;
			 PageFactory.initElements(driver, this);
		}
		
//Actions:
		
		public void enterUsername(String usernameText) {
			usernameTextBox.sendKeys(usernameText);
		}
		
		public void enterPassword(String passwordText) {
			passwordTextbox.sendKeys(passwordText);
		}
		
		public void clickOnsigninButton() {
			signinButton.click();
		}
		
		public String retrieveTemporaryErrorMessageText() {
			String tempErrorMessage = tempraryIssueMessageLocator.getText();
			return tempErrorMessage;
		}
}
