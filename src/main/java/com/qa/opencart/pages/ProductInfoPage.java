package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
 
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader(String mainProductName) {
		System.out.println("Product name is : "+mainProductName);
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xpath));
		return productHeader;
	}
}
