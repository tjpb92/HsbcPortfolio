# HsbcPortfolio
Ecrire un programme en Java permettant d’extraire du site HSBC les OPC d’un portefeuille donné.  

On ne conserve dans un fichier Excel que les 365 dernières valeurs (un an glissant) de chaque OPC.  

On essaye ensuite d’analyser les variations avec différents outils et de comprendre quand et où placer l’argent sur les OPC.

## Utilisation:
```
java HsbcPortfolio [[-b début] [-e fin]|[-n nbJour]] [-o fichier.xml] [-p path] [-d] [-t] 
```
où :
* ```-b début``` : date de début de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à hier par défaut (paramètre optionnel).
* ```-e fin``` : date de fin de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à aujourd'hui par défaut (paramètre optionnel).
* ```-n nbJour``` : précise le nombre de jour(s) à compter de la date courante. Non défini par défaut (paramètre optionnel).
* ```-o fichier``` est le nom du fichier Excel contenant les OPC suivis, par défaut *HsbcPortfolio.xlsx* (paramètre optionnel).
* ```-p path``` est le répertoire vers lequel exporter le fichier des résultats. Par défaut c'est le répertoire courant du programme (paramètre optionnel).
* ```-d``` le programme s'exécute en mode débug, il est beaucoup plus verbeux. Désactivé par défaut (*paramètre optionnel*),
* ```-t``` le programme s'exécute en mode test. Désactivé par défaut (*paramètre optionnel*).

## Pré-requis :
- Java 6 ou supérieur.

## Références:

- [API Java Exel POI](http://poi.apache.org/download.html)
- [Tuto Java POI Excel](http://thierry-leriche-dessirier.developpez.com/tutoriels/java/charger-modifier-donnees-excel-2010-5-minutes/)
- [Tuto Java POI Excel](http://jmdoudoux.developpez.com/cours/developpons/java/chap-generation-documents.php)
- [Codes pour en-tête et pied de page](https://poi.apache.org/apidocs/dev/org/apache/poi/xssf/usermodel/extensions/XSSFHeaderFooter.html)
