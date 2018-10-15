package com.firstframework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateArticle extends PageObject{
	
	@FindBy(id = "edit-title-0-value")
	WebElement titleField;
	
	@FindBy(xpath = "//div[@id='cke_1_contents']/iframe")
	WebElement bodyFrame;
	
	@FindBy(xpath = "/html/body")
	WebElement bodyInput;
	
	@FindBy(id = "edit-field-tags-target-id")
	WebElement tags;
	
	@FindBy(id = "edit-field-image-0-upload")
	WebElement imageUpload;
	
	@FindBy(css = "*[id^='edit-field-image-0-alt']")
	WebElement imageAltText;
	
	@FindBy(id = "edit-submit")
	WebElement saveButton;
	
	private String title;
	
	public CreateArticle() {
		waitForPage("Create Article", "CreateArticlePage did not load correctly");
		PageFactory.initElements(driver, this);
	}
	
	public void setTitle(String title) {
		waitForVisible(titleField).sendKeys(title);
		this.title = title; 
	}
	
	public void setBody(String text) {
		waitForVisible(bodyFrame);
		driver.switchTo().frame(bodyFrame);
		bodyInput.sendKeys(text);
		driver.switchTo().defaultContent();
	}
	
	public void setTags(String text) {
		waitForVisible(tags).sendKeys(text);
	}
	
	public void uploadImage(String filePath, String altText) {
		waitForVisible(imageUpload).sendKeys(filePath);
		waitForVisible(imageAltText).sendKeys(altText);
	}
	
	public ArticlePage save() {
		saveButton.click();
		return new ArticlePage(title);
	}
}
