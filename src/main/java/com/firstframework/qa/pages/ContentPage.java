package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentPage extends PageObject{
	
	@FindBy(linkText = "Add content")
	WebElement addContentButton;
	
	@FindBy(css = "#edit-title")
	WebElement filterField;
	
	@FindBy(css = "#edit-submit-content")
	WebElement filterButton;
	
	@FindBy(xpath = "//table[contains(@class, 'views-table')]/tbody/tr/td[2]/a")
	WebElement firstListItem;
	
	public ContentPage() {
		waitForPage("Content", "ContentPage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	
	public AddContentPage addContent() {
		waitForClick(addContentButton).click();
		return new AddContentPage();
	}
	
	public void filterContents(String title) {
		waitForVisible(filterField).sendKeys(title);
		waitForClick(filterButton).click();
	}
	
	public ArticlePage openContent(String title) {
		filterContents(title);
		waitForVisible(firstListItem).click();
		return new ArticlePage(title);
	}
}
