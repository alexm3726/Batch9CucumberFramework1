package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
    static String employee_id;

    //@Test
    public void sampleTest() {
        //Given
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json")
                .queryParam("employee_id", "24246A");

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        /*
         *Printing response using asString() method to convert JSON object to a string and printing using sysout.
         */

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
                "}").log().all();
        /*
         * log().all() will log and print all information being sent with the request
         */

        //when
        Response response = preparedRequest.when().post("/createEmployee.php");
        /*
         *response.prettyPrint() is the same as System.out.println(response.asString());
         */
        response.prettyPrint();

        /*
         * jsonPath() allows us to retrieve specific data from a jason object - just like an xpath with selenium
         */
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        /*
         * Performing Assertions
         */
        //Then
        response.then().assertThat().statusCode(201);

        /*
         * Using Hamcrest Matchers class equalTo()
         * manually imported the class with-> import static org.hamcrest.Matchers.*;
         */
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        /*
        TASK
         *Write an assertion that verifies that the response body has the name you used
         */
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Whooptie"));
        /*
        TASK
         *Write an assertion that verifies the response server
         */
        response.then().assertThat().header("Server", equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));


    }

    @Test
    public void getCreatedEmployee(){

      RequestSpecification preparedRequest= given().header("Authorization", token).header("Content-Type", "application/json").queryParam("employee_id", employee_id);

      Response response = preparedRequest.when().get("/getOneEmployee.php");

      response.prettyPrint();
    }

}
