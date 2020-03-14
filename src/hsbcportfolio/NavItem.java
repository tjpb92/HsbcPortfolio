package hsbcportfolio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe représentant un NavItem
 * @author Thierry Baribaud
 * @version 0.02
 */
@XmlRootElement(name = "Navs")
@XmlType(propOrder = {"nav", "shareClassTot", "fundTot"})
@XmlAccessorType(XmlAccessType.FIELD)
public class NavItem {
    
    @XmlAttribute(name="val_date")
    private String valDate;
    
    @XmlElement(name="Nav")
    private String nav;
    
    @XmlElement(name="Share_Class_Tot")
    private String shareClassTot;
    
    @XmlElement(name="Fund_Tot")
    private String fundTot;

    /**
     * @return the nav
     */
    public String getNav() {
        return nav;
    }

    /**
     * @param nav the nav to set
     */
    public void setNav(String nav) {
        this.nav = nav;
    }

    /**
     * @return the shareClassTot
     */
    public String getShareClassTot() {
        return shareClassTot;
    }

    /**
     * @param shareClassTot the shareClassTot to set
     */
    public void setShareClassTot(String shareClassTot) {
        this.shareClassTot = shareClassTot;
    }

    /**
     * @return the fundTot
     */
    public String getFundTot() {
        return fundTot;
    }

    /**
     * @param fundTot the fundTot to set
     */
    public void setFundTot(String fundTot) {
        this.fundTot = fundTot;
    }

    /**
     * @return the valDate
     */
    public String getValDate() {
        return valDate;
    }

    /**
     * @param valDate the valDate to set
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    @Override
    public String toString() {
        return "NavItem:{" + "valDate:" + valDate + ", nav:" + nav + ", shareClassTot:" + shareClassTot + ", fundTot:" + fundTot + '}';
    }
}
