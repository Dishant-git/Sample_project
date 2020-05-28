Feature: Login into TempRE Application 	
@TempRE 
Scenario Outline: Login using valid username and password
	Given User enter the email address <username>
	When User enter the Password <password>
	And Click on Sign in Button
	Then User should successfully login to application
			
	Examples: 
		| username	                      | password    | 
		| tempre.trust.user@liaisonfs.com | T3mpReG3n2! | 

@TempRECandidatesPage
Scenario Outline: Select and deselect all candidates on candidates page
	Given User enter the email address <username>
	When User enter the Password <password>
	And Click on Sign in Button
	Then User should successfully login to application
	Given User navigate to candidates page
	When User select all candidates from one page 
	When User deselect all candidates from one page 
	When User click on select all button 
	When User click on Deselect all button
	When User selects one candidate
	Then Action on selected function should enable and user click on Action on selected button
	And User selects Assign group
	When User selects group 
	And Click on save button
	Then Success message is thrown on candidates page <assignGroupSuccessMsg>
	When Action on selected function should enable and user click on Action on selected button
	And User selects Assign group
	When User click on Add new group
	Then User enter group name <newGroupName>
	And Enters description of group <newGroupDescription>
	And Click on save button
	Then Success message is thrown on candidates page <assignGroupSuccessMsg>
#	Then Success message is thrown <newGroupSuccessMsg>
#	When User selects group 
#	And Click on save button
#	Then Success message is thrown on candidates page <assignGroupSuccessMsg>
	  
	Examples:
	| username	                      | password    | assignGroupSuccessMsg | newGroupName  | newGroupDescription |newGroupSuccessMsg |
	| tempre.trust.user@liaisonfs.com | T3mpReG3n2! | Changes Saved         | Group Name 21 | Description12       |Changes Saved      |
	
	
	@PMToolDB
Scenario Outline: TC_01
	
	Given Connect to DB and fetch the details <SQLquery>
#	Given Connect to DB and fetch the details
#	Then Enter Mipegs Password <password>
#	Then Click on Mipegs Login button
#	Then Verify Mipegs Logout button displayed
	
Examples:
	| SQLquery |
	|SELECT TOP (05) [UserId],[CompanyId],[FirstName],[LastName],[Email],[IsSuperUser],[VerificationCode],[SendMailError],[HasActivated],[Password],[PasswordToBeDeleted],[WorkPhoneNo] FROM [PMTool_QA].[dbo].[tblUser]|
	