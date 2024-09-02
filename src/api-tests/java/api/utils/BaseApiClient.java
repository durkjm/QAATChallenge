package api.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiClient {
    private final String baseUri;
    private final RequestSpecification requestSpec;

    public BaseApiClient(String baseUri) {
        this.baseUri = baseUri;
        RestAssured.baseURI = this.baseUri;
        this.requestSpec = given().contentType("application/json");
    }

    public Response post(Object requestBody, String endpoint) {
        return requestSpec.body(requestBody).when().post(endpoint);
    }

    public Response get(String endpoint) {
        return requestSpec.when().get(endpoint);
    }

    public Response put(Object requestBody, String endpoint) {
        return requestSpec.body(requestBody).when().put(endpoint);
    }

    // Add other methods like delete() as needed
}
