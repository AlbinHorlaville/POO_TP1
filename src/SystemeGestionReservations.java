import java.time.LocalDate;
import java.util.ArrayList;

public interface SystemeGestionReservations {
    public void nouveauClient(String nom, String adresse, String courriel, String numTéléphone);

    public void nouveauHébergement(String typeHébergement, String nom, String adresse, String services); // Transformer services en ArrayList

    public void nouvelleChambre(int prix, String typeChambre, Hébergement hébergement);

    public void supprimerChambre(int index, Hébergement hébergement);

    public void réserver(LocalDate dateA, LocalDate dateD, Client client, Hébergement hébergement, Chambre chambre);

    public void annulerRéservation(Client client, int indexRéservation, Hébergement hébergement);

    public ArrayList<Réservation> getRéservations(Client client);

    public ArrayList<Réservation> getRéservations(Hébergement hébergement);

    public ArrayList<Chambre> trouverLesMeilleursChambres(String typeHébergement, String adresse, String typeChambre, String services, LocalDate dateArrivée, LocalDate dateDépart, int prixMax);
}