package com.face.pageFactory;

import org.openqa.selenium.By;
import core.generic.StepBase;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import io.appium.java_client.AppiumDriver;

public class TempREAssignGroupPage {

	protected static AppiumDriver appiumDriver = null;
	
	// Local variables
		private WrapperFunctions objWrapperFunctions = new WrapperFunctions();
		private Utilities objUtilities = new Utilities();
		private StepBase objStepBase = new StepBase();
		
		
		By btnNewGroup = By.xpath("//*[contains(@for,'287b557d-522a-4cb9-b724-6914698f9019')]");
		By btnSaveGroup = By.xpath("//*[contains(@id,'saveBtn')]");
		By txtSuccessMsg = By.xpath("//*[contains(@class,'toast-message ng-star-inserted')]");
		By btnAddNewGroup = By.xpath("//*[contains(@class,'btn btn-secondary mb-2')]");
		
		By txtNewGroupName = By.xpath("//*[contains(@id,'groupName')]");
		By txtDescriptionOfGroup = By.xpath("//*[contains(@id,'groupDescription')]");
		
		String ipaddress = "203.193.165.235";
		String DatabaseName = "PMTool_QA";
		String username = "sa";
		String password = "ge!f*tku12";
		String portnumber = "1433";
		
		
		public boolean selectNewGroup()throws InterruptedException{
			objWrapperFunctions.waitForElement(5);
			return objWrapperFunctions.click(btnNewGroup);	
		}
		
		public boolean clickOnSaveGroupButton()throws InterruptedException{
			return objWrapperFunctions.click(btnSaveGroup);
		}

		public String verifySuccessMsgOfGroupAssigned(String assignGroupSuccessMsg) {
			assignGroupSuccessMsg = objWrapperFunctions.getText(txtSuccessMsg, "Text");
			return assignGroupSuccessMsg;
		}
		
		public boolean clickOnAddNewGroup()throws InterruptedException{
			return objWrapperFunctions.click(btnAddNewGroup);
		}
		
		public boolean enterNewGroupName(String newGroupName)throws InterruptedException{
			return objWrapperFunctions.setText(txtNewGroupName, newGroupName);
		}
		
		public boolean enterNewGroupNameDescription(String newGroupDescription)throws InterruptedException{
			return objWrapperFunctions.setText(txtDescriptionOfGroup, newGroupDescription);
		}
	
		public boolean executeDBQuery(String SQLquery){ 
			Utilities.ConnectToDB(ipaddress,username, password, DatabaseName, portnumber, SQLquery);
			return true;
			}
		
		
	}
