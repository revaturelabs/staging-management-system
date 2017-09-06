Feature: Logging Into Staging Management System
	I want to use a pre-existing associate to log into Staging Management System

Scenario Outline: Logging into Staging Management System
Given I am at the login screen for Staging Management System
When I input my "<username>" and "<password>" and click login
Then I shall be redirected to the associate profile page.

Examples:
	|username												|password		|
	|timothygillette@ymail.com 			|password		|
	|stephan.kritikos@gmail.com			|password		|
	|philosopherlife@yahoo.com			|password		|
