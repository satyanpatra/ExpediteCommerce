package org.satyan.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class UserRegistrationSteps extends TestConfig {
    private Response response;
    private String username;
    private String password;
    private String authToken;

    @Given("a user with username {string} and password {string}")
    public void aUserWithUsernameAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @When("the user registers")
    public void theUserRegisters() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", username);
        requestBody.put("password", password);

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", username);
        jsonBody.put("password", password);
        response = RestAssured.given()
                                .log().all()
                                .contentType(ContentType.JSON)
                                .body(jsonBody.toString())

                .when()
                    .post("http://localhost:8080/register");
    }

    @Then("the registration should be successful")
    public void theRegistrationShouldBeSuccessful() {
        Assert.assertEquals(response.getStatusCode(), 200, "Registration failed");
        Assert.assertNotNull(response.jsonPath().getString("token"));
        System.out.println("Registration Successful: " + response.getBody().asString());
    }

    @When("the user logs in")
    public void theUserLogsIn() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("http://localhost:8080/login");
    }

    @Then("the login should be successful and an auth token is returned")
    public void theLoginShouldBeSuccessfulAndAnAuthTokenIsReturned() {
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        authToken = response.jsonPath().getString("token");
        Assert.assertNotNull(authToken, "Auth token is null");
        System.out.println("Login Successful: " + response.getBody().asString());
    }

    @When("the user creates a support case with title {string} and description {string}")
    public void theUserCreatesASupportCaseWithTitleAndDescription(String title, String description) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("description", description);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(requestBody)
                .post("http://localhost:8080/create-case");
    }

    @Then("the support case creation should be successful")
    public void theSupportCaseCreationShouldBeSuccessful() {
        Assert.assertEquals(response.getStatusCode(), 201, "Support case creation failed");
        Assert.assertNotNull(response.jsonPath().getString("caseId"));
        System.out.println("Support Case Created: " + response.getBody().asString());
    }
}
