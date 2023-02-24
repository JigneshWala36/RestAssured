package com.qa.restapi.basic;

import com.qa.restapi.file.AddData;
import com.qa.restapi.util.TestUtil;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidationOfAmount {


    //        6. Verify if Sum of all Course prices matches with Purchase Amount

    @Test
    public void validateTotalPurchaseAmount(){

        JsonPath js = TestUtil.rawDataToJsonDataConvert(AddData.CourseBody());
        int courseCount = js.getInt("courses.size()");

        int purchaseAmount = 0;


        int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalPurchaseAmount);

        for (int i = 0; i < courseCount; i++) {
//            String courseTitle = js.getString("courses[" + i + "].title");
//            System.out.println(courseTitle);
            int coursePrice = js.getInt("courses[" + i + "].price");
//            System.out.println(coursePrice);

            int courseCopies = js.getInt("courses[" + i + "].copies");
//            System.out.println(courseCopies);

            purchaseAmount = purchaseAmount + (courseCopies*coursePrice);
//            System.out.println(purchaseAmount);


        }
        System.out.println(purchaseAmount);

        Assert.assertEquals(totalPurchaseAmount,purchaseAmount,"Amount Did Not Match");
        System.out.println("Success");

    }
}
