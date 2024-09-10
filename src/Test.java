import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("\nTest of classes Client, Réservation, Chambre, Hébergement : \n\n");

        // Test client
        Client client1 = new Client("Paul", "16 avenue Pierre Mendes France 38320 Poisat", "paul.senger@gmail.com", "0762234369");
        assert client1.toString().equals("Nom : Paul\nAdresse : 16 avenue Pierre Mendes France 38320 Poisat\nCourriel : paul.senger@gmail.com\nTéléphone : 0762234369\nNombre de réservations : 0\n");

        // Test Chambre
        ChambreSimple chambreSimple = new ChambreSimple(100);
        assert chambreSimple.toString().equals(" * Chambre : Simple \n   prix : 100\n");

        // Test Hébergement
        ArrayList<Chambre> chambres = new ArrayList<Chambre>();
        chambres.add(chambreSimple);
        ArrayList<String> services = new ArrayList<String>();
        services.add("Piscine");
        services.add("spa");
        services.add("boulangerie");

        Hôtel hotel = new Hôtel("Grand Budapest Hôtel", "4 rue des jonquilles", services);

        assert hotel.getNombreChambreDouble() == 0;
        assert hotel.getNombreChambreSimple() == 0;
        assert hotel.getNombreChambreSuite() == 0;

        hotel.ajouterChambre(chambreSimple);
        assert hotel.getNombreChambreSimple() == 1;
        hotel.ajouterChambre(chambreSimple);
        assert hotel.getNombreChambreSimple() == 2;
        hotel.supprimerChambre(chambreSimple);
        assert hotel.getNombreChambreSimple() == 1;

        // Test Réservation
        // Réservation réservation = new Réservation(
        //     LocalDate.of(2024, 9, 10),
        //     LocalDate.of(2024, 9, 13), client1, hotel, chambreSimple
        // );

        // Test trouverChambre
        hotel.ajouterChambre(new ChambreDouble(300));
        hotel.ajouterChambre(new ChambreSimple(90));
        hotel.ajouterChambre(new ChambreSuite(500));
        hotel.ajouterChambre(new ChambreSimple(50));

        Réservation réservation = new Réservation(
            LocalDate.of(2024, 10, 11),
            LocalDate.of(2024, 10, 12), client1, hotel, chambreSimple
        );

        System.out.println(hotel.toString());

        Chambre c = hotel.trouverChambre("Simple", LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 13), 200);
        System.out.println(c.toString());
        
        
    }
}
