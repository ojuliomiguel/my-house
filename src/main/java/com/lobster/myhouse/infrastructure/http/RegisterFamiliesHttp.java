package com.lobster.myhouse.infrastructure.http;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/families")
public class RegisterFamiliesHttp {

    @PostMapping("")
    public String execute() {
        return "Ola, mundo";
    }

}
