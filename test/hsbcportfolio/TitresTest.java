package hsbcportfolio;

import java.io.File;
import java.util.List;
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
 * Jeux de tests pour la classe Titres
 * @author Thierry Baribaud
 * @version 0.03
 */
public class TitresTest {
    
    public TitresTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

//    /**
//     * Test of getTitres method, of class Titres.
//     */
//    @Test
//    public void testGetTitres() {
//        System.out.println("getTitres");
//        Titres instance = new Titres();
//        List<Titre> expResult = null;
//        List<Titre> result = instance.getTitres();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setTitres method, of class Titres.
//     */
//    @Test
//    public void testSetTitres() {
//        System.out.println("setTitres");
//        List<Titre> titres = null;
//        Titres instance = new Titres();
//        instance.setTitres(titres);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addNavItem method, of class Titres.
//     */
//    @Test
//    public void testAddNavItem() {
//        System.out.println("addNavItem");
//        Titre titre = null;
//        Titres instance = new Titres();
//        instance.addNavItem(titre);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Titres.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Titres instance = new Titres();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of object serialization
     */
    @Test
    public void testTitresSerialization() {
        System.out.println("TitresTest.testSerialization()");
        try {
            JAXBContext contextTitre = JAXBContext.newInstance(Titre.class);
            Marshaller marshallerTitre = contextTitre.createMarshaller();
            Unmarshaller unmarshallerTitre = contextTitre.createUnmarshaller();
            JAXBContext contextTitres = JAXBContext.newInstance(Titres.class);
            Marshaller marshallerTitres = contextTitres.createMarshaller();
            Unmarshaller unmarshallerTitres = contextTitres.createUnmarshaller();
            Titre titre;
            Titres titres;
            File file;
            
            file = new File("titre.xml");
            marshallerTitre.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            titre = (Titre) unmarshallerTitre.unmarshal(file);  
            
            titres = new Titres();
            titres.addTitre(titre);
            titres.addTitre(titre);
            System.out.println(titres);
            marshallerTitres.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerTitres.marshal(titres, System.out);
//            marshallerObj.marshal(fund, file);
            
            assertNotNull(titres);
            
        } catch (JAXBException exception) {
            System.out.println("exception:" + exception);
            fail(exception.getMessage());
            Logger.getLogger(TitresTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    /**
     * Test of file to object deserialization
     */
    @Test
    public void testTitresDeserialization() {
        System.out.println("TitresTest.testDeserialization()");
        try {
            JAXBContext contextTitres = JAXBContext.newInstance(Titres.class);
            Marshaller marshallerTitres = contextTitres.createMarshaller();
            Unmarshaller unmarshallerTitres = contextTitres.createUnmarshaller();
            Titres titres;
            File file;
            
            file = new File("titres.xml");
            
            marshallerTitres.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            titres = (Titres) unmarshallerTitres.unmarshal(file); 
            System.out.println(titres);
            assertNotNull(titres);
            
        } catch (JAXBException exception) {
            System.out.println("exception:" + exception);
            fail(exception.getMessage());
            Logger.getLogger(TitresTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
}
