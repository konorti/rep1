package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteConfPageFromArticlePage extends PageObject{

	@FindBy(id = "edit-submit")
	WebElement deleteButton;
	
	public DeleteConfPageFromArticlePage(String title) {
		waitForPage(title, "DeleteConfrimationPage did not load correctly: " + title);
		PageFactory.initElements(driver, this);
	}
	
	public HomePage delete() {
		waitForClick(deleteButton).click();
		return new HomePage();
	}
}
