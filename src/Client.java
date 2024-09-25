import java.util.ArrayList;

public class Client {
    String nom;
    String adresse;
    String courriel;
    String numTelephone;
    private ArrayList<Réservation> réservations;

    Client(String nom, String adresse, String courriel, String numTelephone){
        this.nom = nom;
        this.adresse = adresse;
        this.courriel = courriel;
        this.numTelephone = numTelephone;
        this.réservations = new ArrayList<Réservation>();
    }

    Réservation getRéservation(int index){
        return this.réservations.get(index);
    }

    ArrayList<Réservation> getRéservations(){
        return this.réservations;
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
               "Téléphone : " + this.numTelephone + "\n" +
               "Nombre de réservations : " + Integer.toString(réservations.size()) + "\n";
    }
}
