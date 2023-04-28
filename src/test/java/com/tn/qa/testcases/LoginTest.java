package com.tn.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.AccountPage;
import com.tn.qa.pages.LandingPage;
import com.tn.qa.pages.LoginPage;
import com.tn.qa.testbase.TestBase;
import com.tn.qa.utils.Utilities;

public class LoginTest extends TestBase{
	
	public LoginTest() throws Exception {
		super();
	}
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		LandingPage landingpage = new LandingPage(driver);
		landingpage.clickOnmyAccountLink();
		landingpage.clickOnlogInLink();
		//	driver.findElement(By.xpath("//span[contains(text(), 'My Account')]")).click();
		//	driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
	}
	
	@Test(priority = 1)
	public void verifyValidUsernameAndValidPassword(){
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(prop.getProperty("validUsername"));
		//	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUsername"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		//	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		AccountPage accountpage = new AccountPage(driver);
		softassert.assertTrue(accountpage.logoutLinkisDisplayedOrNot());
		//	softassert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		softassert.assertAll();
	}
		
	@Test(priority = 2)
	public void verifyInvalidUsernameAndInvalidPassword() {   
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(Utilities.generateEmailWithTimeStamp());
		//	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		//	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();  
		
		String actualWarningMessage = loginpage.retrieveTemporaryErrorMessageText();
		//	String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("tempErrorMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifyValidUsernameAndInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(prop.getProperty("validUsername"));
		//	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUsername"));
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		//	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMessage = loginpage.retrieveTemporaryErrorMessageText();
		//	String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialsWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void verifyEmptyUsernameAndValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(prop.getProperty("validUsername"));
		//	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		
		String actualWarningMessage = loginpage.retrieveTemporaryErrorMessageText();
		//	String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialsWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
		
	@Test(priority = 5)
	public void verifyValidUsernameAndEmptyPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(prop.getProperty("validUsername"));
		//	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUsername"));
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMessage = loginpage.retrieveTemporaryErrorMessageText();
		//	String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialsWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
		
	@Test(priority = 6)
	public void verifyEmptyUsernameAndEmptyPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMessage = loginpage.retrieveTemporaryErrorMessageText();
		//	String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialsWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
		
	@Test(priority = 7)
	public void verifyInvalidUsernameAndValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(Utilities.generateEmailWithTimeStamp());
		//	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		//	driver.findElement(By.id("input-password")).sendKeys("validPassword");
		loginpage.clickOnsigninButton();
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMessage = loginpage.retrieveTemporaryErrorMessageText();
		//	String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialsWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}	
	
	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
		}
}
