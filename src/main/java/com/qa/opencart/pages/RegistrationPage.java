package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	public ElementUtil eleUtil;
	
	private By firstName = By.name("firstname");
	private By lastName = By.name("lastname");
	private By email = By.name("email");
	private By telephone = By.name("telephone");
	private By password = By.name("password");
	private By confirm = By.name("confirm");
	private By subscribeYes = By.xpath("//div[@class='form-group']/div/label/input[@type='radio' and @value='1']");
	private By subscribeNo = By.xpath("//div[@class='form-group']/div/label/input[@type='radio' and @value='0']");
	private By policy = By.xpath("//input[@type='checkbox' and @value='1']");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By registerSuccessMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerlink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String doRegister(String fname, String lName, String emailid, String phone, String pwd, String subscribe) {
		System.out.println("Registering new User");
		//eleUtil.waitForElementVisible(firstName, AppConstants.DEFAULT_LAGE_TIME_OUT).sendKeys(fname);
		eleUtil.doSendKeys(firstName, fname);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(email, emailid);
		eleUtil.doSendKeys(telephone, phone);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confirm, pwd);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(policy);
		eleUtil.doClick(continueBtn);
		String successMsg = eleUtil.waitForElementVisible(registerSuccessMsg, AppConstants.DEFAULT_LAGE_TIME_OUT).getText();
		System.out.println("Success Message : "+successMsg);

		eleUtil.doClickWithVisibleElement(logoutLink, AppConstants.DEFAULT_LAGE_TIME_OUT);
		//eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerlink);
		
		return successMsg;
	}
	
	
	
	
	
	
	
	
	
	
}
