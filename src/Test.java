import java.time.LocalDate;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("\nTest of classes Client, Réservation, Chambre, Hébergement : \n\n");

        // Test client
        Client client1 = new Client("Paul", "16 avenue Pierre Mendes France 38320 Poisat", "paul.senger@gmail.com", "0762234369");
        assert client1.toString().equals("Nom : Paul\nAdresse : 16 avenue Pierre Mendes France 38320 Poisat\nCourriel : paul.senger@gmail.com\nTéléphone : 0762234369\nNombre de réservations : 0\n");

        // Test Chambre
        ChambreSimple chambreSimple = new ChambreSimple(100);
        assert chambreSimple.toString().equals(" * Chambre : Simple \n   prix : 100\n");

        // Test Réservation
        //Réservation réservation = new Réservation(LocalDate.of(2024, 9, 10), null, client1, null, chambreSimple)
    }
}
