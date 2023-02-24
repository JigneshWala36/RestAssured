package com.qa.restapi.Jira;

import com.qa.restapi.file.AddData;
import com.qa.restapi.util.TestUtil;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JiraRestAssuredWithCookies {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "http://localhost:8080";

        // SessionFilter Store the session cookies

        SessionFilter storeSession = new SessionFilter();

        // Login and Capture session with SessionFilter
        String successLoginJsonResponse = given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Path.of(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\qa\\restapi\\Jira\\LoginUsernamePassword.json")))).log().all().filter(storeSession)
                .when().post("/rest/auth/1/session").then().log().all().extract().asString();

        // Add Comment to the Issue

        JsonPath commentBodyMessageSent = JsonPath.given(new String(Files.readAllBytes(Path.of(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\qa\\restapi\\Jira\\AddComment.json"))));

        String commentBodyMessageSentJS = commentBodyMessageSent.get("body");
        System.out.println("*************************"+commentBodyMessageSentJS);





        String addCommentResponse = given().pathParam("key", "10003").log().all().header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Path.of(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\qa\\restapi\\Jira\\AddComment.json")))).log().all().filter(storeSession)
                .when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().asString();

        JsonPath addCommentResponseJS = TestUtil.rawDataToJsonDataConvert(addCommentResponse);
        String commentId = addCommentResponseJS.get("id");

        String commentBodyMessage = addCommentResponseJS.get("body");


        // Add Attachment to the Issue

        given().header("X-Atlassian-Token", "no-check").filter(storeSession).pathParam("key", "10003")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", new File(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\qa\\restapi\\Jira\\Jira.txt"))
                .when().post("/rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);

        // Fetch Details of the  Issue

        String getDetailsOfTheAddedIssue = given().filter(storeSession).pathParam("key", "10003")
                .queryParam("fields","comment")
                .log().all()
                .when().get("/rest/api/2/issue/{key}")
                .then().log().all().extract().response().asString();

        System.out.println(getDetailsOfTheAddedIssue);

        // Validate the Latest Comment

        JsonPath getDetailsOfTheAddedCommentIssueJS = TestUtil.rawDataToJsonDataConvert(getDetailsOfTheAddedIssue);
        int getCommentCount = getDetailsOfTheAddedCommentIssueJS.getInt("fields.comment.comments.size()");

        for (int i = 0; i < getCommentCount; i++) {

            String getCommentIndex = getDetailsOfTheAddedCommentIssueJS.get("fields.comment.comments["+i+"].id").toString();
            if (getCommentIndex.equalsIgnoreCase(commentId)){

                String latestAddedComment = getDetailsOfTheAddedCommentIssueJS.get("fields.comment.comments["+i+"].body").toString();
                System.out.println(latestAddedComment);

                Assert.assertEquals(commentBodyMessage,latestAddedComment,"Added Comment Does Not match");

            }


        }




    }
}
