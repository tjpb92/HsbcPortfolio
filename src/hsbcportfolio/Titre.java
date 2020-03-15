package hsbcportfolio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe représentrant un titre
 * @author Thierry Baribaud
 * @version 0.03
 */
@XmlRootElement(name = "Titre")
@XmlType(propOrder = {"name", "altId", "id", "classe"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Titre {
    
    @XmlElement(name = "Nom")
    private String name;
    
    @XmlElement(name = "AltId")
    private String altId;
    
    @XmlElement(name = "Id")
    private String id;
    
    @XmlElement(name = "Classe")
    private String classe;

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
     * @return the altId
     */
    public String getAltId() {
        return altId;
    }

    /**
     * @param altId the altId to set
     */
    public void setAltId(String altId) {
        this.altId = altId;
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
     * @return the classe
     */
    public String getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Titre:{" + "name:" + name + ", altId:" + altId + ", id:" + id + ", classe:" + classe + '}';
    }
}
