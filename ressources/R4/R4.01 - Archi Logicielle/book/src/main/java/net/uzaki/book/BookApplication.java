import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import net.uzaki.book.BookRepositoryInterface;
import net.uzaki.book.BookRepositorySqlitedb;


@ApplicationPath("/api")
@ApplicationScoped
public class BookApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     *
     * @return un objet implémentant l'interface BookRepositoryInterface utilisée
     * pour accéder aux données des livres, voire les modifier
     */
    @Produces
    private BookRepositoryInterface openDbConnection() {
        BookRepositorySqlitedb db = null;

        try {
            db = new BookRepositorySqlitedb("jdbc:sqlite:book.db");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     *
     * @param bookRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes BookRepositoryInterface bookRepo) {
        bookRepo.close();
    }
}