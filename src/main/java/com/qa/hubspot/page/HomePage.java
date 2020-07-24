package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	By header = By.xpath("//i18n-string[contains(text(),'Thanks for choosing HubSpot')]");
	By accountName = By.xpath("//span[contains(@class,'account-name')]");
	
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// Actions
	// pls check the changes after using elementUtil
	public String getHomePageTitle() {
		return elementUtil.doGetPageTitle();
		//return driver.getTitle();
	}

	public String getHomePageHeader() {
		return elementUtil.doGetText(header);
		//return driver.findElement(header).getText();
	}

	public String getUserAccountName() {
		elementUtil.waitForElementVisible(accountName);
		return elementUtil.doGetText(accountName);
		//return driver.findElement(accountName).getText();
	}
	
	public void clickOnContacts() {
		elementUtil.waitForElementPresent(mainContactsLink);
		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClick(mainContactsLink);
		elementUtil.doClick(childContactsLink);
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

}
