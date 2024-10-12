package api.utils.fakerDataGenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import api.payload.ProductsPayload;

public class ProductsFakerDataGenerator {
    private static final Faker faker = new Faker();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static ProductsPayload generateBaseProduct() {
        ProductsPayload product = new ProductsPayload();
        product.setName(faker.commerce().productName());
        product.setType(faker.commerce().department());
        double randomPrice = faker.number().randomDouble(2, 1, 1000);
        product.setPrice(Float.parseFloat(String.format("%.2f", randomPrice)));
        product.setDescription(faker.lorem().sentence());
        product.setManufacturer(faker.company().name());
        product.setUpc(faker.number().digits(12));
        String model = "Model-" + faker.commerce().productName().replaceAll(" ", "-");
        product.setModel(model.length() > 25 ? model.substring(0, 25) : model);
        return product;
    }

    private static String convertToJson(ProductsPayload product) throws JsonProcessingException {
        return objectMapper.writeValueAsString(product);
    }

    public static String generateRandomValidProductData() throws JsonProcessingException {
        return convertToJson(generateBaseProduct());
    }

    public static String generateInvalidProductWithInvalidPrice() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setPrice(199.999f);
        return convertToJson(product);
    }

    public static String generateInvalidProductWithZeroPrice() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setPrice(0.00f);
        return convertToJson(product);
    }

    public static String generateInvalidProductWithNegativePrice() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setPrice(-10.00f);
        return convertToJson(product);
    }

    public static String generateInvalidProductWithLongModel() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        String longModel = "Model-" + faker.lorem().characters(26);
        product.setModel(longModel);
        return convertToJson(product);
    }

    public static String generateInvalidProductWithLongUPCFormat() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setUpc(faker.number().digits(16));
        return convertToJson(product);
    }

    public static String generateInvalidProductWithAllFieldsNull() throws JsonProcessingException {
        ProductsPayload product = new ProductsPayload();
        return convertToJson(product);
    }

    public static String generateInvalidProductWithAllStringFieldsEmpty() throws JsonProcessingException {
        ProductsPayload product = new ProductsPayload();
        product.setName("");
        product.setType("");
        product.setDescription("");
        product.setManufacturer("");
        product.setUpc("");
        product.setModel("");
        return convertToJson(product);
    }

    public static String generateProductWithLongName() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setName(faker.lorem().characters(101));
        return convertToJson(product);
    }

    public static String generateProductWithLongType() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setType(faker.lorem().characters(31));
        return convertToJson(product);
    }

    public static String generateProductWithLongDescription() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setDescription(faker.lorem().paragraph());
        return convertToJson(product);
    }

    public static String generateProductWithLongManufacturer() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        product.setManufacturer(faker.company().name() + faker.lorem().characters(51));
        return convertToJson(product);
    }

    public static String generateProductWithLargePrice() throws JsonProcessingException {
        ProductsPayload product = generateBaseProduct();
        float largePrice = (float) faker.number().randomDouble(2, 10000000, 100000000);
        product.setPrice(largePrice);
        return convertToJson(product);
    }

    public static String generateSimpleRandomInvalidProductData() {
        return "{"
                + "\"name\": " + faker.number().randomNumber() + ","
                + "\"type\": " + faker.bool().bool() + ","
                + "\"upc\": " + faker.number().randomDigit() + ","
                + "\"description\": " + faker.number().randomDouble(2, 100, 1000) + ","
                + "\"manufacturer\": " + faker.bool().bool() + ","
                + "\"price\": \"" + faker.lorem().word() + "\","
                + "\"model\": " + faker.number().numberBetween(1, 100)
                + "}";
    }
}