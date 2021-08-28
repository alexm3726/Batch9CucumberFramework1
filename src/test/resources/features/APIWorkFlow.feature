#Author: Jose Moreno Santos
  Feature: Syntax HRMS API Workflow
    Description: This feature files test syntax HRMS API Workflow

    Background:
      Given a JWT is generated

    @APIWorkflow
    Scenario: Creating an employee
      Given a request is prepared to create an employee
      When a POST call is made to create an employee
      Then the status code for creating an employee is 201
      And the employee created contains key "Message" and value "Entry Created"
      And the employeeID "" is stored as a global variable to be used for other calls