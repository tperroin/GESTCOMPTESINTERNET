/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package controleurs;

import com.alee.laf.table.renderers.WebDefaultTableCellRenderer;
import com.alee.utils.swing.WebDefaultCellEditor;
import modele.jtable.MyTableCellRenderer;
import modele.jtable.TableColumnAdjuster;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import metier.Enregistrement;
import metier.Libelle;
import modele.dao.DaoException;
import modele.dao.DaoH2;
import modele.jtable.ButtonColumn;
import modele.jtable.ModelTableCIO;
import vues.VueAfficherCompteCIO;
import vues.VueAjouterEnregistrement;
import modele.jtable.ImageRenderer;
import modele.jtable.TableCellListener;


/**
 *
 * @author btssio
 */
public class CtrlAfficherCompteCIO extends Controleur {
    
    DaoH2 dao = null;
    
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAfficherCompteCM ctrlAfficherCompteCM;
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    CtrlArchivageCIO ctrlArchivageCIO;
    CtrlArchivageCM ctrlArchivageCM;
    
    public CtrlAfficherCompteCIO(Controleur ctrl) throws DaoException {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueAfficherCompteCIO(this));
            this.afficherVue();
            chargerSoldeCompteCIO();
            afficherColonnes();
            
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlAfficherCompteCIO - instanciation - " + ex.getMessage(), "Afficher", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void affichertAnnuler() {
        
        this.getCtrl().afficherVue();
        this.cacherVue();
        
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
    
    public void afficherColonnes(){
        
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Déplacer");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Date Enregistrement");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Libellé");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Motif");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Date facture");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Mode de règlement");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Numéro de chèque");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Montant");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Solde");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Anticipation");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Modifier");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("RecDep");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Supprimer");
    }
    
    public void chargerEnregistrement(int annee) throws DaoException {
        
        
        List<Enregistrement> desEnregistrements = dao.lireTousLesEnregistrements(1, "%"+String.valueOf(annee) +"%");
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{"",unEnregistrement.getDate(), unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), Boolean.parseBoolean(unEnregistrement.getAnticipation()), "M", unEnregistrement.getRecetteDepense(), "X"});   
        }
        
        aspectJtable();
        
    }
    
    public void chargerEnregistrementRecpDepAnt(Integer id, String recDep, String anticipation) throws DaoException {
           
        List<Enregistrement> desEnregistrements = dao.lireEnregistrementsRecDepAnt(id, recDep, anticipation);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
             ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{unEnregistrement.getDate(), unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(), Boolean.parseBoolean(unEnregistrement.getAnticipation()), "M", unEnregistrement.getRecetteDepense(), "X"});   
        }
        
        aspectJtable();
        
    }
        
    public void chargerEnregistrementAnt(Integer id, String anticipation) throws DaoException {
           
        List<Enregistrement> desEnregistrements = dao.lireEnregistrementsAnt(id, anticipation);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
             ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{unEnregistrement.getDate(), unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(), Boolean.parseBoolean(unEnregistrement.getAnticipation()), "M", unEnregistrement.getRecetteDepense(), "X"});   
        }
        
        aspectJtable();
        
    }
    
    Action supprimer = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            int rep = JOptionPane.showConfirmDialog(((VueAfficherCompteCIO)vue), "Supprimer l'enregistrement\nEtes-vous sûr(e) ?", "Affichage des enregistrements", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            JTable table = (JTable)e.getSource();
            int modelRow = Integer.valueOf( e.getActionCommand() );
            ((ModelTableCIO)table.getModel()).removeRow(modelRow);
            int nbEnregistrement = 0;
            try {
                dao.supprimerEnregistrementFromOrdre(modelRow + 1, 1);
                nbEnregistrement = dao.compterNbEnregistrement();
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i;
            int j = modelRow;
            for(i=modelRow+1;i<=nbEnregistrement;i++){
                    
                try {  
                    BigDecimal nouveauSolde = null;
                    BigDecimal ancienSolde = null;
                    BigDecimal bigDecimalMontant = null;
                    
                    bigDecimalMontant = new BigDecimal(dao.lireMontantFromOrdre(i+1, 1));
                    ancienSolde = new BigDecimal(dao.lireNouveauSoldeFromOrdre(i-1, 1));
                    
                    if(dao.verifierRecDep(i+1, 1) == true){
                        nouveauSolde = ancienSolde.subtract(bigDecimalMontant);
                    }else{
                        nouveauSolde = bigDecimalMontant.add(ancienSolde);
                    }
                    
                    Integer idEnr = dao.lireIdEnrFromOrdreCompte(i+1, 1);
                    
                    dao.mettreAJourOrdreInfSup(i,i+1 , idEnr, 1);
                    dao.mettreAJourLesSoldes(nouveauSolde, ancienSolde, 1, i);
                    
                } catch (DaoException ex) {
                    Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
                }
        }
        }
    };
    
    
    
    Action modifier = new AbstractAction(){
        
        public void actionPerformed(ActionEvent e){
            
            int modelRow = Integer.valueOf( e.getActionCommand() );
            
            JTable table = ((VueAfficherCompteCIO)vue).getjTableCIO();
                        
            Object date = table.getValueAt(modelRow, 0);
            Object libelle = table.getValueAt(modelRow, 1);
            Object motif = table.getValueAt(modelRow, 2);
            Object dateFacture = table.getValueAt(modelRow, 3);
            Object modeReglement = table.getValueAt(modelRow, 4);
            Object numchq = table.getValueAt(modelRow, 5);
            Object montant = table.getValueAt(modelRow, 6);
            Object etat = table.getValueAt(modelRow, 8);
            Object anticipation = table.getValueAt(modelRow, 9);
            Object recdep = table.getValueAt(modelRow, 11);
            
            Integer idLibelle = null;
            Integer idModeReglement = null;
            Integer idEtat = null;
            Integer idMotif = null;
            BigDecimal bigDecimalMontant= null;
            BigDecimal ancienSolde = null;
            
            int nbEnregistrement = 0;
            
            try {
                
                idLibelle = dao.lireIdLibelleFromLibelle(libelle.toString());
                idModeReglement = dao.lireIdModeReglementFromLibelle(modeReglement.toString());
                idEtat = dao.lireIdEtatFromLibelle(etat.toString());
                idMotif = dao.lireIdMotifFromLibelle(motif.toString());
                bigDecimalMontant = new BigDecimal(montant.toString());
                ancienSolde = new BigDecimal(dao.lireNouveauSoldeFromOrdre(modelRow, 1));
                nbEnregistrement = dao.compterNbEnregistrement();
                
                
                if(idLibelle == null){
                    Integer dernierIdLibelle = dao.recupererDernierIdLibelle() + 1;
                    dao.ajouterLibelle(dernierIdLibelle, libelle.toString());
                    idLibelle = dao.lireIdLibelleFromLibelle(libelle.toString());
                }
                                
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                
               
                BigDecimal ancienMontant = new BigDecimal(dao.lireMontantFromOrdre(modelRow+1, 1)).setScale(2, RoundingMode.FLOOR);
                BigDecimal nouveauMontant = bigDecimalMontant.setScale(2);
                    
                    if(ancienMontant.compareTo(nouveauMontant) == 1){
                        dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, date.toString(), bigDecimalMontant, new BigDecimal(dao.lireNouveauSoldeFromOrdre(modelRow+1, 1)), dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()), numchq.toString(), 1);
                    }else{
                        if("Dépense".equals(recdep.toString())){
                            dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, date.toString(), bigDecimalMontant, ancienSolde.subtract(bigDecimalMontant), dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()),numchq.toString(), 1);
           
                        }else{

                            dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, date.toString(), bigDecimalMontant, ancienSolde.add(bigDecimalMontant), dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()), numchq.toString(), 1);
                         }
                        
                    }
                        
                    
                    
            } catch (DaoException ex) {
                
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            int i;
            
            for(i=modelRow+1; i<nbEnregistrement; i++){
                
                try {
                    
                    
                    bigDecimalMontant = new BigDecimal(dao.lireMontantFromOrdre(i+1, 1));
                    ancienSolde = new BigDecimal(dao.lireNouveauSoldeFromOrdre(i, 1));
                    BigDecimal nouveauSolde = null;
                    
                    if(dao.verifierRecDep(i+1, 1) == true){
                        nouveauSolde = ancienSolde.subtract(bigDecimalMontant);
                    }else{
                        nouveauSolde = bigDecimalMontant.add(ancienSolde);
                    }
                    
                    dao.mettreAJourLesSoldes(nouveauSolde, ancienSolde, 1, i+1);
                    
                } catch (DaoException ex) {
                    Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                dao.mettreAJourSoldeCompte(1, new BigDecimal(dao.recupererDernierSolde(1)));
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public void aspectJtable(){
        
            JTable table = ((VueAfficherCompteCIO)vue).getjTableCIO();
            
            table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
                   
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumnAdjuster tca = new TableColumnAdjuster(table);
            tca.adjustColumns();
            
            table.getColumnModel().getColumn(11).setMinWidth(0);
            table.getColumnModel().getColumn(11).setMaxWidth(0);
            
                    
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
            table.setRowSorter(sorter);
            
            Action delete = null;
            ButtonColumn boutonModifier = new ButtonColumn(table, modifier, 10);
            ButtonColumn boutonAnnuler = new ButtonColumn(table, supprimer, 12);
            
            TableColumn tcolumnas = table.getColumnModel().getColumn(9); 
            tcolumnas.setCellRenderer(table.getDefaultRenderer(Boolean.class)); 
            tcolumnas.setCellEditor(table.getDefaultEditor(Boolean.class));
            
            table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
            
            TableCellListener tcl = new TableCellListener(table, action);
                
    }

Action action = new AbstractAction()
{
    public void actionPerformed(ActionEvent e)
    {
        TableCellListener tcl = (TableCellListener)e.getSource();
        System.out.println("Row   : " + tcl.getRow());
        System.out.println("Column: " + tcl.getColumn());
        System.out.println("Old   : " + tcl.getOldValue());
        System.out.println("New   : " + tcl.getNewValue());
    }
};



    public void chargerSoldeCompteCIO() throws DaoException{
        
        Float soldeCIO = dao.lireSoldeCompte(1);
        Float soldeCIOSansAnticipation = dao.lireSoldeCompteSansAnticipation(1);
        
        ((VueAfficherCompteCIO)vue).getjTextFieldSoldeCompteCIO().setText(soldeCIO.toString());
        
        ((VueAfficherCompteCIO)vue).getjTextFieldSoldeCompteCIOSansAnticipation().setText(soldeCIOSansAnticipation.toString());
        
        
    }
    
    public void viderJtableModel(){
        
        DefaultTableModel model = ((VueAfficherCompteCIO)vue).getModeleJTableCIO();
        
        for(int i = model.getRowCount(); i > 0; --i)
            model.removeRow(i-1);
        
        model.getDataVector().removeAllElements();
    }
    

}
