package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.contstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productSearchLayout = By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean isSearchSuccessful() {
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_LAGE_TIME_OUT);
		
		if(searchList.size()>0) {
			System.out.println("Search is Successfull....");
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By mainPrName = By.linkText(productName);
		eleUtil.doClick(mainPrName);
		return new ProductInfoPage(driver);
	}
}
