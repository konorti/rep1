package com.firstframework.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.firstframework.qa.pages.AddContentPage;
import com.firstframework.qa.pages.ArticlePage;
import com.firstframework.qa.pages.ContentPage;
import com.firstframework.qa.pages.CreateArticle;
import com.firstframework.qa.pages.DeleteConfirmationPage;
import com.firstframework.qa.pages.EditArticle;
import com.firstframework.qa.pages.HomePage;
import com.firstframework.qa.pages.LoginPage;
import com.firstframework.qa.pages.PageObject;
import com.firstframework.qa.pages.UserPage;
import com.firstframework.qa.usecases.UseCases;

public class ContentTest extends PageObject {

	@BeforeMethod
	public void setUp() {
		PageObject.startBrowser();
	}

	@AfterMethod
	public void cleanUp() {
		// PageObject.closeBrowser();
	}

	@Test
	public void addNewContent() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.goToLogin();
		UserPage userPage = loginPage.loginWithDefaultUser();
		ContentPage contentPage = userPage.getAdminMenu().goToContent();
		AddContentPage addContentPage = contentPage.addContent();
		CreateArticle createArticle = addContentPage.goToArticle();
		createArticle.setTitle("This is a new title");
		createArticle.setBody("This is a very good article");
		createArticle.setTags("new article, news, cool");
		createArticle.uploadImage(
				"C:\\eclipse-workspace\\MyFirstTestFramework\\src\\test\\resources\\Hamburger.jpg",
				"This is a picture about a hamburger");
		ArticlePage articlePage = createArticle.save();
		Assert.assertTrue(articlePage.checkTitle("This is a new title"), 
				"Article's title is not correct");
		Assert.assertTrue(articlePage.checkContent("This is a very good article"),
				"Article's content is not correct");
		Assert.assertTrue(articlePage.checkTags("new article, news, cool"), 
				"Article's tags are not correct");
		Assert.assertTrue(articlePage.checkImage("This is a picture about a hamburger"), 
				"Article's image is not correct");
		DeleteConfirmationPage deleteConfirmationPage = articlePage.delete();
		homePage = deleteConfirmationPage.delete();
		Assert.assertTrue(homePage.getMessages().contains("This is a new title" + " has been deleted"), 
				"Delete success message did not appear correctly"); 
	}

	@Test
	public void editContent() {
		Assert.assertTrue(
				UseCases.createArticle("Test title", "This is a cool text", 
						"állatos,new,trending",
						"C:\\eclipse-workspace\\MyFirstTestFramework\\src\\test\\resources\\Hamburger.jpg",
						"This is a picture about a hamburger"),
				"Test data preparation: create article failed");
		
		PageObject.startBrowser();
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.goToLogin();
		UserPage userPage = loginPage.loginWithDefaultUser();
		ContentPage contentPage = userPage.getAdminMenu().goToContent();
		ArticlePage articlePage = contentPage.openContent("Test title");
		EditArticle editArticle = articlePage.editContent();
		editArticle.setTitle("Edited title");
		editArticle.setBody("Edited body");
		editArticle.setTags("edited tag1, edited tag2");
		editArticle.uploadImage(
				"C:\\eclipse-workspace\\MyFirstTestFramework\\src\\test\\resources\\Pizza.jpg",
				"This is a picture about a pizza");
		articlePage = editArticle.save();
		
		Assert.assertTrue(articlePage.checkTitle("Edited title"), 
				"Article's title is not correct");
		Assert.assertTrue(articlePage.checkContent("Edited body"),
				"Article's content is not correct");
		Assert.assertTrue(articlePage.checkTags("edited tag1, edited tag2"), 
				"Article's tags are not correct");
		Assert.assertTrue(articlePage.checkImage("This is a picture about a pizza"), 
				"Article's image is not correct");
		DeleteConfirmationPage deleteConfirmationPage = articlePage.delete();
		homePage = deleteConfirmationPage.delete();
		Assert.assertTrue(homePage.getMessages().contains("Edited title" + " has been deleted"), 
				"Delete success message did not appear correctly"); 
	}
}
