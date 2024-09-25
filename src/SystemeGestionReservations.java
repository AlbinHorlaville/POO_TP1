import java.time.LocalDate;
import java.util.ArrayList;

public interface SystemeGestionReservations {
    public void nouveauClient(String nom, String adresse, String courriel, String numTelephone);

    public void nouveauHébergement(String typeHebergement, String nom, String adresse, String services); // Transformer services en ArrayList

    public void nouvelleChambre(int prix, String typeChambre, Hebergement hébergement);

    public void supprimerChambre(int index, Hebergement hébergement);

    public void réserver(LocalDate dateA, LocalDate dateD, Client client, Chambre chambre);

    public void annulerRéservation(Réservation reservation);

    public Hebergement getHebergementByName(String nom);
    
    public ArrayList<Réservation> getRéservations(Client client);

    public ArrayList<Réservation> getRéservations(Hebergement hébergement);

    public ArrayList<Chambre> trouverLesMeilleursChambres(String typeHebergement, String adresse, String typeChambre, String services, LocalDate dateArrivée, LocalDate dateDépart, int prixMax);
}