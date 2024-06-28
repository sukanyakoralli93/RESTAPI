package Tests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apiTest.Dto.AddUserResponse;
import org.apiTest.Dto.UpdateUserResponse;
import org.apiTest.Dto.UserDetails.UserDetails;
import org.apiTest.ExtentReportListener;
import org.apiTest.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

@Listeners(ExtentReportListener.class)
public class RestAssuredTestAPI {

    private static final Logger log = LoggerFactory.getLogger(RestAssuredTestAPI.class);
    static RequestSpecification requestSpecification;


    @BeforeTest
    public static void setup() {
        requestSpecification = given()
                .baseUri("https://reqres.in")
                .log().all();
    }

    @Test
    public void testGet() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Response response = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .basePath("api/users")
                .queryParam("page", "2")
                .get();
        response.then().log().all();

        log.info("Get Response {}", response.asString());
        UserDetails ud = objectMapper.readValue(response.getBody().asString(), UserDetails.class);
        log.debug(ud.toString());
        log.info(String.valueOf(response.getTime()));
        Assert.assertEquals(ud.getPage(), 2);
        Assert.assertEquals(ud.getTotal(), 12);
        log.info("Assertion completed for get response");
    }

    @Test(enabled = true)
    public void testPost() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "src/main/resources/users.json";
        String jsonString = Utils.readJsonFile(filePath);
       // UpdateUser updateUser = new UpdateUser();
        Response response = given()
                .spec(requestSpecification)
                .basePath("api/users")
               // .body(updateUser.createUserBody("Ashwini", "IT"))
                .body(jsonString)
              // .body(jsonString.replace("#name","sukanya").replace("#job","PT"))
                .post();
        System.out.println("Post resopnse");
        System.out.println(response.asString());
        AddUserResponse ud = objectMapper.readValue(response.getBody().asString(), AddUserResponse.class);
        Assert.assertNotNull(ud.getId());
        String date = Utils.extractDate(ud.getCreatedAt().toString());
        String currentDate = Utils.getTodaysDate();
        Assert.assertEquals(currentDate, date);
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.asString());


    }

    @Test(enabled = true)
    public void testPut() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateUser updateUser = new UpdateUser();
        Response response = given()
                .spec(requestSpecification)
                .basePath("api/users/2")
                .body(updateUser.updateUserBody("Sukanya", "govt"))
                .put();
        System.out.println(response.asString());
        UpdateUserResponse ur = objectMapper.readValue(response.getBody().asString(), UpdateUserResponse.class);
        System.out.println(ur);
        Assert.assertNotNull(ur.getUpdatedAt());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(enabled = true)
    public void testPatch() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateUser updateUser = new UpdateUser();
        Response response = given()
                .spec(requestSpecification)
                .basePath("api/users/2")
                .body(updateUser.updateUserBody("rani", "govt"))
                .patch();
        System.out.println(response.asString());
        UpdateUserResponse ur = objectMapper.readValue(response.getBody().asString(), UpdateUserResponse.class);
        System.out.println(ur);
        Assert.assertNotNull(ur.getUpdatedAt());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(enabled = true)
    public void testDelete() {
        Response response = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .basePath("api/users/2")
                .queryParam("page", "2")
                .delete();
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 204);

    }
}
