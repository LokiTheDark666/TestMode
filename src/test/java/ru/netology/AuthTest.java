package ru.netology;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class AuthTest {

    @Test
    void shouldRegistatedActive() {
        Configuration.holdBrowserOpen = true;
        Registration registration = DataGenerator.getNewUser("active");
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(registration.getLogin());
        form.$("[data-test-id=password] input").setValue(registration.getPassword());
        form.$(".button").click();
        $(withText("Личный кабинет")).shouldBe(exist);
    }

    @Test
    void shouldRegistatedBlocked() {
        Registration registration = DataGenerator.getNewUser("blocked");
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(registration.getLogin());
        form.$("[data-test-id=password] input").setValue(registration.getPassword());
        form.$(".button").click();
        $(withText("Пользователь заблокирован")).shouldBe(exist);
    }

    @Test
    void shouldRegistatedInvalidLogin() {
        Registration registration = DataGenerator.getNewUser("active");
        Faker faker = new Faker();
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(faker.name().firstName());
        form.$("[data-test-id=password] input").setValue(registration.getPassword());
        form.$(".button").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(exist);
    }

    @Test
    void shouldRegistatedInvalidPassword() {
        Registration registration = DataGenerator.getNewUser("active");
        Faker faker = new Faker();
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=login] input").setValue(registration.getLogin());
        form.$("[data-test-id=password] input").setValue(faker.internet().password());
        form.$(".button").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(exist);
    }
}
