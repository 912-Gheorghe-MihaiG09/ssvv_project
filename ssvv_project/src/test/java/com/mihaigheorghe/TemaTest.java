package com.mihaigheorghe;

import com.mihaigheorghe.repository.NotaXMLRepository;
import com.mihaigheorghe.repository.StudentXMLRepository;
import com.mihaigheorghe.repository.TemaXMLRepository;
import com.mihaigheorghe.service.Service;
import com.mihaigheorghe.validation.NotaValidator;
import com.mihaigheorghe.validation.StudentValidator;
import com.mihaigheorghe.validation.TemaValidator;
import com.mihaigheorghe.validation.ValidationException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TemaTest
        extends TestCase {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";

    StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, filenameStudent);
    TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, filenameTema);
    NotaValidator notaValidator = new NotaValidator();
    NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, filenameNota);
    Service service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TemaTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TemaTest.class);
    }


    public void testAddTema1() {
        try {
            service.saveTema("test_tema", "test_description", 10, 1);
        } catch (Exception e) {
            fail();
        }
        service.deleteTema("test_tema");
        assertTrue(true);
    }

    public void testAddTema2() {
        try {
            service.saveTema("test_tema", "test_description", 15, 1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testTC2() {
        try {
            service.saveTema("", "", 6, 8);
            fail();
        } catch (ValidationException e) {
            if (!e.getMessage().equals("ID invalid! \n")) {
                fail();
            }
        }
    }

    public void testTC3() {
        try {
            service.saveTema(null, "desc", 6, 8);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    public void testTC4() {
        try {
            service.saveTema("1", "", 6, 8);
            fail();
        } catch (ValidationException e) {
            if (!e.getMessage().equals("Descriere invalida! \n")) {
                fail();
            }
        }
    }

    public void testTC5() {
        try {
            service.saveTema("1", "desc", 6, 15);
            fail();
        } catch (ValidationException e) {
            if (!e.getMessage().equals("Deadline invalid! \n")) {
                fail();
            }
        }
    }

    public void testTC6() {
        try {
            service.saveTema("1", "desc", -1, 8);
            fail();
        } catch (ValidationException e) {
            if (!e.getMessage().equals("Deadline invalid! \n")) {
                fail();
            }
        }
    }
}
