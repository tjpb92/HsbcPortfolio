package hsbcportfolio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe représentant une liste de titres
 *
 * @author Thierry Baribaud
 * @version 0.03
 */
@XmlRootElement(name = "Titres")
@XmlAccessorType(XmlAccessType.FIELD)
public class Titres {

    @XmlElement(name = "Titre")
    private List<Titre> titres;

    /**
     * Class main constructor
     */
    public Titres() {
        titres = new ArrayList();
    }

    /**
     * @return the titres
     */
    public List<Titre> getTitres() {
        return titres;
    }

    /**
     * @param titres the titres to set
     */
    public void setTitres(List<Titre> titres) {
        this.titres = titres;
    }

    /**
     * Add one Titre to the list
     *
     * @param titre to add
     */
    public void addTitre(Titre titre) {
        titres.add(titre);
    }

    @Override
    public String toString() {
        return "Titres:{" + titres + '}';
    }

}
