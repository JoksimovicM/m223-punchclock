package ch.zli.m223;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
                .when().get("/entries")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    @TestTransaction
    public void testDeleteEndpoint() {
        Entry entry = new Entry();
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.now());

        given().contentType(ContentType.JSON).body(entry).when().post("/entries");
        given()
                .when().delete("/entries/{id}", "1")
                .then()
                .statusCode(204);
    }

    @Test
    @TestTransaction
    public void testUpdateEndpoint() {
        Entry entry = new Entry();
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.now());

        given().contentType(ContentType.JSON).body(entry).when().post("/entries");

        entry.setCheckIn(LocalDateTime.of(2023, 7, 12, 7, 0, 0));
        entry.setCheckOut(LocalDateTime.of(2023, 7, 12, 12, 0, 0));
        given().contentType(ContentType.JSON).body(entry)
                .when().put("/entries/{id}", "1")
                .then().statusCode(204);

    }
}