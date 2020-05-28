package com.face.stepDefinitions;

import org.junit.Assert;
import com.face.pageFactory.TempRELoginPage;
import core.generic.Utilities;
import core.generic.WrapperFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TempRELogin {
	// Local variables
	private Utilities objUtilities = new Utilities();
	private WrapperFunctions objWrapperFunctions = new WrapperFunctions();

	private TempRELoginPage objTempRELoginPage = new TempRELoginPage();
	
	@Given("^User enter the email address(.*)$")
	public void enterEmailAddress(String username){
		Assert.assertTrue(objTempRELoginPage.enterEmailAddress(username));
	}
	
	@When("^User enter the Password(.*)$")
	public void clickOnAppointmentTab(String password){
		Assert.assertTrue(objTempRELoginPage.enterPassword(password));
	}
	
	@And("^Click on Sign in Button$")
	public void clickOnAppointmentTab(){
		Assert.assertTrue(objTempRELoginPage.clickSignIn());
	}	
}
