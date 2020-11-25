package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Student_;
import com.example.demo.repository.StudentRepository;
import com.example.demo.vm.StudentCriteria;
import com.example.demo.vm.StudentFilter;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StudentService extends QueryService<Student> {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(StudentFilter filter) {
        StudentCriteria studentCriteria = buildCriteria(filter);
        Specification<Student> specification = createSpecification(studentCriteria);
        return studentRepository.findAll(specification);
    }

    private StudentCriteria buildCriteria(StudentFilter filter) {
        StudentCriteria studentCriteria = new StudentCriteria();
        if (filter.getCities() != null) {
            StringFilter stringFilter = new StringFilter();
            List<String> cities = Arrays.stream(filter.getCities().split(",")).collect(Collectors.toList());
            stringFilter.setIn(cities);
            studentCriteria.setCity(stringFilter);
        }
        studentCriteria.setQuery(filter.getQuery());
        return studentCriteria;
    }

    private Specification<Student> createSpecification(StudentCriteria criteria) {
        Specification<Student> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCity(), Student_.city));
            }
            if (criteria.getQuery() != null) {
                specification = specification.and(buildQuerySpecification(criteria.getQuery()));
            }
        }
        return specification;
    }
    private Specification<Student> buildQuerySpecification(String query){
        if (StringUtils.isEmpty(query)) return null;
        return buildEqualsSpecification(query, Student_.city)
        .or(buildLikeSpecification(query, Student_.name));
    }

    private Specification<Student> buildLikeSpecification(String value, SingularAttribute<Student, String> field) {
        return (root, query, builder) -> builder.like(root.get(field), wrapLikeQuery(value));
    }
    private Specification<Student> buildEqualsSpecification(String value, SingularAttribute<Student, String> field) {
        return (root, query, builder) -> builder.equal(root.get(field), value);
    }
}
