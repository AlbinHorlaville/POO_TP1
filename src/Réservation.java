import java.time.LocalDate;

public class Réservation implements java.io.Serializable{
    private LocalDate dateArrivée;
    private LocalDate dateDépart;
    Client client;
    Hebergement hébergement;
    Chambre chambre;

    Réservation(LocalDate dateA, LocalDate dateD, Client client, Hebergement hébergement, Chambre chambre){
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
        return "  Date d'arrivée : " + this.dateArrivée + "\n" + 
               "  Date de départ : " + this.dateDépart + "\n" +
               "  Client : " + this.client.nom + "\n" +
               "  Hebergement : " + this.hébergement.nom + "\n" +
               "  Chambre : " + this.chambre.getType() + "\n";
    }
}
