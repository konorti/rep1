package com.firstframework.qa.pageelements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.firstframework.qa.pages.ContentPage;
import com.firstframework.qa.pages.PageObject;

public class AdminMenu extends PageObject {
	
	@FindBy(id= "toolbar-item-administration")
	WebElement manageButton;
	
	@FindBy(id= "toolbar-link-system-admin_content")
	WebElement contentMenuItem;
	
	public AdminMenu() {
		PageFactory.initElements(driver, this);
	}
	public ContentPage goToContent() {
		waitForVisible(manageButton);
		try {
			contentMenuItem.isDisplayed();
		} catch (NoSuchElementException e) {
			manageButton.click();
		}
		waitForClick(contentMenuItem).click();
		return new ContentPage();
	}
}
