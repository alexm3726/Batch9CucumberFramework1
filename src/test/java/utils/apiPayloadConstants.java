package utils;

public class apiPayloadConstants {

    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Whooptie\",\n" +
                "  \"emp_lastname\": \"Movie\",\n" +
                "  \"emp_middle_name\": \"blueCheese\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2021-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}";
        return createEmployeePayload;
    }

}
