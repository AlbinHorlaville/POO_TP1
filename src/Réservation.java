import java.time.LocalDate;

public class Réservation {
    private LocalDate dateArrivée;
    private LocalDate dateDépart;
    Client client;
    Hébergement hébergement;
    Chambre chambre;

    Réservation(LocalDate dateA, LocalDate dateD, Client client, Hébergement hébergement, Chambre chambre){
        this.dateArrivée = dateA;
        this.dateDépart = dateD;
        this.client = client;
        this.client.ajouterRéservation(this);
        this.hébergement = hébergement;
        this.hébergement.ajouterRéservation(this);
        this.chambre = chambre;
    }

    LocalDate getDateArrivée(){
        return this.dateArrivée;
    }

    LocalDate getDateDépart(){
        return this.dateDépart;
    }

    public String toString(){
        return "  LocalDate d'arrivée : " + this.dateArrivée + "\n" + 
               "  LocalDate de départ : " + this.dateDépart + "\n" +
               "  Client : " + this.client.nom + "\n" +
               "  Hébergement : " + this.hébergement.nom + "\n" +
               "  Chambre : " + this.chambre.getType() + "\n";
    }
}
