package com.vrfvitor.knwallets.resource;

import com.vrfvitor.knwallets.dto.*;
import io.restassured.*;
import io.restassured.http.*;
import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.*;

import static com.vrfvitor.knwallets.KnwalletsApplicationTests.*;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_EACH_TEST_METHOD;
import static org.hamcrest.Matchers.*;

@Sql({"/schema.sql", "/data-test.sql"})
@AutoConfigureEmbeddedDatabase(refresh = AFTER_EACH_TEST_METHOD)
@AutoConfigureWebMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WalletRestControllerTest {

    private static final String URI = BASE_URL.concat("/wallets");

    @Test
    public void checkIndexGetsAll() {
        var matchesOneOfIds = anyOf(
                is("3d3b1a58-cf7b-4c75-8daf-af62b6f1851a"),
                is("55560ee8-3536-46af-8525-027e93810476")
        );

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(URI)
                .then()
                .log().body()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .body("[0].id", matchesOneOfIds)
                .body("[1].id", matchesOneOfIds);
    }

    @Test
    public void checkShowReturns404GivenNonexistentId() {
        var nonExistingId = "1f810060-d6b3-437d-b71d-765d7e80c141";

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("%s/%s", URI, nonExistingId))
                .then()
                .log().body()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void checkCreateSuccessfully() {
        var person = new WalletForm("Vitor", "Ferreira", "vrfvitor@hotmail.com");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                .post(URI)
                .then()
                .log().body()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void checkFailToCreateDueMissingRequiredData() {
        var person = new WalletForm("Vitor", null, "his@mail.com");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                .post(URI)
                .then()
                .log().body()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void checkDepositsSuccessfully() {
        var depositUri = String.format("%s/%s/%s", URI, "3d3b1a58-cf7b-4c75-8daf-af62b6f1851a", "deposit");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{ \"amountCents\": 5000 }")
                .when()
                .post(depositUri)
                .then()
                .log().body()
                .statusCode(HttpStatus.OK.value())
                .body("balanceCents", is(55000));
    }

    @Test
    public void checkDepositFails() {
        var depositUri = String.format("%s/%s/%s", URI, "3d3b1a58-cf7b-4c75-8daf-af62b6f1851a", "deposit");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{ \"amountCents\": -30 }")
                .when()
                .post(depositUri)
                .then()
                .log().body()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void checkWithdrawsSuccessfully() {
        var depositUri = String.format("%s/%s/%s", URI, "3d3b1a58-cf7b-4c75-8daf-af62b6f1851a", "withdraw");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{ \"amountCents\": 500 }")
                .when()
                .post(depositUri)
                .then()
                .log().body()
                .statusCode(HttpStatus.OK.value())
                .body("balanceCents", is(49500));
    }

    @Test
    public void checkWithdrawFails() {
        var depositUri = String.format("%s/%s/%s", URI, "55560ee8-3536-46af-8525-027e93810476", "withdraw");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{ \"amountCents\": 120000 }")
                .when()
                .post(depositUri)
                .then()
                .log().body()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}
