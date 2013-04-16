package controleurs;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metier.Compta;
import metier.Enregistrement;
import modele.dao.DaoException;
import modele.dao.DaoH2;
import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;
import vues.VueAccueil;
import vues.VueAfficherCompteCIO;
import vues.VueArchivageCIO;
import vues.VueArchivageCM;

/**
 * 
 * Permet de gérer les données et les actions pour la vue VueArchivageCM. 
 * C'est une classe fille de Controleur.
 * Elle a donc une vue et un controleur propre.
 *
 * @author Perroin Thibault
 */
public class CtrlArchivageCM extends Controleur {

    private VueArchivageCM vueArchivageCM = null;
    private DaoH2 dao = null;
    
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAfficherCompteCM ctrlAfficherCompteCM;
    CtrlArchivageCIO ctrlArchivageCIO;
    CtrlArchivageCM ctrlArchivageCM;
    CtrlImpression ctrlImpression;

    /**
     * Le constructeur du controleur de la vue VueArchivageCIO.
     *
     * @param ctrl
     *          Le controleur à appeler.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public CtrlArchivageCM(Controleur ctrl) {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueArchivageCM(this));
            this.afficherVue();
            
            chargerListeCompta("%MUTUEL%");
            
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(vueArchivageCM, "CtrlArchivageCIO - instanciation - " + ex.getMessage(), "Accueil", JOptionPane.ERROR_MESSAGE);
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
    
    /**
     * 
     * Permet d'archiver des enregistrements sous format CSV en fonction d'une année et d'une comptabilité.
     *
     * @param annee
     *          L'année à archiver.
     * @param idCompte
     *          La comptabilité à archiver.
     * @throws DaoException
     */
    public void sauvegarder(int annee, int idCompte) throws DaoException{
        
        List<Enregistrement> desEnregistrements = dao.lireTousLesEnregistrements(idCompte, "%"+String.valueOf(annee) +"%");
        
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("ID", Types.BIGINT, 19, 0);
        rs.addColumn("LIBELLE", Types.VARCHAR, 255, 0);
        rs.addColumn("MODE DE REGLEMENT", Types.VARCHAR, 255, 0);
        rs.addColumn("MOTIF", Types.VARCHAR, 255, 0);
        rs.addColumn("ETAT", Types.VARCHAR, 255, 0);
        rs.addColumn("RECETTE ou DEPENSE", Types.VARCHAR, 255, 0);
        rs.addColumn("DATE ENREGISTREMENT", Types.VARCHAR, 255, 0);
        rs.addColumn("MONTANT", Types.VARCHAR, 255, 0);
        rs.addColumn("ANCIEN SOLDE", Types.DECIMAL, 255, 0);
        rs.addColumn("NOUVEAU SOLDE", Types.DECIMAL, 255, 0);
        rs.addColumn("DATE FACTURE", Types.VARCHAR, 255, 0);
        rs.addColumn("NUMCHQ", Types.VARCHAR, 255, 0);
        rs.addColumn("ANTICIPATION", Types.BOOLEAN, 1, 0);
        rs.addColumn("ORDRE", Types.INTEGER, 10, 0);
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            rs.addRow(unEnregistrement.getId(),unEnregistrement.getIdLibelle(),
                    unEnregistrement.getModeReglement(),unEnregistrement.getMotif(),
                    unEnregistrement.getIdEtat(),unEnregistrement.getRecetteDepense(),
                    unEnregistrement.getDate(),unEnregistrement.getMontant(),unEnregistrement.getAncienSolde(),
                    unEnregistrement.getNouveauSolde(),unEnregistrement.getDateFacture(),unEnregistrement.getNumCHQ()
                    ,unEnregistrement.getAnticipation(),unEnregistrement.getOrdre());
        }
        
        
        try {
            Csv csv = new Csv();
            
            csv.setFieldSeparatorWrite(";");
            
            csv.write("sauvegarde\\"+String.valueOf(((VueArchivageCM)vue).getjYearChooser1().getYear()) + "\\"+((VueArchivageCM)vue).getWebComboBoxCompta().getSelectedItem().toString()+"\\saveCM.csv", rs, null);
        } catch (SQLException ex) {
            Logger.getLogger(VueArchivageCIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * Permet de charger le model de la combobox JComboboxCompta des comptabilités de la base de données en fonction de leur banque.
     *
     * @param banque
     *          La banque des comptabilité.
     * @throws DaoException
     */
    public void chargerListeCompta(String banque) throws DaoException {
        List<Compta> desComptas = dao.lireToutesLesComptas(banque);
        for (Compta uneCompta : desComptas) {
            ((VueArchivageCM)vue).getModeleComboboxCompta().addElement(uneCompta);
        }
    }
    
}
