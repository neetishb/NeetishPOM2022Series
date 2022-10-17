package com.qa.opencart.contstants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final int DEFAULT_TIME_OUT = 5;
	public static final int DEFAULT_LAGE_TIME_OUT = 10;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login1";
	public static final String ACCOUNT_PAGE_TITLE = "My Account1";
	
	public static final String LOGIN_PAGE_URL_PARAM = "route=account/login";
	public static final String ACCOUNT_PAGE_URL_PARAM = "route=account/account";
	
	public static final List<String> ACCOUNT_PAGE_HEADER_SECTION = 
			Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	
	public static final String ACC_CREATE_SUCC_MESG = "Your Account Has Been Created!";
	
	
	//**************Sheet name*****************//
	public static final String REGISTER_SHEET_NAME = "Register";
	
}
