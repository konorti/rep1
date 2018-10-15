package com.firstframework.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.firstframework.qa.pages.HomePage;
import com.firstframework.qa.pages.LoginPage;
import com.firstframework.qa.pages.PageObject;
import com.firstframework.qa.pages.UserPage;


public class LoginTest {
	
	@BeforeMethod
	public void setUp() {
		PageObject.startBrowser();
	}
	
	@AfterMethod
	public void cleanUp() {
		PageObject.closeBrowser();
	}
	
	@Test
	public void testLogin() {
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.goToLogin();
		UserPage userPage = loginPage.login("Umami","pass");
		
		Assert.assertTrue(userPage.checkUserName("Umami"), "Login failed: Username did not appear");
	}
	
	@Test
	public void testLogout() {
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.goToLogin();
		UserPage userPage = loginPage.login("Umami","pass");
		homePage = userPage.logout();
		
		if(!homePage.isLoggedIn()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Logout was unsuccessful");
		}
	}
	
}
