package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageObject{

	@FindBy(id = "edit-name")
	WebElement userNameField;
	
	@FindBy(id = "edit-pass")
	WebElement passwordField;
	
	@FindBy(xpath = "//form[@id='user-login-form']//input[@id='edit-submit']")
	WebElement submitButton;
	
	public LoginPage() {
		waitForPage("Log in", "LoginPage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public UserPage loginWithDefaultUser() {
		waitForVisible(userNameField).sendKeys(user);
		passwordField.sendKeys(pass);
		submitButton.submit();
		return new UserPage();	
	}
	
	public UserPage login(String userName, String password) {
		user = userName;
		pass = password;
		waitForVisible(userNameField).sendKeys(userName);
		passwordField.sendKeys(password);
		submitButton.submit();
		return new UserPage();	
	}
	
}
