package com.goeuro.cucumber;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class PlayWithSparkStepdefs {

    private Response response;

    @Given("^I have spark consumer started$")
    public void i_have_spark_consumer_started() throws Throwable {
        //in BDD 'given' is a step were we are defining the initial state
        // of the system, then comes action we are actually testing and
        // then is a result we expect. In this case given are empty but it's just a showcase

        //assuming consumer started here
    }

    @Given("^I have spark publisher started$")
    public void i_have_spark_publisher_started() throws Throwable {
        //assuming publisher started here
    }

    @When("^I call publisher with (\\d+) delay$")
    public void i_call_publisher_with_delay(int arg1) throws Throwable {
        response = get("http://localhost:8081/?delayMillis=0");
    }

    @Then("^I get a response with transport time overhead measurement$")
    public void i_get_a_response_with_transport_time_overhead_measurement() throws Throwable {
        response.then()
        .statusCode(200); //whatever
    }

}
