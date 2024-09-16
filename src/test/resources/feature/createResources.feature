Feature: Valid JSON Placeholder Website Functionality

  Scenario: Verify user can create a resource

    Given user wants to call base "jsonplaceholder"
    Given user wants to call "/posts" endpoint
    And set header "Content-type" to "application/json"
    And set request body for "jsonplaceholder" from the file "createResource.json" using pojo
    When user performs post call
    Then verify status code 201

    And verify id is not empty
    And store created id into "resource.id"

    When user wants to call "/posts/@id" endpoint
    When user performs get call
    Then verify status code 200
