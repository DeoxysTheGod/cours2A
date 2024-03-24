package net.uzaki.user;

/**
 * Classe représentant le cas d'utilisation "authentifier un utilisateur"
 */
public class UserAuthenticationService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UserRepositoryInterface userRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param userRepo objet implémentant l'interface d'accès aux données
     */
    public UserAuthenticationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Méthode d'authentifier un utilisateur
     * @param id identifiant de l'utilisateur
     * @param pwd mot de passe de l'utilisateur
     * @return true si l'utilisateur a été authentifié, false sinon
     */
    public boolean isValidUser( String id, String pwd){

        User currentUser = userRepo.getUser( id );

        System.out.println(currentUser);

        // si l'utilisateur n'a pas été trouvé
        if( currentUser == null )
            return false;

        // si le mot de passe n'est pas correcte
        return currentUser.pwd.equals(pwd);
    }
}