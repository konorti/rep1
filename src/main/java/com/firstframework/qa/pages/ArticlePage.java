package com.firstframework.qa.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage extends PageObject {

	@FindBy(css = "h1.page-title")
	WebElement title;

	@FindBy(css = ".node__content .field--name-body")
	WebElement content;

	@FindBy(linkText = "Delete")
	WebElement deleteButton;

	@FindBy(css = ".field--name-field-tags")
	WebElement tagsArea;
	
	@FindBy(css = ".field--name-field-tags .field__item")
	List<WebElement> tagsList;
	
	@FindBy( css = ".field--name-field-image img")
	WebElement image;
	
	@FindBy( linkText = "Edit")
	WebElement editButton;
	
	private String articleTitle;

	public ArticlePage(String title) {
		waitForPage(title, "Article did not load correctly: " + title);
		PageFactory.initElements(driver, this);
		articleTitle = title;
	}

	public String getContent() {
		return waitForVisible(content).getText().trim();
	}

	public String getTitle() {
		return waitForVisible(title).getText();
	}

	public DeleteConfirmationPage delete() {
		waitForClick(deleteButton).click();
		return new DeleteConfirmationPage("Are you sure you want to delete the content " + articleTitle + "?");
	}

	public boolean checkTitle(String title) {
		return getTitle().contains(title);
	}
	
	public boolean checkContent(String title) {
		return getContent().contains(title);
	}
	
	public boolean checkTags(String tags) {
		String[] tagsArray = tags.split(",");
		waitForVisible(tagsArea);
		for (int i = 0; i < tagsArray.length; i++) {
			if(!tagsArray[i].trim().equals(tagsList.get(i).getText()))
				return false;
		}
		return true;
	}
	
	public boolean checkImage(String altText) {
		if( !altText.isEmpty() ) {
			if (!waitForVisible(image).isDisplayed())
				return false;
		}
		return image.getAttribute("alt").contains(altText);
	}
	
	public EditArticle editContent() {
		waitForClick(editButton).click();
		return new EditArticle();
	}
}
