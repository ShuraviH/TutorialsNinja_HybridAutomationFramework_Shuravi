package com.tn.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.LandingPage;
import com.tn.qa.pages.RegisterPage;
import com.tn.qa.testbase.TestBase;
import com.tn.qa.utils.Utilities;


public class RegisterAccountTest extends TestBase{

public RegisterAccountTest() throws Exception{
		super();
	}
	
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();
	public Select select;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		LandingPage landingpage = new LandingPage(driver);
		landingpage.clickOnmyAccountLink();
		landingpage.clickOnregisterAccountLink();
		//driver.findElement(By.linkText("My Account")).click();
		//driver.findElement(By.linkText("Register")).click();
	}

	@Test(priority = 1)
	public void verifyRegisterAccountWithAllValidFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataprop.getProperty("firstName"));
		//	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		registerpage.enterLastName(dataprop.getProperty("lastName"));
		//	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		registerpage.enterEmailId(Utilities.generateEmailWithTimeStamp());
		//	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		//	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		registerpage.enterPassword(dataprop.getProperty("createPassword"));
		//	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("createPassword"));
		registerpage.enterConfirmPassword(dataprop.getProperty("confirmPassword"));
		//	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("confirmPassword"));
		registerpage.clickPrivacyPolicy("agree");
		//	driver.findElement(By.name("agree")).click();
		registerpage.clickContinue("//input[@class='btn btn-primary']");
		//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		//String actualSuccessMessageRegistration = registerpage.retrieveSuccessfulRegistrationText();
		String actualSuccessMessageRegistration  = driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();
		String expectedSuccessMessageRegistration = dataprop.getProperty("expectedSuccessMessageRegistration");
		softassert.assertEquals(actualSuccessMessageRegistration, expectedSuccessMessageRegistration); 
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void verifyRegisterAccountWithAllEmptyFields() {		
		
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")).getText();
		String expectedWarningMessage = "Warning: You must agree to the Privacy Policy!";
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualFirstNameWarningMessage.equals(expectedFirstNameWarningMessage), "Warning message is not correct");
		
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
		String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualLastNameWarningMessage.equals(expectedLastNameWarningMessage), "Warning message is not correct");
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		softassert.assertTrue(actualEmailWarningMessage.equals(expectedEmailWarningMessage), "Warning message is not correct");
		softassert.assertAll();
}

	@Test(priority = 3)
	public void verifyRegisterAccountWithEmptyFirstNameAndByEnteringAllValidFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("Haque");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Selenium$23");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium$23");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualFirstNameWarningMessage.equals(expectedFirstNameWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void verifyRegisterAccountWithEmptyLastnameAndByEnteringAllValidFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Shuravi");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Selenium$23");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium$23");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualLastWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
		String expectedLastWarningMessage = "Last Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualLastWarningMessage.equals(expectedLastWarningMessage), "Warning message is not correct");
		softassert.assertAll();	
	}
	
	@Test(priority = 5)
	public void verifyRegisterAccountWithEmptyEmailAndByEnteringAllValidFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Shuravi");
		driver.findElement(By.id("input-lastname")).sendKeys("Haque");
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Selenium$23");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium$23");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		softassert.assertTrue(actualEmailWarningMessage.equals(expectedEmailWarningMessage), "Warning message is not correct");
		softassert.assertAll();	
	}
	
	@Test(priority = 6)
	public void verifyRegisterAccountWithEmptyTelephoneAndByEnteringAllValidFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Shuravi");
		driver.findElement(By.id("input-lastname")).sendKeys("Haque");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("Selenium$23");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium$23");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click(); 
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualTelephoneWarningMessage.equals(expectedTelephoneWarningMessage), "Warning message is not correct");
		softassert.assertAll();	
	}
	
	@Test(priority = 7)
	public void verifyRegisterAccountWithEmptyPasswordAndEmptyPasswordConfirmAndByEnteringAllValidFields() {
	
		driver.findElement(By.id("input-firstname")).sendKeys("Shuravi");
		driver.findElement(By.id("input-lastname")).sendKeys("Haque");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText();
		String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualPasswordWarningMessage.equals(expectedPasswordWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
	
	@Test(priority = 8)
	public void verifyRegisterAccountWithEmptyPaswordConfirmAndByEnteringAllValidFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Shuravi");
		driver.findElement(By.id("input-lastname")).sendKeys("Haque");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Selenium$23");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualPaswordConfirmMessage  = driver.findElement(By.xpath("//div[contains(text(),'Password confirmation does not match password!')]")).getText();
		String expectedPaswordConfirmMessage = "Password confirmation does not match password!";
		softassert.assertTrue(actualPaswordConfirmMessage.contains(expectedPaswordConfirmMessage), "Warning message is not correct"); 
		softassert.assertAll();
	}
	
	@Test(priority = 9)
	public void verifyRegisterAccountWithEmptyTeleponeAndEmptyEmailAndByEnteringAllValidFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Shuravi");
		driver.findElement(By.id("input-lastname")).sendKeys("Haque");
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("Selenium$23");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium$23");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		softassert.assertTrue(actualEmailWarningMessage.equals(expectedEmailWarningMessage), "Warning message is not correct");
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualTelephoneWarningMessage.equals(expectedTelephoneWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
	
	@Test(priority = 10)
	public void verifyRegisterAccountWithValidEmailAndEmptyAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualFirstNameWarningMessage.equals(expectedFirstNameWarningMessage), "Warning message is not correct");
		
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
		String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualLastNameWarningMessage.equals(expectedLastNameWarningMessage), "Warning message is not correct");
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualTelephoneWarningMessage.equals(expectedTelephoneWarningMessage), "Warning message is not correct");
		
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText();
		String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualPasswordWarningMessage.equals(expectedPasswordWarningMessage), "Warning message is not correct");
		softassert.assertAll();
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
		}
}
