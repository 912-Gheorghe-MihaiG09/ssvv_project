package com.mihaigheorghe;

import com.mihaigheorghe.repository.NotaXMLRepository;
import com.mihaigheorghe.repository.StudentXMLRepository;
import com.mihaigheorghe.repository.TemaXMLRepository;
import com.mihaigheorghe.service.Service;
import com.mihaigheorghe.validation.NotaValidator;
import com.mihaigheorghe.validation.StudentValidator;
import com.mihaigheorghe.validation.TemaValidator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class IntegrationTest
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
    public IntegrationTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(IntegrationTest.class);
    }

    public void testAddStudent() {
        try {
            service.saveStudent("test_id", "Test Student", 933);
        } catch (Exception e) {
            fail();
        }
        service.deleteStudent("test_id");
        assertTrue(true);
    }

    public void testAddAssignmentAndStudent() {
        try {
            service.saveTema("test_tema", "test_description", 10, 1);
        } catch (Exception e) {
            fail();
        }
        service.deleteTema("test_tema");

        try {
            service.saveStudent("test_id", "Test Student", 933);
        } catch (Exception e) {
            fail();
        }
        service.deleteStudent("test_id");
        assertTrue(true);
    }

    public void testAddAll() {
        try {
            service.saveTema("test_tema", "test_description", 11, 10);
        } catch (Exception e) {
            fail();
        }

        try {
            service.saveStudent("test_id", "Test Student", 933);
        } catch (Exception e) {
            service.deleteTema("test_tema");
            fail();
        }
        assertTrue(true);

        try {
            service.saveNota("test_id", "test_tema", 9, 2, "test_feedback");
        } catch (Exception e) {
            System.out.print(e.toString());

            service.deleteTema("test_tema");
            service.deleteStudent("test_id");
            fail();
        }
        service.deleteTema("test_tema");
        service.deleteStudent("test_id");
        assertTrue(true);
    }

}
