package com.qa.restapi.basic;

import com.qa.restapi.file.AddData;
import com.qa.restapi.util.TestUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Add Place
        String addResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(AddData.addDataInJsonForm()).when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(addResponse);

        // For Parsing JSON
        JsonPath json = new JsonPath(addResponse);
        String addPLaceId = json.getString("place_id");

        System.out.println(addPLaceId);

        // Update Place
        String updateAddress = "Chhani Vadodara India";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + addPLaceId + "\",\n" +
                        "\"address\":\""+updateAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get Place

        String getUpdatedAddressDetail =
                given().log().all().queryParam("key", "qaclick123").queryParam("place_id", addPLaceId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

//        JsonPath js = new JsonPath(getUpdatedAddressDetail);
        JsonPath js = TestUtil.rawDataToJsonDataConvert(getUpdatedAddressDetail);
        String updatedAddressDetailsFetchFromJson = js.getString("address");
        System.out.println(updatedAddressDetailsFetchFromJson);
//
        Assert.assertEquals(updatedAddressDetailsFetchFromJson,updateAddress,"Address Does not Match");


    }
}
