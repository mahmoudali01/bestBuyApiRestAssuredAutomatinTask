package api.test;

import api.endpoints.ProductEndPoints;
import api.payload.ProductsPayload;
import api.utils.fakerDataGenerator.ProductsFakerDataGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
public class ProductTests {

    private int createdProductId;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test(priority = 1, description = "Create Product with invalid price (greater than two decimal places)")
    @Story("POST Request with invalid price")
    @Description("Test Description: Verify that user can't create a product with price greater than two decimal places")
    public void createProductWithInvalidPrice() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithInvalidPrice();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'price' should be multiple of 0.01"));
    }

    @Test(priority = 2, description = "Create Product with zero price")
    @Story("POST Request with zero price")
    @Description("Test Description: Verify that user can't create a product with zero price")
    public void createProductWithZeroPrice() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithZeroPrice();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'price' should be greater than 0"));
    }

    @Test(priority = 3, description = "Create Product with negative price")
    @Story("POST Request with negative price")
    @Description("Test Description: Verify that user can't create a product with negative price")
    public void createProductWithNegativePrice() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithNegativePrice();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'price' shouldn't be negative"));
    }

    @Test(priority = 4, description = "Create Product with long price")
    @Story("POST Request with long price")
    @Description("Test Description: Verify that user can't create a product with price longer than allowed")
    public void createProductWithLongPrice() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateProductWithLargePrice();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'price' should NOT be longer than 10000000.00"));
    }

    @Test(priority = 5, description = "Create Product with long model")
    @Story("POST Request with long model")
    @Description("Test Description: Verify that user can't create a product with model longer than allowed")
    public void createProductWithLongModel() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithLongModel();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'model' should NOT be longer than 25 characters"));
    }

    @Test(priority = 6, description = "Create Product with long name")
    @Story("POST Request with long name")
    @Description("Test Description: Verify that user can't create a product with name longer than allowed")
    public void createProductWithLongName() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateProductWithLongName();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'name' should NOT be longer than 100 characters"));
    }

    @Test(priority = 7, description = "Create Product with long type")
    @Story("POST Request with long type")
    @Description("Test Description: Verify that user can't create a product with type longer than allowed")
    public void createProductWithLongType() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateProductWithLongType();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'type' should NOT be longer than 30 characters"));
    }

    @Test(priority = 8, description = "Create Product with long description")
    @Story("POST Request with long description")
    @Description("Test Description: Verify that user can't create a product with description longer than allowed")
    public void createProductWithLongDescription() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateProductWithLongDescription();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'description' should NOT be longer than 100 characters"));
    }

    @Test(priority = 9, description = "Create Product with long manufacturer")
    @Story("POST Request with long manufacturer")
    @Description("Test Description: Verify that user can't create a product with manufacturer longer than allowed")
    public void createProductWithLongManufacturer() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateProductWithLongManufacturer();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'manufacturer' should NOT be longer than 50 characters"));
    }

    @Test(priority = 10, description = "Create Product with long UPC")
    @Story("POST Request with long UPC")
    @Description("Test Description: Verify that user can't create a product with UPC longer than allowed")
    public void createProductWithLongUPCFormat() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithLongUPCFormat();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("name", equalTo("BadRequest"))
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'upc' should NOT be longer than 15 characters"));
    }

    @Test(priority = 11, description = "Create Product with all fields equals null")
    @Story("POST Request with all fields null")
    @Description("Test Description: Verify that user can't create a product with all fields null")
    public void createProductWithAllFieldsNull() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithAllFieldsNull();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'name' should be string"))
                .body("errors[1]", equalTo("'type' should be string"))
                .body("errors[2]", equalTo("'upc' should be string"))
                .body("errors[3]", equalTo("'description' should be string"))
                .body("errors[4]", equalTo("'manufacturer' should be string"))
                .body("errors[5]", equalTo("'model' should be string"));
    }

    @Test(priority = 12, description = "Create Product with all string fields empty")
    @Story("Create Product with all string fields empty")
    @Description("Test Description : Verify that user can't create a product with all string fields empty")
    public void createProductWithAllStringFieldsEmpty() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateInvalidProductWithAllStringFieldsEmpty();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'name' should NOT be shorter than 1 characters"))
                .body("errors[1]", equalTo("'type' should NOT be shorter than 1 characters"))
                .body("errors[2]", equalTo("'upc' should NOT be shorter than 1 characters"))
                .body("errors[3]", equalTo("'description' should NOT be shorter than 1 characters"))
                .body("errors[4]", equalTo("'manufacturer' should NOT be shorter than 1 characters"))
                .body("errors[5]", equalTo("'model' should NOT be shorter than 1 characters"));
    }

    @Test(priority = 13, description = "Create Product with invalid data types for fields")
    @Story("Create Product with invalid data types")
    @Description("Test Description : Verify that user can't create a product when price is not a float and other string fields contain non-string data types")
    public void createProductWithInvalidDataTypes() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateSimpleRandomInvalidProductData();
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(400)
                .body("message", equalTo("Invalid Parameters"))
                .body("errors[0]", equalTo("'name' should be string"))
                .body("errors[1]", equalTo("'type' should be string"))
                .body("errors[2]", equalTo("'price' should be number"))
                .body("errors[3]", equalTo("'upc' should be string"))
                .body("errors[4]", equalTo("'description' should be string"))
                .body("errors[5]", equalTo("'manufacturer' should be string"))
                .body("errors[6]", equalTo("'model' should be string"));
    }

    @Test(priority = 14, description = "Create Product with valid data")
    @Story("Create Product with valid data")
    @Description("Test Description : Verify that user can create a product with valid data")
    public void createProductWithValidData() throws Exception {
        String productJson = ProductsFakerDataGenerator.generateRandomValidProductData();
        ProductsPayload createdProduct = objectMapper.readValue(productJson, ProductsPayload.class);
        Response response = ProductEndPoints.createProduct(productJson);
        response.then()
                .statusCode(201)
                .body("name", equalTo(createdProduct.getName()))
                .body("type", equalTo(createdProduct.getType()))
                .body("price", equalTo(createdProduct.getPrice()))
                .body("description", equalTo(createdProduct.getDescription()))
                .body("manufacturer", equalTo(createdProduct.getManufacturer()))
                .body("upc", equalTo(createdProduct.getUpc()))
                .body("model", equalTo(createdProduct.getModel()));
        createdProductId = Integer.parseInt(response.jsonPath().getString("id"));
    }

    @Test(priority = 15, description = "Get Product by ID", dependsOnMethods = "createProductWithValidData")
    @Story("Get Product by ID")
    @Description("Test Description : Verify that user can retrieve a product by its ID")
    public void getProductByIdTest() {
        Response response = ProductEndPoints.getProductById(createdProductId);
        response.then()
                .statusCode(200)
                .body("id", equalTo(createdProductId));
    }

    @Test(priority = 16, description = "List All Products")
    @Story("GET Request to list all product")
    @Description("Test Description: Verify that use can list all products")
    public void listAllProducts() {
        Response response = ProductEndPoints.listProducts();
        response.then()
                .statusCode(200)
                .body("total", greaterThan(0))
                .body("data", instanceOf(List.class));
    }

    @Test(priority = 17, description = "Update Product by ID")
    @Story("Update Product by ID")
    @Description("Test Description : Verify that user can update a product by its ID")
    public void updateProductByIdTest() throws JsonProcessingException {
        String productJson = ProductsFakerDataGenerator.generateRandomValidProductData();
        ProductsPayload updatedProduct = objectMapper.readValue(productJson, ProductsPayload.class);
        Response response = ProductEndPoints.updateProductById(productJson, createdProductId);
        response.then()
                .statusCode(200)
                .body("name", equalTo(updatedProduct.getName()))
                .body("type", equalTo(updatedProduct.getType()))
                .body("price", equalTo(updatedProduct.getPrice()))
                .body("description", equalTo(updatedProduct.getDescription()))
                .body("manufacturer", equalTo(updatedProduct.getManufacturer()))
                .body("upc", equalTo(updatedProduct.getUpc()))
                .body("model", equalTo(updatedProduct.getModel()));
    }

    @Test(priority = 18, description = "Delete Product by ID")
    @Story("Delete Product by ID")
    @Description("Test Description : Verify that user can delete a product by its ID")
    public void deleteProductByIdTest() {
        Response response = ProductEndPoints.deleteProductById(createdProductId);
        response.then()
                .statusCode(200);
    }

    @Test(priority = 19, description = "Get Product by ID After Deletion")
    @Story("Get Product by ID After Deletion")
    @Description("Test Description : Verify that user can't retrieve the created product by its ID after being Deleted")
    public void getDeletedProductByIdTest() {
        Response response = ProductEndPoints.getProductById(createdProductId);
        response.then()
                .statusCode(404)
                .body("name", equalTo("NotFound"))
                .body("message", equalTo("No record found for id '" + createdProductId + "'"));
    }
}