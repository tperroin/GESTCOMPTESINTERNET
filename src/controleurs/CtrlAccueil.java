package controleurs;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoH2;
import vues.VueAccueil;

/**
 * 
 * Permet de gérer les données et les actions pour la vue VueAccueil. 
 * C'est une classe fille de Controleur.
 * Elle a donc une vue et un controleur propre.
 *
 * @author Perroin Thibault
 */
public class CtrlAccueil extends Controleur {

    private VueAccueil vueAccueil = null;
    private DaoH2 dao = null;
    
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAfficherCompteCM ctrlAfficherCompteCM;
    CtrlArchivageCIO ctrlArchivageCIO;
    CtrlArchivageCM ctrlArchivageCM;
    CtrlImpression ctrlImpression;

    /**
     * 
     * Constructeur du contrôleur CtrlAccueil.
     * Il définit sa vue et son controleur qui est lui même.
     *
     * @param ctrl
     *          Le controleur à charger.
     */
    public CtrlAccueil(Controleur ctrl) {
        
        super(ctrl);
        
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            
            dao.connecter();
            
            // initialiser l'interface graphique
            setVue(new VueAccueil(this));
            this.afficherVue();
            
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(vueAccueil, "CtrAccueil - instanciation - " + ex.getMessage(), "Accueil", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    
    /**
     * 
     * Permet d'afficher la vue VueAjouterEnregistrement.
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void afficherAjouterEnregistrement() throws DaoException{
        if (ctrlAjouterEnregistrement == null){
            ctrlAjouterEnregistrement = new CtrlAjouterEnregistrement(this);
        }else{
            ctrlAjouterEnregistrement.afficherVue();
        }
        this.cacherVue();
    }
    
    /**
     * Permet d'afficher la vue VueAfficherCompteCIO.
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void afficherAfficherCompteCIO() throws DaoException{
        if (ctrlAfficherCompteCIO == null){
            ctrlAfficherCompteCIO = new CtrlAfficherCompteCIO(this);
        }else{
            ctrlAfficherCompteCIO.afficherVue();
        }
        this.cacherVue();
    }
    
    /**
     * 
     * Permet d'afficher la vue VueAfficherCompteCM.
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void afficherAfficherCompteCM() throws DaoException{
        if (ctrlAfficherCompteCM == null){
            ctrlAfficherCompteCM = new CtrlAfficherCompteCM(this);
        }else{
            ctrlAfficherCompteCM.afficherVue();
        }
        this.cacherVue();
    }
    
    /**
     * 
     * Permet d'afficher la vue VueArchivageCIO
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void afficherArchivageCIO() throws DaoException{
        if (ctrlArchivageCIO == null){
            ctrlArchivageCIO = new CtrlArchivageCIO(this);
        }else{
            ctrlArchivageCIO.afficherVue();
        }
        this.cacherVue();
    }
    
    /**
     * 
     * Permet d'afficher la vue VueArchivageCM.
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void afficherArchivageCM() throws DaoException{
        if (ctrlArchivageCM == null){
            ctrlArchivageCM = new CtrlArchivageCM(this);
        }else{
            ctrlArchivageCM.afficherVue();
        }
        this.cacherVue();
    }
    /**
     * 
     * Permet d'afficher la vue VueImpression
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void afficherImpression() throws DaoException{
        if (ctrlImpression == null){
            ctrlImpression = new CtrlImpression(this);
        }else{
            ctrlImpression.afficherVue();
        }
        this.cacherVue();
    }
}
