package com.example.demo.vm;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentFilter implements Serializable {
    private String cities;
    private String query;
}
