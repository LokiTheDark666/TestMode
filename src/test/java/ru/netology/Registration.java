package ru.netology;


import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Locale;


@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Registration {

    private String login;
    private String password;
    private String status;


}
