package com.nigames.jbdd.service.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;

public abstract class AbstractRequestTest {

    protected static String BASE_URL = "http://localhost:8888";

    protected abstract String getResourcePath();

    @Test
    public void canGet() {
        RestAssured.given().auth().basic("admin", "admin")
                .and().contentType(ContentType.JSON)
                .and().accept(ContentType.JSON)
                .when().get(BASE_URL + getResourcePath())
                .then().statusCode(200);
    }

    @Test
    public void isJson() {
        RestAssured.given().auth().basic("admin", "admin")
                .and().contentType(ContentType.JSON)
                .and().accept(ContentType.JSON)
                .when().get(BASE_URL + getResourcePath())
                .then().statusCode(200).and().header("Content-type", "application/json;charset=UTF-8");
    }

}
