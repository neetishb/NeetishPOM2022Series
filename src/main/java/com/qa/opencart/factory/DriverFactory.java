package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	private static final Logger LOG = Logger.getLogger(DriverFactory.class);
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	public static String highlight;
	public OptionsManager om;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		System.out.println("Browser name is : " + browserName);
		LOG.info("Provided browser name is "+browserName);

		highlight = prop.getProperty("highlight");
		om = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			tldriver.set(new ChromeDriver(om.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tldriver.set(new EdgeDriver());
		} else {
			System.out.println("Please provide correct browser name");
			LOG.error("Provided browser name is not correct. Please provide correct one.");
			throw new FrameworkException(AppErrors.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		/*
		 * try { FileInputStream ip = new
		 * FileInputStream(".\\src\\test\\resources\\config\\config.properties");
		 * prop.load(ip); } catch (FileNotFoundException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		FileInputStream ip = null;
		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		System.out.println("Running test cases in environment : " + envName);

		if (envName == null) {
			try {
				System.out.println("Running test cases in Default environment : QA env");
				ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName) {
				case "qa":

					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;

				case "dev":

					ip = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;

				case "uat":

					ip = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;

				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
					break;

				default:
					System.out.println("Please provide the right env name....");
					throw new FrameworkException(AppErrors.ENV_NOT_FOUND);
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}
	
	public static String getScreenshot() {
		
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() +".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
