package com.firstframework.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.firstframework.qa.pages.AddContentPage;
import com.firstframework.qa.pages.ArticlePage;
import com.firstframework.qa.pages.ContentPage;
import com.firstframework.qa.pages.CreateArticle;
import com.firstframework.qa.pages.HomePage;
import com.firstframework.qa.pages.LoginPage;
import com.firstframework.qa.pages.PageObject;
import com.firstframework.qa.pages.UserPage;

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
		UserPage userPage = loginPage.login("Umami","pass");
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
		Assert.assertEquals(articlePage.getTitle(), "This is a new title",
				"Article's title is not correct");
		Assert.assertEquals(articlePage.getContent(), "This is a very good article",
				"Article's content is not correct");
		Assert.assertTrue(articlePage.checkTags("new article, news, cool"), 
				"Article's tags are not correct");
	//	DeleteConfirmationPage deleteConfirmationPage = articlePage.delete();
	//	homePage = deleteConfirmationPage.delete();
	//	Assert.assertTrue(homePage.getMessages().contains("This is a new title" + " has been deleted"), 
	//			"Delete success message did not appear correctly"); 
	}

}
