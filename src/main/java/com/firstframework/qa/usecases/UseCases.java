package com.firstframework.qa.usecases;

import org.testng.Assert;

import com.firstframework.qa.pages.AddContentPage;
import com.firstframework.qa.pages.ArticlePage;
import com.firstframework.qa.pages.ContentPage;
import com.firstframework.qa.pages.CreateArticle;
import com.firstframework.qa.pages.DeleteConfPageFromContentPage;
import com.firstframework.qa.pages.HomePage;
import com.firstframework.qa.pages.LoginPage;
import com.firstframework.qa.pages.PageObject;
import com.firstframework.qa.pages.UserPage;

public class UseCases extends PageObject {

	public static boolean createArticle(String title, String body, String tags, String imageURL, 
			String imageAltText) {

		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.goToLogin();
		UserPage userPage = loginPage.login(user, pass);
		ContentPage contentPage = userPage.getAdminMenu().goToContent();
		AddContentPage addContentPage = contentPage.addContent();
		CreateArticle createArticle = addContentPage.goToArticle();
		createArticle.setTitle(title);
		createArticle.setBody(body);
		createArticle.setTags(tags);
		if (!imageURL.isEmpty())
			createArticle.uploadImage(imageURL, imageAltText);
		ArticlePage articlePage = createArticle.save();

		if (!articlePage.getTitle().contains(title) || !articlePage.getContent().contains(body)
				|| !articlePage.checkTags(tags))
			return false;

		if (!imageURL.isEmpty()) {
			if (!articlePage.checkImage(imageAltText))
				return false;
		}

		return true;
	}

	public static boolean createArticleWithoutImage(String title, String body, String tags) {
		return createArticle(title, body, tags, "", "");
	}
	
	public static boolean deleteArticle(String title) {
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.goToLogin();
		UserPage userPage = loginPage.loginWithDefaultUser();
		ContentPage contentPage = userPage.getAdminMenu().goToContent();
		contentPage.filterContents(title);
		DeleteConfPageFromContentPage deleteConfirmationPage = contentPage.deleteContent(title);
		contentPage = deleteConfirmationPage.delete();
		return contentPage.getMessages().contains("Deleted 1 content item.");
	}
}
