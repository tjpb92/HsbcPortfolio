package hsbcportfolio;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import utils.GetArgsException;

/**
 * Programme permettant d’extraire du site HSBC les OPC d’un portefeuille donné.
 * On ne conserve dans un fichier Excel que les 365 dernières valeurs (un an
 * glissant) de chaque OPC. On essaye ensuite d’analyser les variations avec
 * différents outils et de comprendre quand et où placer l’argent sur les OPC.
 *
 * @author Thierry Baribaud
 * @version 0.03
 */
public class HsbcPortfolio {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * begDate : date de début de l'export à 0h, 7 jours avant.
     */
    private Timestamp begDate = new Timestamp((new java.util.Date().getTime()) - 1000 * 60 * 60 * 24 * 7);

    /**
     * endDate : date de fin de l'export à 0h.
     */
    private Timestamp endDate = new Timestamp(new java.util.Date().getTime());

    /**
     * nbJour : nombre de jours à compter de la date courante
     */
    private int nbJour;

    /**
     * filename : fichier Excel qui contient les résultats. Valeur par défaut :
     * HsbcPortfolio.xlsx.
     */
    private String filename = "HsbcPortfolio.xlsx";

    /**
     * Directory : répertoire vers lequel exporter le fichier des résultats. Par
     * défaut c'est le répertoire du programme.
     */
    private String directory = ".";

    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par défaut : false.
     */
    private static boolean debugMode = false;

    /**
     * testMode : fonctionnement du programme en mode test (true/false). Valeur
     * par défaut : false.
     */
    private static boolean testMode = false;

    /**
     * titles : titres à suivre
     */
    private Titres titles;

    /**
     * Class main constructor
     *
     * @param args arguments en ligne de commande
     * @throws GetArgsException en cas d'erreur avec les paramètres en ligne de
     * commande
     */
    public HsbcPortfolio(String[] args) throws GetArgsException, JAXBException {
        System.out.println("Création d'une instance de HsbcPortfolio ...");

        System.out.println("Analyse des arguments de la ligne de commande ...");
        this.getArgs(args);
        System.out.println("Argument(s) en ligne de commande lus().");

        System.out.println("Lecture des titres à suivre ...");
        readTitles();
        System.out.println("Titres à suivre lus.");

        if (debugMode) {
            System.out.println(this.toString());
        }
    }

    /**
     * Récupère les titres à suivre
     */
    private void readTitles() throws JAXBException {
        JAXBContext contextTitres = JAXBContext.newInstance(Titres.class);
        Marshaller marshallerTitres = contextTitres.createMarshaller();
        Unmarshaller unmarshallerTitres = contextTitres.createUnmarshaller();
        Titres titres;
        File file;

        file = new File("Titres.xml");
        marshallerTitres.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        titres = (Titres) unmarshallerTitres.unmarshal(file);
        
        if  (debugMode) {
            for (Titre titre:titres.getTitres()) {
                System.out.println("  " + titre.getId() + ", " + titre.getName());
            }
        }
    }

    /**
     * Récupère les paramètres en ligne de commande
     *
     * @param args arguments en ligne de commande
     */
    private void getArgs(String[] args) throws GetArgsException {
        int i;
        int n;
        int ip1;
        String currentParam;
        String nextParam;
        Date date;

        n = args.length;
        System.out.println("nargs=" + n);
        for (i = 0; i < n; i++) {
            System.out.println("args[" + i + "]=" + args[i]);
        }
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            currentParam = args[i];
            ip1 = i + 1;
            nextParam = (ip1 < n) ? args[ip1] : null;
            switch (currentParam) {
                case "-b":
                    if (ip1 < n) {
                        try {
                            date = (Date) dateFormat.parse(nextParam);
                            begDate = new Timestamp(date.getTime());
                            i = ip1;
                        } catch (Exception exception) {
                            throw new GetArgsException("La date de début doit être valide jj/mm/aaaa : " + nextParam);
                        }
                    } else {
                        throw new GetArgsException("Date de début non définie");
                    }
                    break;
                case "-e":
                    if (ip1 < n) {
                        try {
                            date = (Date) dateFormat.parse(nextParam);
                            endDate = new Timestamp(date.getTime());
                            i = ip1;
                        } catch (Exception exception) {
                            throw new GetArgsException("La date de fin doit être valide jj/mm/aaaa : " + nextParam);
                        }
                    } else {
                        throw new GetArgsException("Date de fin non définie");
                    }
                    break;
                case "-n":
                    if (ip1 < n) {
                        try {
                            nbJour = Integer.parseInt(nextParam);
                            i = ip1;
                        } catch (Exception exception) {
                            throw new GetArgsException("Le nombre de jour(s) doit être numérique : " + nextParam);
                        }
                    } else {
                        throw new GetArgsException("Nombre de jour(s) non défini");
                    }
                    break;
                case "-o":
                    if (ip1 < n) {
                        filename = nextParam;
                        i = ip1;
                    } else {
                        throw new GetArgsException("Nom de fichier non défini");
                    }
                    break;
                case "-p":
                    if (ip1 < n) {
                        directory = nextParam;
                        i = ip1;
                    } else {
                        throw new GetArgsException("Répertoire non défini");
                    }
                    break;
                case "-d":
                    setDebugMode(true);
                    break;
                case "-t":
                    setTestMode(true);
                    break;
                default:
                    usage();
                    throw new GetArgsException("ERREUR : Mauvais paramètre : " + currentParam);
            }
            i++;
        }
        if (begDate.after(endDate)) {
            throw new GetArgsException("La date de début " + dateFormat.format(begDate)
                    + " doit être antérieure à la date de fin " + dateFormat.format(endDate));
        }
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java HsbcPortfolio"
                + " [[-b début] [-f fin]|[-n nbJour]]"
                + " [-o fichier.xml] [-p répertoire]"
                + " [-d] [-t]");
    }

    /**
     * @return begDate : date de début de l'export à 0h.
     */
    public Timestamp getBegDate() {
        return (begDate);
    }

    /**
     * @param begDate : date de début de l'export à 0h.
     */
    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    /**
     * @return endDate : date de fin de l'export à 0h.
     */
    public Timestamp getEndDate() {
        return (endDate);
    }

    /**
     * @param endDate : date de fin de l'export à 0h.
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * @return le nombre de jours à compter de la date courante
     */
    public int getNbJour() {
        return nbJour;
    }

    /**
     * @param nbJour définit le nombre de jours à compter de la date courante La
     * date de début et la date de fin sont définit en conséquence.
     */
    public final void setNbJour(int nbJour) {
        Calendar calendar;

        this.nbJour = nbJour;

        // Récupère la date du jour
        calendar = new GregorianCalendar();

        // Elimine les heures, minutes, secondes et millisecondes.
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        setEndDate(new Timestamp(calendar.getTimeInMillis()));

        // Calcule la date de début d'extraction
        calendar.add(Calendar.DAY_OF_YEAR, -nbJour);
        setBegDate(new Timestamp(calendar.getTimeInMillis()));
    }

    /**
     * @return filename : le nom du fichier où envoyer les résultats.
     */
    public String getFilename() {
        return (filename);
    }

    /**
     * @param filename : définit le fichier où envoyer les résultats.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return le répertoire où exporter le fichier des résultats
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory définit le répertoire où exporter le fichier des
     * résultats
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * @return debugMode le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * @param debugMode définit le fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        HsbcPortfolio.debugMode = debugMode;
    }

    /**
     * @return testMode le mode de fonctionnement test.
     */
    public boolean getTestMode() {
        return (testMode);
    }

    /**
     * @param testMode définit le fonctionnement du programme en mode test
     * (true/false).
     */
    public void setTestMode(boolean testMode) {
        HsbcPortfolio.testMode = testMode;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HsbcPortfolio hsbcPortfolio;

        System.out.println("Lancement de HsbcPortfolio ...");
        try {
            hsbcPortfolio = new HsbcPortfolio(args);
        } catch (GetArgsException | JAXBException exception) {
            Logger.getLogger(HsbcPortfolio.class.getName()).log(Level.SEVERE, null, exception);
            //            Logger.getLogger(HsbcPortfolio.class.getName()).log(Level.INFO, null, exception);
        }

        System.out.println("Fin de HsbcPortfolio.");

    }

    /**
     * Affiche l'objet sous forme textuelle
     *
     * @return l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "HsbcPortfolio:{"
                + "début=" + dateFormat.format(begDate)
                + ", fin=" + dateFormat.format(endDate)
                + ", fichier=" + filename
                + ", répertoire=" + directory
                + ", nbJour=" + nbJour
                + ", debugMode=" + debugMode
                + ", testMode=" + testMode
                + "}";
    }

}
