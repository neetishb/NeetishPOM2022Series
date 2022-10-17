package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contstants.AppConstants;

public class LoginPageTest extends BaseTest {

	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Test(priority=3)
	public void isForgetPwdLinkExistTest() {
		Assert.assertEquals(loginPage.isForgetPasswordLinkDisplayed(), true);
	}
	
	@Test(priority=4)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
}
