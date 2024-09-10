import java.util.Date;

public class Réservation {
    private Date dateArrivée;
    private Date dateDépart;
    Client client;
    Hébergement hébergement;
    Chambre chambre;

    Réservation(Date dateA, Date dateD, Client client, Hébergement hébergement, Chambre chambre){
        this.dateArrivée = dateA;
        this.dateDépart = dateD;
        this.client = client;
        this.hébergement = hébergement;
        this.chambre = chambre;
    }

    Date getDateArrivée(){
        return this.dateArrivée;
    }

    Date getDateDépart(){
        return this.dateDépart;
    }

    public String toString(){
        return "  Date d'arrivée : " + this.dateArrivée + "\n" + 
               "  Date de départ : " + this.dateDépart + "\n" +
               "  Client : " + this.client.nom + "\n" +
               "  Hébergement : " + this.hébergement.nom + "\n" +
               "  Chambre = " + this.chambre.getClass().toString() + "\n";
    }
}
