import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

abstract class Hébergement {
    String nom;
    String adresse;
    ArrayList<String> services;
    ArrayList<Réservation> réservations;
    ArrayList<Chambre> chambres;

    Hébergement(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        this.nom = nom;
        this.adresse = adresse;
        this.services = services;
        this.réservations = new ArrayList<Réservation>();
        this.chambres = chambres;
    }

    Hébergement(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        this.nom = nom;
        this.adresse = adresse;
        this.services = services;
        this.réservations = réservations;
        this.chambres = chambres;
    }

    void ajouterRéservation(Réservation réservation){
        this.réservations.add(réservation);
    }

    void annulerRéservation(Réservation réservation){
        this.réservations.remove(réservation);
    }

    /*
     * trouverChambre
     * Retourne une chambre de l'hôtel qui correspond au paramêtres
     * Si aucune chambre ne correspond aux critères, renvoie null
     */
    Chambre trouverChambre(Class<Chambre> typeChambre, Date dateArrivée, Date dateDépart, int prixMax){
        return null;
    }

    int getNombreChambreSimple(){
        int resultat = 0;
        Iterator<Chambre> iter = this.chambres.iterator();
        while (iter.hasNext()){
            Object chambre = iter.next();
            if (chambre instanceof ChambreSimple){
                resultat += 1;
            }
        }
        return resultat;
    }

    int getNombreChambreDouble(){
        int resultat = 0;
        Iterator<Chambre> iter = this.chambres.iterator();
        while (iter.hasNext()){
            Object chambre = iter.next();
            if (chambre instanceof ChambreDouble){
                resultat += 1;
            }
        }
        return resultat;
    }

    int getNombreChambreSuite(){
        int resultat = 0;
        Iterator<Chambre> iter = this.chambres.iterator();
        while (iter.hasNext()){
            Object chambre = iter.next();
            if (chambre instanceof ChambreSuite){
                resultat += 1;
            }
        }
        return resultat;
    }

    public String toString(){
        String resultat = new String();
        // Nom
        resultat = "Nom : " + this.nom + "\n";

        // Adresse
        resultat += "Adresse : " + this.adresse + "\n";

        // Services
        String concatServices = "";
        for (String s : this.services){
            concatServices += s + ", ";
        }
        if (!concatServices.equals("")){
            concatServices.substring(0, concatServices.length()-2);
        }
        resultat += "Services : " + concatServices + "\n";

        // Chambres
        resultat += " --- Chambres --- \n";
        if (this.chambres.size()==0){
            resultat += "  0 chambres enregistrées. \n";
        }else{
            for (Chambre c : this.chambres){
                resultat += c.toString();
            }
        }

        // Réservations
        resultat += " --- Réservations --- \n";
        if (this.réservations.size()==0){
            resultat += "  0 réservations. \n";
        }else{
            int i = 0;
            for (Réservation c : this.réservations){
                resultat += "* Chambre n°" + Integer.toString(i) + ": \n";
                resultat += c.toString();
            }
        }

        return resultat;
    }
}

class Hôtel extends Hébergement{
    Hôtel(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        super(nom, adresse, services, chambres);
    }

    Hôtel(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        super(nom, adresse, services, réservations, chambres);
    }

    public String toString(){
        String resultat = ((Hébergement)this).toString();
        return "Hébergement : Hôtel \n" + resultat;
    }
}

class Motel extends Hébergement{
    Motel(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        super(nom, adresse, services, chambres);
    }

    Motel(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        super(nom, adresse, services, réservations, chambres);
    }

    public String toString(){
        String resultat = ((Hébergement)this).toString();
        return "Hébergement : Motel \n" + resultat;
    }
}

class CouetteEtCaffe extends Hébergement{
    CouetteEtCaffe(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        super(nom, adresse, services, chambres);
    }

    CouetteEtCaffe(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        super(nom, adresse, services, réservations, chambres);
    }

    public String toString(){
        String resultat = ((Hébergement)this).toString();
        return "Hébergement : Couette & Caffé \n" + resultat;
    }
}
