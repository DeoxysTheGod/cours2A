package net.uzaki.reservation;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public class BookRepositoryAPI implements BookRepositoryInterface {

    /**
     * URL de l'API des livres
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public BookRepositoryAPI(String url){
        this.url = url ;
    }

    @Override
    public void close() {}

    @Override
    public Book getBook(String reference) {
        Book myBook = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget bookResource  = client.target(url);
        // définition du point d'accès
        WebTarget bookEndpoint = bookResource.path("books/"+reference);
        // envoi de la requête et récupération de la réponse
        Response response = bookEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le livre a bien été trouvé, conversion du JSON en Book
        if( response.getStatus() == 200)
            myBook = response.readEntity(Book.class);

        // fermeture de la connexion
        client.close();
        return myBook;
    }

    @Override
    public boolean updateBook(String reference, String title, String authors, char status) {
        boolean result = false ;

        Book updatedBook = new Book(reference, title, authors);
        updatedBook.setStatus( status ) ;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget bookResource  = client.target(url);
        // définition du point d'accès
        WebTarget bookEndpoint = bookResource.path("books/"+reference);
        // envoi de la requête avec le livre en JSON et récupération de la réponse
        Response response = bookEndpoint.request(MediaType.APPLICATION_JSON)
                .put( Entity.entity(updatedBook, MediaType.APPLICATION_JSON) );

        // si la mise à jour a été faite
        if( response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }
}