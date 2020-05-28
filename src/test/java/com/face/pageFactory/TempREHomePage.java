package com.face.pageFactory;

import org.openqa.selenium.By;
import core.generic.StepBase;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import io.appium.java_client.AppiumDriver;

public class TempREHomePage {

	protected static AppiumDriver appiumDriver = null;
	
	// Local variables
		private WrapperFunctions objWrapperFunctions = new WrapperFunctions();
		private Utilities objUtilities = new Utilities();
		private StepBase objStepBase = new StepBase();
		
		By btnElementPresence = By.xpath("//*[contains(@id,'navbarDropdown')]");
		By btnCandidates = By.xpath("//*[contains(@title,'Candidates')]");
		
		public boolean checkElementPresence() {
			objWrapperFunctions.waitForElementPresence(btnElementPresence, 5);
			return true;
		}

		public boolean clickOnCandidatesButton(){
			return objWrapperFunctions.click(btnCandidates);
		}
	}
