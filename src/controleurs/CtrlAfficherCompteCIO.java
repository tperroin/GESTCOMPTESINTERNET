package controleurs;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.table.renderers.WebDefaultTableCellRenderer;
import com.alee.utils.swing.WebDefaultCellEditor;
import modele.jtable.MyTableCellRenderer;
import modele.jtable.TableColumnAdjuster;
import java.awt.event.ActionEvent;
import java.awt.print.Printable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.TransferHandler;
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
import modele.jtable.ModelTableCIO;
import vues.VueAfficherCompteCIO;
import vues.VueAjouterEnregistrement;
import modele.jtable.ImageRenderer;
import modele.jtable.TableCellListener;


/**
 * 
 * Permet de gérer les données et les actions pour la vue VueAfficherCompteCIO. 
 * C'est une classe fille de Controleur.
 * Elle a donc une vue et un controleur propre.
 *
 * @author Perroin Thibault
 */

public class CtrlAfficherCompteCIO extends Controleur {
    
    DaoH2 dao = null;
    
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAfficherCompteCM ctrlAfficherCompteCM;
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    CtrlArchivageCIO ctrlArchivageCIO;
    CtrlArchivageCM ctrlArchivageCM;
    CtrlImpression ctrlImpression;
    
    /**
     * Le constructeur du controleur de la vue VueAfficherCompteCM
     *
     * @param ctrl
     *          Le controleur à appeler.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public CtrlAfficherCompteCIO(Controleur ctrl) throws DaoException {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueAfficherCompteCIO(this));
            this.afficherVue();
            afficherColonnes();
            chargerListeCompta("%CIC%");
            chargerSoldeCompteCIO();
            
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlAfficherCompteCIO - instanciation - " + ex.getMessage(), "Afficher", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Permet de cacher la vue courante.
     *
     */
    public void affichertAnnuler() {
        
        this.getCtrl().afficherVue();
        this.cacherVue();
        
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
     * Permet d'afficher les colonnes de la JTable des comptes du CIC Ouest.
     *
     */
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
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Etat");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Anticipation");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("RecDep");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("Supprimer");
        ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addColumn("idCompte");
    }
    
    /**
     * 
     * Permet de charger les enregistrements bancaires en fonction d'une annee et d'un compte.
     *
     * @param annee
     *          L'annee que l'on souhaite charger.
     * @param idCompte
     *          L'id du compte que l'on souhaite charger.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     *          
     */
    public void chargerEnregistrement(int annee, int idCompte) throws DaoException {
        
       
        List<Enregistrement> desEnregistrements = dao.lireTousLesEnregistrements(idCompte, "%"+String.valueOf(annee) +"%");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            String date = dateFormat.format(unEnregistrement.getDate());
            ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{"",date, unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(),Boolean.parseBoolean(unEnregistrement.getAnticipation()), unEnregistrement.getRecetteDepense(), "X", unEnregistrement.getIdCompte()});   
        }
        
        aspectJtable();
        
    }
    
     /**
     *
     * Permet de charger la JCombobox avec les comptabilités en fonction d'une banque.
     * 
     * @param banque
     *          Nom de la banque permettant de charger les comptabilités.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void chargerListeCompta(String banque) throws DaoException {
        List<Compta> desComptas = dao.lireToutesLesComptas(banque);
        for (Compta uneCompta : desComptas) {
            ((VueAfficherCompteCIO)vue).getModeleComboboxCompta().addElement(uneCompta);
        }
    }
    
    /**
     * 
     * Permet de charger les enregistrements en fonction dl'identifiant du compte, de l'année
     * et de trois critères :
     *          - enregistrements si c'est une recette
     *          - enregistrements si c'est une dépense
     *          - enregistrements si c'est une anticipation
     *
     * @param id
     *          L'identifiant du compte interrogé.
     * @param recDep
     *          Prend pour valeur une "Recette" ou une "Dépense".
     * @param anticipation
     *          Renvoi VRAI ou FAUX en fonction du critère choisi.
     * @param annee
     *          Année des enregistrements à charger.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void chargerEnregistrementRecpDepAnt(Integer id, String recDep, String anticipation, int annee) throws DaoException {
           
        List<Enregistrement> desEnregistrements = dao.lireEnregistrementsRecDepAnt(id, recDep, anticipation, "%"+String.valueOf(annee) +"%");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            String date = dateFormat.format(unEnregistrement.getDate());
           ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{"",date, unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(),Boolean.parseBoolean(unEnregistrement.getAnticipation()), unEnregistrement.getRecetteDepense(), "X", unEnregistrement.getIdCompte()});   
        }
        
        aspectJtable();
        
    }
        
    /**
     * 
     * Permet de charger les enregistrements anticipés en fonction d'un identifiant de compte et l'année.
     *
     * @param id
     *          L'identifiant du compte sélectionné.
     * @param anticipation
     *          Retourne VRAI ou FAUX en fonction du critère choisi.
     * @param annee
     *          Année des enregistrements à charger.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public void chargerEnregistrementAnt(Integer id, String anticipation, int annee) throws DaoException {
           
        List<Enregistrement> desEnregistrements = dao.lireEnregistrementsAnt(id, anticipation, "%"+String.valueOf(annee) +"%");
        
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            String date = dateFormat.format(unEnregistrement.getDate());
           ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{"",date, unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(),Boolean.parseBoolean(unEnregistrement.getAnticipation()), unEnregistrement.getRecetteDepense(), "X", unEnregistrement.getIdCompte()});   
       }
        
        aspectJtable();
        
    }
    
    /**
      * 
      * Permet de rechercher une valeur dans l'ensemble des comptes
     *
     * @param valeur
     *          La valeur recherchée.
     * @param idCompte
     *          Le compte dans lequel chercher la valeur.
     * @param annee
     *          L'année pour la recherche.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    
    
    public void rechercherValeurEnregistrement(String valeur, Integer idCompte, int annee) throws DaoException{
        List<Enregistrement> desEnregistrements = dao.rechercherValeurEnregistrement(valeur, idCompte, "%"+String.valueOf(annee) +"%");
        
        if(desEnregistrements.isEmpty()==true){
            WebOptionPane.showMessageDialog ( ((VueAfficherCompteCIO)vue), "La recherche : " + valeur + " n'a donnée aucun résultat", "Recherche", WebOptionPane.INFORMATION_MESSAGE );
            
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        
        viderJtableModel();
        
        for (Enregistrement unEnregistrement : desEnregistrements) {
            String date = dateFormat.format(unEnregistrement.getDate());
           ((VueAfficherCompteCIO)vue).getModeleJTableCIO().addRow(new Object[]{"",date, unEnregistrement.getIdLibelle(), unEnregistrement.getMotif(), unEnregistrement.getDateFacture(), unEnregistrement.getModeReglement(), unEnregistrement.getNumCHQ(), unEnregistrement.getMontant(), unEnregistrement.getNouveauSolde(), unEnregistrement.getIdEtat(),Boolean.parseBoolean(unEnregistrement.getAnticipation()), unEnregistrement.getRecetteDepense(), "X", unEnregistrement.getIdCompte()});   
        }
        
        aspectJtable();
    }
    
    /**
     * 
     * Permet de supprimer un enregistrement après le clique sur le bouton en fin de ligne d'un enregistrement.
     */
    
    Action supprimer = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            
            Compta unCompte = (Compta) ((VueAfficherCompteCIO)vue).getModeleComboboxCompta().getSelectedItem();
            Integer idCompte = unCompte.getId();
            
            int rep = JOptionPane.showConfirmDialog(((VueAfficherCompteCIO)vue), "Supprimer l'enregistrement\nEtes-vous sûr(e) ?", "Affichage des enregistrements", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            JTable table = (JTable)e.getSource();
            int modelRow = Integer.valueOf( e.getActionCommand() );
            ((ModelTableCIO)table.getModel()).removeRow(modelRow);
            int nbEnregistrement = 0;
            try {
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                dao.supprimerEnregistrementFromOrdre(modelRow + 1, idCompte, "%" + String.valueOf(annee) + "%");
                nbEnregistrement = dao.compterNbEnregistrement("%" + String.valueOf(annee) + "%", idCompte);
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i;
            int j = modelRow;
            for(i=modelRow+1;i<=nbEnregistrement;i++){
                    
                try {  
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                    BigDecimal nouveauSolde = null;
                    BigDecimal ancienSolde = null;
                    BigDecimal bigDecimalMontant = null;
                    
                    
                    bigDecimalMontant = dao.lireMontantFromOrdre(i+1, idCompte, "%" + String.valueOf(annee) + "%");
                    
                    ancienSolde = dao.lireNouveauSoldeFromOrdre(i-1, idCompte, "%" + String.valueOf(annee) + "%");
                    
                    if(dao.verifierRecDep(i+1, 1, "%" + String.valueOf(annee) + "%") == true){
                        nouveauSolde = ancienSolde.subtract(bigDecimalMontant);
                    }else{
                        nouveauSolde = bigDecimalMontant.add(ancienSolde);
                    }
                    
                    Integer idEnr = dao.lireIdEnrFromOrdreCompte(i+1, idCompte, "%" + String.valueOf(annee) + "%");
                    
                    dao.mettreAJourOrdreInfSup(i,i+1 , idEnr, 1, "%" + String.valueOf(annee) + "%");
                    dao.mettreAJourLesSoldes(nouveauSolde, ancienSolde, idCompte, i, "%" + String.valueOf(annee) + "%");
                    
                } catch (DaoException ex) {
                    Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
                }
            try {
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                dao.mettreAJourSoldeCompte(idCompte, dao.recupererDernierSolde("%" + String.valueOf(annee) + "%", idCompte));
                viderJtableModel();
                chargerEnregistrement(((VueAfficherCompteCIO)vue).getjYearChooser1().getYear(), idCompte);
                chargerSoldeCompteCIO();
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    };
    
    
     /**
     *
     * Permet d'affecter un aspect à la JTable et de définir les actions sur les différents boutons qu'elle contient.
     */
    
    public void aspectJtable(){
        
            JTable table = ((VueAfficherCompteCIO)vue).getjTableCIO();
            
            table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
                   
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumnAdjuster tca = new TableColumnAdjuster(table);
            tca.adjustColumns();
            
            table.getColumnModel().getColumn(11).setMinWidth(0);
            table.getColumnModel().getColumn(11).setMaxWidth(0);
            
            
            table.getColumnModel().getColumn(13).setMinWidth(0);
            table.getColumnModel().getColumn(13).setMaxWidth(0);
            
            
//            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//            table.setRowSorter(sorter);
            
            Action delete = null;
            ButtonColumn boutonAnnuler = new ButtonColumn(table, supprimer, 12);
            
            TableColumn tcolumnas = table.getColumnModel().getColumn(10); 
            tcolumnas.setCellRenderer(table.getDefaultRenderer(Boolean.class)); 
            tcolumnas.setCellEditor(table.getDefaultEditor(Boolean.class));
            
            table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
                        
            TableColumn colonneLibelle = table.getColumnModel ().getColumn (2);
            TableColumn colonneMotif = table.getColumnModel().getColumn(3);
            TableColumn colonneModeReglement = table.getColumnModel ().getColumn (5);
            TableColumn colonneEtat = table.getColumnModel().getColumn(9);
            
            colonneLibelle.setCellRenderer(new MyTableCellRenderer());
            colonneMotif.setCellRenderer(new MyTableCellRenderer());
            colonneModeReglement.setCellRenderer(new MyTableCellRenderer());
            colonneEtat.setCellRenderer(new MyTableCellRenderer());
            
            JComboBox comboboxLibelle = new JComboBox();
            JComboBox comboboxMotif = new JComboBox();
            JComboBox comboboxModeReglement = new JComboBox();
            JComboBox comboboxEtat = new JComboBox();
            
            List<Libelle> desLibelles = null;
            List<Motif> desMotifs = null;
            List<ModeReglement> desModesReglement = null;
            List<Etat> desEtats = null;
            
            try {
                desLibelles = dao.lireTousLesLibelles();
                desMotifs = dao.lireTousLesMotifs();
                desModesReglement = dao.lireTousLesModesReglements();
                desEtats= dao.lireTousLesEats();
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (Libelle unLibelle : desLibelles) {
                comboboxLibelle.addItem(unLibelle);
            }
            for(Motif unMotif : desMotifs){
                comboboxMotif.addItem(unMotif);
            }
            for(ModeReglement unModeReglement : desModesReglement){
                comboboxModeReglement.addItem(unModeReglement);
            }
            for(Etat unEtat : desEtats){
                comboboxEtat.addItem(unEtat);
            }
            
            
            colonneLibelle.setCellEditor ( new WebDefaultCellEditor ( comboboxLibelle ) );
            colonneMotif.setCellEditor ( new WebDefaultCellEditor ( comboboxMotif ) );
            colonneModeReglement.setCellEditor ( new WebDefaultCellEditor ( comboboxModeReglement ) );
            colonneEtat.setCellEditor(new WebDefaultCellEditor(comboboxEtat));
            
            comboboxLibelle.setEditable(true);
            
            try{
            TableCellListener tcl = new TableCellListener(table, action);
            }catch(Exception e){
                
            }
    }

    /**
     * Permet de modifier un enregistrement en prenant en compte le calcul des soldes.
     */
    
Action action = new AbstractAction()
{
    public void actionPerformed(ActionEvent e)
    {
        Compta unCompte = (Compta) ((VueAfficherCompteCIO)vue).getModeleComboboxCompta().getSelectedItem();
        Integer idCompte = unCompte.getId();
            
        TableCellListener tcl = (TableCellListener)e.getSource();
        int modelRow = tcl.getRow();
        int colonne = tcl.getColumn();
        
        
        JTable table = ((VueAfficherCompteCIO)vue).getjTableCIO();
            
            Object date = table.getValueAt(modelRow, 1);
            Object libelle = table.getValueAt(modelRow, 2);
            Object motif = table.getValueAt(modelRow, 3);
            Object dateFacture = table.getValueAt(modelRow, 4);
            Object modeReglement = table.getValueAt(modelRow, 5);
            Object numchq = table.getValueAt(modelRow, 6);
            Object montant = table.getValueAt(modelRow, 7);
            Object etat = table.getValueAt(modelRow, 9);
            Object anticipation = table.getValueAt(modelRow, 10);
            Object recdep = table.getValueAt(modelRow, 11);
            

            Date dateEnr = null;
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" );
            String textDate = date.toString();
            try {
                dateEnr = df.parse(textDate);
            } catch (Exception ebt) {
                System.out.println("Error while parsing date" );
                ebt.printStackTrace();
            }
    
            
            
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
                
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                try{
                    if(modelRow == 0){
                        ancienSolde = dao.recupererDernierSolde("%" + String.valueOf(annee-1) + "%", idCompte);
                        
                    }else{
                        ancienSolde = dao.lireNouveauSoldeFromOrdre(modelRow, idCompte, "%" + String.valueOf(annee) + "%");
                    }
                    
                }catch(Exception e1){
                    
                }
                nbEnregistrement = dao.compterNbEnregistrement("%" + String.valueOf(annee) + "%", idCompte);
                
                if(idLibelle == null){
                    Integer dernierIdLibelle = dao.recupererDernierIdLibelle() + 1;
                    dao.ajouterLibelle(dernierIdLibelle, libelle.toString(), idCompte);
                    idLibelle = dao.lireIdLibelleFromLibelle(libelle.toString());
                }
                                
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                 BigDecimal ancienMontant = (dao.lireMontantFromOrdre(modelRow+1, idCompte, "%" + String.valueOf(annee) + "%")).setScale(2);
                BigDecimal nouveauMontant = bigDecimalMontant.setScale(2);
                    
                    if(ancienMontant.compareTo(nouveauMontant) == 1){
                        dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, new java.sql.Date(dateEnr.getTime()), bigDecimalMontant, dao.lireNouveauSoldeFromOrdre(modelRow+1, idCompte, "%" + String.valueOf(annee) + "%"), dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()), numchq.toString(), idCompte, "%" + String.valueOf(annee) + "%");
                    }else{
                        if("Dépense".equals(recdep.toString())){
                            dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, new java.sql.Date(dateEnr.getTime()), bigDecimalMontant, ancienSolde.subtract(bigDecimalMontant),dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()), numchq.toString(), idCompte, "%" + String.valueOf(annee) + "%");
           
                        }else{
                            
                                dao.modifierUnEnregistrementFromOrdre(modelRow+1, idLibelle,idModeReglement , idEtat, idMotif, new java.sql.Date(dateEnr.getTime()), bigDecimalMontant, ancienSolde.add(bigDecimalMontant), dateFacture.toString(), Boolean.parseBoolean(anticipation.toString()), numchq.toString(), idCompte, "%" + String.valueOf(annee) + "%");
                            
                         }
                        
                    }
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i;
            
            for(i=modelRow+1; i<nbEnregistrement; i++){
            try {
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                bigDecimalMontant = dao.lireMontantFromOrdre(i+1, idCompte, "%" + String.valueOf(annee) + "%");
                ancienSolde = dao.lireNouveauSoldeFromOrdre(i, idCompte, "%" + String.valueOf(annee) + "%");
                BigDecimal nouveauSolde = null;
                
                if(dao.verifierRecDep(i+1, idCompte, "%" + String.valueOf(annee) + "%") == true){
                    nouveauSolde = ancienSolde.subtract(bigDecimalMontant);
                }else{
                    nouveauSolde = bigDecimalMontant.add(ancienSolde);
                }
                
                dao.mettreAJourLesSoldes(nouveauSolde, ancienSolde, idCompte, i+1, "%" + String.valueOf(annee) + "%");
            } catch (Exception ex) {
            }
                    
                
            }
            try {
                int annee = ((VueAfficherCompteCIO)vue).getjYearChooser1().getYear();
                dao.mettreAJourSoldeCompte(idCompte, dao.recupererDernierSolde("%" + String.valueOf(annee) + "%", idCompte));
                viderJtableModel();
                chargerEnregistrement(((VueAfficherCompteCIO)vue).getjYearChooser1().getYear(), idCompte);
                chargerSoldeCompteCIO();
            } catch (DaoException ex) {
                Logger.getLogger(CtrlAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            WebOptionPane.showMessageDialog ( ((VueAfficherCompteCIO)vue), "Modification effectuée : " + tcl.getOldValue() + " remplacé par "+ tcl.getNewValue(), "Modification", WebOptionPane.INFORMATION_MESSAGE );
             
        }
    
};



    /**
     * 
     * Permet de charger le solde de la comptabilité en cours et le solde sans les anticipations.
     *
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     *          
     */
    public void chargerSoldeCompteCIO() throws DaoException{
        
        
        Compta unCompte = (Compta) ((VueAfficherCompteCIO)vue).getModeleComboboxCompta().getSelectedItem();
        Integer idCompte = unCompte.getId();
        
        if(idCompte == null){

        }else{
        
        BigDecimal soldeCIO = dao.lireSoldeCompte(idCompte);
        BigDecimal soldeCIOSansAnticipation = dao.lireSoldeCompteSansAnticipation(idCompte);
        
        ((VueAfficherCompteCIO)vue).getjTextFieldSoldeCompteCIO().setText(soldeCIO.toString());
        
        ((VueAfficherCompteCIO)vue).getjTextFieldSoldeCompteCIOSansAnticipation().setText(soldeCIOSansAnticipation.toString());
        
        }
    }
    
    /**
     * 
     * Permet de vider la JTable.
     *
     */
    public void viderJtableModel(){
        
        DefaultTableModel model = ((VueAfficherCompteCIO)vue).getModeleJTableCIO();
        
        for(int i = model.getRowCount(); i > 0; --i)
            model.removeRow(i-1);
        
        model.getDataVector().removeAllElements();
    }
    
    
    

}
