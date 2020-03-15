package hsbcportfolio;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jeux de tests pour la classe Titre
 * @author Thierry Baribaud
 * @version 0.03
 */
public class TitreTest {
    
    public TitreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

//    /**
//     * Test of getName method, of class Titre.
//     */
//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        Titre instance = new Titre();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setName method, of class Titre.
//     */
//    @Test
//    public void testSetName() {
//        System.out.println("setName");
//        String name = "";
//        Titre instance = new Titre();
//        instance.setName(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAltId method, of class Titre.
//     */
//    @Test
//    public void testGetAltId() {
//        System.out.println("getAltId");
//        Titre instance = new Titre();
//        String expResult = "";
//        String result = instance.getAltId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAltId method, of class Titre.
//     */
//    @Test
//    public void testSetAltId() {
//        System.out.println("setAltId");
//        String altId = "";
//        Titre instance = new Titre();
//        instance.setAltId(altId);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getId method, of class Titre.
//     */
//    @Test
//    public void testGetId() {
//        System.out.println("getId");
//        Titre instance = new Titre();
//        String expResult = "";
//        String result = instance.getId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setId method, of class Titre.
//     */
//    @Test
//    public void testSetId() {
//        System.out.println("setId");
//        String id = "";
//        Titre instance = new Titre();
//        instance.setId(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getClasse method, of class Titre.
//     */
//    @Test
//    public void testGetClasse() {
//        System.out.println("getClasse");
//        Titre instance = new Titre();
//        String expResult = "";
//        String result = instance.getClasse();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setClasse method, of class Titre.
//     */
//    @Test
//    public void testSetClasse() {
//        System.out.println("setClasse");
//        String classe = "";
//        Titre instance = new Titre();
//        instance.setClasse(classe);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Titre.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Titre instance = new Titre();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of object serialization/deserialization
     */
    @Test
    public void testTitreSerialization() {
        System.out.println("TitreTest.testSerialization()");
        try {
            JAXBContext contextObj = JAXBContext.newInstance(Titre.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            Titre titre;
            File file;
            
            file = new File("titre.xml");
            
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            titre = (Titre) jaxbUnmarshaller.unmarshal(file); 
            System.out.println(titre);
            assertNotNull(titre);
            
        } catch (JAXBException exception) {
            System.out.println("exception:" + exception);
            fail(exception.getMessage());
            Logger.getLogger(TitreTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
}
