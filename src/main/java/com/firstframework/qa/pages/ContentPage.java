package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContentPage extends PageObject{
	
	@FindBy(linkText = "Add content")
	WebElement addContentButton;
	
	@FindBy(id = "edit-node-bulk-form-0")
	WebElement firstElementChkBox;
	
	@FindBy(id = "edit-action")
	WebElement actionDropDown;
	
	@FindBy(id = "edit-submit--2")
	WebElement applyActionBtn;
	
	@FindBy(css = "#edit-title")
	WebElement filterField;
	
	@FindBy(css = "#edit-submit-content")
	WebElement filterButton;
	
	@FindBy(xpath = "//table[contains(@class, 'views-table')]/tbody/tr/td[2]/a")
	WebElement firstListItem;
	
	@FindBy(xpath = "//div[contains(@class, 'messages--status')]")
	WebElement messages;
	
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
	
	public DeleteConfPageFromContentPage deleteContent(String title) {
		waitForClick(firstElementChkBox).click();
		Select s = new Select(actionDropDown);
		s.selectByValue("node_delete_action");
		applyActionBtn.click();
		return new DeleteConfPageFromContentPage("Are you sure you want to delete this content item?");
	}
	
	public String getMessages() {
		return waitForVisible(messages).getText();
	}
}
