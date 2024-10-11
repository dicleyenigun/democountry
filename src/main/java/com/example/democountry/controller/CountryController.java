package com.example.democountry.controller;

import com.example.democountry.models.Country;
import com.example.democountry.service.CountryInfoServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController  {

    @Autowired
    private CountryInfoServiceHandler countryInfoServiceHandler;

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryInfoServiceHandler.getCountries();
    }

    @GetMapping("/countries/{code}")
    public Country getCountryDetails(@PathVariable String code) {
        return countryInfoServiceHandler.getCountryDetails(code);
    }
}
