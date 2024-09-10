import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

abstract class Hébergement {
    String nom;
    String adresse;
    private ArrayList<String> services;
    private ArrayList<Réservation> réservations;
    private ArrayList<Chambre> chambres;

    Hébergement(String nom, String adresse, ArrayList<String> services){
        this.nom = nom;
        this.adresse = adresse;
        this.services = services;
        this.réservations = new ArrayList<Réservation>();
        this.chambres = new ArrayList<Chambre>();
    }

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

    void ajouterChambre(Chambre chambre){
        this.chambres.add(chambre);
    }

    void supprimerChambre(Chambre chambre){
        this.chambres.remove(chambre);
    }

    Chambre getChambre(int index){
        return this.chambres.get(index);
    }

    Réservation getRéservation(int index){
        return this.réservations.get(index);
    }

    ArrayList<Réservation> getRéservations(){
        return this.réservations;
    }

    /*
     * trouverChambre
     * Retourne une chambre de l'hôtel qui correspond au paramêtres
     * Si aucune chambre ne correspond aux critères, renvoie null
     */
    Chambre trouverChambre(String typeChambre, LocalDate dateArrivée, LocalDate dateDépart, int prixMax){
        // Parcours des chambres : quand une correspond aux critères, on vérifie sa disponibilité en parcourant les réservations
        for (Chambre chambre : this.chambres){
            if (!(chambre.getType().equals(typeChambre) && chambre.getPrix() <= prixMax)){
                continue;
            }
            boolean verified = true;
            for (Réservation réservation : this.réservations){
                if (chambre!=réservation.chambre){
                    continue;
                }
                LocalDate dateArrivéeRéservation = réservation.getDateArrivée();
                LocalDate dateDépartRéservation = réservation.getDateDépart();
                // Si les dates chevauchent celles d'une réservation, on passe à la prochaine chambre
                if (dateArrivée.isBefore(dateArrivéeRéservation) && dateDépart.isAfter(dateDépartRéservation)){
                    verified = false;
                    break;
                }
                if (dateArrivée.isEqual(dateArrivéeRéservation) || (dateArrivée.isAfter(dateArrivéeRéservation) && dateArrivée.isBefore(dateDépartRéservation))){
                    verified = false;
                    break;
                }
                if (dateDépart.isEqual(dateDépartRéservation) || (dateDépart.isAfter(dateArrivéeRéservation) && dateDépart.isBefore(dateDépartRéservation))){
                    verified = false;
                    break;
                }
            }
            if (verified){
                return chambre;
            }
        }
        return null; // 0 chambres ou toutes complètes ou ne correspondent pas aux critères
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
        resultat += "Hébergement : " + this.getType() + " \n";
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
            concatServices = concatServices.substring(0, concatServices.length()-2);
            
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
            int i = 1;
            for (Réservation c : this.réservations){
                resultat += "* Chambre n°" + Integer.toString(i) + ": \n";
                resultat += c.toString();
            }
        }

        return resultat;
    }

    abstract public String getType();
}

class Hôtel extends Hébergement{
    Hôtel(String nom, String adresse, ArrayList<String> services){
        super(nom, adresse, services);
    }

    Hôtel(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        super(nom, adresse, services, chambres);
    }

    Hôtel(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        super(nom, adresse, services, réservations, chambres);
    }

    @Override
    public String getType(){
        return "Hôtel";
    }
}

class Motel extends Hébergement{
    Motel(String nom, String adresse, ArrayList<String> services){
        super(nom, adresse, services);
    }

    Motel(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        super(nom, adresse, services, chambres);
    }

    Motel(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        super(nom, adresse, services, réservations, chambres);
    }

    @Override
    public String getType(){
        return "Motel";
    }
}

class CouetteEtCaffe extends Hébergement{
    CouetteEtCaffe(String nom, String adresse, ArrayList<String> services){
        super(nom, adresse, services);
    }

    CouetteEtCaffe(String nom, String adresse, ArrayList<String> services, ArrayList<Chambre> chambres){
        super(nom, adresse, services, chambres);
    }

    CouetteEtCaffe(String nom, String adresse, ArrayList<String> services, ArrayList<Réservation> réservations, ArrayList<Chambre> chambres){
        super(nom, adresse, services, réservations, chambres);
    }

    @Override
    public String getType(){
        return "Couette & Caffé";
    }
}
