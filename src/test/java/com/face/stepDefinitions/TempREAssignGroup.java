package com.face.stepDefinitions;

import org.junit.Assert;

import com.face.pageFactory.TempREAssignGroupPage;
import com.face.pageFactory.TempRELoginPage;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TempREAssignGroup {
	// Local variables
	private Utilities objUtilities = new Utilities();
	private WrapperFunctions objWrapperFunctions = new WrapperFunctions();

	private TempREAssignGroupPage objTempREAssignGroupPage = new TempREAssignGroupPage();
	
	@When("^User selects group$")
	public void User_selects_group() throws Throwable {
		Assert.assertTrue(objTempREAssignGroupPage.selectNewGroup());
	}

	@When("^Click on save button$")
	public void Click_on_save_button() throws Throwable {
		Assert.assertTrue(objTempREAssignGroupPage.clickOnSaveGroupButton());
	}
	
	@Then("^Success message is thrown on candidates page (.*)$")
	public void Success_message_is_thrown_on_candidates_page_Changes_Saved(String assignGroupSuccessMsg) throws Throwable {
		Assert.assertEquals(assignGroupSuccessMsg, objTempREAssignGroupPage.verifySuccessMsgOfGroupAssigned(assignGroupSuccessMsg));
	}

	@When("^User click on Add new group$")
	public void User_click_on_Add_new_group() throws Throwable {
		Assert.assertTrue(objTempREAssignGroupPage.clickOnAddNewGroup());
	}

	@Then("^User enter group name (.*)$")
	public void User_enter_group_name_Group_Name(String newGroupName) throws Throwable {
		Assert.assertTrue(objTempREAssignGroupPage.enterNewGroupName(newGroupName));
	}

	@Then("^Enters description of group (.*)$")
	public void Enters_description_of_group_Description(String newGroupDescription) throws Throwable {
		Assert.assertTrue(objTempREAssignGroupPage.enterNewGroupNameDescription(newGroupDescription));
	}

//	@Then("^Click on save button$")
//	public void Click_on_save_button() throws Throwable {
//	    // Express the Regexp above with the code you wish you had
//	    throw new PendingException();
//	}
//
//	@Then("^Success message is thrown Changes Saved$")
//	public void Success_message_is_thrown_Changes_Saved() throws Throwable {
//	    // Express the Regexp above with the code you wish you had
//	    throw new PendingException();
//	}
	
	@Given("^Connect to DB and fetch the details (.*)$")
	public void executeDBQuery(String SQLquery){

		Assert.assertTrue(objTempREAssignGroupPage.executeDBQuery(SQLquery));
	}
}
