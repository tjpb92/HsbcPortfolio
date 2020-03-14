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
 * Jeux de tests pour la classe Fund
 * @author Thierry Baribaud
 * @version 0.02
 */
public class FundTest {
    
    private final Fund fund;
    
    private final static String SHARECLASS = "E";
    private final static String NAME = "HSBC EE Dynamique";
    private final static String ID = "HFDF031";
    private final static String PERIOD = "Daily";
    private final static String CURRENCY = "EUR";

    private final static String VAL_DATE = "2020-03-09T00:00:00Z";
    private final static String NAV = "20.0520";
    private final static String SHARE_CLASS_TOT = "1452300.7746";
    private final static String FUND_TOT = "1452300.7746";

    public FundTest() {
        NavItem navItem;
        
        navItem = new NavItem();
        navItem.setValDate(VAL_DATE);
        navItem.setNav(NAV);
        navItem.setShareClassTot(SHARE_CLASS_TOT);
        navItem.setFundTot(FUND_TOT);

        fund = new Fund();
        fund.setShareclass(SHARECLASS);
        fund.setName(NAME);
        fund.setId(ID);
        fund.setPeriod(PERIOD);
        fund.setCurrency(CURRENCY);
//        fund.setNavItem(navItem);
        fund.addNavItem(navItem);
        fund.addNavItem(navItem);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of object serialization/deserialization
     */
    @Test
    public void testFundSerialization() {
        System.out.println("FundTest.testSerialization()");
        try {
            JAXBContext contextObj = JAXBContext.newInstance(Fund.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            Fund expFund;
            File file;
            
            file = new File("tstFund.xml");
            
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(fund, System.out);
            marshallerObj.marshal(fund, file);
            
            expFund = (Fund) jaxbUnmarshaller.unmarshal(file);  
            assertEquals(fund.toString(), expFund.toString());
            
        } catch (JAXBException exception) {
            System.out.println("exception:" + exception);
            fail(exception.getMessage());
            Logger.getLogger(NavItemTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    /**
     * Test object from XML file
     * WARNING : file should contains real values coming from HSBC site
     */
    @Test
    public void testFundDeserialization() {
        System.out.println("FundTest.testDeserialization()");
        try {
            JAXBContext contextObj = JAXBContext.newInstance(Fund.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            Fund expFund;
            File file;
            
            file = new File("HFDF031.xml");
            
            expFund = (Fund) jaxbUnmarshaller.unmarshal(file);  
            System.out.println(expFund);
            for (NavItem navItem:expFund.getNavItems()) {
                System.out.println("  " + navItem.getValDate() + " " + navItem.getNav());
            }
            assertEquals("HFDF031", expFund.getId());
            assertEquals(4, expFund.getNavItems().size());
            assertNotNull(expFund); 
            
        } catch (JAXBException exception) {
            System.out.println("exception:" + exception);
            fail(exception.getMessage());
            Logger.getLogger(NavItemTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
}
