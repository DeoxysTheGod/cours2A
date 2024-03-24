package net.uzaki.book;

/**
 * Classe représentant un livre
 */
public class Book {

    /**
     * Référence du livre
     */
    protected String reference;

    /**
     * titre du livre
     */
    protected String title;

    /**
     * Auteurs du livre
     */
    protected String authors;

    /**
     * Statut du livre
     * ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    protected char status;

    /**
     * Constructeur par défaut
     */
    public Book(){
    }

    /**
     * Constructeur de livre
     * @param reference référence du livre
     * @param title titre du livre
     * @param authors auteurs du livre
     */
    public Book(String reference, String title, String authors){
        this.reference = reference;
        this.title = title;
        this.authors = authors;
        this.status = 'd';
    }

    /**
     * Méthode permettant d'accéder à la réference du livre
     * @return un chaîne de caractères avec la référence du livre
     */
    public String getReference() {
        return reference;
    }

    /**
     * Méthode permettant d'accéder au titre du livre
     * @return un chaîne de caractères avec le titre du livre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Méthode permettant d'accéder aux auteurs du livre
     * @return un chaîne de caractères avec la liste des auteurs
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Méthode permettant d'accéder au statut du livre
     * @return un caractère indiquant lestatu du livre ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    public char getStatus() {
        return status;
    }

    /**
     * Méthode permettant de modifier la référence du livre
     * @param reference une chaîne de caractères avec la référence à utiliser
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Méthode permettant de modifier le titre du livre
     * @param title une chaîne de caractères avec le titre à utiliser
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Méthode permettant de modifier les autheurs du livre
     * @param authors une chaîne de caractères avec la liste des auteurs
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * Méthode permettant de modifier le statut du livre
     * @param status le caractère 'r' pour réservé, 'e' pour emprunté, ou 'd' pour disponible
     */
    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "reference='" + reference + '\'' +
                ", titre='" + title + '\'' +
                ", auteurs='" + authors + '\'' +
                ", statut=" + status +
                '}';
    }
}