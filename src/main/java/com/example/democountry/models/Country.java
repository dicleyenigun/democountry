package com.example.democountry.models;

import java.util.List;

public class Country {
    private String code;
    private String name;
    private List<String> languages;

    public Country(String code, String name, List<String> languages) {
        this.code = code;
        this.name = name;
        this.languages = languages;
    }

    // Getter ve Setter metodları
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
