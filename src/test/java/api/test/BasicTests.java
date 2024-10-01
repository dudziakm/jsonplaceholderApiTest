package api.test;

import api.config.ApiConfig;
import api.constants.ApiEndpoints;
import api.constants.ApiHeaders;
import api.constants.ApiStrings;
import api.constants.HttpStatusCodes;
import api.constants.FilePaths;
import api.model.Post;
import api.utils.Helpers;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicTests {

    @Test(description = "Verify that the Get Post API returns correctly")
    public void verifyGetAPI() {
        // Given
        given()
                .baseUri(ApiConfig.BASE_URL)
                .header(ApiHeaders.CONTENT_TYPE_JSON)
                // When
                .when()
                .get(ApiEndpoints.POST_BY_ID, 1)
                // Then
                .then()
                .statusCode(HttpStatusCodes.OK)  // Using external status code constant
                // Verify correct value using constants from ApiStrings
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo(ApiStrings.POST_TITLE))
                .body("body", equalTo(ApiStrings.POST_BODY));
    }

    @Test(description = "Verify that the publish post API returns correctly")
    public void verifyPostAPI() throws IOException {
        // Read body from JSON file and parse into Post object
        Post expectedPost = Helpers.parseJsonToPost(FilePaths.POST_REQUEST_BODY);  // Using external file path

        // Given
        given()
                .baseUri(ApiConfig.BASE_URL)
                .header(ApiHeaders.CONTENT_TYPE_JSON)
                .body(expectedPost)
                // When
                .when()
                .post(ApiEndpoints.POSTS)
                // Then
                .then()
                .statusCode(HttpStatusCodes.CREATED)  // Using external status code constant
                // Verify correct value from JSON
                .body("userId", equalTo(expectedPost.getUserId()))
                .body("title", equalTo(expectedPost.getTitle()))
                .body("body", equalTo(expectedPost.getBody()));
    }
}
