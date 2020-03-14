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
 * Jeux de tests pour la classe NavItem
 *
 * @author Thierry Baribaud
 * @version 0.02
 */
public class NavItemTest {

    private final NavItem navItem;

    private final static String VAL_DATE = "2020-03-09T00:00:00Z";
    private final static String NAV = "20.0520";
    private final static String SHARE_CLASS_TOT = "1452300.7746";
    private final static String FUND_TOT = "1452300.7746";

    public NavItemTest() {
        navItem = new NavItem();
        navItem.setValDate(VAL_DATE);
        navItem.setNav(NAV);
        navItem.setShareClassTot(SHARE_CLASS_TOT);
        navItem.setFundTot(FUND_TOT);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getValDate method, of class NavItem.
     */
    @Test
    public void testNavItemGetValDate() {
        System.out.println("NavItem.getValDate()");
        String expResult = VAL_DATE;
        String result = navItem.getValDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValDate method, of class NavItem.
     */
    @Test
    public void testNavItemSetValDate() {
        System.out.println("NavItem.setValDate()");
        String result = "2019-03-12T00:00:00Z";
        navItem.setValDate(result);
        String expResult = navItem.getValDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNav method, of class NavItem.
     */
    @Test
    public void testNavItemGetNav() {
        System.out.println("NavItem.getNav()");
        String expResult = NAV;
        String result = navItem.getNav();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNav method, of class NavItem.
     */
    @Test
    public void testNavItemSetNav() {
        System.out.println("NavItem.setNav()");
        String result = "1234.56";
        navItem.setNav(result);
        String expResult = navItem.getNav();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShareClassTot method, of class NavItem.
     */
    @Test
    public void testNavItemGetShareClassTot() {
        System.out.println("NavItem.getShareClassTot()");
        String expResult = SHARE_CLASS_TOT;
        String result = navItem.getShareClassTot();
        assertEquals(expResult, result);
    }

    /**
     * Test of setShare_Class_Tot method, of class NavItem.
     */
    @Test
    public void testNavItemSetShareClassTot() {
        System.out.println("NavItem.setShareClassTot()");
        String expResult = "2345.67";
        navItem.setShareClassTot(expResult);
        String result = navItem.getShareClassTot();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFund_Tot method, of class NavItem.
     */
    @Test
    public void testNavItemGetFundTot() {
        System.out.println("NavItem.getFundTot()");
        String expResult = FUND_TOT;
        String result = navItem.getFundTot();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFundTot method, of class NavItem.
     */
    @Test
    public void testNavItemSetFundTot() {
        System.out.println("NavItem.setFundTot()");
        String expResult = "3456.78";
        navItem.setFundTot(expResult);
        String result = navItem.getFundTot();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class NavItem.
     */
    @Test
    public void testNavItemToString() {
        System.out.println("NavItem.toString()");
        System.out.println("  toString:" + navItem.toString());
        String expResult = "NavItem:{" + "valDate:" + VAL_DATE + ", nav:" + NAV + ", shareClassTot:" + SHARE_CLASS_TOT + ", fundTot:" + FUND_TOT + '}';;
        String result = navItem.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of object serialization/deserialization
     */
    @Test
    public void testNavItemSerialization() {
        System.out.println("NavItemTest.testSerialization()");
        try {
            JAXBContext contextObj = JAXBContext.newInstance(NavItem.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            NavItem expNavItem;
            File file;
            
            file = new File("tstNavItem.xml");
            
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(navItem, System.out);
            marshallerObj.marshal(navItem, file);
            
            expNavItem = (NavItem) jaxbUnmarshaller.unmarshal(file);  
            assertEquals(navItem.toString(), expNavItem.toString());
            
        } catch (JAXBException exception) {
            System.out.println("exception:" + exception);
            fail(exception.getMessage());
            Logger.getLogger(NavItemTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
