package com.mihaigheorghe;

import com.mihaigheorghe.domain.Student;
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
public class AppTest 
    extends TestCase
{
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
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testAddStudent1()
    {
        try {
            service.saveStudent("test_id", "Test Student", 933);
        } catch (Exception e){
            fail();
        }
        service.deleteStudent("test_id");
        assertTrue( true );
    }

    public void testAddStudent2()
    {
        try {
            service.saveStudent("test_id", "Test Student", -933);
//            service.deleteStudent("test_id");
            fail();
        } catch (Exception e){
            assertTrue( true );
        }
    }

    public void testAddTema1()
    {
        try {
            service.saveTema("test_tema", "test_description", 10, 1);
        } catch (Exception e){
            fail();
        }
        service.deleteTema("test_tema");
        assertTrue( true );
    }

    public void testAddTema2()
    {
        try {
            service.saveTema("test_tema", "test_description", 15, 1);
            fail();
        } catch (Exception e){
            assertTrue( true );
        }
    }

    public void testCase1()
    {
        try {
            service.saveStudent("1", "John Doe", 933);
        } catch (Exception e){
            fail();
        }
        service.deleteStudent("1");
        assertTrue( true );
    }
    public void testCase2()
    {
        try {
            service.saveStudent("2", "John Doe", -9);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    public void testCase3()
    {
        try {
            service.saveStudent("3", "John Doe", 932);
        } catch (Exception e){
            fail();
        }
        service.deleteStudent("3");
        assertTrue( true );
    }
    public void testCase5()
    {
        try {
            service.saveStudent("1", "", 933);
            fail();
        } catch (ValidationException e){
            assertTrue( true );
        }
    }
    public void testCase6()
    {
        try {
            service.saveStudent("1", null, 933);
            fail();
        } catch (ValidationException e){
            assertTrue( true );
        }
    }
    public void testCase7()
    {
        try {
            service.saveStudent("2345", "John Doe", 932);
        } catch (Exception e){
            fail();
        }
        service.deleteStudent("2345");
        assertTrue( true );
    }
    public void testCase8()
    {
        try {
            service.saveStudent("", "John Doe", 933);
            fail();
        } catch (ValidationException e){
            assertTrue( true );
        }
    }
    public void testCase9()
    {
        try {
            service.saveStudent(null, "John Doe", 933);
            fail();
        } catch (ValidationException e){
            assertTrue( true );
        }
    }
    public void testCase14()
    {
        service.saveStudent("1", "John Doe", 932);
        try {
            service.saveStudent("2", "John Doe", 932);
        } catch (Exception e){
            fail();
        }
        service.deleteStudent("1");
        service.deleteStudent("2");
        assertTrue( true );
    }
}
