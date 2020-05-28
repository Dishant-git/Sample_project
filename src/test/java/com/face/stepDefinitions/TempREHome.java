package com.face.stepDefinitions;

import org.junit.Assert;
import com.face.pageFactory.TempREHomePage;
import com.face.pageFactory.TempRELoginPage;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TempREHome {
	// Local variables
	private Utilities objUtilities = new Utilities();
	private WrapperFunctions objWrapperFunctions = new WrapperFunctions();

	private TempREHomePage objTempREHomePage = new TempREHomePage();
	
	@Then("^User should successfully login to application$")
	public void checkElementPresence(){
		Assert.assertTrue(objTempREHomePage.checkElementPresence());
	}	
	
	@Given ("^User navigate to candidates page$")
	public void clickOnCandidates(){
		Assert.assertTrue(objTempREHomePage.clickOnCandidatesButton());
	}	
	
}
