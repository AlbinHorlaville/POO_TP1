import java.time.LocalDate;
import java.util.ArrayList;

public class SystemeGestionReservationsImpl implements SystemeGestionReservations {
    ArrayList<Hebergement> hebergements;
    ArrayList<Client> clients;

    SystemeGestionReservationsImpl(){
        this.hebergements = new ArrayList<Hebergement>();
        this.clients = new ArrayList<Client>();
    }

    @Override
    public void nouveauClient(String nom, String adresse, String courriel, String numTelephone) {
        Client client = new Client(nom, adresse, courriel, numTelephone);
        this.clients.add(client);
    }

    @Override
    public void nouveauHébergement(String typeHebergement, String nom, String adresse, String services) {
        // Transformer services en ArrayList
        ArrayList<String> servicesArray = new ArrayList<String>();
        String[] servicesSplited = services.split(", ");
        for (String s : servicesSplited){
            servicesArray.add(s);
        }
        Hebergement hébergement;
        switch(typeHebergement){
            case "Hotel":
                hébergement = new Hotel(nom, adresse, servicesArray);
                break;
            case "Motel":
                hébergement = new Motel(nom, adresse, servicesArray);
                break;
            case "Couette & Caffé":
                hébergement = new CouetteEtCaffe(nom, adresse, servicesArray);
                break;
            default:
                System.out.println("Erreur, type d'Hebergement inconnu, fichier SystemGestionReservationsImpl");
                return;
        }
        this.hebergements.add(hébergement);
    }

    @Override
    public void nouvelleChambre(int prix, String typeChambre, Hebergement hébergement) {
        hébergement.ajouterChambre(typeChambre, prix);
    }

    @Override
    public void supprimerChambre(int index, Hebergement hébergement) {
        hébergement.supprimerChambre(hébergement.getChambre(index));
    }

    @Override
    public void réserver(LocalDate dateA, LocalDate dateD, Client client, Chambre chambre) {
        new Réservation(dateA, dateD, client, chambre.hébergement, chambre);
    }

    @Override
    public void annulerRéservation(Réservation reservation) {
        reservation.client.annulerRéservation(reservation);
        reservation.hébergement.annulerRéservation(reservation);
    }

    @Override
    public Hebergement getHebergementByName(String nom){
        for (Hebergement h : this.hebergements){
            if (h.nom.equals(nom)){
                return h;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Réservation> getRéservations(Client client) {
        return client.getRéservations();
    }

    @Override
    public ArrayList<Réservation> getRéservations(Hebergement hébergement) {
        return hébergement.getRéservations();
    }

    @Override
    public ArrayList<Chambre> trouverLesMeilleursChambres(String typeHebergement, String adresse, String typeChambre,
            String services, LocalDate dateArrivée, LocalDate dateDépart, int prixMax) {
        // Faire la liste de toutes les chambres éligibles et renvoyer le top 5 en triant par prix du moins chère au plus chère
        ArrayList<Chambre> chambresÉligibles = new ArrayList<Chambre>();
        for (Hebergement hébergement : this.hebergements){
            if (!hébergement.getType().equals(typeHebergement) || !hébergement.adresse.equals(adresse)){
                continue;
            }
            Chambre chambre = hébergement.trouverChambre(typeChambre, dateArrivée, dateDépart, prixMax);
            if (chambre!=null){
                chambresÉligibles.add(chambre);
            }
        }
        chambresÉligibles.sort(new ChambreComparator());
        while (chambresÉligibles.size()>5){
            chambresÉligibles.remove(chambresÉligibles.size()-1);
        }
        return chambresÉligibles;
    }
}
