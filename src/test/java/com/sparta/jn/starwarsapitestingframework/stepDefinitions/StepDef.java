package com.sparta.jn.starwarsapitestingframework.stepDefinitions;

import com.sparta.jn.starwarsapitestingframework.APIResources;
import com.sparta.jn.starwarsapitestingframework.connection.Utils;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;
import com.sparta.jn.starwarsapitestingframework.injection.Injector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class StepDef extends Utils {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;

    @Given("GET People resource")
    public void get_people_resource() {
        requestSpecification = given().spec(requestSpecification());
    }
    @When("user calls {string} with {string} http request and id is {int}")
    public void user_calls_with_http_request_and_id_is(String resource, String method, int id) {
        APIResources apiResource = APIResources.valueOf(resource);
        System.out.println(apiResource.getResource());
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if (method.equalsIgnoreCase("GET")) {
            response = requestSpecification.when().get(apiResource.getResource() + id);
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the Edited Date is after Created Date")
    public void the_edited_date_is_after_created_date() {
        PeopleDTO peopleDto = Injector.injectPeopleDTO(response);
        Assert.assertTrue(peopleDto.hasLogicalEditedDate());
    }
}
