package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contstants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger LOG = Logger.getLogger(LoginPage.class);

	
	//By locators
	
	private By emaillocator = By.name("email");
	private By passlocator = By.name("password");
	private By loginbtnlocator = By.xpath("//input[@type='submit']");
	private By forgetpasslocator = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logolocator = By.xpath("//img[@title='naveenopencart']");
	
	//Constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//Page actions
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		//String title = driver.getTitle();
		System.out.println("Login Page title : "+title);
		LOG.info("Login page title is "+title);
		return title;
	}
	
	public boolean getLoginPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		//String url = driver.getCurrentUrl();
		System.out.println("Login Page Url : "+url);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	public boolean isForgetPasswordLinkDisplayed() {
		return eleUtil.doEleIsDisplayed(forgetpasslocator);
		//return driver.findElement(forgetpasslocator).isDisplayed();
	
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("Login Credentials are : "+username+ " : "+pwd);
		
		eleUtil.doSendKeysWithWait(emaillocator, AppConstants.DEFAULT_TIME_OUT, username);
		//eleUtil.doSendKeys(emaillocator, username);
		eleUtil.doSendKeys(passlocator, pwd);
		eleUtil.doClick(loginbtnlocator);
		
		//driver.findElement(emaillocator).sendKeys(username);
		//driver.findElement(passlocator).sendKeys(pwd);
		//driver.findElement(loginbtnlocator).click();
		
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage() {
		System.out.println("Navigating to Registration Page....");
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
}
