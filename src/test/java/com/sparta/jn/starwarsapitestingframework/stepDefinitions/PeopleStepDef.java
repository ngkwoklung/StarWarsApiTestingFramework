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

import static io.restassured.RestAssured.given;

public class PeopleStepDef extends Utils {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    static int MAX_PEOPLE;

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

    @When("user calls {string} with {string} http request and id is {string}")
    public void user_calls_with_http_request_and_id_is(String resource, String method, String id) {
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

    @When("user get Max number of people")
    public void user_get_max_number_of_people() {
        response = requestSpecification.when().get(APIResources.getPeopleAPI.getResource());
        MAX_PEOPLE = Integer.parseInt(getJsonPath(response, "count"));
    }

    @When("user call {string} with {string} http request and id is {int} to {string}")
    public void user_call_with_http_request_and_id_is_to(String string, String string2, Integer int1, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
