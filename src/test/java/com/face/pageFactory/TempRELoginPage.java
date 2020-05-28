package com.face.pageFactory;

import org.openqa.selenium.By;
import core.generic.StepBase;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import io.appium.java_client.AppiumDriver;

public class TempRELoginPage {

	protected static AppiumDriver appiumDriver = null;
	
	// Local variables
		private WrapperFunctions objWrapperFunctions = new WrapperFunctions();
		private Utilities objUtilities = new Utilities();
		private StepBase objStepBase = new StepBase();
		
		By txtEmail = By.xpath("//*[contains(@name,'Username or email address')]");
		By txtPassword = By.xpath("//*[contains(@name,'Password')]");
		By btnLoginButton = By.xpath("//*[contains(@id,'next')]");
		By btnForgetPassowrd = By.xpath("//*[contains(@id,'forgotPassword')]");
		
		public boolean enterEmailAddress(String username) {
			return objWrapperFunctions.setText(txtEmail, username);
		}

		public boolean enterPassword(String password){
			return objWrapperFunctions.setText(txtPassword, password);
		}
		
		public boolean clickSignIn(){
			return objWrapperFunctions.click(btnLoginButton);
		}
	}
