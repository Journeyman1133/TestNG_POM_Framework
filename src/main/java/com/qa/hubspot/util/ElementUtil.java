package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage{

	WebDriver driver;
	WebDriverWait wait;
	JSUtil jsUtil;
	Properties prop;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME);
		jsUtil = new JSUtil(driver);
	}

	/**
	 * Title wait method
	 * @param title
	 * @return
	 */
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	/**
	 * wait element
	 * @param locator
	 * @return
	 */
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	/**
	 * Visibility element
	 * @param locator
	 * @return
	 */
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	/**
	 * getTitle
	 * 
	 * @return
	 */
	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception occured while getting the title..");
		}
		return null;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		
		try {
			element = driver.findElement(locator);
			if (highlightElement) {
				jsUtil.flash(element);
			}
		} catch (Exception e) {
			System.out.println("some exception occured while getting the element..");
		}
		return element;
	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception occured while clicking the element..");
		}
	}

	public void doSendKeys(By locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception occured while sending the value..");
		}
	}

	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception occured for isDisplayed..");
		}
		return false;
	}

	public boolean doIsSelected(By locator) {
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("some exception occured for isSelected..");
		}
		return false;
	}

	public boolean doIsEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("some exception occured for isEnabled..");
		}
		return false;
	}

	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception occured while getting the text from page..");
		}
		return null;
	}
}
