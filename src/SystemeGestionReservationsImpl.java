import java.time.LocalDate;
import java.util.ArrayList;

public class SystemeGestionReservationsImpl implements SystemeGestionReservations {
    ArrayList<Hébergement> hébergements;
    ArrayList<Client> clients;

    @Override
    public void nouveauClient(String nom, String adresse, String courriel, String numTéléphone) {
        Client client = new Client(nom, adresse, courriel, numTéléphone);
        this.clients.add(client);
    }

    @Override
    public void nouveauHébergement(String typeHébergement, String nom, String adresse, String services) {
        // Transformer services en ArrayList
        ArrayList<String> servicesArray = new ArrayList<String>();
        String[] servicesSplited = services.split(", ");
        for (String s : servicesSplited){
            servicesArray.add(s);
        }
        Hébergement hébergement;
        switch(typeHébergement){
            case "Hôtel":
                hébergement = new Hôtel(nom, adresse, servicesArray);
                break;
            case "Motel":
                hébergement = new Motel(nom, adresse, servicesArray);
                break;
            case "Couette & Caffé":
                hébergement = new CouetteEtCaffe(nom, adresse, servicesArray);
                break;
            default:
                System.out.println("Erreur, type d'Hébergement inconnu, fichier SystemGestionReservationsImpl");
                return;
        }
        this.hébergements.add(hébergement);
    }

    @Override
    public void nouvelleChambre(int prix, String typeChambre, Hébergement hébergement) {
        Chambre chambre;
        switch(typeChambre){
            case "Simple":
                chambre = new ChambreSimple(prix);
                break;
            case "Double":
                chambre = new ChambreDouble(prix);
                break;
            case "Suite":
                chambre = new ChambreSuite(prix);
                break;
            default:
            System.out.println("Erreur, type de chambre inconnu, fichier SystemGestionReservationsImpl");
            return;
        }
        hébergement.ajouterChambre(chambre);
    }

    @Override
    public void supprimerChambre(int index, Hébergement hébergement) {
        hébergement.supprimerChambre(hébergement.getChambre(index));
    }

    @Override
    public void réserver(LocalDate dateA, LocalDate dateD, Client client, Hébergement hébergement, Chambre chambre) {
        new Réservation(dateA, dateD, client, hébergement, chambre);
    }

    @Override
    public void annulerRéservation(Client client, int indexRéservation, Hébergement hébergement) {
        Réservation réservation = client.getRéservation(indexRéservation);
        client.annulerRéservation(réservation);
        hébergement.annulerRéservation(réservation);
    }

    @Override
    public ArrayList<Réservation> getRéservations(Client client) {
        return client.getRéservations();
    }

    @Override
    public ArrayList<Réservation> getRéservations(Hébergement hébergement) {
        return hébergement.getRéservations();
    }

    @Override
    public ArrayList<Chambre> trouverLesMeilleursChambres(String typeHébergement, String adresse, String typeChambre,
            String services, LocalDate dateArrivée, LocalDate dateDépart, int prixMax) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trouverLesMeilleursChambres'");
        // Faire la liste de toutes les chambres éligibles et renvoyer le top 5 en triant par prix du moins chère au plus chère
    }
    
}
