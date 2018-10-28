package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteConfPageFromContentPage extends PageObject{

	@FindBy(id = "edit-submit")
	WebElement deleteButton;
	
	public DeleteConfPageFromContentPage(String title) {
		waitForPage(title, "DeleteConfrimationPage did not load correctly: " + title);
		PageFactory.initElements(driver, this);
	}
	
	public ContentPage delete() {
		waitForClick(deleteButton).click();
		return new ContentPage();
	}
}
