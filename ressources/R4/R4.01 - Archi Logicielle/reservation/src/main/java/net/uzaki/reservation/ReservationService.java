package net.uzaki.reservation;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;

public class ReservationService {
    protected ReservationRepositoryInterface reservationRepo;
    protected BookRepositoryInterface bookRepo;
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param reservationRepo objet implémentant l'interface d'accès aux données des réservations
     * @param bookRepo  objet implémentant l'interface d'accès aux données des livres
     * @param userRepo  objet implémentant l'interface d'accès aux données des utilisateurs
     */
    public ReservationService(ReservationRepositoryInterface reservationRepo, BookRepositoryInterface bookRepo,
                              UserRepositoryInterface userRepo) {
        this.reservationRepo = reservationRepo;
        this.bookRepo = bookRepo ;
        this.userRepo = userRepo ;
    }

    public ReservationService(ReservationRepositoryInterface reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    /**
     * Méthode permettant d'enregistrer une réservation (la date est définie automatiquement)
     * @param id identifiant de l'utilisateur
     * @param reference référence du livre à réserver
     * @return true si le livre a pu être réservé, false sinon
     */
    public boolean registerReservation(String id, String reference) {
        boolean result = false;

        // récupération des informations du livre
        Book myBook = bookRepo.getBook( reference );

        //si le livre n'est pas trouvé
        if( myBook == null )
            throw new NotFoundException("book not exists");

        // si le livre est disponible
        if( myBook.status == 'd')
        {
            // recherche l'utilisateur
            User myUser = userRepo.getUser(id);

            // si l'utilisateur n'existe pas
            if( myUser == null)
                throw new NotFoundException("user not exists");

            // modifier le statut du livre à "r" (réservé) dans le dépôt
            result = bookRepo.updateBook(myBook.reference, myBook.title, myBook.authors, 'r');

            // faire la réservation
            if( result )
                result = reservationRepo.registerReservation(id, reference);
        }
        return result;
    }

    /**
     * Méthode supprimant une réservation
     * @param reference référence du livre à libérer
     * @return true si la réservation a été supprimée, false sinon
     */
    boolean removeReservation(String reference){
        boolean result = false;

        // récupération des informations du livre
        Book myBook = bookRepo.getBook( reference );

        //si le livre n'est pas trouvé
        if( myBook == null )
            throw  new NotFoundException("book not exists");
        else
        {
            // modifier le statut du livre à "d" (disponible) dans le dépôt des livres
            result = bookRepo.updateBook(myBook.reference, myBook.title, myBook.authors, 'd');

            // supprimer la réservation
            if(result ) result = reservationRepo.releaseReservation( reference );
        }
        return result;
    }

    public String getAllReservationsJSON(){

            ArrayList<Reservation> allReservations = reservationRepo.getAllReservations();

            // création du json et conversion de la liste de livres
            String result = null;
            try( Jsonb jsonb = JsonbBuilder.create()){
                result = jsonb.toJson(allReservations);
            }
            catch (Exception e){
                System.err.println( e.getMessage() );
            }

            return result;
    }

    public String getReservationJSON( String reference ){
        String result = null;
        Reservation myReservation = reservationRepo.getReservation(reference);

        // si le livre a été trouvé
        if( myReservation != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myReservation);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}
