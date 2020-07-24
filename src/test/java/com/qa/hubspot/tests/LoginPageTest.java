package com.qa.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : Create login features")
@Feature("US - 501 : Create test for login HubSpot")

public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;

	Logger log = Logger.getLogger(LoginPageTest.class);
	
//	@BeforeMethod(alwaysRun = true)
	@BeforeTest
	@Parameters(value = { "browser" })
	public void setUp(String browser) {
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if (browser.equals(null)) {
			browserName = prop.getProperty("browser");
		} else {
			browserName = browser;
		}
		
		browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched - " + prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	// groups
	@Test(priority = 1, groups = "sanity", description = "Get title as HubSpot Login", enabled = true)
	@Description("Verify Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyPageTitleTest() {
		log.info("starting ------------- >>>>>> verifyLoginPageTest");
		String title = loginPage.getPageTitle();
		System.out.println("Login page title: " + title);
		// Assert.assertEquals(title, "HubSpot Login");
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		log.info("ending ------------- >>>>>> verifyLoginPageTest");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}

	@Test(priority = 2, description = "Verify Signup link in Login page", enabled = true)
	@Description("Verify Signup Link")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 3, description = "Login Page Credentials", enabled = true)
	@Description("Login Page Credentials")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getUserAccountName();
		System.out.println("logged in account name:" + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
		// loginPage.doLogin(prop.getProperty("username"),
		// prop.getProperty("password"));
		// Thread.sleep(2000);
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "ammar@sample.com", "123test456" }, { "unal@sample.com", " " }, { " ", "123test456" },
				{ "yummy", "yummy" }, { " ", " " } };
		return data;
	}

	// we did all tests enabled false to see only this test.
	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled = false)
	public void login_invalidTestCase(String username, String pwd) {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}
	
//	@AfterMethod(alwaysRun = true)
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
