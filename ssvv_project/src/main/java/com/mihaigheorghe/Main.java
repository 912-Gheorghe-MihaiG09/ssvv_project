package com.mihaigheorghe;

import com.mihaigheorghe.console.UI;
import com.mihaigheorghe.domain.Nota;
import com.mihaigheorghe.domain.Student;
import com.mihaigheorghe.domain.Tema;
import com.mihaigheorghe.repository.NotaXMLRepository;
import com.mihaigheorghe.repository.StudentXMLRepository;
import com.mihaigheorghe.repository.TemaXMLRepository;
import com.mihaigheorghe.service.Service;
import com.mihaigheorghe.validation.NotaValidator;
import com.mihaigheorghe.validation.StudentValidator;
import com.mihaigheorghe.validation.TemaValidator;
import com.mihaigheorghe.validation.Validator;

public class Main {
    public static void main(String[] args) {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
