package com.vrfvitor.knwallets.resource;

import io.restassured.*;
import io.restassured.http.*;
import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.*;

import static com.vrfvitor.knwallets.KnwalletsApplicationTests.*;
import static org.hamcrest.Matchers.*;

@AutoConfigureEmbeddedDatabase
@Sql({"/schema.sql", "/data-test.sql"})
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

}
