package com.qa.restapi.basic;

import com.qa.restapi.file.AddData;
import com.qa.restapi.util.TestUtil;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js = TestUtil.rawDataToJsonDataConvert(AddData.CourseBody());

//        1. Print No of courses returned by API

        int courseCount = js.getInt("courses.size()");
        System.out.println(courseCount);

//        2.Print Purchase Amount

        int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalPurchaseAmount);

//        3. Print Title, Price & Copies of the first course

        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println(firstCourseTitle);

        int firstCoursePrice = js.getInt("courses[0].price");
        System.out.println(firstCoursePrice);

        int firstCourseCopies = js.getInt("courses[0].copies");
        System.out.println(firstCourseCopies);

//        4. Print All course titles and their respective Prices



        for (int i = 0; i < courseCount; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            System.out.println(courseTitle);

            int coursePrice = js.getInt("courses[" + i + "].price");
            System.out.println(coursePrice);
//            or
            System.out.println(js.get("courses[" + i + "].price").toString());

            int courseCopies = js.getInt("courses[" + i + "].copies");


        }

//        5. Print no of copies sold by RPA Course

        for (int i = 0; i < courseCount; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")){

                int copiesCount = js.getInt("courses[" + i + "].copies");
                System.out.println("--------------------"+copiesCount);
                break;
            }

        }






    }
}
