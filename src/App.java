import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;

public class App {
    /*
     * Sauvegarde dans un fichier les données entre deux sessions.
     */
    static void serialize(Serializable obj){
        try {
            FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/data.ser");
         } catch (IOException i) {
            i.printStackTrace();
         }
    }
    /*
     * Charge les données depuis un fichier au début de la session, si vide alors créer un gestionnaire
     */
    static SystemeGestionReservationsImpl getGestionnaire(){
        SystemeGestionReservationsImpl g = null;
        try {
           FileInputStream fileIn = new FileInputStream("tmp/data.ser");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           g = (SystemeGestionReservationsImpl) in.readObject();
           in.close();
           fileIn.close();
        } catch (FileNotFoundException i) {
           g = new SystemeGestionReservationsImpl();
        } catch (Exception c) {
           System.out.println("Error Occured");
           c.printStackTrace();
           return null;
        }
        return g;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("*** Gestionnaire de réservation de Apafi NiDévoyager ***");
        
        Scanner inputScanner = new Scanner(System.in);

        SystemeGestionReservationsImpl gestionnaire = getGestionnaire();

        Client client = null; // Sauvegarde du client dans la session
        String typeHebergementReservation = "";
        String adresseReservation = "";
        String dateArriveeReservation = "00/00/00";
        String dateDepartReservation = "00/00/00";
        String typeChambreReservation = "";
        String servicesReservation = "";
        int prixMaxReservation = 0;
        ArrayList<Chambre> chambresReservation = new ArrayList<Chambre>();
        Chambre chambreChoisi = null;

        int choice = -1;

        State state = State.CHOIX_PROFIL;
        boolean exit = false;
        while (true){
            if (exit){
                System.out.println("Programme arrété.");
                break;  // Quitter le programme
            }
            switch(state){
                case CHOIX_PROFIL:
                    System.out.println("1. Choix du Profil (tapper 1, 2 ou 3)");
                    System.out.println("  1 - Client");
                    System.out.println("  2 - Administrateur");
                    System.out.println("  3 - Quitter");
                    do{
                        int profile = inputScanner.nextInt();
                        inputScanner.nextLine();
                        if (profile == 1){
                            state = State.CHOIX_CLIENT;
                            break;
                        }else if (profile == 2){
                            state = State.CREATION;
                            break;
                        }else if (profile == 3){
                            exit = true;
                            break;
                        }
                        System.out.println("Tapper 1, 2 ou 3.");
                    }while(true);
                    //System.out.flush(); // TEST
                    break;
                case CHOIX_CLIENT:
                    System.out.println("2. Choix du client (Tapper le numéro du client ou -1 pour revenir en arrière)");
                    if (gestionnaire.clients.size() == 0){
                        System.out.println("  0 clients. Vous pouvez en créer dans le profil Administrateur.");
                    }
                    for (int i=0; i<gestionnaire.clients.size(); i++){
                        System.out.println("  " + i + " - " + gestionnaire.clients.get(i).nom);
                    }
                    do{
                        int numClient = inputScanner.nextInt();
                        inputScanner.nextLine();
                        if (numClient == -1){
                            state = State.CHOIX_PROFIL;
                            break;
                        }
                        try{
                            client = gestionnaire.clients.get(numClient);
                        }catch(Exception e){
                            System.out.println("Tapper -1 pour revenir en arrière.");
                            continue;
                        }
                        if (client != null){
                            state = State.ACTION;
                            break;
                        }
                        System.out.println("Tapper -1 ou un numéro de client");                
                    }while(true);
                    break;
                case ACTION:
                    System.out.println("3. Profil Client : " + client.nom + " (Tapper 1 ou 2 selon l'action ou -1 pour revenir en arrière)");
                    System.out.println("  1 - Réserver");
                    System.out.println("  2 - Voir mes réservations");
                    do{
                        choice = inputScanner.nextInt();
                        inputScanner.nextLine();
                        if (choice == -1){
                            state = State.CHOIX_CLIENT;
                            break;
                        }else if (choice == 1){
                            state = State.RESERVER;
                            break;
                        }else if (choice == 2){
                            state = State.RESERVATIONS;
                            break;
                        }
                        System.out.println("Tapper 1, 2 ou -1");
                    }while(true);
                    break;
                case CREATION:
                    System.out.println("2. Création de profil client / hébergement (Tapper 1, 2 ou -1 pour revenir en arrière)");
                    System.out.println("  1 - Créer un client");
                    System.out.println("  2 - Créer un hébergement");
                    System.out.println("  3 - Créer une chambre");
                    System.out.println("  4 - Afficher les clients");
                    System.out.println("  5 - Afficher les hébergments");
                    do{
                        choice = inputScanner.nextInt();
                        inputScanner.nextLine();
                        if (choice == -1){
                            state = State.CHOIX_PROFIL;
                            break;
                        }else if (choice == 1){
                            state = State.CREATION_CLIENT;
                            break;
                        }else if (choice == 2){
                            state = State.CREATION_HEBERGEMENT;
                            break;
                        }else if(choice == 3){
                            state = State.CREATION_CHAMBRE;
                            break;
                        }else if (choice == 4){
                            System.out.println("Clients enregistrés :");
                            if (gestionnaire.clients.size() == 0){
                                System.out.println(" 0 client enregistrés.");
                            }
                            for (Client c: gestionnaire.clients){
                                System.out.println(c.toString());
                            }
                        }else if (choice == 5){
                            System.out.println("Hébergements enregistrés :");
                            if (gestionnaire.hebergements.size() == 0){
                                System.out.println(" 0 hebergements enregistrés.");
                            }
                            for (Hebergement h: gestionnaire.hebergements){
                                System.out.println(h.toString());
                            }
                        }
                        System.out.println("Tapper 1, 2, 3, 4 ou -1");
                    }while(true);
                    break;
                case CREATION_CLIENT:
                    System.out.println("3. Création de client");
                    System.out.println("Tapper le nom du client :");
                    String nomClient = inputScanner.nextLine();
                    System.out.println("Tapper l'adresse du client :");
                    String adresseClient = inputScanner.nextLine();
                    System.out.println("Tapper le courriel du client :");
                    String courrielClient = inputScanner.nextLine();
                    System.out.println("Tapper le téléphone du client :");
                    String telephoneClient = inputScanner.nextLine();
                    try{
                        gestionnaire.nouveauClient(nomClient, adresseClient, courrielClient, telephoneClient);
                        System.out.println("**  Client créé !  **");
                    }catch (Exception e){
                        System.out.println("Erreur création client. Nouvel essai");
                        System.out.println(e.toString());
                        break;
                    }
                    state = State.CHOIX_PROFIL;
                    break;
                case CREATION_HEBERGEMENT:
                    System.out.println("3. Création de l'hébergement");
                    System.out.println("Tapper le type de l'hébergement :");
                    String typeHebergement = inputScanner.nextLine();
                    System.out.println("Tapper le nom de l'hébergement :");
                    String nomHebergement = inputScanner.nextLine();
                    System.out.println("Tapper l'adresse de l'hébergement :");
                    String adresseHebergement = inputScanner.nextLine();
                    System.out.println("Tapper les services de l'hébergement. Format : <piscine, jaccuzy, badminton>");
                    String servicesHebergement = inputScanner.nextLine();
                    gestionnaire.nouveauHébergement(typeHebergement, nomHebergement, adresseHebergement, servicesHebergement);
                    state = State.CHOIX_PROFIL;
                    break;
                case CREATION_CHAMBRE:
                    System.out.println("3. Création d'une chambre");
                    String typeChambre;
                    while(true){
                        System.out.println("Tapper le type de la chambre");
                        typeChambre = inputScanner.nextLine();
                        if (typeChambre.equals("Simple") || typeChambre.equals("Double") || typeChambre.equals("Suite")){
                            break;
                        }
                    }
                    System.out.println("Tapper le prix de la chambre");
                    int prixChambre = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.println("Tapper le nom de l'hébergement");
                    String nom = inputScanner.nextLine();
                    Hebergement hebergement = gestionnaire.getHebergementByName(nom);
                    if (hebergement == null){
                        System.out.println("Hébergement non trouvé");
                        break;
                    }
                    gestionnaire.nouvelleChambre(prixChambre, typeChambre, hebergement);
                    state = State.CHOIX_PROFIL;
                    System.out.println("Chambre créée !\n");
                    break;
                case RESERVER:
                    System.out.println("4. Réservation : entrez vos critères");
                    System.out.println("Tapper le type d'hébergement :");
                    typeHebergementReservation = inputScanner.nextLine();
                    System.out.println("Tapper l'adresse (ville)");
                    adresseReservation = inputScanner.nextLine();
                    while (true){
                        try{
                            System.out.println("Tapper la date d'arrivée : (Format 2002-11-23)");
                            dateArriveeReservation = inputScanner.nextLine();
                            LocalDate.parse(dateArriveeReservation);
                            break;
                        }catch(Exception e){}
                    }
                    while (true){
                        try{
                            System.out.println("Tapper la date de départ : (Format : 2002-11-23)");
                            dateDepartReservation = inputScanner.nextLine();
                            LocalDate.parse(dateDepartReservation);
                            break;
                        }catch(Exception e){}
                    }
                    System.out.println("Entrer le type de chambre :");
                    typeChambreReservation = inputScanner.nextLine();
                    System.out.println("Entrer les services voulus :");
                    servicesReservation = inputScanner.nextLine();
                    System.out.println("Tapper le prix maximum :");
                    prixMaxReservation = inputScanner.nextInt();
                    inputScanner.nextLine();
                    state = State.CHOIX_CHAMBRE;
                    break;
                case RESERVATIONS:
                    System.out.println("4. Mes réservations : (Pour supprimer, taper le numéro de la réservation, sinon tapper -1 pour revenir");
                    ArrayList<Réservation> reservations = gestionnaire.getRéservations(client);
                    for (int i=0; i<reservations.size(); i++){
                        System.out.println(" " + i + " - Réservation");
                        System.out.println(reservations.get(i).toString());
                    }
                    choice = inputScanner.nextInt();
                    inputScanner.nextLine();
                    do{
                        if (choice == -1){
                            state = State.ACTION;
                            break;
                        }else if (choice >= 0 && choice < reservations.size()){
                            gestionnaire.annulerRéservation(reservations.get(choice));
                            System.out.println("--- Réservation annulée ! ---");
                            break;
                        }
                    }while(true);
                    break;
                case CHOIX_CHAMBRE:
                    System.out.println("5. Choix de la chambre d'après nos recherches :");
                    LocalDate dateA = LocalDate.parse(dateArriveeReservation);
                    LocalDate dateD = LocalDate.parse(dateDepartReservation);
                    chambresReservation = gestionnaire.trouverLesMeilleursChambres(typeHebergementReservation, adresseReservation, typeChambreReservation, servicesReservation, dateA, dateD, prixMaxReservation);
                    for (int i=0; i< chambresReservation.size(); i++){
                        System.out.println(" " + i + " - Chambre");
                        System.out.println(chambresReservation.get(i).toString());
                    }
                    do{
                        try{
                            choice = inputScanner.nextInt();
                            inputScanner.nextLine();
                            if (choice >= 0 && choice < chambresReservation.size()){
                                chambreChoisi = chambresReservation.get(choice);
                                break;
                            }
                        }catch(Exception e){}
                    }while(true);
                    gestionnaire.réserver(dateA, dateD, client, chambreChoisi);
                    state = State.RESERVATION_VALIDEE;
                    break;
                case RESERVATION_VALIDEE:
                    System.out.println("6. Réservation validée ! Vous pouvez la consulter dans votre profil");
                    System.out.println("Entrer -1 pour revenir au menu.");
                    inputScanner.nextLine();
                    state = State.CHOIX_PROFIL;
                    break;
                default:
                    System.out.println("Error state machine");
                    return;
            }
        }
        // Serialize data before quit
        serialize(gestionnaire);
        inputScanner.close();
    }
}
