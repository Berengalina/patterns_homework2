package ru.netology.web.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataHelper {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUp(RegistrationDto registeredDto) {
        given()
                .spec(requestSpec)
                .body(registeredDto)
                .when()
                .post("api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegistrationDto registerValidUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        val validUser = new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "active"
        );
        setUp(validUser);
        return validUser;

    }

    public static RegistrationDto registerBlockedUser (String locale){
        Faker faker = new Faker(new Locale(locale));
        val blockedUser = new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "blocked"
        );
        setUp(blockedUser);
        return blockedUser;
    }

    public static RegistrationDto registerWrongLoginUser(String locale){
        Faker faker = new Faker(new Locale(locale));
        val validUser = new RegistrationDto(
                faker.name().username(),
                "password",
                "active"
        );
        val invalidUser = new RegistrationDto(
                faker.name().username(),
                "password",
                "active"
        );
        setUp(validUser);
        return invalidUser;
    }

    public static RegistrationDto registerWrongPasswordUser(String locale){
        Faker faker = new Faker(new Locale(locale));
        val validUser = new RegistrationDto(
                "Анна.Белоусова",
                faker.internet().password(),
                "active"
        );
        val invalidUser = new RegistrationDto(
                "Анна.Белоусова",
                faker.internet().password(),
                "active"
        );
        setUp(validUser);
        return invalidUser;
    }

    public static RegistrationDto registerUserNotExist(String locale){
        Faker faker = new Faker (new Locale(locale));
        val validUser = new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "active"
        );
        return validUser;
    }


}
