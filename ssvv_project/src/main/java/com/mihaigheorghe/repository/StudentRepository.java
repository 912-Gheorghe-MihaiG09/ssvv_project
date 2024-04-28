package com.mihaigheorghe.repository;

import com.mihaigheorghe.domain.Student;
import com.mihaigheorghe.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

