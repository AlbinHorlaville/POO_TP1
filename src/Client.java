import java.util.ArrayList;

public class Client {
    String nom;
    String adresse;
    String courriel;
    String numTéléphone;
    private ArrayList<Réservation> réservations;

    Client(String n, String a, String c, String numT){
        this.nom = n;
        this.adresse = a;
        this.courriel = c;
        this.numTéléphone = numT;
        this.réservations = new ArrayList<Réservation>();
    }

    void ajouterRéservation(Réservation réservation){
        this.réservations.add(réservation);
    }

    void annulerRéservation(Réservation réservation){
        this.réservations.remove(réservation);
    }

    public String toString(){
        return "Nom : " + this.nom + "\n" +
               "Adresse : " + this.adresse + "\n" +
               "Courriel : " + this.courriel + "\n" +
               "Téléphone : " + this.numTéléphone + "\n" +
               "Nombre de réservations : " + Integer.toString(réservations.size()) + "\n";
    }
}
