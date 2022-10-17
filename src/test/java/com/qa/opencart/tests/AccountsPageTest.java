package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contstants.AppConstants;

public class AccountsPageTest extends BaseTest{

	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccPageTittle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccPageUrl());
	}
	
	@Test(priority=3)
	public void isLogoutLinkExistTest() {
		Assert.assertEquals(accPage.isLogoutLinkExist(), true);
	}
	
	@Test(priority=4)
	public void isSearchBoxExistTest() {
		Assert.assertEquals(accPage.isSearchBoxExist(), true);
	}
	
	@Test(priority=5)
	public void accHeadersTest() {
		ArrayList<String> actualHeaderList = accPage.getAccountHeaders();
		System.out.println("Accounts Headers Section List : "+actualHeaderList);
		Assert.assertEquals(actualHeaderList, AppConstants.ACCOUNT_PAGE_HEADER_SECTION);
	}
	
	@DataProvider
	public Object[][] getProductKey(){
		return new Object[][] {
			{"Macbook"},
			{"imac"},
			{"samsung"}
		};
	}
	
	@Test(priority=6, dataProvider="getProductKey")
	public void searchCheckTest(String key) {
		searchResultsPage = accPage.performSearch(key);
		Assert.assertTrue(searchResultsPage.isSearchSuccessful());
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(priority=7, dataProvider="getProductData")
	public void searchTest(String searchKey, String mainPrName) {
		searchResultsPage = accPage.performSearch(searchKey);
		if(searchResultsPage.isSearchSuccessful()) {
			productInfoPage = searchResultsPage.selectProduct(mainPrName);
			String actualProductHeader = productInfoPage.getProductHeader(mainPrName);
			Assert.assertEquals(actualProductHeader, mainPrName);
		}
	}

}
