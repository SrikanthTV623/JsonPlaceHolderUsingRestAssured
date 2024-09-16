package com.automation.steps;

import com.automation.pojo.CreateObjectPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code {int}")
    public void verifyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @And("verify id is not empty")
    public void verifyIdIsNotEmpty() {
        String resourceId = RestAssuredUtils.getResponseFieldValue("id");
        Assert.assertTrue(!resourceId.isEmpty());
    }

    @And("store created id into {string}")
    public void storeCreatedIdInto(String key) {
        ConfigReader.setConfigValue(key,
                RestAssuredUtils.getResponseFieldValue("id"));
    }

    @And("verify response is same as request passed in post call")
    public void verifyResponseIsSameAsRequestPassedInPostCall() throws JsonProcessingException {
        String content = RestAssuredUtils.getDataFromFile("src/test/resources/json/createObject.json");
        ObjectMapper om = new ObjectMapper();

        CreateObjectPojo expectedRequest = om.readValue(content, CreateObjectPojo.class);

        CreateObjectPojo actualResponse = RestAssuredUtils.getResponse().as(CreateObjectPojo.class);

        System.out.println(expectedRequest.toString());
        System.out.println(actualResponse.toString());
        System.out.println(expectedRequest.equals(actualResponse));

        Assert.assertTrue(expectedRequest.equals(actualResponse));
    }

    @And("verify response is matching with the {string}")
    public void verifyResponseIsMatchingWithThe(String fileName) {
        RestAssuredUtils.getResponse().then().assertThat().
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath
                        ("json/createObjectSchema.json"));
    }
}
