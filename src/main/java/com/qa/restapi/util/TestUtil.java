package com.qa.restapi.util;

import io.restassured.path.json.JsonPath;

public class TestUtil {

    public static JsonPath rawDataToJsonDataConvert(String response){

//        JsonPath js = new JsonPath(response);
//        return js;
        return new JsonPath(response);

    }
}
