package com.tn.qa.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.SearchPage;
import com.tn.qa.testbase.TestBase;

public class SearchTest extends TestBase {

	public SearchTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public Select select;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		SearchPage searchpage = new SearchPage(driver);
		searchpage.clickOnSearchTextBox();
		// driver.findElement(By.name("search")).click();
	}

	@Test(priority = 1)
	public void verifyEmptySearchProduct() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("");
		searchpage.clickSearchButton();
		// driver.findElement(By.name("search")).sendKeys("");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		String actualEmptySearchMessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))
				.getText();
		String expectedEmptySearchMessage = dataprop.getProperty("wrongSearchErrorMessage");
		softassert.assertTrue(actualEmptySearchMessage.contains(expectedEmptySearchMessage),
				"Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void verifyInvalidSearchProduct() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("flower");
		searchpage.clickSearchButton();
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		String actualInvalidSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))
				.getText();
		String expectedInvalidSearchMessage = dataprop.getProperty("wrongSearchErrorMessage");
		softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
				"Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifySearchProductWithSpacesInTheMiddle() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("i    phone");
		searchpage.clickSearchButton();
		
		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}

//		String actualInvalidSearchMessage = driver
//				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))
//				.getText();
//		String expectedInvalidSearchMessage = dataprop.getProperty("wrongSearchErrorMessage");
//		softassert.assertFalse(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
//				"Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void verifyMisspelledSearchProduct() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("iphont");
		searchpage.clickSearchButton();
		// driver.findElement(By.name("search")).sendKeys("iphont");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		List<WebElement> products = driver.findElements(By.xpath("//div[@id=\"content\"]/div[3]/div")); // product-thumb
																										// tells me how
																										// many products
																										// I have
		System.out.println("Total Number of Products : " + products.size());

		String actualInvalidSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))
				.getText();
		String expectedInvalidSearchMessage = dataprop.getProperty("wrongSearchErrorMessage");
		softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
				"Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 5)
	public void verifyAllUpperCaseSearchProduct() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("MACBOOK");
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys("MACBOOK");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		List<WebElement> products = driver.findElements(By.xpath("//div[@id=\"content\"]/div[3]/div")); 
		System.out.println("Total Number of Products : " + products.size());
		softassert.assertTrue(products.size() > 0);

		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}

		// String actualInvalidSearchMessage =
		// driver.findElement(By.xpath("//p[contains(text(),'There is no product that
		// matches the search criteria.')]")).getText();
		// String expectedInvalidSearchMessage =
		// dataprop.getProperty("wrongSearchErrorMessage");
		// softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
		// "Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 6)
	public void verifySpecialCharacterSearchProduct() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("phon&");
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys("phon&");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		String actualInvalidSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))
				.getText();
		String expectedInvalidSearchMessage = dataprop.getProperty("wrongSearchErrorMessage");
		softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
				"Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 7)
	public void verifyNumbersInSearchProduct() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("123456");
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys("123456");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		String actualInvalidSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))
				.getText();
		String expectedInvalidSearchMessage = dataprop.getProperty("wrongSearchErrorMessage");
		softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
				"Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 8)
	public void SearchProductWithSpacesBetweenEachLetter() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("t o u c h");
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys("t o u c h");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}																								

		// String actualInvalidSearchMessage =
		// driver.findElement(By.xpath("//p[contains(text(),'There is no product that
		// matches the search criteria.')]")).getText();
		// String expectedInvalidSearchMessage =
		// dataprop.getProperty("wrongSearchErrorMessage");
		// softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
		// "Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 9)
	public void SearchProductWithLeadingAndTrailingSpaces() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("          samsung          ");
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys(" samsung ");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}

		// String actualInvalidSearchMessage =
		// driver.findElement(By.xpath("//p[contains(text(),'There is no product that
		// matches the search criteria.')]")).getText();
		// String expectedInvalidSearchMessage =
		// dataprop.getProperty("wrongSearchErrorMessage");
		// softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
		// "Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 10)
	public void SearchProductWithPartialProductName() {
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch("book");
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys("book");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}

		// String actualInvalidSearchMessage =
		// driver.findElement(By.xpath("//p[contains(text(),'There is no product that
		// matches the search criteria.')]")).getText();
		// String expectedInvalidSearchMessage =
		// dataprop.getProperty("wrongSearchErrorMessage");
		// softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
		// "Warning message is not correct");
		softassert.assertAll();
	}

	@Test(priority = 11)
	public void SearchProductWithTwoSpaces() {
		String keyword = "  ";
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterSearch(keyword);
		searchpage.clickSearchButton();

		// driver.findElement(By.name("search")).sendKeys(" ");
		// driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}

		// String actualInvalidSearchMessage =
		// driver.findElement(By.xpath("//p[contains(text(),'There is no product that
		// matches the search criteria.')]")).getText();
		// String expectedInvalidSearchMessage =
		// dataprop.getProperty("wrongSearchErrorMessage");
		// softassert.assertTrue(actualInvalidSearchMessage.contains(expectedInvalidSearchMessage),
		// "Warning message is not correct");
		softassert.assertAll();
	}

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}
}
