package com.face.pageFactory;

import org.openqa.selenium.By;
import core.generic.StepBase;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import io.appium.java_client.AppiumDriver;

public class TempRECandidatesPage {

	protected static AppiumDriver appiumDriver = null;
	
	// Local variables
		private WrapperFunctions objWrapperFunctions = new WrapperFunctions();
		private Utilities objUtilities = new Utilities();
		private StepBase objStepBase = new StepBase();
		
		By txtSelectedCandidates = By.xpath("//*[contains(@class,'mr-2 ng-tns-c9-0 ng-star-inserted')]");
		By btnSelectAllInOnePage = By.xpath("//*[contains(@for,'k-grid0-select-all')]");
		
		By btnSelectAll = By.xpath("//*[contains(@class,'btn btn-secondary ng-tns-c9-0 ng-star-inserted')]");
		By btnDeselectAll = By.xpath("//*[contains(@class,'btn btn-secondary ng-tns-c9-0 ng-star-inserted')]");
		By selectCandidate = By.xpath("//*[contains(@for,'k-grid0-checkbox0')]");
		By btnActionOnSelected = By.xpath("//*[contains(@title,'Candidate Options')]");
		By btnClickAssignGroup = By.xpath("//*[contains(@class,'dropdown-item')]");
		
		public boolean clickOnSelectAllInOnePage() throws InterruptedException{
			objWrapperFunctions.waitForElement(5);
			return objWrapperFunctions.click(btnSelectAllInOnePage);
		}

		public boolean clickOnDeselectAllInOnePage() throws InterruptedException{
			objWrapperFunctions.waitForElement(5);
			return objWrapperFunctions.click(btnSelectAllInOnePage);
		}
		
		public boolean clickOnSelectAll()throws InterruptedException{
			return objWrapperFunctions.click(btnSelectAll);
		}
		
		public boolean clickOnDeselectAll()throws InterruptedException{
			objWrapperFunctions.waitForElement(5);
			return objWrapperFunctions.click(btnDeselectAll);
		}
		
		public boolean selectOneCandidate()throws InterruptedException{
			return objWrapperFunctions.click(selectCandidate);
		}
		
		public boolean clickOnActionSelectedFunction()throws InterruptedException{
			return objWrapperFunctions.click(btnActionOnSelected);
		}
		
		public boolean clickOnSelectAssignGroup()throws InterruptedException{
			return objWrapperFunctions.click(btnClickAssignGroup);
		}
		
		public boolean checkElementPresence(String selectedCandidates) {
			objWrapperFunctions.getText(txtSelectedCandidates, selectedCandidates);
			System.out.println(selectedCandidates);
			return true;
		}
	}
