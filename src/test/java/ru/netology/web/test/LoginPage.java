package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    @Test
    void shouldLogin(){
        val user = DataHelper.registerValidUser("ru");
        open("http://localhost:9999/");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("h2").shouldHave(text("Личный кабинет")).shouldBe(Condition.visible);
    }


    @Test
    void shouldNotLoginWrongLogin(){
        val user = DataHelper.registerWrongLoginUser("ru");
        open("http://localhost:9999/");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль")).shouldBe(visible);
    }

    @Test
    void shouldNotLoginWrongPassword(){
        val user = DataHelper.registerWrongPasswordUser("ru");
        open("http://localhost:9999/");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль")).shouldBe(visible);
    }

    @Test
    void shouldNotLoginBlockedUser(){
        val user = DataHelper.registerBlockedUser("ru");
        open("http://localhost:9999/");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    void shouldNotRegisterNotExistUser(){
        val user = DataHelper.registerUserNotExist("ru");
        open("http://localhost:9999/");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль")).shouldBe(visible);
    }

}
