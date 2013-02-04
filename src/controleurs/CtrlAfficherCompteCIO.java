/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package controleurs;

import modele.jtable.MyTableCellRenderer;
import modele.jtable.TableColumnAdjuster;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import metier.Compte;
import metier.Enregistrement;
import metier.Etat;
import metier.ModeReglement;
import metier.Motif;
import modele.dao.DaoException;
import modele.dao.DaoH2;
import modele.jtable.ButtonColumn;
import modele.jtable.ModelTable;
import vues.VueAfficherCompteCIO;
import vues.VueAjouterEnregistrement;


/**
 *
 * @author btssio
 */
public class CtrlAfficherCompteCIO extends Controleur {
    
    DaoH2 dao = null;
    
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    
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
    
    public void afficherColonnes(){
        
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Date Enregistrement");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Libellé");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Motif");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Date facture");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Mode de règlement");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Montant");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Solde");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Etat");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Anticipation");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Modifier");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("RecDep");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Supprimer");
    }
    
    public void chargerEnregistrement(int annee) throws DaoException {
        
        
        List<Enregistrement> desEnregistrements = dao.lireTousLesEnregistrements(1, "%"+String.valueOf(annee) +"%");
        
        
        
        
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{unEnregistrement.getDate(), unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(), Boolean.parseBoolean(unEnregistrement.getAnticipation()), "M", unEnregistrement.getRecetteDepense(), "X"});   
        }
        
        aspectJtable();
        
    }
    
    public void chargerEnregistrementRecpDepAnt(Integer id, String recDep, String anticipation) throws DaoException {
           
        List<Enregistrement> desEnregistrements = dao.lireEnregistrementsRecDepAnt(id, recDep, anticipation);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
             ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{unEnregistrement.getDate(), unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(), Boolean.parseBoolean(unEnregistrement.getAnticipation()), "M", unEnregistrement.getRecetteDepense()});   
        }
        
        aspectJtable();
        
    }
        
    public void chargerEnregistrementAnt(Integer id, String anticipation) throws DaoException {
           
        List<Enregistrement> desEnregistrements = dao.lireEnregistrementsAnt(id, anticipation);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
             ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{unEnregistrement.getDate(), unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(), Boolean.parseBoolean(unEnregistrement.getAnticipation()), "M", unEnregistrement.getRecetteDepense()});   
        }
        
        aspectJtable();
        
    }
    
    Action supprimer = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            int rep = JOptionPane.showConfirmDialog(((VueAfficherCompteCIO)vue), "Supprimer l'enregistrement\nEtes-vous sûr(e) ?", "Affichage des enregistrements", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            JTable table = (JTable)e.getSource();
            int modelRow = Integer.valueOf( e.getActionCommand() );
            ((ModelTable)table.getModel()).removeRow(modelRow);
            int nbEnregistrement = 0;
            try {
                dao.supprimerEnregistrementFromOrdre(modelRow + 1);
                nbEnregistrement = dao.compterNbEnregistrement();
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i;
            int j = modelRow;
            for(i=modelRow+1;i<=nbEnregistrement;i++){
                    
                try {  
                    Float nouveauSolde = null;
                    Float ancienSolde = null;
                    Float floatMontant = null;
                    
                    floatMontant = dao.lireMontantFromOrdre(i+1);
                    ancienSolde = dao.lireNouveauSoldeFromOrdre(i-1);
                    
                    if(dao.verifierRecDep(i+1) == true){
                        nouveauSolde = ancienSolde - floatMontant;
                    }else{
                        nouveauSolde = floatMontant + ancienSolde;
                    }
                    
                    Integer idEnr = dao.lireIdEnrFromOrdre(i+1);
                    
                    dao.mettreAJourOrdreInfSup(i,i+1 , idEnr);
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
            Object montant = table.getValueAt(modelRow, 5);
            Object solde = table.getValueAt(modelRow, 6);
            Object etat = table.getValueAt(modelRow, 7);
            Object anticipation = table.getValueAt(modelRow, 8);
            Object recdep = table.getValueAt(modelRow, 10);
            
            Integer idLibelle = null;
            Integer idModeReglement = null;
            Integer idEtat = null;
            Integer idMotif = null;
            Float floatMontant= null;
            Float ancienSolde = null;
            
            int nbEnregistrement = 0;
            
            try {
                idLibelle = dao.lireIdLibelleFromLibelle(libelle.toString());
                idModeReglement = dao.lireIdModeReglementFromLibelle(modeReglement.toString());
                idEtat = dao.lireIdEtatFromLibelle(etat.toString());
                idMotif = dao.lireIdMotifFromLibelle(motif.toString());
                floatMontant = Float.parseFloat(montant.toString());
                ancienSolde = dao.lireNouveauSoldeFromOrdre(modelRow);
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
                if("Dépense".equals(recdep.toString())){
                    dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, date.toString(), floatMontant, ancienSolde-floatMontant, dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()));
           
                }else{
                    dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, date.toString(), floatMontant, ancienSolde+floatMontant, dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()));
           
                }
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i;
            
            for(i=modelRow+1; i<nbEnregistrement; i++){
                
                try {
                    
                    
                    floatMontant = dao.lireMontantFromOrdre(i+1);
                    ancienSolde = dao.lireNouveauSoldeFromOrdre(i);
                    Float nouveauSolde = null;
                    
                    if(dao.verifierRecDep(i+1) == true){
                        nouveauSolde = ancienSolde - floatMontant;
                    }else{
                        nouveauSolde = floatMontant + ancienSolde;
                    }
                    
                    dao.mettreAJourLesSoldes(nouveauSolde, ancienSolde, 1, i+1);
                } catch (DaoException ex) {
                    Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                dao.mettreAJourSoldeCompte(1, dao.recupererDernierSolde());
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
        
        table.getColumnModel().getColumn(10).setMinWidth(0);
        table.getColumnModel().getColumn(10).setMaxWidth(0);
        
                
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        
        Action delete = null;
        ButtonColumn boutonModifier = new ButtonColumn(table, modifier, 9);
        ButtonColumn boutonAnnuler = new ButtonColumn(table, supprimer, 11);
        
        TableColumn tcolumnas = table.getColumnModel().getColumn(8); 
        tcolumnas.setCellRenderer(table.getDefaultRenderer(Boolean.class)); 
        tcolumnas.setCellEditor(table.getDefaultEditor(Boolean.class));
        
       
                
    }

    

    
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
