package steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import utilities.GenericFunctions;

import java.util.List;
import java.util.Map;

public class PetScenarioSteps {

    private static Response response;
    private static int count;

    @Given("^I request PetStore application for the status data below$")
    public void iRequestPetStoreApplicationForTheStatusDataBelow(DataTable dt) throws Exception {
        //Read Data from data table
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        String petStatus = list.get(0).get("Status");

        //Read Properties
        String url = GenericFunctions.getURL("status.endpoint", petStatus);

        //API Call
        APIServices apiServices = new APIServices();
        response = apiServices.getMethod(url);
        //response.getStatusCode();
    }

    @Then("^I verify the response for the Status and Pet name data below$")
    public void iVerifyTheResponseForTheStatusAndPetNameDataBelow(DataTable dt) {

        //Read Data from data table
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        String petStatus = list.get(0).get("Status");
        String petName = list.get(0).get("PetName");

        int count=0;
        JsonArray entries = (JsonArray) new JsonParser().parse(response.asString());
        for(int i=0;i<entries.size();i++){
            JsonElement name = ((JsonObject)entries.get(i)).get("name");
            JsonElement status = ((JsonObject)entries.get(i)).get("status");
            if(name !=null) {
                if (name.getAsString().equalsIgnoreCase(petName) && status.getAsString().equalsIgnoreCase(petStatus)) {
                    count++;
                }
            }
        }
        System.out.println("**** The no. of records meeting the criteria are: "+count);
    }
}
