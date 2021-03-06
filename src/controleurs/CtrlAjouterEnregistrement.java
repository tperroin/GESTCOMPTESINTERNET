package controleurs;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import metier.Compta;
import metier.Etat;
import metier.Libelle;
import metier.ModeReglement;
import metier.Motif;
import modele.dao.DaoException;
import modele.dao.DaoH2;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import vues.VueAjouterEnregistrement;


/**
 * 
 * Permet de gérer les données et les actions pour la vue VueAjouterEnregistrement. 
 * C'est une classe fille de Controleur.
 * Elle a donc une vue et un controleur propre.
 *
 * @author Perroin Thibault
 */
public class CtrlAjouterEnregistrement extends Controleur {
    
    DaoH2 dao = null;
    
    CtrlAjouterEnregistrement ctrlAjouterEnregistrement;
    CtrlAfficherCompteCIO ctrlAfficherCompteCIO;
    CtrlAfficherCompteCM ctrlAfficherCompteCM;
    CtrlAccueil ctrlAccueil;
    CtrlArchivageCIO ctrlArchivageCIO;
    CtrlArchivageCM ctrlArchivageCM;
    CtrlImpression ctrlImpression;
    
    /**
     * Le constructeur du controleur de la vue VueAjouterEnregistrement.
     *
     * @param ctrl
     *          Le controleur à appeler.
     * @throws DaoException
     *          Exception liée à une erreur avec la classe DAO.
     */
    public CtrlAjouterEnregistrement(Controleur ctrl) throws DaoException {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoH2("gestComptes", "sa", "");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueAjouterEnregistrement(this));
            this.afficherVue();
            chargerListeModesReglements();
            chargerListeEtats();
            chargerListeLibelles();
            chargerListeCompta("%");
            
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlAjouterEnregistrement - instanciation - " + ex.getMessage(), "Ajouter", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }
    
    /**
     * 
     * Permet de cacher la vue VueAjouterEnregistrement.
     *
     */
    public void ajouterAnnuler() {
        
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
    
    public void afficherAccueil() throws DaoException{
        if (ctrlAccueil == null){
            ctrlAccueil = new CtrlAccueil(this);
        }else{
            ctrlAccueil.afficherVue();
        }
        this.cacherVue();
    }
    
   
    /**
     * 
     * Permet de charger le modele de la JComboBoxMotif avec les motifs stockés dans la base de données en fonction d'une recette ou d'une dépense.
     *
     * @param recdep
     *          Valeur 0 si c'est une recette ou 1 si c'est une dépense.
     * @throws DaoException
     */
    public void chargerListeMotifsFromRecDep(Boolean recdep) throws DaoException {
        
        List<Motif> desMotifs = dao.lireMotifsFromDepRec(recdep);
        for (Motif unMotif : desMotifs) {
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxMotif().addElement(unMotif);
        }
        
    }
    
    /**
     * 
     * Permet de charger le modele de la JComboBoxModesReglements avec les modes de règlements stockés dans la base de données.
     *
     * @throws DaoException
     */
    public void chargerListeModesReglements() throws DaoException {
        List<ModeReglement> desModesReglements = dao.lireTousLesModesReglements();
        for (ModeReglement unModeReglement : desModesReglements) {
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().addElement(unModeReglement);
        }
    }
    
    /**
     * 
     * Permet de charger le modele de la JComboBoxEtat avec les états stockés dans la base de données.
     *
     * @throws DaoException
     */
    public void chargerListeEtats() throws DaoException {
        List<Etat> desEtats = dao.lireTousLesEats();
        for (Etat unEtat : desEtats) {
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxEtat().addElement(unEtat);
        }
    }
    
    /**
     * 
     * Permet de charger le modele de la JComboBoxCompta avec les comptabilités stockés dans la base de données en fonction d'une banque.
     *
     * @param banque
     *          La banque de la comptabilité recherchée.
     * @throws DaoException
     */
    public void chargerListeCompta(String banque) throws DaoException {
        List<Compta> desComptas = dao.lireToutesLesComptas(banque);
        for (Compta uneCompta : desComptas) {
            ((VueAjouterEnregistrement)vue).getModeleComboboxCompta().addElement(uneCompta);
        }
    }
    
    /**
     * 
     * Permet de charger les modeles des JComboboxLibelle,1,2,3,5 avec les libellés enregistrés dans la base de données.
     *
     * @throws DaoException
     */
    public void chargerListeLibelles() throws DaoException {
        List<Libelle> desLibelles = dao.lireTousLesLibelles();
        
        
                
        for (Libelle unLibelle : desLibelles) {
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle().addElement(unLibelle);
            
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().addElement(unLibelle);
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().addElement(unLibelle);
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().addElement(unLibelle);
            ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle5().addElement(unLibelle);
        }
    }
    
    /**
     * 
     * Permet d'afficher les éléments nécessaire à la saisie pour les remises de chèques en fonction des choix de l'utilisateur.
     *
     */
    public void afficherRemiseChq(){
        String modereglement = ((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().getSelectedItem().toString();
            if("CHQ".equals(modereglement)){
                ((VueAjouterEnregistrement)vue).getjLabelNumCHQ().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldNumCHQ().setVisible(true);
            }else{
                ((VueAjouterEnregistrement)vue).getjLabelNumCHQ().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldNumCHQ().setVisible(false);
            }
            
            if("Remise de chèques".equals(modereglement)){
                ((VueAjouterEnregistrement)vue).getjLabelMontant().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelMontant().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelMontant1().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelMontant2().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelMontant3().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelMontant4().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant1().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant2().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant3().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture1().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture2().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture3().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture5().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture5().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle1().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle2().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle5().setVisible(true);
                
                
                AutoCompleteDecorator.decorate(((VueAjouterEnregistrement)vue).getjComboBoxLibelle1());
                AutoCompleteDecorator.decorate(((VueAjouterEnregistrement)vue).getjComboBoxLibelle2());
                AutoCompleteDecorator.decorate(((VueAjouterEnregistrement)vue).getjComboBoxLibelle3());
                AutoCompleteDecorator.decorate(((VueAjouterEnregistrement)vue).getjComboBoxLibelle5());
            
            }else{
                ((VueAjouterEnregistrement)vue).getjLabelMontant().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelMontant().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().setVisible(true);
                ((VueAjouterEnregistrement)vue).getjLabelMontant1().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelMontant2().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelMontant3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelMontant4().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant1().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant2().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture1().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture2().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture5().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture5().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle1().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle2().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle5().setVisible(false);
            }
    } 
    
    
    /**
     * 
     * Permet d'afficher les éléments nécessaires à la saisie d'un enregistrement lorsqu'il s'agit d'une facture.
     *
     * @param valeur
     *          Valeur du libellé à choisir.
     */
    public void affichagePourFactureEtRèglement(String valeur){
        String modereglement = ((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().getSelectedItem().toString();
        
        ((VueAjouterEnregistrement)vue).getjLabelDateFacture().setVisible(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setVisible(true);
        
        if("Remise de chèque".equals(modereglement)){
            ((VueAjouterEnregistrement)vue).getjLabelDateFacture().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setVisible(false);
        }
        
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().getEditor().setItem(valeur);
        
        
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().requestFocusInWindow();
    }
    
    /**
     * 
     * Permet d'afficher les éléments nécessaire à la saisie pour les restaurants et les prêts en fonction des choix de l'utilisateur.
     *
     * @param valeur
     *          La valeur est "Restaurant" quand le choix de l'utilisateur a choisit d'enregistrement une dépense en restaurant
     *          ou la valeur est "Prêt" quand l'utilisateur à choisir d'enregistrer une dépense en prêt.
     */
    public void affichagePourRestaurantEtPret(String valeur){
        
        ((VueAjouterEnregistrement)vue).getjLabelDateFacture().setVisible(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setVisible(false);
        
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().getEditor().setItem(valeur);
        
        
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().requestFocusInWindow();
        
    }
    
    /**
     * 
     * Permet d'enregistrer un enregistrement avec les valeurs saisie dans la vue par l'utilisateur.
     *
     * @throws DaoException
     */
    public void enregistrerEnregistrement() throws DaoException {
        // Déclarations de variables locales
        dao = new DaoH2("gestComptes", "sa", "");
         try {
            dao.connecter();
            
            Integer idMotif = null;
            try{
                Motif unMotif = (Motif)(((VueAjouterEnregistrement)vue).getModeleJComboBoxMotif().getSelectedItem());
                idMotif = unMotif.getId();
            }catch(Exception e){
                
            }
            
            Libelle unLibelle;
            Integer idLibelle = null;
            Compta unCompte = (Compta)(((VueAjouterEnregistrement)vue).getModeleComboboxCompta().getSelectedItem());
            Integer idCompte = unCompte.getId();
            try{
                unLibelle = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle().getSelectedItem());
                idLibelle = unLibelle.getId();
            }catch(Exception e){
                idLibelle = dernierIdLibelle();
                dao.ajouterLibelle(idLibelle, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle().getSelectedItem().toString(), idCompte);
            }
            
            
            
            ModeReglement unModeReglement = (ModeReglement)(((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().getSelectedItem());
            Integer idModeReglement = unModeReglement.getId();
            
            String date = ((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getCalendar().getTime().toString();
            String annee = date.substring(date.length() - 4);
            
            Integer ordre = dernierOrdreEnr(idCompte, "%"+annee +"%");
            
            Etat unEtat = (Etat)(((VueAjouterEnregistrement)vue).getModeleJComboBoxEtat().getSelectedItem());
            Integer idEtat = unEtat.getId();
            
            String PreLEnc = ((VueAjouterEnregistrement)vue).getjComboBoxModRegl().getSelectedItem().toString();
            
            String RecDep = ((VueAjouterEnregistrement)vue).getjComboBoxRecDep().getSelectedItem().toString();
            
            
            java.sql.Date DateEnr = null;
            
            
            
            try{
                if(verifierSaisie() != false ){
                    DateEnr = new java.sql.Date(((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getCalendar().getTimeInMillis());
                    
                }
            }catch(Exception e){
                
            }
            
            try{
                if(verifierSaisieRemiseCheque() != false){
                     DateEnr = new java.sql.Date(((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getCalendar().getTimeInMillis());
                    
                }
            }catch(Exception e){
                
            }
            
            
            BigDecimal montant = null;
            BigDecimal ancienSolde = null;
            BigDecimal nouveauSolde = null;
            String dateFacture = "";
            
            try{
                montant = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant().getText());
                
                ancienSolde = soldeCompte(idCompte);
            
                nouveauSolde = null;

                if("Dépense".equals(RecDep)){
                    nouveauSolde = ancienSolde.subtract(montant);
                }else{
                    if("Recette".equals(RecDep)){
                        nouveauSolde = ancienSolde.add(montant);
                    }
                }

                dateFacture = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().getText();
            }catch(Exception e){
                
            }
            
            
            
            String numCHQ = ((VueAjouterEnregistrement)vue).getjTextFieldNumCHQ().getText();
            
            Integer id = dernierIdEnr();
            
            Date aujourdhui = new Date();
            Date dateEnregistrement = ((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getDate();
            
            Boolean anticipation = false;   
            try{
                if(dateEnregistrement.getTime() > aujourdhui.getTime()){
                    anticipation = true;
                }else{
                    anticipation = false;
                }
            }catch(Exception e){
                
            }
            if("Remise de chèques".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().getSelectedItem().toString())){
                
                // Insertion pour la jcombobox libelle5
                
                Integer id5 = dernierIdEnr();
                Integer ordre5 = dernierOrdreEnr(idCompte, "%"+String.valueOf(annee) +"%");
                
                Libelle unLibelle5;
                Integer idLibelle5 = null;

                try{
                    unLibelle5 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle5().getSelectedItem());
                    idLibelle5 = unLibelle5.getId();
                }catch(Exception e){
                    idLibelle5 = dernierIdLibelle();
                    dao.ajouterLibelle(idLibelle5, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle5().getSelectedItem().toString(), idCompte);
                }
                                
                String dateFacture1 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture5().getText();
                
                if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                    
                    BigDecimal montant1 = null;
                    BigDecimal ancienSolde1 = null;
                    BigDecimal nouveauSolde1 = null;
                    
                    try{

                        montant1 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant1().getText());
                        nouveauSolde1 = null;
                        ancienSolde1 = soldeCompte(idCompte);

                        if("Dépense".equals(RecDep)){
                            nouveauSolde1 = ancienSolde1.subtract(montant1);
                        }else{
                            if("Recette".equals(RecDep)){
                                nouveauSolde1 = ancienSolde1.add(montant1);
                            }
                        }
                    }catch(Exception e){

                    }
                    
                    dao.ajouterUnEnregistrement(id5, idLibelle5, idModeReglement, idCompte, idMotif, idEtat, RecDep, DateEnr, montant1, ancienSolde1, nouveauSolde1, dateFacture1, numCHQ, anticipation, ordre5);
                    dao.mettreAJourSoldeCompte(idCompte, nouveauSolde1);
                    affichageBoutonsValidation();
                    desactiverElements();
                }
                
                // Insertion pour la jcombobox libelle1
                
                Integer idLibelle2 = 0;
                String dateFacture2 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().getText();
                
                
                if(!"".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().getSelectedItem().toString())){
                        
                    Libelle unLibelle2;
                    
                    try{
                        unLibelle2 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().getSelectedItem());
                        idLibelle2 = unLibelle2.getId();
                    }catch(Exception e){
                        idLibelle2 = dernierIdLibelle();
                        dao.ajouterLibelle(idLibelle2, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().getSelectedItem().toString(), idCompte);
                    }
                    
                    try{
                        

                        Integer id2 = dernierIdEnr();
                        Integer ordre2 = dernierOrdreEnr(idCompte, "%"+String.valueOf(annee) +"%");
                        
                        if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                            
                            BigDecimal montant2 = null;
                            BigDecimal ancienSolde2 = null;
                            BigDecimal nouveauSolde2 = null;
                            
                            try{
                                montant2 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant2().getText());

                                ancienSolde2 = soldeCompte(idCompte);
                                nouveauSolde2 = null;

                                if("Dépense".equals(RecDep)){
                                    nouveauSolde2 = ancienSolde2.subtract(montant2);
                                }else{
                                    if("Recette".equals(RecDep)){
                                        nouveauSolde2 = ancienSolde2.add(montant2);
                                    }
                                }
                            }catch(Exception e){
                                
                            }
                        
                            dao.ajouterUnEnregistrement(id2, idLibelle2, 7, idCompte, idMotif, idEtat, RecDep, DateEnr, montant2, ancienSolde2, nouveauSolde2, dateFacture2, numCHQ, anticipation, ordre2);
                            dao.mettreAJourSoldeCompte(idCompte, nouveauSolde2);
                            affichageBoutonsValidation();
                            desactiverElements();
                        }
                    }catch(Exception e){
                        
                    }
                }
                
                // Insertion pour la jcombobox libelle2
                
                Integer idLibelle3 = 0;
                String dateFacture3 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().getText();
                
                if(!"".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().getSelectedItem().toString())){
                    
                    Libelle unLibelle3;
                    
                    try{
                        unLibelle3 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().getSelectedItem());
                        idLibelle3 = unLibelle3.getId();
                    }catch(Exception e){
                        idLibelle3 = dernierIdLibelle();
                        dao.ajouterLibelle(idLibelle3, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().getSelectedItem().toString(), idCompte);
                    }
                    
                    try{
                        

                        Integer id3 = dernierIdEnr();
                        Integer ordre3 = dernierOrdreEnr(idCompte, "%"+String.valueOf(annee) +"%");
                        
                        if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                            
                            BigDecimal ancienSolde3 = null;
                            BigDecimal montant3 = null;
                            BigDecimal nouveauSolde3 = null;
                            
                            try{
                            
                                ancienSolde3 = soldeCompte(idCompte);
                                montant3 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant3().getText());
                                nouveauSolde3 = null;

                                if("Dépense".equals(RecDep)){
                                nouveauSolde3 = ancienSolde3.subtract(montant3);
                                }else{
                                    if("Recette".equals(RecDep)){
                                        nouveauSolde3 = ancienSolde3.add(montant3);
                                    }
                                }
                            
                            }catch(Exception e){
                                
                            }
                            
                            dao.ajouterUnEnregistrement(id3, idLibelle3, 7, idCompte, idMotif, idEtat, RecDep, DateEnr, montant3, ancienSolde3, nouveauSolde3, dateFacture3, numCHQ, anticipation, ordre3);
                            dao.mettreAJourSoldeCompte(idCompte, nouveauSolde3);
                            affichageBoutonsValidation();
                            desactiverElements();
                        }
                    }catch(Exception e1){
                        
                    }
                }
                
                // Insertion pour la jcombobox libelle3
                
                Integer idLibelle4 = 0;
                String dateFacture4 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().getText();
                
                if(!"".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().getSelectedItem().toString())){
                    
                    Libelle unLibelle4;
                    
                    try{
                        unLibelle4 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().getSelectedItem());
                        idLibelle4 = unLibelle4.getId();
                    }catch(Exception e){
                        idLibelle4 = dernierIdLibelle();
                        dao.ajouterLibelle(idLibelle4, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().getSelectedItem().toString(), idCompte);
                    }
                    
                    try{
                        
                        Integer id4 = dernierIdEnr();
                        Integer ordre4 = dernierOrdreEnr(idCompte, "%"+String.valueOf(annee) +"%");
                        
                        if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                            
                            BigDecimal ancienSolde4 = null;
                            BigDecimal montant4 = null;
                            BigDecimal nouveauSolde4 = null;
                            
                            try{
                            
                                ancienSolde4 = soldeCompte(idCompte);
                                montant4 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant4().getText());
                                nouveauSolde4 = null;

                                if("Dépense".equals(RecDep)){
                                nouveauSolde4 = ancienSolde4.subtract(montant4);
                                }else{
                                    if("Recette".equals(RecDep)){
                                        nouveauSolde4 = ancienSolde4.add(montant4);
                                    }
                                }
                            }catch(Exception e){
                                
                            }
                            
                            dao.ajouterUnEnregistrement(id4, idLibelle4, 7, idCompte, idMotif, idEtat, RecDep, DateEnr, montant4, ancienSolde4, nouveauSolde4, dateFacture4, numCHQ, anticipation, ordre4);
                            dao.mettreAJourSoldeCompte(idCompte, nouveauSolde4);
                            affichageBoutonsValidation();
                            desactiverElements();
                        }
                    }catch(DaoException | NumberFormatException e2){
                        
                    }
                }
               
            }else{
                if(verifierValeursMontant() != false && verifierSaisie() != false && verifierSelectionCombobox() != false){
                    
                    dao.ajouterUnEnregistrement(id, idLibelle, idModeReglement, idCompte, idMotif, idEtat, RecDep, DateEnr, montant, ancienSolde, nouveauSolde, dateFacture, numCHQ, anticipation, ordre);
                    dao.mettreAJourSoldeCompte(idCompte, nouveauSolde);
                    affichageBoutonsValidation();
                    desactiverElements();
                    
                }
                
            }
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlAjouterEnregistrement - instanciation - " + ex.getMessage(), "Enregistrement", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * 
     * Permet de définir l'identifiant de l'enregistrement que l'utilisateur vient de créer.
     *
     * @return
     *          Un nouvel identifiant d'enregistrement.
     * @throws DaoException
     */
    public Integer dernierIdEnr() throws DaoException{
        dao = new DaoH2("gestComptes", "sa", "");
         
            dao.connecter();
            Integer dernierNum = dao.recupererDernierIdEnr();
            Integer nouveauNum = dernierNum + 1;
            
            return nouveauNum;
    }
    
    /**
     * 
     * Permet de définir l'ordre de l'enregistrement que l'utilisateur vient de créer.
     *
     * @param idCompte
     *          L'identifiant de la comptabilité dans laquelle doit se trouver l'enregistrement.
     * @param date
     *          La date à laquelle l'enregistrement est saisie.
     * @return
     *          Un nouvel ordre d'enregistrement.
     * @throws DaoException
     */
    public Integer dernierOrdreEnr(Integer idCompte, String date) throws DaoException{
        dao = new DaoH2("gestComptes", "sa", "");
         
            dao.connecter();
            Integer dernierOrdre = dao.recupererDernierOrdre(idCompte, date);
            Integer nouvelOrdre = dernierOrdre + 1;
            
            return nouvelOrdre;
    }
    
    /**
     * 
     * Permet de charger le solde d'une comptabilité.
     *
     * @param compte
     *          La comptabilité à charger.
     * @return
     *          Le solde de la comptabilité.
     * @throws DaoException
     */
    public BigDecimal soldeCompte(Integer compte) throws DaoException{
        dao = new DaoH2("gestComptes", "sa", "");
         
            dao.connecter();
            BigDecimal solde = dao.lireSoldeCompte(compte);
            
            return solde;
    }
    
    /**
     *
     * Permet de définir l'identifiant du libellé que l'utilisateur vient de créer.
     * 
     * @return
     *          Un nouvel identifiant de libellé.
     * @throws DaoException
     */
    public Integer dernierIdLibelle() throws DaoException{
        dao = new DaoH2("gestComptes", "sa", "");
         
            dao.connecter();
            Integer dernierNum = dao.recupererDernierIdLibelle();
            Integer nouveauNum = dernierNum + 1;
            
            return nouveauNum;
    }
    
    /**
     * 
     * Permet de vérifier la bonne saisie d'un montant lors de la création d'un enregistrement.
     * Une couleur est définie pour les champs lorsque la saisie n'est pas correcte.
     *
     * @return
     *      Vrai ou Faux si la valeur est numérique ou non.
     */
    public Boolean verifierValeursMontant(){
        
        JTextField montant = ((VueAjouterEnregistrement)vue).getjTextFieldMontant();
        JTextField montant1 = ((VueAjouterEnregistrement)vue).getjTextFieldMontant1();
        JTextField montant2 = ((VueAjouterEnregistrement)vue).getjTextFieldMontant2();
        JTextField montant3 = ((VueAjouterEnregistrement)vue).getjTextFieldMontant3();
        JTextField montant4 = ((VueAjouterEnregistrement)vue).getjTextFieldMontant4();

        String contenuMontant = montant.getText();
        String contenuMontant1 = montant1.getText();
        String contenuMontant2 = montant2.getText();
        String contenuMontant3 = montant3.getText();
        String contenuMontant4 = montant4.getText();
        
        boolean isNumeric = false;
        Border bColor = BorderFactory.createLineBorder(Color.red);
        try {
            
            
            if(!"".equals(contenuMontant)) {
                Float.parseFloat(contenuMontant);
            }
            
            if(!"".equals(contenuMontant1)) {
                Float.parseFloat(contenuMontant1);
            }
            
            if(!"".equals(contenuMontant2)) {
                Float.parseFloat(contenuMontant2);
            }
            
            if(!"".equals(contenuMontant3)) {
                Float.parseFloat(contenuMontant3);
            }
            
            if(!"".equals(contenuMontant4)) {
                Float.parseFloat(contenuMontant4);
            }
            
            
                        
            isNumeric = true;
            bColor = BorderFactory.createLineBorder(Color.white);
        } catch (Exception e) {
            isNumeric = false;
        }
        
        montant.setBorder(bColor);
        
        if(!"".equals(contenuMontant)) {
            montant.setBorder(bColor);
        }

        if(!"".equals(contenuMontant1)) {
            montant1.setBorder(bColor);
        }

        if(!"".equals(contenuMontant2)) {
            montant2.setBorder(bColor);
        }

        if(!"".equals(contenuMontant3)) {
            montant3.setBorder(bColor);
        }

        if(!"".equals(contenuMontant4)) {
            montant4.setBorder(bColor);
        }
        
        
        
        
       return isNumeric;
    }
    
    /**
     * 
     * Permet de vérifier que l'utilisateur à bien saisie l'ensemble des champs qu'il devait compléter pour valider l'enreistrement.
     * Une couleur est définie sur les champs en cas d'erreur.
     * 
     * @return
     *          Vrai ou Faux si la saisie est correcte ou non.
     */
    public Boolean verifierSaisie(){
        
        JDateChooser dateEnregistrement = ((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement();
        JTextField montant = ((VueAjouterEnregistrement)vue).getjTextFieldMontant();
        

        String contenuDateEnregistrement = "";
        Boolean contenuMontant = false;
        
        
        boolean saisie = false;
        Border bColor = BorderFactory.createLineBorder(Color.red);
        try {
            
            contenuDateEnregistrement = dateEnregistrement.getDate().toString();
            
            
            saisie = true;
            bColor = BorderFactory.createLineBorder(Color.white);
        } catch (Exception e) {
            saisie = false;
        }
        
        dateEnregistrement.setBorder(bColor);
        
        Border bColorMontant;
        
        if(contenuMontant = montant.getText().isEmpty() == true){
            saisie = false;
            bColorMontant = BorderFactory.createLineBorder(Color.red);
        }else{
            bColorMontant = BorderFactory.createLineBorder(Color.white);
        }
        
        montant.setBorder(bColorMontant);
                
       return saisie;
    }
    
    /**
     * 
     * Permet de vérifier que l'utilisateur à bien réaliser un choix de sélection dans une combobox.
     * Une couleur est définie lors d'une erreur de l'utilisateur.
     *
     * @return
     *          Vrai ou Faux si la sélection a été effectuée ou non.
     */
    public Boolean verifierSelectionCombobox(){
        
        JComboBox recdep = ((VueAjouterEnregistrement)vue).getjComboBoxRecDep();
        JComboBox libelle = ((VueAjouterEnregistrement)vue).getjComboBoxLibelle();

        String contenuRecpDep = recdep.getSelectedItem().toString();
        String contenuLibelle = libelle.getSelectedItem().toString();
        
        
        boolean saisie = false;
        Border bColor = BorderFactory.createLineBorder(Color.red);
       
            
        if("Recette".equals(contenuRecpDep) || "Dépense".equals(contenuRecpDep) && (!"".equals(contenuLibelle))) {
            saisie = true;
            bColor = BorderFactory.createLineBorder(Color.white);
        }else{
            saisie = false;
        }
      
        recdep.setBorder(bColor);
        libelle.setBorder(bColor);
        
       return saisie;
    }
    
    /**
     * 
     * Permet de vérifier la bonne saisie lors d'une remise de chèques.
     * Des couleurs sont définies en cas d'erreurs.
     *
     * @return
     *          Vrai ou Faux si une erreur a été commise par l'utilisateur.
     */
    public Boolean verifierSaisieRemiseCheque(){
         
        Boolean saisie= false;
        
        JTextField montant1 = ((VueAjouterEnregistrement)vue).getjTextFieldMontant1();
        Boolean contenuMontant1 = false;

        Border bColorMontantRemiseChèque;

        if(contenuMontant1 = montant1.getText().isEmpty() == true){
            saisie = false;
            bColorMontantRemiseChèque = BorderFactory.createLineBorder(Color.red);
        }else{
            bColorMontantRemiseChèque = montant1.getBorder();
            saisie= true;
        }

        montant1.setBorder(bColorMontantRemiseChèque);
            
        
        return saisie;
    }

    
    /**
     * 
     * Permet d'afficher les éléments après la validation d'un ajout d'un enregistrement.
     *
     */
    public void affichageBoutonsValidation(){
        ((VueAjouterEnregistrement)vue).getjLabelValidation().setVisible(true);
        ((VueAjouterEnregistrement)vue).getjButtonNouveau().setVisible(true);
        ((VueAjouterEnregistrement)vue).getjButtonModifier().setVisible(true);
        ((VueAjouterEnregistrement)vue).getjButtonAfficher().setVisible(true);

        ((VueAjouterEnregistrement)vue).getjButtonAnnuler().setVisible(false);
        ((VueAjouterEnregistrement)vue).getjButtonValider().setVisible(false);
    }
    
    /**
     * 
     * Permet de désactiver l'ensemble des éléments de la vue.
     *
     */
    public void desactiverElements(){
        
        ((VueAjouterEnregistrement)vue).getWebComboBoxCompta().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxEtat().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle1().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle2().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle5().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxModRegl().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxMotif().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjComboBoxRecDep().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant1().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant2().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant3().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjRadioButtonLibFacture().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjRadioButtonLibPret().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjRadioButtonLibRestaurant().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjRadioButtonRegl().setEnabled(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture5().setEditable(false);
        ((VueAjouterEnregistrement)vue).getjTextFieldNumCHQ().setEditable(false);
        
        boolean checkMontant2 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant2().getText());             
        boolean checkMontant3 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant3().getText());             
        boolean checkMontant4 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant4().getText());
        
        if(checkMontant2 == true && checkMontant3 == true && checkMontant4 == true){
            
            ((VueAjouterEnregistrement)vue).getjLabelDateFacture2().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjLabelDateFacture3().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjLabelDateFacture1().setVisible(false);
            
            ((VueAjouterEnregistrement)vue).getjLabelMontant2().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjLabelMontant3().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjLabelMontant4().setVisible(false);
            
            ((VueAjouterEnregistrement)vue).getjTextFieldMontant2().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjTextFieldMontant3().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setVisible(false);
            
            ((VueAjouterEnregistrement)vue).getjComboBoxLibelle2().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjComboBoxLibelle1().setVisible(false);
        
            ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setVisible(false);
            ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().setVisible(false);
        
        }else{
            if(checkMontant3 == true && checkMontant4 == true){
                
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelDateFacture2().setVisible(false);

                ((VueAjouterEnregistrement)vue).getjLabelMontant3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjLabelMontant4().setVisible(false);
                
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setVisible(false);

                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjComboBoxLibelle2().setVisible(false);

                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setVisible(false);
                ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().setVisible(false);
                
            }else{
                if(checkMontant4 == true){
                    
                    ((VueAjouterEnregistrement)vue).getjLabelDateFacture3().setVisible(false);

                    ((VueAjouterEnregistrement)vue).getjLabelMontant4().setVisible(false);
                    
                    ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setVisible(false);

                    ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setVisible(false);

                    ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setVisible(false);
                    
                }
            }
        }
        
    }
    
    /**
     * 
     * Permet d'activer l'ensemble des éléments de la vue.
     *
     */
    public void activerElements(){
        
        ((VueAjouterEnregistrement)vue).getWebComboBoxCompta().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxEtat().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle1().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle2().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle3().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxLibelle5().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxModRegl().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxMotif().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjComboBoxRecDep().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant1().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant2().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant3().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldMontant4().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjRadioButtonLibFacture().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjRadioButtonLibPret().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjRadioButtonLibRestaurant().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjRadioButtonRegl().setEnabled(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture5().setEditable(true);
        ((VueAjouterEnregistrement)vue).getjTextFieldNumCHQ().setEditable(true);
        
    }
    
    /**
     * 
     * Permet d'afficher les éléments lors d'une modification par un utilisateur.
     *
     */
    public void modifierEnregistrement(){
        
        activerElements();
        ((VueAjouterEnregistrement)vue).getjLabelValidation().setText("Modification en cours");
        ((VueAjouterEnregistrement)vue).getjButtonModifier().setVisible(false);
        ((VueAjouterEnregistrement)vue).getjButtonAfficher().setVisible(false);
        ((VueAjouterEnregistrement)vue).getjButtonNouveau().setVisible(false);
        
        ((VueAjouterEnregistrement)vue).getjButtonValiderModifier().setVisible(true);
        ((VueAjouterEnregistrement)vue).getjButtonAnnuler().setVisible(true);
        
        
    }
    
    /**
     * 
     * Permet de modifier un enregistrement.
     *
     * @throws DaoException
     */
    public void validerModifierEnregistrement () throws DaoException{
        Integer idMotif = null;
            try{
                Motif unMotif = (Motif)(((VueAjouterEnregistrement)vue).getModeleJComboBoxMotif().getSelectedItem());
                idMotif = unMotif.getId();
            }catch(Exception e){
                
            }
            
            Libelle unLibelle;
            Integer idLibelle = null;
            
            Compta unCompte = (Compta)(((VueAjouterEnregistrement)vue).getModeleComboboxCompta().getSelectedItem());
            Integer idCompte = unCompte.getId();
            
            try{
                unLibelle = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle().getSelectedItem());
                idLibelle = unLibelle.getId();
            }catch(Exception e){
                idLibelle = dernierIdLibelle();
                dao.ajouterLibelle(idLibelle, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle().getSelectedItem().toString(), idCompte);
            }
            
            ModeReglement unModeReglement = (ModeReglement)(((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().getSelectedItem());
            Integer idModeReglement = unModeReglement.getId();
          
            Etat unEtat = (Etat)(((VueAjouterEnregistrement)vue).getModeleJComboBoxEtat().getSelectedItem());
            Integer idEtat = unEtat.getId();
            
            String PreLEnc = ((VueAjouterEnregistrement)vue).getjComboBoxModRegl().getSelectedItem().toString();
            
            String RecDep = ((VueAjouterEnregistrement)vue).getjComboBoxRecDep().getSelectedItem().toString();
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
            String DateEnr = "";
            
            try{
                if(verifierSaisie() != false ){
                    DateEnr = formatter.format(((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getDate());
                }
            }catch(Exception e){
                
            }
            try{
                if(verifierSaisieRemiseCheque() != false){
                    DateEnr = formatter.format(((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getDate());
                }
            }catch(Exception e){
                
            }
             
            
            BigDecimal montant = null;
            BigDecimal ancienSolde = null;
            BigDecimal nouveauSolde = null;
            String dateFacture = "";
            Integer idEnregistrement = dao.recupererDernierIdEnr();
            try{
                montant = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant().getText());
                
                ancienSolde = dao.recupererAncienDernierSolde(idCompte);
                
            
                nouveauSolde = null;

                if("Dépense".equals(RecDep)){
                    nouveauSolde = ancienSolde.subtract(montant);
                }else{
                    if("Recette".equals(RecDep)){
                        nouveauSolde = ancienSolde.add(montant);
                    }
                }

                dateFacture = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture().getText();
            }catch(Exception e){
                
            }
            
            
            
            String numCHQ = ((VueAjouterEnregistrement)vue).getjTextFieldNumCHQ().getText();
            
            
            
            Date aujourdhui = new Date();
            Date dateEnregistrement = ((VueAjouterEnregistrement)vue).getjDateChooserDateEnregistrement().getDate();
            
            Boolean anticipation = false;   
            try{
                if(dateEnregistrement.getTime() > aujourdhui.getTime()){
                    anticipation = true;
                }else{
                    anticipation = false;
                }
            }catch(Exception e){
                
            }
            if("Remise de chèques".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxModeReglement().getSelectedItem().toString())){
            
                
                
                boolean checkMontant1 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant1().getText());             
                boolean checkMontant2 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant2().getText());             
                boolean checkMontant3 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant3().getText());             
                boolean checkMontant4 = "".equals(((VueAjouterEnregistrement)vue).getjTextFieldMontant4().getText());             
                
                
                
                Compta unCompteRemiseCheques = (Compta)(((VueAjouterEnregistrement)vue).getModeleComboboxCompta().getSelectedItem());
                Integer idCompteRemiseCheques = unCompteRemiseCheques.getId();
                
                
                // Insertion pour la jcombobox libelle5
                
                
                
                Libelle unLibelle5;
                Integer idLibelle5 = null;

                try{
                    unLibelle5 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle5().getSelectedItem());
                    idLibelle5 = unLibelle5.getId();
                }catch(Exception e){
                    idLibelle5 = dernierIdLibelle();
                    dao.ajouterLibelle(idLibelle5, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle5().getSelectedItem().toString(), idCompte);
                }
                                
                String dateFacture1 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture5().getText();
                
                if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                    
                    BigDecimal montant1 = null;
                    BigDecimal ancienSolde1 = null;
                    BigDecimal nouveauSolde1 = null;
                   
                    Integer id5;
                    
                    if(checkMontant4 == true && checkMontant3 == true && checkMontant2 == true){
                        id5 = dao.recupererDernierIdEnr();
                    }else{
                        if(checkMontant4 == true && checkMontant3 == true){
                            id5 = dao.recupererDernierIdEnr() - 1;
                        }else{
                            if(checkMontant4 == true){
                                id5 = dao.recupererDernierIdEnr() - 2;
                            }else{
                                id5 = dao.recupererDernierIdEnr() - 3;
                            }
                        }
                    }
                    
                    try{
                        
                        
                        montant1 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant1().getText());
                        nouveauSolde1 = null;
                        ancienSolde1 = dao.recupererDernierSoldeRemiseCheques(id5 - 1, idCompteRemiseCheques);

                        
                        if("Dépense".equals(RecDep)){
                            nouveauSolde1 = ancienSolde1.subtract(montant1);
                        }else{
                            if("Recette".equals(RecDep)){
                                nouveauSolde1 = ancienSolde1.add(montant1);
                            }
                        }
                    }catch(Exception e){

                    }
                    String annee = DateEnr.substring(DateEnr.length() - 4);
                    dao.modifierUnEnregistrement(id5, idLibelle5, idModeReglement, idCompte, idEtat, idMotif, RecDep, DateEnr, montant1, ancienSolde1, nouveauSolde1, dateFacture1, numCHQ, anticipation, "%" +annee +"%");
                    dao.mettreAJourSoldeCompte(idCompte, nouveauSolde1);
                    affichageBoutonsValidation();
                    desactiverElements();
                }
                
                // Insertion pour la jcombobox libelle1
                
                Integer idLibelle2 = 0;
                String dateFacture2 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture1().getText();
                
                
                if(!"".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().getSelectedItem().toString())){
                        
                    Libelle unLibelle2;
                    
                    try{
                        unLibelle2 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().getSelectedItem());
                        idLibelle2 = unLibelle2.getId();
                    }catch(Exception e){
                        idLibelle2 = dernierIdLibelle();
                        dao.ajouterLibelle(idLibelle2, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle1().getSelectedItem().toString(), idCompte);
                    }
                    
                    
                    
                    try{
                        
                        if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                            
                            BigDecimal montant2 = null;
                            BigDecimal ancienSolde2 = null;
                            BigDecimal nouveauSolde2 = null;
                               
                            Integer id2;
                    
                           
                            if(checkMontant4 == true && checkMontant3 == true){
                                id2 = dao.recupererDernierIdEnr();
                            }else{
                                if(checkMontant4 == true){
                                    id2 = dao.recupererDernierIdEnr() - 1;
                                }else{
                                    id2 = dao.recupererDernierIdEnr() - 2;
                                }
                            }
                           
                            
                            
                            try{
                                montant2 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant2().getText());

                                ancienSolde2 = dao.recupererDernierSoldeRemiseCheques(id2 - 1, idCompteRemiseCheques);
                                 nouveauSolde2 = null;

                                if("Dépense".equals(RecDep)){
                                    nouveauSolde2 = ancienSolde2.subtract(montant2);
                                }else{
                                    if("Recette".equals(RecDep)){
                                        nouveauSolde2 = ancienSolde2.add(montant2);
                                    }
                                }
                            }catch(Exception e){
                                
                            }
                            String annee = DateEnr.substring(DateEnr.length() - 4);
                            dao.modifierUnEnregistrement(id2, idLibelle2, idModeReglement, idCompte, idEtat, idMotif, RecDep, DateEnr, montant2, ancienSolde2, nouveauSolde2, dateFacture2, numCHQ, anticipation, "%" +annee +"%");
                            dao.mettreAJourSoldeCompte(idCompte, nouveauSolde2);
                            affichageBoutonsValidation();
                            desactiverElements();
                        }
                    }catch(Exception e){
                        
                    }
                }
                
                // Insertion pour la jcombobox libelle2
                
                Integer idLibelle3 = 0;
                String dateFacture3 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture2().getText();
                
                if(!"".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().getSelectedItem().toString())){
                    
                    Libelle unLibelle3;
                    
                    try{
                        unLibelle3 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().getSelectedItem());
                        idLibelle3 = unLibelle3.getId();
                    }catch(Exception e){
                        idLibelle3 = dernierIdLibelle();
                        dao.ajouterLibelle(idLibelle3, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle2().getSelectedItem().toString(), idCompte);
                    }
                    
                    try{
                        

                        
                        
                        if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                            
                            BigDecimal ancienSolde3 = null;
                            BigDecimal montant3 = null;
                            BigDecimal nouveauSolde3 = null;
                            
                            Integer id3;
                    
                            
                            if(checkMontant4 == true){
                                id3 = dao.recupererDernierIdEnr();
                            }else{
                                id3 = dao.recupererDernierIdEnr() - 1;
                            }
                                
                            
                            try{
                            
                                montant3 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant3().getText());
                                nouveauSolde3 = null;
                                
                                ancienSolde3 = dao.recupererDernierSoldeRemiseCheques(id3 - 1, idCompteRemiseCheques);
                                
                                if("Dépense".equals(RecDep)){
                                nouveauSolde3 = ancienSolde3.subtract(montant3);
                                }else{
                                    if("Recette".equals(RecDep)){
                                        nouveauSolde3 = ancienSolde3.add(montant3);
                                    }
                                }
                            
                            }catch(Exception e){
                                
                            }
                            String annee = DateEnr.substring(DateEnr.length() - 4);
                            dao.modifierUnEnregistrement(id3, idLibelle3, idModeReglement, idCompte, idEtat, idMotif, RecDep, DateEnr, montant3, ancienSolde3, nouveauSolde3, dateFacture3, numCHQ, anticipation, "%" +annee +"%");
                            dao.mettreAJourSoldeCompte(idCompte, nouveauSolde3);
                            affichageBoutonsValidation();
                            desactiverElements();
                        }
                    }catch(Exception e1){
                        
                    }
                }
                
                // Insertion pour la jcombobox libelle3
                
                Integer idLibelle4 = 0;
                String dateFacture4 = ((VueAjouterEnregistrement)vue).getjTextFieldDateFacture3().getText();
                
                if(!"".equals(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().getSelectedItem().toString())){
                    
                    Libelle unLibelle4;
                    
                    try{
                        unLibelle4 = (Libelle)(((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().getSelectedItem());
                        idLibelle4 = unLibelle4.getId();
                    }catch(Exception e){
                        idLibelle4 = dernierIdLibelle();
                        dao.ajouterLibelle(idLibelle4, ((VueAjouterEnregistrement)vue).getModeleJComboBoxLibelle3().getSelectedItem().toString(), idCompte);
                    }
                    
                    try{
                        
                        
                        
                        if(verifierValeursMontant() != false && verifierSaisieRemiseCheque() != false){
                            
                            BigDecimal ancienSolde4 = null;
                            BigDecimal montant4 = null;
                            BigDecimal nouveauSolde4 = null;
                            Integer id4 = dao.recupererDernierIdEnr();
                            try{
                            
                                ancienSolde4 = dao.recupererDernierSoldeRemiseCheques(id4 - 1, idCompteRemiseCheques);
                                montant4 = new BigDecimal(((VueAjouterEnregistrement)vue).getjTextFieldMontant4().getText());
                                nouveauSolde4 = null;

                                if("Dépense".equals(RecDep)){
                                nouveauSolde4 = ancienSolde4.subtract(montant4);
                                }else{
                                    if("Recette".equals(RecDep)){
                                        nouveauSolde4 = ancienSolde4.add(montant4);
                                    }
                                }
                                
                            }catch(Exception e){
                                
                            }
                            String annee = DateEnr.substring(DateEnr.length() - 4);
                            dao.modifierUnEnregistrement(id4, idLibelle4, idModeReglement, idCompte, idEtat, idMotif, RecDep, DateEnr, montant4, ancienSolde4, nouveauSolde4, dateFacture4, numCHQ, anticipation, "%" +annee +"%");
                            dao.mettreAJourSoldeCompte(idCompte, nouveauSolde4);
                            affichageBoutonsValidation();
                            desactiverElements();
                        }
                    }catch(DaoException | NumberFormatException e2){
                        
                    }
                }
               
            }else{
                if(verifierValeursMontant() != false && verifierSaisie() != false && verifierSelectionCombobox() != false){
                    
                    
                    String annee = DateEnr.substring(DateEnr.length() - 4);
                    dao.modifierUnEnregistrement(idEnregistrement, idLibelle, idModeReglement, idCompte, idEtat, idMotif, RecDep, DateEnr, montant, ancienSolde, nouveauSolde, dateFacture, numCHQ, anticipation, "%" +annee +"%");
                    
                    dao.mettreAJourSoldeCompte(idCompte, nouveauSolde);
                    affichageBoutonsValidation();
                    desactiverElements();
                    
                }
                
            }
        
        
        ((VueAjouterEnregistrement)vue).getjButtonValiderModifier().setVisible(false);
        ((VueAjouterEnregistrement)vue).getjLabelValidation().setText("Modification effectuée");
        
    }
}