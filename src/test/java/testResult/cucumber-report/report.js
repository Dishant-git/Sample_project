$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("PPlusComparison.feature");
formatter.feature({
  "line": 1,
  "name": "Login into TempRE Application",
  "description": "",
  "id": "login-into-tempre-application",
  "keyword": "Feature"
});
formatter.before({
  "duration": 7682890929,
  "status": "passed"
});
formatter.scenario({
  "line": 58,
  "name": "TC_01",
  "description": "",
  "id": "login-into-tempre-application;tc-01;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 47,
      "name": "@PMToolDB"
    }
  ]
});
formatter.step({
  "line": 50,
  "name": "Connect to DB and fetch the details SELECT TOP (05) [UserId],[CompanyId],[FirstName],[LastName],[Email],[IsSuperUser],[VerificationCode],[SendMailError],[HasActivated],[Password],[PasswordToBeDeleted],[WorkPhoneNo] FROM [PMTool_QA].[dbo].[tblUser]",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "SELECT TOP (05) [UserId],[CompanyId],[FirstName],[LastName],[Email],[IsSuperUser],[VerificationCode],[SendMailError],[HasActivated],[Password],[PasswordToBeDeleted],[WorkPhoneNo] FROM [PMTool_QA].[dbo].[tblUser]",
      "offset": 36
    }
  ],
  "location": "TempREAssignGroup.executeDBQuery(String)"
});
formatter.result({
  "duration": 141302369,
  "status": "passed"
});
});