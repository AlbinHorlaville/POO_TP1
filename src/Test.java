import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("\nTest of classes Client, Réservation, Chambre, Hebergement : \n\n");

        // Test client
        Client client1 = new Client("Paul", "16 avenue Pierre Mendes France 38320 Poisat", "paul.senger@gmail.com", "0762234369");
        assert client1.toString().equals("Nom : Paul\nAdresse : 16 avenue Pierre Mendes France 38320 Poisat\nCourriel : paul.senger@gmail.com\nTéléphone : 0762234369\nNombre de réservations : 0\n");



        // Test Hebergement
        ArrayList<String> services = new ArrayList<String>();
        services.add("Piscine");
        services.add("spa");
        services.add("boulangerie");

        Hotel hotel = new Hotel("Grand Budapest Hotel", "4 rue des jonquilles", services);

        // Test Chambre
        ChambreSimple chambreSimple = new ChambreSimple(hotel, 100);
        // assert chambreSimple.toString().equals(" * Chambre : Simple \n   prix : 100\n");

        assert hotel.getNombreChambreDouble() == 0;
        assert hotel.getNombreChambreSimple() == 0;
        assert hotel.getNombreChambreSuite() == 0;

        hotel.ajouterChambre("Simple", 10);
        assert hotel.getNombreChambreSimple() == 1;
        hotel.ajouterChambre("Simple", 20);
        assert hotel.getNombreChambreSimple() == 2;
        hotel.supprimerChambre(hotel.getChambre(0));
        assert hotel.getNombreChambreSimple() == 1;

        // Test Réservation
        // Réservation réservation = new Réservation(
        //     LocalDate.of(2024, 9, 10),
        //     LocalDate.of(2024, 9, 13), client1, hotel, chambreSimple
        // );

        // Test trouverChambre
        hotel.ajouterChambre("Simple", 100);
        hotel.ajouterChambre("Simple", 90);
        hotel.ajouterChambre("Suite", 300);
        hotel.ajouterChambre("Double", 250);

        Réservation réservation = new Réservation(
            LocalDate.of(2024, 10, 11),
            LocalDate.of(2024, 10, 12), client1, hotel, chambreSimple
        );

        //System.out.println(hotel.toString());

        Chambre c1 = hotel.trouverChambre("Simple", LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 13), 200);
        //System.out.println(c1.toString());

        // Test Gestionnaire
        SystemeGestionReservationsImpl gestionnaire = new SystemeGestionReservationsImpl();
        gestionnaire.nouveauClient("Albin", "Paris","albin@gmail.com", "0011223344");
        gestionnaire.nouveauClient("Paul", "Grenoble","paul@gmail.com", "0011223344");
        gestionnaire.nouveauClient("Antoine", "Lyon","antoine@gmail.com", "0011223344");

        System.out.println("---- Création nouveaux clients ----");
        for (Client c : gestionnaire.clients){
            System.out.println(c.toString());
        }

        gestionnaire.nouveauHébergement("Motel", "Motel1", "Paris", "");
        gestionnaire.nouveauHébergement("Motel", "Motel2", "Paris", "");
        gestionnaire.nouveauHébergement("Motel", "Motel3", "Paris", "");
        gestionnaire.nouveauHébergement("Motel", "Motel4", "Paris", "");
        gestionnaire.nouveauHébergement("Motel", "Motel5", "Paris", "");
        gestionnaire.nouveauHébergement("Motel", "Motel6", "Paris", "");

        System.out.println("--- Création hebergements ---");
        for (Hebergement h: gestionnaire.hebergements){
            System.out.println(h.toString());
        }

        gestionnaire.nouvelleChambre(109, "Simple", gestionnaire.hebergements.get(5));
        gestionnaire.nouvelleChambre(119, "Simple", gestionnaire.hebergements.get(5));
        gestionnaire.nouvelleChambre(209, "Double", gestionnaire.hebergements.get(5));
        gestionnaire.nouvelleChambre(309, "Suite", gestionnaire.hebergements.get(5));

        gestionnaire.nouvelleChambre(105, "Simple", gestionnaire.hebergements.get(1));
        gestionnaire.nouvelleChambre(115, "Simple", gestionnaire.hebergements.get(1));
        gestionnaire.nouvelleChambre(205, "Double", gestionnaire.hebergements.get(1));
        gestionnaire.nouvelleChambre(305, "Suite", gestionnaire.hebergements.get(1));

        gestionnaire.nouvelleChambre(108, "Simple", gestionnaire.hebergements.get(4));
        gestionnaire.nouvelleChambre(118, "Simple", gestionnaire.hebergements.get(4));
        gestionnaire.nouvelleChambre(208, "Double", gestionnaire.hebergements.get(4));
        gestionnaire.nouvelleChambre(308, "Suite", gestionnaire.hebergements.get(4));

        gestionnaire.nouvelleChambre(106, "Simple", gestionnaire.hebergements.get(2));
        gestionnaire.nouvelleChambre(116, "Simple", gestionnaire.hebergements.get(2));
        gestionnaire.nouvelleChambre(206, "Double", gestionnaire.hebergements.get(2));
        gestionnaire.nouvelleChambre(306, "Suite", gestionnaire.hebergements.get(2));

        gestionnaire.nouvelleChambre(107, "Simple", gestionnaire.hebergements.get(3));
        gestionnaire.nouvelleChambre(117, "Simple", gestionnaire.hebergements.get(3));
        gestionnaire.nouvelleChambre(207, "Double", gestionnaire.hebergements.get(3));
        gestionnaire.nouvelleChambre(307, "Suite", gestionnaire.hebergements.get(3));

        gestionnaire.nouvelleChambre(100, "Simple", gestionnaire.hebergements.get(0));
        gestionnaire.nouvelleChambre(110, "Simple", gestionnaire.hebergements.get(0));
        gestionnaire.nouvelleChambre(200, "Double", gestionnaire.hebergements.get(0));
        gestionnaire.nouvelleChambre(300, "Suite", gestionnaire.hebergements.get(0));

        ArrayList<Chambre> results = gestionnaire.trouverLesMeilleursChambres("Motel", "Paris", "Simple", "", LocalDate.of(2024, 11, 12), LocalDate.of(2024, 11, 13), 200);
        System.out.println("--- Trouver les meilleures chambres ---");
        for (Chambre c : results){
            System.out.println(c.toString());
        }

        // System.out.println("--- Afficher hotel ---");
        // System.out.println(gestionnaire.hebergements.get(0).toString());
    }
}
