package com.firstframework.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.firstframework.qa.pageelements.AdminMenu;

public class UserPage extends PageObject{
	
	@FindBy(linkText = "Log out")
	WebElement logoutButton;
	
	@FindBy(xpath ="//h1[contains(concat(' ',@class,' '),'js-quickedit-page-title')]")
	WebElement userLabel;
	
	public UserPage() {
		waitForPage("Umami", "UserPage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	public boolean checkUserName(String userName) {
		return waitForVisible(userLabel).getText().equals(userName);
	}
	public HomePage logout( ) {
		waitForClick(logoutButton).click();
		return new HomePage();
	}
	public AdminMenu getAdminMenu() {
		return new AdminMenu();
	}
}
