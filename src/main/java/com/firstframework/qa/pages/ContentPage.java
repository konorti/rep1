package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentPage extends PageObject{
	
	@FindBy(linkText = "Add content")
	WebElement addContentButton;
	
	public ContentPage() {
		waitForPage("Content", "ContentPage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	
	public AddContentPage addContent() {
		waitForClick(addContentButton).click();
		return new AddContentPage();
	}

}
