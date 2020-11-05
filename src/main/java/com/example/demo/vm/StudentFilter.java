package com.example.demo.vm;

import java.io.Serializable;
import java.util.List;

public class StudentFilter implements Serializable {
    private List<String> cities;

    public StudentFilter() {
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
