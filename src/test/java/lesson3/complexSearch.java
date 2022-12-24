package lesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class complexSearch extends AbstractTest {

    @Test
    void getStatusCode200() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch" +
                        "includeNutrition=false&apiKey=" + getApiKey())
                    .then()
                    .assertThat()
                    .statusCode(200);
    }

    @Test
    void getApiResponseFields() {

        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("number"), equalTo(10));
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("totalResults"), equalTo(5219));

    }

    @Test
    void getStatusLineOk() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch" +
                        "includeNutrition=false&apiKey=" + getApiKey())
                .then()
                .assertThat()
                .statusLine(containsString("OK"));

    }

    @Test
    void getHeaderKeepAlive() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch" +
                        "includeNutrition=false&apiKey=" + getApiKey())
                .then()
                .assertThat()
                .header("Connection", "keep-alive");

    }

    @Test
    void getTimeLessThan600() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch" +
                        "includeNutrition=false&apiKey=" + getApiKey())
                .then()
                .assertThat()
                .time(lessThan(600L));

    }
}
