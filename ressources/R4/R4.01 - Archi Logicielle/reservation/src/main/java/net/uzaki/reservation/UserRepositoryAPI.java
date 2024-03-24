package net.uzaki.reservation;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public class UserRepositoryAPI implements UserRepositoryInterface {

    /**
     * URL de l'API des utilisateurs
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public UserRepositoryAPI(String url){
        this.url = url ;
    }

    @Override
    public void close() {

    }

    @Override
    public User getUser(String id) {
        User myUser = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget bookResource  = client.target(url);
        // définition du point d'accès
        WebTarget bookEndpoint = bookResource.path("users/"+id);
        // envoi de la requête et récupération de la réponse
        Response response = bookEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le livre a bien été trouvé, conversion du JSON en User
        if( response.getStatus() == 200)
            myUser = response.readEntity(User.class);

        // fermeture de la connexion
        client.close();
        return myUser;
    }
}