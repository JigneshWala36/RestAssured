package com.qa.restapi.file;

public class AddData {

    public static String addDataInJsonForm() {

        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Webmyne\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"Vadodara, India\",\n" +
                "  \"types\": [\n" +
                "    \"IT HUB\",\n" +
                "    \"International\"\n" +
                "  ],\n" +
                "  \"website\": \"http://webmyne.com\",\n" +
                "  \"language\": \"Hindi\"\n" +
                "}\n";
    }

    public static String updateAddedDataInJsonForm() {
        return "{\n" +
                "\"place_id\":\"a576d6283cb6c0ac9157283789ea71fb\",\n" +
                "\"address\":\"Alkapuri, India\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    public static String CourseBody() {

        return "{\n" +
                "\"dashboard\": {\n" +
                "\"purchaseAmount\": 910,\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "},\n" +
                "\"courses\": [\n" +
                "{\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\"price\": 50,\n" +
                "\"copies\": 6\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"Cypress\",\n" +
                "\"price\": 40,\n" +
                "\"copies\": 4\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"RPA\",\n" +
                "\"price\": 45,\n" +
                "\"copies\": 10\n" +
                "}\n" +
                "]\n" +
                "}\n";
    }

    public static String addBookLibrary(String isbn, String aisle) {
        String addBookInLibraryJson = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return addBookInLibraryJson;
    }


}
