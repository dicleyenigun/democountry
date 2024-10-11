package com.example.democountry.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class CustomErrorController implements ErrorController {

    // Hata durumlarında bu endpoint çağrılacak
    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP durum kodunu özelleştirebilirsiniz
    public String handleError(HttpServletRequest request) {
        // Hata durumunda gösterilecek mesaj
        return "Bir hata oluştu. Lütfen daha sonra tekrar deneyin.";
    }

    // Bu metot Spring Boot 2.3'ten sonra önerilmez, fakat uyumluluk için eklenebilir.
    public String getErrorPath() {
        return "/error";
    }
}


