So steps to create a test are as follows:

- You create a feature file (play_with_spark.feature)

> Feature: Play with Spark HTTP Server a bit
>
>  Scenario: call no delay
>    Given I have spark consumer started
>    And I have spark publisher started
>    When I call publisher with 0 delay
>    Then I get a response with transport time overhead measurement


- You create an empty test class with Cucumber runner

```java
@RunWith(Cucumber.class)
public class GenerateFeaturesTest {
}
```

- You run the empty test -> console prints you the test methods sceleton

```java
@Given("^I have spark consumer started$")
public void i_have_spark_consumer_started() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Given("^I have spark publisher started$")
public void i_have_spark_publisher_started() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@When("^I call publisher with (\\d+) delay$")
public void i_call_publisher_with_delay(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Then("^I get a response with transport time overhead measurement$")
public void i_get_a_response_with_transport_time_overhead_measurement() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```

- You fill the methods with gluecode (the test runner is stateful so each method is just a step in the test)

We could use rest-assured or any other testing tools underneeth, Cucumber is just to wrap this in a human readable Acceptance Test
scenarios. I think feature files support mark down, so we could create a very nice documentation as well. Also using a framework like
this enforce us to use a really blackbox approach - not to go to much into implementation details when writing tests.