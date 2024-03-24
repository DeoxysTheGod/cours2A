package net.uzaki.reservation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux réservations de livres
 * (point d'accès de l'API REST)
 */
@Path("/reservations")
@ApplicationScoped
public class ReservationResource {

    /**
     * Service utilisé pour accéder aux données de réservations et récupérer/modifier leurs informations
     */
    private ReservationService service;

    /**
     * Constructeur par défaut
     */
    public ReservationResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec les interfaces d'accès aux données
     * @param reservationRepo objet implémentant l'interface d'accès aux données des réservations
     */
    public @Inject ReservationResource(ReservationRepositoryInterface reservationRepo) {
        this.service = new ReservationService(reservationRepo);
    }

    public ReservationResource(ReservationService service) {
        this.service = service;
    }

    /**
     * Enpoint permettant de soumettre une nouvelle réservation de livre demandée par un utilisateur enregistré
     * @param id identifiant de l'utilisateur souhaitant faire la réservation
     * @param reference référence du livre à réserver
     * @return un objet Response indiquant "registred" si la réservation a été faite ou une erreur "conflict" sinon
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response registerReservation(@FormParam("id") String id, @FormParam("reference") String reference){

        if( service.registerReservation(id, reference) )
            return Response.ok("registred").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();
    }

    /**
     * Endpoint permettant de supprimer une réservation
     * @param reference référence du livre à "libérer"
     * @return un objet Response indiquant "removed" si la réservation a été annulée ou une erreur "not found" sinon
     */
    @DELETE
    @Path("{reference}")
    public Response removeReservation(@PathParam("reference") String reference){

        if( service.removeReservation(reference) )
            return Response.ok("removed").build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }

    /**
     * Enpoint permettant de publier de toutes les réservations enregistrées
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllReservations() {
        return service.getAllReservationsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'une réservation
     * dont la référence est passée paramètre dans le chemin
     * @param reference référence du livre recherché
     * @return les informations de réservation pour le livre recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getReservation( @PathParam("reference") String reference){

        String result = service.getReservationJSON( reference );

        // si aucune réservation n'a été trouvée
        // on retourne simplement un JSON vide
        if( result == null )
           return "{}";

        return result;
    }
}
