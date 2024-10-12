
package api.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

public class ProductEndPoints {
    public static Response listProducts() {
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .accept(ContentType.JSON)
                .when()
                .get(Routes.product_uri)
                .then().log().all()
                .extract().response();
        return response;
    }

    public static Response createProduct(String payload) {
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.product_uri)
                .then().log().all()
                .extract().response();
        return response;
    }

    public static Response getProductById(int productId) {
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .accept(ContentType.JSON)
                .when()
                .get(Routes.product_id_uri, productId)
                .then().log().all()
                .extract().response();
        return response;
    }

    public static Response updateProductById(String payload, int productId) {
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.product_id_uri, productId)
                .then().log().all()
                .extract().response();
        return response;
    }

    public static Response deleteProductById(int productId) {
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .accept(ContentType.JSON)
                .when()
                .delete(Routes.product_id_uri, productId)
                .then().log().all()
                .extract().response();
        return response;
    }
}