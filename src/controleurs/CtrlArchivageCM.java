/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
 * @author btssio
 */
public class CtrlArchivageCM extends Controleur {

    private VueArchivageCM vueArchivageCM = null;
    private DaoH2 dao = null;
    
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAfficherCompteCM ctrlAfficherCompteCM;
    CtrlArchivageCIO ctrlArchivageCIO;
    CtrlArchivageCM ctrlArchivageCM;

    public CtrlArchivageCM(Controleur ctrl) {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueArchivageCM(this));
            this.afficherVue();
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(vueArchivageCM, "CtrlArchivageCIO - instanciation - " + ex.getMessage(), "Accueil", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public void afficherAjouterEnregistrement() throws DaoException{
        if (ctrlAjouterEnregistrement == null){
            ctrlAjouterEnregistrement = new CtrlAjouterEnregistrement(this);
        }else{
            ctrlAjouterEnregistrement.afficherVue();
        }
        this.cacherVue();
    }
    
    public void afficherAfficherCompteCIO() throws DaoException{
        if (ctrlAfficherCompteCIO == null){
            ctrlAfficherCompteCIO = new CtrlAfficherCompteCIO(this);
        }else{
            ctrlAfficherCompteCIO.afficherVue();
        }
        this.cacherVue();
    }
    
    public void afficherAfficherCompteCM() throws DaoException{
        if (ctrlAfficherCompteCM == null){
            ctrlAfficherCompteCM = new CtrlAfficherCompteCM(this);
        }else{
            ctrlAfficherCompteCM.afficherVue();
        }
        this.cacherVue();
    }
    
    public void afficherArchivageCIO() throws DaoException{
        if (ctrlArchivageCIO == null){
            ctrlArchivageCIO = new CtrlArchivageCIO(this);
        }else{
            ctrlArchivageCIO.afficherVue();
        }
        this.cacherVue();
    }
    
    public void afficherArchivageCM() throws DaoException{
        if (ctrlArchivageCM == null){
            ctrlArchivageCM = new CtrlArchivageCM(this);
        }else{
            ctrlArchivageCM.afficherVue();
        }
        this.cacherVue();
    }
    
    public void sauvegarder(int annee) throws DaoException{
        
        List<Enregistrement> desEnregistrements = dao.lireTousLesEnregistrements(2, "%"+String.valueOf(annee) +"%");
        
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
            new Csv().write("sauvegarde\\"+String.valueOf(((VueArchivageCIO)vue).getjYearChooser1().getYear())+"\\saveCM.csv", rs, null);
        } catch (SQLException ex) {
            Logger.getLogger(VueArchivageCIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
