package com.example.demo.vm;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.StringFilter;

public class StudentCriteria implements Criteria {
    StringFilter city;

    public StudentCriteria() {
    }

    private StudentCriteria(StudentCriteria other) {
        this.city = other.getCity() == null ?null: other.getCity().copy();
    }

    @Override
    public Criteria copy() {
        return new StudentCriteria(this);
    }

    public StringFilter getCity() {
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }
}
