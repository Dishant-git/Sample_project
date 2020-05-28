package com.face.stepDefinitions;

import org.junit.Assert;
import com.face.pageFactory.TempRECandidatesPage;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TempRECandidates {
	// Local variables
	private Utilities objUtilities = new Utilities();
	private WrapperFunctions objWrapperFunctions = new WrapperFunctions();

	private TempRECandidatesPage objTempRECandidatesPage = new TempRECandidatesPage();
	
	@When ("^User select all candidates from one page$")
	public void clickOnSelectAllInOnePage() throws InterruptedException{
		Assert.assertTrue(objTempRECandidatesPage.clickOnSelectAllInOnePage());
	}
	
//	@Then ("^All candidates in one page should get selected$")
//	public void verifyElementPresence(String selectedCandidates){
//		Assert.assertTrue(objTempRECandidatesPage.checkElementPresence(selectedCandidates));
//	}
	
	@When("^User deselect all candidates from one page$")
	public void User_deselect_all_candidates() throws Throwable {
		Assert.assertTrue(objTempRECandidatesPage.clickOnSelectAllInOnePage());
	}
//
//	@Then("^All candidates in one page should get de-selected$")
//	public void All_candidates_in_one_page_should_get_de_selected() throws Throwable {
//	    // Express the Regexp above with the code you wish you had
//	    throw new PendingException();
//	}
//
	@When("^User click on select all button$")
	public void User_click_on_select_all_button() throws Throwable {
		Assert.assertTrue(objTempRECandidatesPage.clickOnSelectAll());
	}
//
//	@Then("^User should able to select all candidates$")
//	public void User_should_able_to_select_all_candidates() throws Throwable {
//	    // Express the Regexp above with the code you wish you had
//	    throw new PendingException();
//	}
//
	@When("^User click on Deselect all button$")
	public void User_click_on_Deselect_all_button() throws Throwable {
		Assert.assertTrue(objTempRECandidatesPage.clickOnDeselectAll());
	}
//
//	@Then("^User should able to deselect all candidates$")
//	public void User_should_able_to_deselect_all_candidates() throws Throwable {
//	    // Express the Regexp above with the code you wish you had
//	    throw new PendingException();
//	}
//
	@When("^User selects one candidate$")
	public void User_selects_one_candidate() throws Throwable {
		Assert.assertTrue(objTempRECandidatesPage.selectOneCandidate());
	}

	@Then("^Action on selected function should enable and user click on Action on selected button$")
	public void Action_on_selected_function_should_enable_and_user_click_on_Action_on_selected_button() throws Throwable {
		Assert.assertTrue(objTempRECandidatesPage.clickOnActionSelectedFunction());
	}

	@Then("^User selects Assign group$")
	public void User_selects_Assign_group() throws Throwable {
		Assert.assertTrue(objTempRECandidatesPage.clickOnSelectAssignGroup());
	}	
}
