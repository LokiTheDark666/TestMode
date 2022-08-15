package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class AuthTest {
    Faker faker = new Faker();

    @BeforeEach
    void openUrl() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRegistatedActive() {
        Registration registration = DataGenerator.getNewUser("active");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(registration.getLogin());
        form.$("[data-test-id=password] input").setValue(registration.getPassword());
        form.$(".button").click();
        $(withText("Личный кабинет")).shouldHave(text("Личный кабинет"));
    }

    @Test
    void shouldRegistatedBlocked() {
        Registration registration = DataGenerator.getNewUser("blocked");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(registration.getLogin());
        form.$("[data-test-id=password] input").setValue(registration.getPassword());
        form.$(".button").click();
        $(withText("Пользователь заблокирован")).shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void shouldRegistatedInvalidLogin() {
        Registration registration = DataGenerator.getNewUser("active");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(faker.name().firstName());
        form.$("[data-test-id=password] input").setValue(registration.getPassword());
        form.$(".button").click();
        $(withText("Неверно указан логин или пароль")).shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldRegistatedInvalidPassword() {
        Registration registration = DataGenerator.getNewUser("active");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(registration.getLogin());
        form.$("[data-test-id=password] input").setValue(faker.internet().password());
        form.$(".button").click();
        $(withText("Неверно указан логин или пароль")).shouldHave(text("Неверно указан логин или пароль"));
    }
}
