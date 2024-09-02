package api.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import api.models.Category;
import api.models.Pet;
import api.models.Tag;
import org.testng.Assert;

import java.util.Collections;
public class PetHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Pet createPet(int id, String name, String categoryName) {
        Category category = new Category(1, categoryName);
        Tag tag = new Tag(0, "string");
        return new Pet(id, name, category, Collections.singletonList("string"), Collections.singletonList(tag), "available");
    }

    public static void validateResponseStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Expected status code " + expectedStatusCode);
    }

    public static Pet parsePetResponse(Response response) throws Exception {
        return objectMapper.readValue(response.asString(), Pet.class);
    }

    public static void validatePet(Pet expectedPet, Pet actualPet) {
        Assert.assertEquals(actualPet.getName(), expectedPet.getName(), "Pet names do not match");
        Assert.assertEquals(actualPet.getStatus(), expectedPet.getStatus(), "Pet statuses do not match");
        Assert.assertEquals(actualPet.getId(), expectedPet.getId(), "Pet IDs do not match");
        Assert.assertEquals(actualPet.getCategory().getId(), expectedPet.getCategory().getId(), "Pet category IDs do not match");
        Assert.assertEquals(actualPet.getCategory().getName(), expectedPet.getCategory().getName(), "Pet category names do not match");
    }
}
