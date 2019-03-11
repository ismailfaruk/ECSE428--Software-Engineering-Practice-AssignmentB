#Author: Mustafa Khawaja
#ID: 260630187
#Key Words Summary:
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Sending emails with attachments through Gmail using Google Chrome

  @NormalFlow
  Scenario: Sending an email with an image to someone
    Given I am on the Gmail homepage using Google Chrome
    And I log in with valid credentials
    When I compose a new email
    And attach an image
    Then I will send the email successfully


  @AlternateFlow1-url_file
  Scenario: Sending an email to myself with a file url
    Given I am on the Gmail homepage using Google Chrome
    And I log in with valid credentials
    When I compose a new email
    And send an attachment as a url
    Then I will send the email successfully with a url
    
  @AlternateFlow2-email_with_video
  Scenario: Sending an email with a video to someone
    Given I am on the Gmail homepage using Google Chrome
    And I log in with valid credentials
    When I compose a new email
    And attach a video
    Then I will send the email successfully

  @AlternateFlow3-multiple_attachments
  Scenario: Sending an email with a video and image to someone
    Given I am on the Gmail homepage using Google Chrome
    And I log in with valid credentials
    When I compose a new email
    And attach a video and image
    Then I will send the email successfully
    

  @ErrorFlow-file_does_not_exist
  Scenario: Sending an email with an attachment that does not exist
    Given I am on the Gmail homepage using Google Chrome
    And I log in with valid credentials
    When I compose a new email
    And attach a file that does not exist
    Then I will not send the email
