package petstore;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
   String  uri = "https://petstore.swagger.io/v2/pet"; //endereço  deentidade Pet

   public String lerJson(String caminhoJson) throws IOException {

      return new String(Files.readAllBytes(Paths.get(caminhoJson)));
   }

   @Test (priority = 1)
   public void incluirPet() throws IOException {
     String jsonBody = lerJson("Database/pet1.json");

     given()
             .contentType("application/json")
             .log().all()
             .body(jsonBody)
     .when()
             .post(uri)
     .then()
             .log().all()
             .statusCode(200)
             .body("name", is("miau"))
             .body("status", is("available"))
             .body("category.name", is("AX23292383LO"))
             .body("tags.name", contains("sta"));
     // uma estrutura sem colchete usa is quando vou checar com um colchote eu uso contains

   }

   @Test (priority = 2)
   public void consultarPet(){
       String petId = "1234567966";

       given()
               .contentType("application/json")
               .log().all()

       .when()
               .get(uri + "/" + petId)
       .then()
               .log().all()
               .statusCode(200)
               .body("name", is("miau"))
               .body("status", is("available"));
   }
}
