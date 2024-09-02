package api.utils;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import api.models.Pet;
import static io.restassured.RestAssured.given;

public class PetApiClient {
    private final RequestSpecification requestSpec;

    public PetApiClient() {
        requestSpec = given().baseUri(api.config.ApiConfig.BASE_URI).contentType("application/json");
    }

    public Response createPet(Pet pet) {
        return requestSpec.body(pet).post("/pet");
    }

    public Response getPetById(int petId) {
        return requestSpec.get("/pet/" + petId);
    }

    public Response updatePet(Pet pet) {
        return requestSpec.body(pet).put("/pet");
    }

    public Response updatePetName(int petId, String name) {
        // Update with query parameter
        return requestSpec.post("/pet/" + petId + "?name=" + name);
    }


    public Response deletePetById(int petId) {
        // Handle DELETE request
        return requestSpec.delete("/pet/" + petId);
    }
}
