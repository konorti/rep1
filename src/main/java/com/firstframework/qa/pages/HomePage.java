package com.firstframework.qa.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.firstframework.qa.pageelements.AdminMenu;

public class HomePage extends PageObject{

	@FindBy(linkText = "Log in") 
	WebElement loginButton;
	
	@FindBy(linkText = "Log out") 
	WebElement logoutButton;
	
	@FindBy(css = ".messages__item")
	WebElement messages;
	
	//Initialization
	public HomePage() {
		waitForPage("Home", "HomePage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLoggedIn() {
		try {
			logoutButton.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	public LoginPage goToLogin() {
		waitForClick(loginButton).click();
		return new LoginPage();
	}
	
	public AdminMenu getAdminMenu() {
		return new AdminMenu();
	}
	
	public String getMessages() {
		return waitForVisible(messages).getText();
	}
}
