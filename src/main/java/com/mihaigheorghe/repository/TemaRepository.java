package com.mihaigheorghe.repository;


import com.mihaigheorghe.domain.Tema;
import com.mihaigheorghe.validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

