package com.juaracoding;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class testMovieDB {
    String baseUrl = "https://api.themoviedb.org";
    String key = "ecf38ca9ee3c423d71e213b3fcb474fb";
    String endpointNowPlaying = baseUrl+"/3/movie/now_playing?language=en-US&page=1";
    String endpointMoviePopular = baseUrl+"/3/movie/popular";
    String endpoinMovieRating = baseUrl+"/3/movie/569094/rating";


    @Test
    public void testStatusNowPlaying(){
        given()
                .queryParam("api_key",key)
                .when()
                .get(endpointNowPlaying)
                .then()
                .statusCode(200)
                .body("results.id[0]",equalTo(976573));
    }

    @Test
    public void testStatusMoviePopular(){
        given()
                .queryParam("api_key",key)
                .when()
                .get(endpointMoviePopular)
                .then()
                .statusCode(200)
                .body("results.id[0]",equalTo(569094));
    }

    @Test
    public void testMovieRating(){
        JSONObject request = new JSONObject();
        request.put("value",8.50);
        System.out.println(request.toJSONString());

        given()
                .header("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlY2YzOGNhOWVlM2M0MjNkNzFlMjEzYjNmY2I0NzRmYiIsInN1YiI6IjY0ZGI4ZDVlNTllOGE5MDBjNTlmOGMyYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.c7VYqXYal0e34cmJtyFr_L4x7f2dAsaUC3z7ZOOuKmk")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(endpoinMovieRating)
                .then()
                .statusCode(201)
                .log().all();
    }
}