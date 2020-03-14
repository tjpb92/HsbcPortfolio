package hsbcportfolio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe représentant un Fund
 *
 * @author Thierry Baribaud
 * @version 0.02
 */
@XmlRootElement(name = "Fund")
@XmlType(propOrder = {"shareclass", "name", "id", "period", "currency", "navItems"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Fund {

    @XmlAttribute()
    private String shareclass;

    @XmlAttribute()
    private String name;

    @XmlAttribute()
    private String id;

    @XmlElement(name = "Period")
    private String period;

    @XmlElement(name = "Curr")
    private String currency;

    @XmlElement(name="Navs")
//    private NavItem navItem;
    private List<NavItem> navItems;
    
    /**
     * Class main constructor
     */
    public Fund(){
        navItems = new ArrayList();
    }
    
    /**
     * @return the shareclass
     */
    public String getShareclass() {
        return shareclass;
    }

    /**
     * @param shareclass the shareclass to set
     */
    public void setShareclass(String shareclass) {
        this.shareclass = shareclass;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the navItems
     */
//    public NavItem getNavItem() {
    public List<NavItem> getNavItems() {
        return navItems;
    }

    /**
     * @param navItems the navItems to set
     */
    public void setNavItems(List<NavItem> navItems) {
        this.navItems = navItems;
    }

    /** Add one NavItem to the list
     * 
     * @param navItem item to add
     */
    public void addNavItem(NavItem navItem) {
        navItems.add(navItem);
    }
    
    @Override
    public String toString() {
        return "Fund:{" + "shareclass:" + shareclass + ", name:" + name + ", id:" + id + ", period:" + period + ", currency:" + currency + ", navs:" + navItems+ '}';
    }

}
