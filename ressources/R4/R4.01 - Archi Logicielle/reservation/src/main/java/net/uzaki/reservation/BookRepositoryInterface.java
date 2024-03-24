package net.uzaki.reservation;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des livres
 */
public interface BookRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
    public void close();

    /**
     * Méthode retournant le livre dont la référence est passée en paramètre
     * @param reference identifiant du livre recherché
     * @return un objet Book représentant le livre recherché
     */
    public Book getBook( String reference );

    /**
     * Méthode permettant de mettre à jours un livre enregistré
     * @param reference identifiant du livre à mettre à jours
     * @param title nouveau titre du livre
     * @param authors nouvelle liste d'auteurs
     * @param status nouveau status du livre
     * @return true si le livre existe et la mise à jours a été faite, false sinon
     */
    public boolean updateBook( String reference, String title, String authors, char status);
}