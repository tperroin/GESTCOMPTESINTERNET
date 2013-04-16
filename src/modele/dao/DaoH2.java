package modele.dao;

/**
 *
 * @author Thibault
 */
public class DaoH2 extends Dao{

    /**
     * 
     * Constructeur de la classe DaoH2 permettant la connexion à la base de données.
     *
     * @param nomBd
     *          Le nom de la base de données.
     * @param loginBd
     *          L'identifiant de l'utilisateur de la base de données.
     * @param mdpBd
     *          Le mot de passe de l'utilisateur de la base de données.
     */
    public DaoH2(String nomBd, String loginBd, String mdpBd) {
        super("org.h2.Driver", "jdbc:h2:~/" + nomBd, loginBd, mdpBd);
    }
    
}
