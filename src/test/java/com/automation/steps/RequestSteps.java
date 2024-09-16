package com.automation.steps;

import com.automation.pojo.CreateObjectPojo;
import com.automation.pojo.CreateResourcePojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class RequestSteps {
    @Given("user wants to call base {string}")
    public void userWantsToCallBase(String baseValue) {
        RestAssuredUtils.setUri(baseValue);
    }

    @Given("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        RestAssuredUtils.setHeader(key,value);
    }

    @Given("set request body from the file {string}")
    public void set_request_body_from_the_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @When("user performs post call")
    public void user_performs_post_call() {
        RestAssuredUtils.post();
    }

    @When("user performs get call")
    public void userPerformsGetCall() {
        RestAssuredUtils.get();
    }

    @And("set request body for {string} from the file {string} using pojo")
    public void setRequestBodyForFromTheFileUsingPojo(String urlValue, String fileName)throws JsonProcessingException {
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        String jsonBody = RestAssuredUtils.getDataFromFile(jsonFolderPath + fileName);

        ObjectMapper om = new ObjectMapper();
        if(urlValue.equals("jsonplaceholder")){
            CreateResourcePojo resourcePojo = om.readValue(jsonBody, CreateResourcePojo.class);
            RestAssuredUtils.setBodyUsingPojo(resourcePojo);
        }else {
            CreateObjectPojo objectPojo = om.readValue(jsonBody, CreateObjectPojo.class);
            RestAssuredUtils.setBodyUsingPojo(objectPojo);
        }

    }


}
