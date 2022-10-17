package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.contstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By logoutlink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchBtn = By.xpath("//div[@id='search']//button");
	private By accountHeaders = By.xpath("//div[@id='content']/h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTittle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_TITLE);
		//String title = driver.getTitle();
		System.out.println("Accounts Page title is :"+title);
		return title;
	}
	
	public boolean getAccPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_URL_PARAM);
		//String url = driver.getCurrentUrl();
		System.out.println("Accounts Page Url is :"+url);
		if(url.contains(AppConstants.ACCOUNT_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.doEleIsDisplayed(logoutlink);
		//return driver.findElement(logoutlink).isDisplayed();
	}
	
	public boolean isSearchBoxExist() {
		return eleUtil.doEleIsDisplayed(search);
		//return driver.findElement(search).isDisplayed();
	}
	
	public SearchResultsPage performSearch(String productkey) {
		System.out.println("Search product name : "+ productkey);
		eleUtil.doSendKeys(search, productkey);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}
	
	public ArrayList<String> getAccountHeaders() {
		
		List<WebElement> accHeaderList = eleUtil.waitForElementsToBeVisible(accountHeaders, AppConstants.DEFAULT_LAGE_TIME_OUT);
		//List<WebElement> accHeaderList = driver.findElements(accountHeaders);
		System.out.println("Total Account Section Headers size :"+accHeaderList.size());
		ArrayList<String> headerList = new ArrayList<String>();
		for(WebElement e : accHeaderList) {
			String text = e.getText();
			
			headerList.add(text);
		}
		return headerList;
	
	}
}
