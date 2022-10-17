package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contstants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void registrationSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "SeleniumAutomation"+random.nextInt(10000)+"@abc.com";
		return email;
	}
	
	@Test(dataProvider="getRegTestData")
	public void registerNewUserTest(String fname, String lName, String emailid, String phone, String pwd, String subscribe) {
		String actualSuccMesg = registrationPage.doRegister(fname, lName, emailid, phone, pwd, subscribe);
		Assert.assertEquals(actualSuccMesg, AppConstants.ACC_CREATE_SUCC_MESG);
	
	}
}
