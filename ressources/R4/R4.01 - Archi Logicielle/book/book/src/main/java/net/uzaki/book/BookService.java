package net.uzaki.book;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class BookService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les livres
     */
    protected BookRepositoryInterface bookRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param bookRepo objet implémentant l'interface d'accès aux données
     */
    public  BookService( BookRepositoryInterface bookRepo) {
        this.bookRepo = bookRepo;
    }

    /**
     * Méthode retournant les informations sur les livres au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllBooksJSON(){

        ArrayList<Book> allBooks = bookRepo.getAllBooks();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allBooks);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un livre recherché
     * @param reference la référence du livre recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getBookJSON( String reference ){
        String result = null;
        Book myBook = bookRepo.getBook(reference);

        // si le livre a été trouvé
        if( myBook != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myBook);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un livre
     * @param reference référence du livre à mettre à jours
     * @param book les nouvelles infromations a été utiliser
     * @return true si le livre a pu être mis à jours
     */
    public boolean updateBook(String reference, Book book) {
        return bookRepo.updateBook(reference, book.title, book.authors, book.status);
    }
}