package com.example.demo.vm;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

@Data
public class StudentCriteria implements Criteria {
    private StringFilter city;
    private String query;

    public StudentCriteria() {
    }

    private StudentCriteria(StudentCriteria other) {
        this.city = other.getCity() == null ?null: other.getCity().copy();
        this.query = other.query;
    }

    @Override
    public Criteria copy() {
        return new StudentCriteria(this);
    }

}
