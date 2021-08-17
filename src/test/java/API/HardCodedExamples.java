package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.junit.Test;

public class HardCodedExamples {

    /*
     * NOTE:
     *
     * Given - Preparing the request
     *
     * When - making the request/making the call/hitting the endpoint
     *
     * Then - verification/assertions
     */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjkxNjIzNDAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyOTIwNTU0MCwidXNlcklkIjoiMzAxMCJ9.1dlgu_GTKWyPyQTLWJmQVLT_sbYW4jqKmbBleVU6bS0";

    //@Test
    public void sampleTest() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json")
                .queryParam("employee_id", "24245A");

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        System.out.println(response.asString());

    }

    @Test
    public void postCreateEmployee() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"Whooptie\",\n" +
                "  \"emp_lastname\": \"Movie\",\n" +
                "  \"emp_middle_name\": \"blueCheese\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2021-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}");

        Response response = preparedRequest.when().post("/createEmployee.php");
        response.prettyPrint();
    }

}
