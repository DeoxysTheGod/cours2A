package net.uzaki.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux livres
 * (point d'accès de l'API REST)
 */
@Path("/books")
public class BookResources {

    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private BookService service;

    /**
     * Constructeur par défaut
     */
     public BookResources(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param bookRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject void BookResource(BookRepositoryInterface bookRepo ){
        this.service = new BookService( bookRepo) ;
    }
    /**
     * Constructeur permettant d'initialiser le service d'accès aux livres
     */
    public BookResources(BookService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les livres enregistrés
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllBooks() {
        return service.getAllBooksJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un livre dont la référence est passée paramètre dans le chemin
     * @param reference référence du livre recherché
     * @return les informations du livre recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getBook( @PathParam("reference") String reference){

        String result = service.getBookJSON(reference);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un livre uniquement
     * (la requête patch doit fournir le nouveau statut sur livre, les autres informations sont ignorées)
     * @param reference la référence du livre dont il faut changer le statut
     * @param book le livre transmis en HTTP au format JSON et convertit en objet Book
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updateBook(@PathParam("reference") String reference, Book book ){

        // si le livre n'a pas été trouvé
        if( ! service.updateBook(reference, book) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}