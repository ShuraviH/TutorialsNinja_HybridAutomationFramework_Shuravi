package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;
	
	//Objects:
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "input-email")
	private WebElement emailIdTextBox;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBox;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckBox;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
	private WebElement successfulRegistrationMessageLocator;
	
	
	//Initialization:
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public void enterFirstName(String firstNameText) {
		firstNameTextBox.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameTextBox.sendKeys(lastNameText);
	}
	
	public void enterEmailId(String emailText) {
		emailIdTextBox.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneTextBox.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordTextBox.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordTextBox.sendKeys(confirmPasswordText);
	}
	
	public void clickPrivacyPolicy(String privacyPolicyBox) {
		privacyPolicyCheckBox.click();
	}
	
	public void clickContinue(String continueAgreeButton) {
		continueButton.click();
	}
	
	public String retrieveSuccessfulRegistrationText() {
		String successRegistrationMessage = successfulRegistrationMessageLocator.getText();
		return successRegistrationMessage;
	}
}
