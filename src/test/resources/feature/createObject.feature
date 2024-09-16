Feature: Valid Rest API Website Functionality

  Scenario: Verify user can create a object

    Given user wants to call base "restful"
    Given user wants to call "/objects" endpoint
    And set header "Content-type" to "application/json"
    And set request body for "restful" from the file "createObject.json" using pojo
    When user performs post call
    Then verify status code 200

    And store created id into "resource.id"

    When user wants to call "/objects/@id" endpoint
    When user performs get call
    Then verify status code 200
    And verify response is same as request passed in post call

    And verify response is matching with the "createObjectSchema.json"
