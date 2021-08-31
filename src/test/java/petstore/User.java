package petstore;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


public class User {

    String uri = "https://petstore.swagger.io/v2/user";
    Data data;

    @BeforeMethod
    public void  setup(){
        data = new Data();
    }


    @Test(priority = 1)
    public void incluirUsuario() throws IOException {
        String jsonBody = data.lerJson("db/user1.json");
        String userId =

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is (200))
                .body("type", is ("unknown"))

        .extract()
                .path("message");

        System.out.println("User id " + userId);
    }
}
