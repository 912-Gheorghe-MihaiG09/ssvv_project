package com.mihaigheorghe.repository;

import com.mihaigheorghe.domain.Nota;
import com.mihaigheorghe.domain.Pair;
import com.mihaigheorghe.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
