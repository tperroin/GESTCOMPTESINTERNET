package controleurs;

import com.alee.utils.swing.WebDefaultCellEditor;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import metier.Compta;
import metier.Enregistrement;
import metier.Etat;
import metier.Libelle;
import metier.ModeReglement;
import metier.Motif;
import modele.dao.DaoException;
import modele.dao.DaoH2;
import modele.jtable.ButtonColumn;
import modele.jtable.ImageRenderer;
import modele.jtable.MyTableCellRenderer;
import modele.jtable.MyTableCellRendererImpression;
import modele.jtable.TableCellListener;
import modele.jtable.TableColumnAdjuster;
import vues.VueAccueil;
import vues.VueAfficherCompteCM;
import vues.VueImpression;

/**
 * 
 * Permet de gérer les données et les actions pour la vue VueImpression. 
 * C'est une classe fille de Controleur.
 * Elle a donc une vue et un controleur propre.
 *
 * @author Perroin Thibault
 */
public class CtrlImpression extends Controleur {

    private VueAccueil vueAccueil = null;
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
    public CtrlImpression(Controleur ctrl) {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueImpression(this));
            this.afficherVue();
            chargerListeCompta("%");
            afficherColonnes();
            
            
            
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(vueAccueil, "CtrlImpression - instanciation - " + ex.getMessage(), "Impression", JOptionPane.ERROR_MESSAGE);
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
     * Permet de charger le model de la combobox JComboboxCompta des comptabilités de la base de données en fonction de leur banque.
     *
     * @param banque
     *          La banque des comptabilité.
     * @throws DaoException
     */
    public void chargerListeCompta(String banque) throws DaoException {
        List<Compta> desComptas = dao.lireToutesLesComptas(banque);
        for (Compta uneCompta : desComptas) {
            ((VueImpression)vue).getModeleComboboxCompta().addElement(uneCompta);
        }
    }
    
    /**
     * 
     * Permet d'afficher les colonnes de la JTable des comptabilités.
     *
     */
    public void afficherColonnes(){
        
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Date enregistrement");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Libellé");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Motif");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Date facture");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Mode de règlement");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Numéro de chèque");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Montant");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Solde");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Etat");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("Anticipation");
        ((VueImpression)vue).getModeleJTableImpression().addColumn("RecDep");
        
    }
    
    /**
     * 
     * Permet de charger les enregistrements bancaires en fonction d'une date de début, d'une date de fin et d'une comptabilité.
     *
     * @param dateDebut
     *          L'annee de début que l'on souhaite charger.
     * @param dateFin
     *          L'annee de fin que l'on souhaite charger.
     * @param idCompte
     *          L'id du compte que l'on souhaite charger.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     *          
     */
    public void chargerEnregistrementImpression(Date dateDebut, Date dateFin, int idCompte) throws DaoException {
        
        
        List<Enregistrement> desEnregistrements = dao.lireTousLesEnregistrementsImpression(idCompte, dateDebut.toString(), dateFin.toString());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            
            String date = dateFormat.format(unEnregistrement.getDate());
            
            ((VueImpression)vue).getModeleJTableImpression().addRow(new Object[]{date, unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(),Boolean.parseBoolean(unEnregistrement.getAnticipation()),unEnregistrement.getRecetteDepense()});   
        }
        
        aspectJtable();
        
    }
    
    /**
     *
     * Permet d'affecter un aspect à la JTable et de définir les actions sur les différents boutons qu'elle contient.
     */
    
    public void aspectJtable(){
        
        JTable table = ((VueImpression)vue).getjTableImpression();
            
        table.setDefaultRenderer(Object.class, new MyTableCellRendererImpression());
                   
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(table);
        tca.adjustColumns();

        table.getColumnModel().getColumn(10).setMinWidth(0);
        table.getColumnModel().getColumn(10).setMaxWidth(0);
        
        table.getColumnModel().getColumn(9).setMinWidth(0);
        table.getColumnModel().getColumn(9).setMaxWidth(0);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        
                            
    }
    
    /**
     * 
     * Permet de vider la JTable.
     *
     */
    public void viderJtableModel(){
        
        DefaultTableModel model = ((VueImpression)vue).getModeleJTableImpression();
        
        for(int i = model.getRowCount(); i > 0; --i)
            model.removeRow(i-1);
        
        model.getDataVector().removeAllElements();
    }
    
    
    /**
     * 
     * Permet d'imprimer les enregistrements en fonction d'ue date de début et de fin et d'une comptabilité.
     * Le solde se calcul en fonction de ces dates.
     *
     * @param idCompte
     *          L'identifiant de la comptabilité sélectionnée.
     * @param dateDebut
     *          L'annee de début que l'on souhaite charger.
     * @param dateFin
     *          L'annee de fin que l'on souhaite charger.
     * @throws DaoException
     */
    public void imprimer(Integer idCompte, java.sql.Date dateDebut, java.sql.Date dateFin) throws DaoException{
        try {
                SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
                String solde = new DecimalFormat("#,###.##").format(dao.lireSoldeCompteImpression(idCompte, dateDebut, dateFin));
                MessageFormat headerFormat = new MessageFormat("Solde : " + solde);
                MessageFormat footerFormat = new MessageFormat("Solde du " + df.format(dateDebut) + " au " + df.format(dateFin));
                ((VueImpression)vue).getjTableImpression().print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
              System.err.println("Erreur d'impression : " + pe.getMessage());
            }
    }
}