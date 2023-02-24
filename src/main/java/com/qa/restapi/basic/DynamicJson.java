package com.qa.restapi.basic;

import com.qa.restapi.file.AddData;
import com.qa.restapi.util.TestUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {

    @Test(dataProvider = "newBookData")
    public void addBook(String isbn, String aisle) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
//        RestAssured.baseURI = "http://216.10.245.166";


        // Add Book
        String addBook = given().log().all().header("Content-Type", "application/json")
                .body(AddData.addBookLibrary(isbn,aisle)).when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .body("Msg", equalTo("successfully added")).extract().response().asString();

        // For Parsing JSON
        JsonPath addBookJS = TestUtil.rawDataToJsonDataConvert(addBook);
        String addedNewBookID = addBookJS.getString("ID");
        System.out.println(addedNewBookID);

    }

    @DataProvider(name = "newBookData")
    public Object[][] sendMultipleDataToAddBook(){

        //Array  = Collections of Element
        //Multidimensional Array =  Collections of Arrays

        return new Object[][] {{"Das","3691"},{"Das","3692"},{"Das","3693"}};
    }



}
