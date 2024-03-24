package net.uzaki.reservation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class ReservationApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface ReservationRepositoryInterface utilisée
     *          pour accéder aux données des livres, voire les modifier
     */
    @Produces
    private ReservationRepositoryInterface openDbConnection(){
        ReservationRepositoryMariadb db = null;

        try{
            db = new ReservationRepositoryMariadb("jdbc:mariadb://mysql-archilog.alwaysdata.net/archilog_library_db", "archilog_library", "Archilog1234*");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode appelée par l'API CDI pour injecter l'API Book au moment de la création de la ressource
     * @return une instance de l'API avec l'url à utiliser
     */
    @Produces
    private BookRepositoryInterface connectBookApi(){
        return new BookRepositoryAPI("http://localhost:8080/book-1.0-SNAPSHOT/api/");
    }

    /**
     * Méthode appelée par l'API CDI pour injecter l'API User au moment de la création de la ressource
     * @return une instance de l'API avec l'url à utiliser
     */
    @Produces
    private UserRepositoryInterface connectUserApi(){
        return new UserRepositoryAPI("http://localhost:8080/user-1.0-SNAPSHOT/api/");
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param userRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes ReservationRepositoryInterface userRepo ) {
        userRepo.close();
    }
}
