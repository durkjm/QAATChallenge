package api.tests;

import api.helpers.PetHelper;
import api.models.Pet;
import api.utils.PetApiClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class PetController {
    private PetApiClient petApiClient;

    @BeforeClass
    public void setup() {
        petApiClient = new PetApiClient();
    }

    @Test(priority = 1)
    public void CreatePet() throws Exception {
        Pet expectedPet = PetHelper.createPet(10, "Scooby", "Dogs");

        Response response = petApiClient.createPet(expectedPet);

        PetHelper.validateResponseStatusCode(response, 200);
        Pet actualPet = PetHelper.parsePetResponse(response);
        PetHelper.validatePet(expectedPet, actualPet);
    }

    @Test(priority = 2)
    public void GetCreatedPetById() throws Exception {
        int petId = 10;

        Response response = petApiClient.getPetById(petId);
        PetHelper.validateResponseStatusCode(response, 200);

        Pet actualPet = PetHelper.parsePetResponse(response);
        Assert.assertEquals(actualPet.getId(), petId, "Expected pet ID to match");
        Assert.assertEquals(actualPet.getName(), "Scooby", "Expected pet name to be 'Rambo'");
    }

    @Test(priority = 3)
    public void GetNonExistentPet() {
        int nonExistentPetId = 999;

        Response response = petApiClient.getPetById(nonExistentPetId);
        PetHelper.validateResponseStatusCode(response, 404);
    }

    @Test(priority = 4)
    public void UpdateCreatedPet() throws Exception {
        Pet updatedPet = PetHelper.createPet(10, "Coqui", "Dogs");

        Response response = petApiClient.updatePet(updatedPet);
        PetHelper.validateResponseStatusCode(response, 200);

        Pet actualPet = PetHelper.parsePetResponse(response);
        Assert.assertEquals(actualPet.getName(), updatedPet.getName(), "Updated pet names do not match");
        Assert.assertEquals(actualPet.getStatus(), updatedPet.getStatus(), "Updated pet statuses do not match");
    }

    @Test(priority = 5)
    public void UpdatePetNameByName() throws Exception {
        Response response = petApiClient.updatePetName(10, "Mufaza");

        PetHelper.validateResponseStatusCode(response, 200);

        Pet actualPet = PetHelper.parsePetResponse(response);
        Assert.assertEquals(actualPet.getName(), "Mufaza", "Expected pet name to be 'Mufaza'");
    }

    @Test(priority = 6)
    public void DeletePetById() {
        Response deleteResponse = petApiClient.deletePetById(10);
        PetHelper.validateResponseStatusCode(deleteResponse, 200);

        Response getResponse = petApiClient.getPetById(10);
        PetHelper.validateResponseStatusCode(getResponse, 404);
    }


}
