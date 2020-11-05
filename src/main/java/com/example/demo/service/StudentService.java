package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Student_;
import io.github.jhipster.service.QueryService;
import com.example.demo.repository.StudentRepository;
import com.example.demo.vm.StudentCriteria;
import com.example.demo.vm.StudentFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService extends QueryService<Student>{
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(StudentFilter filter) {
        StudentCriteria studentCriteria = buildCriteria(filter);
        Specification<Student> specification = createSpecification(studentCriteria);
        return studentRepository.findAll(specification);
    }
    private StudentCriteria buildCriteria(StudentFilter filter){
        StudentCriteria studentCriteria = new StudentCriteria();
        StringFilter stringFilter = new StringFilter();
        stringFilter.setIn(filter.getCities());
        studentCriteria.setCity(stringFilter);
        return studentCriteria;
    }
    private Specification<Student> createSpecification (StudentCriteria criteria){
        Specification<Student> specification = Specification.where(null);
        if (criteria != null){
            if (criteria.getCity() != null){
                specification = specification.and(buildStringSpecification(criteria.getCity(), Student_.city));
            }
        }
        return specification;
    }
}
