package org.satyan.stepdefs;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestConfig {
    protected static String baseUrl = "https://reqres.in/api"; // Replace with your API base URL

    @BeforeClass
    public static void setupBase() {
        RestAssured.baseURI = baseUrl;
        System.out.println("baseUrl");
        System.out.println(RestAssured.baseURI);
    }
}
