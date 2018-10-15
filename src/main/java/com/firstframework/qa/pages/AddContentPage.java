package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddContentPage extends PageObject{

	@FindBy(xpath = "//span[contains(text(),'Article')]")
	WebElement articleLink;
	
	public AddContentPage() {
		waitForPage("Add content", "AddContentPage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	
	public CreateArticle goToArticle() {
		waitForClick(articleLink).click();
		return new CreateArticle();
	}
}
