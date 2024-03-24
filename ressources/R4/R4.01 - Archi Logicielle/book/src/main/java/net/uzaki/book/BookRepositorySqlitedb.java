package net.uzaki.book;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class BookRepositorySqlitedb implements BookRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    public BookRepositorySqlitedb(String infoConnection) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection(infoConnection);
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Book getBook(String reference) {

        Book selectedBook = null;

        String query = "SELECT * FROM Book WHERE reference=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, reference);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                String title = result.getString("title");
                String authors = result.getString("authors");
                char status = result.getString("status").charAt(0);

                // création et initialisation de l'objet Book
                selectedBook = new Book(reference, title, authors);
                selectedBook.setStatus(status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedBook;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> listBooks ;

        String query = "SELECT * FROM Book";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listBooks = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String reference = result.getString("reference");
                String title = result.getString("title");
                String authors = result.getString("authors");
                char status = result.getString("status").charAt(0);

                // création du livre courant
                Book currentBook = new Book(reference, title, authors);
                currentBook.setStatus(status);

                listBooks.add(currentBook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listBooks;
    }

    @Override
    public boolean updateBook(String reference, String title, String authors, char status) {
        String query = "UPDATE Book SET title=?, authors=?, status=?  where reference=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, title);
            ps.setString(2, authors);
            ps.setString(3, String.valueOf(status) );
            ps.setString(4, reference);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}