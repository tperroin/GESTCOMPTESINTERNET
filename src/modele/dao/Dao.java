package modele.dao;



import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Compta;
import metier.Enregistrement;
import metier.Etat;
import metier.Libelle;
import metier.ModeReglement;
import metier.Motif;
import modele.dao.DaoException;
import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;

/**
 *
 * @author Thibault
 */
public abstract class Dao {

    private String piloteJdbc;
    private String urlBd;
    private String loginBd;
    private String mdpBd;
    private Connection cnx;
    
   private PreparedStatement lireMotifsFromDepRec;
   private PreparedStatement lireTousLesModesReglements;
   private PreparedStatement lireTousLesEtats;
   private PreparedStatement lireTousLesLibelles;
   private PreparedStatement lireSoldeCompte;
   private PreparedStatement lireTousLesEnregistrements;
   private PreparedStatement lireRecetteAnticpation;
   private PreparedStatement lireDepenseAnticpation;
   private PreparedStatement lireEnregistrementsRecDepAnt;
   private PreparedStatement lireEnregistrementsAnt;
   private PreparedStatement lireIdEnrFromOrdreCompte;
   private PreparedStatement lireNouveauSoldeFromOrdre;
   private PreparedStatement lireMontantFromOrdre;
   private PreparedStatement lireIdLibelleFromLibelle;
   private PreparedStatement lireIdModeReglementFromLibelle;
   private PreparedStatement lireIdEtatFromLibelle;
   private PreparedStatement lireIdMotifFromLibelle;
   private PreparedStatement lireTousLesMotifs;
   private PreparedStatement lireToutesLesComptas;
   private PreparedStatement lireTousLesEnregistrementsImpression;
   private PreparedStatement lireSoldeCompteImpression;
   private PreparedStatement lireDateEnregistrementFromIdCompteIdEnr;
   
   private PreparedStatement ajouterEnregistrement;
   private PreparedStatement ajouterLibelle;
   
   private PreparedStatement recupererDernierIdEnr;
   private PreparedStatement recupereDernierIdLibelle;
   private PreparedStatement recupererAncienDernierSolde;
   private PreparedStatement recupererDernierSoldeRemiseCheque;
   private PreparedStatement recupererDernierOrdre;
   private PreparedStatement recupererDernierSolde;
   
   
   private PreparedStatement mettreAJourSoldeCompte;
   private PreparedStatement mettreAJourEnregistrement;
   private PreparedStatement mettreAJourOrdre;
   private PreparedStatement mettreAJourOrdreInfSup;
   private PreparedStatement mettreAJourLesSoldes;
   private PreparedStatement mettreAJourEnregistrementFromOrdre;
   
   private PreparedStatement compterNbOrdreInf;
   private PreparedStatement compterNbOrdreSup;
   
   private PreparedStatement verifierRecDep;
   
   private PreparedStatement compterNbEnregistrement;
   
   private PreparedStatement supprimerEnregistrementFromOrdre;
   
   private PreparedStatement rechercherValeurEnregistrement;

    /**
     * 
     * Permet la connexion à la base de données H2 Datababse avec le pattern DAO.
     *
     * @param piloteJdbc
     *          Le nom du pilote JDBC utilisé.
     * @param urlBd
     *          L'URL de la base de données.
     * @param loginBd
     *          L'identifiant de l'utilisateur de la base de données.
     * @param mdpBd
     *          Le mot de passe de l'utilisateur de la base de données.
     */
    public Dao(String piloteJdbc, String urlBd, String loginBd, String mdpBd) {
        this.piloteJdbc = piloteJdbc;
        this.urlBd = urlBd;
        this.loginBd = loginBd;
        this.mdpBd = mdpBd;
    }

    /**
     * 
     * Permet de se connecter à la base de données et de créer les requêtes SQL nécessaires à l'application.
     *
     * @throws DaoException
     */
    public void connecter() throws DaoException {
        try {
            Class.forName(piloteJdbc);
            cnx = DriverManager.getConnection(urlBd, loginBd, mdpBd);
            
            
          
           lireMotifsFromDepRec = cnx.prepareStatement("SELECT * FROM MOTIF WHERE recetteDepense = ?");
           lireTousLesModesReglements = cnx.prepareStatement("SELECT * FROM MODEREGLEMENT");
           lireTousLesEtats = cnx.prepareStatement("SELECT * FROM ETAT");
           lireTousLesLibelles = cnx.prepareStatement("SELECT * FROM LIBELLE ORDER BY LIBELLE");
           lireSoldeCompte = cnx.prepareStatement("SELECT SOLDEDEPART FROM COMPTA WHERE ID = ?");
           lireTousLesEnregistrements = cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ENREGISTREMENT.ID_COMPTE = ? "
                   + "AND DATEENREGISTREMENT LIKE ?"
                   + "ORDER BY ORDRE");
           lireTousLesEnregistrementsImpression= cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ENREGISTREMENT.ID_COMPTE = ? "
                   + "AND DATEENREGISTREMENT BETWEEN ? AND ? "
                   + "AND ANTICIPATION = 'FALSE'"
                   + "ORDER BY ORDRE");
           lireRecetteAnticpation = cnx.prepareStatement("SELECT SUM(MONTANT) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ANTICIPATION = TRUE AND RECETTEDEPENSE = 'Recette'");
           lireDepenseAnticpation = cnx.prepareStatement("SELECT SUM(MONTANT) FROM ENREGISTREMENT WHERE ID_COMPTE =  ? AND ANTICIPATION = TRUE AND RECETTEDEPENSE = 'Dépense' "
                   + "AND ANTICIPATION = TRUE AND RECETTEDEPENSE = 'Dépense'");
           lireEnregistrementsRecDepAnt = cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ENREGISTREMENT.ID_COMPTE = ? "
                   + "AND ENREGISTREMENT.RECETTEDEPENSE = ? "
                   + "AND ANTICIPATION = ? "
                   + "AND DATEENREGISTREMENT LIKE ?"
                   + "ORDER BY ORDRE");
           lireEnregistrementsAnt = cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ENREGISTREMENT.ID_COMPTE = ? "
                   + "AND ANTICIPATION = ? "
                   + "AND DATEENREGISTREMENT LIKE ?"
                   + "ORDER BY ORDRE");
           lireIdEnrFromOrdreCompte = cnx.prepareStatement("SELECT ID FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           lireNouveauSoldeFromOrdre = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           lireMontantFromOrdre = cnx.prepareStatement("SELECT MONTANT FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           lireIdLibelleFromLibelle = cnx.prepareStatement("SELECT ID FROM LIBELLE WHERE LIBELLE = ?");
           lireIdModeReglementFromLibelle = cnx.prepareStatement("SELECT ID FROM MODEREGLEMENT WHERE LIBELLE = ?");
           lireIdEtatFromLibelle = cnx.prepareStatement("SELECT ID FROM ETAT WHERE LIBELLE = ?");
           lireIdMotifFromLibelle = cnx.prepareStatement("SELECT ID FROM MOTIF WHERE LIBELLE = ?");
           lireTousLesMotifs = cnx.prepareStatement("SELECT * FROM MOTIF");
           lireToutesLesComptas = cnx.prepareStatement("SELECT * FROM COMPTA WHERE LIBELLE LIKE ? ORDER BY ID");
           lireSoldeCompteImpression = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND DATEENREGISTREMENT BETWEEN ? AND ? AND ORDRE IN (SELECT MAX(ORDRE) FROM ENREGISTREMENT WHERE DATEENREGISTREMENT BETWEEN ? AND ?  AND ID_COMPTE = ? AND ANTICIPATION = 'FALSE') AND ANTICIPATION = 'FALSE'");
           lireDateEnregistrementFromIdCompteIdEnr = cnx.prepareStatement("SELECT DATEENREGISTREMENT FROM ENREGISTREMENT WHERE ID = ? AND ID_COMPTE = ?");
                      
           ajouterEnregistrement = cnx.prepareStatement("INSERT INTO ENREGISTREMENT VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
           ajouterLibelle = cnx.prepareStatement("INSERT INTO LIBELLE VALUES (?, ?, ?)");
           
           recupererDernierIdEnr = cnx.prepareStatement("SELECT MAX(ID) FROM ENREGISTREMENT");
           recupererAncienDernierSolde = cnx.prepareStatement("SELECT ANCIENSOLDE  FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ID IN (SELECT MAX (ID) FROM ENREGISTREMENT)");
           recupererDernierSoldeRemiseCheque = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE ID = ? AND ID_COMPTE = ?");
           recupererDernierOrdre = cnx.prepareStatement("SELECT MAX(ORDRE) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           recupereDernierIdLibelle = cnx.prepareStatement("SELECT MAX(ID) FROM LIBELLE");
           recupererDernierSolde = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE DATEENREGISTREMENT LIKE ? AND ORDRE IN (SELECT MAX(ORDRE) FROM ENREGISTREMENT WHERE DATEENREGISTREMENT LIKE ? AND ID_COMPTE = ?) AND ID_COMPTE = ?");
           
           mettreAJourSoldeCompte = cnx.prepareStatement("UPDATE COMPTA SET SOLDEDEPART = ? WHERE ID = ?");
           mettreAJourEnregistrement = cnx.prepareStatement("UPDATE ENREGISTREMENT SET ID_LIBELLE = ?, ID_MODEREGLEMENT = ?, ID_COMPTE = ?, ID_MOTIF = ?, ID_ETAT = ?, RECETTEDEPENSE = ?, DATEENREGISTREMENT = ?, MONTANT = ?, ANCIENSOLDE =?, NOUVEAUSOLDE = ?, DATEFACTURE = ?, NUMCHQ = ?, ANTICIPATION = ? WHERE ID = ? AND DATEENREGISTREMENT LIKE ?");
           mettreAJourEnregistrementFromOrdre= cnx.prepareStatement("UPDATE ENREGISTREMENT SET ID_LIBELLE = ?, ID_MODEREGLEMENT = ?, ID_MOTIF = ?, ID_ETAT = ?, DATEENREGISTREMENT = ?, MONTANT = ?, NOUVEAUSOLDE = ?, DATEFACTURE = ?, ANTICIPATION = ?, NUMCHQ = ? WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           mettreAJourOrdre = cnx.prepareStatement("UPDATE ENREGISTREMENT SET ORDRE = ? WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           mettreAJourOrdreInfSup = cnx.prepareStatement("UPDATE ENREGISTREMENT SET ORDRE = ? WHERE ORDRE = ? AND ID = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           mettreAJourLesSoldes = cnx.prepareStatement("UPDATE ENREGISTREMENT SET NOUVEAUSOLDE = ?, ANCIENSOLDE = ? WHERE ID_COMPTE = ? AND ORDRE = ? AND DATEENREGISTREMENT LIKE ?");
          
           compterNbOrdreInf = cnx.prepareStatement("SELECT COUNT(*) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ORDRE < ? AND DATEENREGISTREMENT LIKE ?");
           compterNbOrdreSup = cnx.prepareStatement("SELECT COUNT(*) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ORDRE > ? AND DATEENREGISTREMENT LIKE ?");
           
           verifierRecDep = cnx.prepareStatement("SELECT RECETTEDEPENSE FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           
           compterNbEnregistrement = cnx.prepareStatement("SELECT COUNT(*) FROM ENREGISTREMENT WHERE DATEENREGISTREMENT LIKE ? AND ID_COMPTE = ?");
           
           supprimerEnregistrementFromOrdre = cnx.prepareStatement("DELETE FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ? AND DATEENREGISTREMENT LIKE ?");
           
           rechercherValeurEnregistrement = cnx.prepareStatement("SELECT E.ID, E.ID_COMPTE, E.DATEENREGISTREMENT, E.ORDRE, E.ANCIENSOLDE, L.LIBELLE, MO.LIBELLE , E.DATEFACTURE , MR.LIBELLE, E.NUMCHQ , E.MONTANT, E.NOUVEAUSOLDE , ET.lIBELLE , E.ANTICIPATION , E.RECETTEDEPENSE FROM FT_SEARCH_DATA(?, 0, 0) FT, ENREGISTREMENT E JOIN LIBELLE L ON (E.ID_LIBELLE = L.ID) JOIN MOTIF MO ON (E.ID_MOTIF = MO.ID) JOIN ETAT ET ON (E.ID_ETAT = ET.ID) JOIN MODEREGLEMENT MR ON (E.ID_MODEREGLEMENT = MR.ID) WHERE E.ID_COMPTE = ? AND E.DATEENREGISTREMENT LIKE ? AND E.ID=FT.KEYS[0] AND FT.TABLE='ENREGISTREMENT' OR L.ID=FT.KEYS[0] AND FT.TABLE='LIBELLE' ORDER BY E.ORDRE");
           
        } catch (SQLException ex) {
            throw new DaoException("DAO - connecter : pb de connexion\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new DaoException("DAO - connecter : pb de pilote JDBC\n" + ex.getMessage());
        }
        
    }

    /**
     * 
     * Permet de se déconnecter de la base de données.
     *
     * @throws DaoException
     */
    public void deconnecter() throws DaoException {
        try {
            cnx.close();
        } catch (SQLException ex) {
            throw new DaoException("DAO - déconnecter : pb JDBC\n" + ex.getMessage());
        }
    }
    
   
    /**
     * 
     * Permet de lire et de charger tous les motifs enregistrés dans la base de données.
     *
     * @return
     *          Un objet desMotifs contenant l'ensemble des motifs enregistrés dans la base de données
     * @throws DaoException
     */
    public List<Motif> lireTousLesMotifs() throws DaoException{
        try {
            List<Motif> desMotifs = new ArrayList<Motif>();
            ResultSet rs = lireTousLesMotifs.executeQuery();
            while (rs.next()) {
                Motif unMotif = chargerUnEnregistrementMotif(rs);
                desMotifs.add(unMotif);
            }            
            return desMotifs;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesMotifs : pb JDBC\n" + ex.getMessage());
        }
    }

    
    
    /**
     * 
     * Permet de lire et de charger les motifs en fonction des dépenses ou des recettes.
     *
     * @param boo
     *          Prend pour valeur VRAI ou FAUX si le choix de l'utilisateur est une recette ou une dépense.
     * @return
     *          Un objet desMotifs contenant l'ensemble des motifs enregistrés dans la base de données en fonction d'une recette ou une dépense.
     * @throws DaoException
     */
    public List<Motif> lireMotifsFromDepRec(Boolean boo) throws DaoException{
        try {
            List<Motif> desMotifs = new ArrayList<Motif>();
            lireMotifsFromDepRec.setBoolean(1, boo);
            ResultSet rs = lireMotifsFromDepRec.executeQuery();
            while (rs.next()) {
                Motif unMotif = chargerUnEnregistrementMotif(rs);
                desMotifs.add(unMotif);
            }            
            return desMotifs;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireMotifsFromDepRec : pb JDBC\n" + ex.getMessage());
        }
    }

    private Motif chargerUnEnregistrementMotif(ResultSet rs) throws DaoException{
        try {
            
            Motif motif = new Motif();
            motif.setId(rs.getInt("ID"));
            motif.setLibelle(rs.getString("LIBELLE"));
            motif.setRecetteDepense(rs.getBoolean("RECETTEDEPENSE"));
            
            return motif;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementMotif : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire et de charger toutes les comptabilités enregistrés dans la base de données en fonction d'une banque.
     *
     * @param banque
     *          La banque sélectionnée par l'utilisateur.
     * @return
     *          Un objet desComptas contenant toutes les comptabilités enregistrées dans la base de données en fonction d'une banque.
     * @throws DaoException
     */
    public List<Compta> lireToutesLesComptas(String banque) throws DaoException{
        try {
            List<Compta> desComptas = new ArrayList<Compta>();
            lireToutesLesComptas.setString(1, banque);
            ResultSet rs = lireToutesLesComptas.executeQuery();
            while (rs.next()) {
                Compta uneCompta = chargerUnEnregistrementCompta(rs);
                desComptas.add(uneCompta);
            }            
            return desComptas;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireToutesLesComptas : pb JDBC\n" + ex.getMessage());
        }
    }
    
    private Compta chargerUnEnregistrementCompta(ResultSet rs) throws DaoException {
        try {
            
            Compta compta = new Compta();
            compta.setId(rs.getInt("ID"));
            compta.setLibelle(rs.getString("LIBELLE"));
            compta.setSoldeDepart(rs.getBigDecimal("SOLDEDEPART"));
            
            return compta;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementCompta : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire et de charger tous les modes de règlement enregistrés dans la base de données.
     *
     * @return
     *          Un objet desModesRglements contenant tous les modes de règlement enregistrés dans la base de données.
     * @throws DaoException
     */
    public List<ModeReglement> lireTousLesModesReglements() throws DaoException{
        try {
            List<ModeReglement> desModesReglements = new ArrayList<ModeReglement>();
            ResultSet rs = lireTousLesModesReglements.executeQuery();
            while (rs.next()) {
                ModeReglement unModeReglement = chargerUnEnregistrementModeReglement(rs);
                desModesReglements.add(unModeReglement);
            }            
            return desModesReglements;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesModesReglements : pb JDBC\n" + ex.getMessage());
        }
    }

    private ModeReglement chargerUnEnregistrementModeReglement(ResultSet rs) throws DaoException {
        try {
            
            ModeReglement modeReglement = new ModeReglement();
            modeReglement.setId(rs.getInt("ID"));
            modeReglement.setLibelle(rs.getString("LIBELLE"));
            
            return modeReglement;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementModeReglement : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * Permet de lire et de charger tous les états enregistrés dans la base de données.
     * 
     * @return
     *          Un objet desEtats contenant tous les états enregistrés dans la base de données.
     * @throws DaoException
     */
    public List<Etat> lireTousLesEats() throws DaoException{
        try {
            List<Etat> desEtats = new ArrayList<Etat>();
            ResultSet rs = lireTousLesEtats.executeQuery();
            while (rs.next()) {
                Etat unEtat = chargerUnEnregistrementEtat(rs);
                desEtats.add(unEtat);
            }            
            return desEtats;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesEats : pb JDBC\n" + ex.getMessage());
        }
    }

    private Etat chargerUnEnregistrementEtat(ResultSet rs) throws DaoException {
        try {
            
            Etat etat = new Etat();
            etat.setId(rs.getInt("ID"));
            etat.setLibelle(rs.getString("LIBELLE"));
            
            return etat;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementEtat : pb JDBC\n" + ex.getMessage());
        }
    }
    
    
    /**
     * 
     * Permet de lire et de charger tous les libellés enregistrés dans la base de données.
     *
     * @return
     *          Un objet desLibelles contenant tous les libellés enregistrés dans la base de données.
     * @throws DaoException
     */
    public List<Libelle> lireTousLesLibelles() throws DaoException{
        try {
            List<Libelle> desLibelles = new ArrayList<Libelle>();
            ResultSet rs = lireTousLesLibelles.executeQuery();
            while (rs.next()) {
                Libelle unLibelle = chargerUnEnregistrementLibelle(rs);
                desLibelles.add(unLibelle);
            }            
            return desLibelles;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesLibelles : pb JDBC\n" + ex.getMessage());
        }
    }

    private Libelle chargerUnEnregistrementLibelle(ResultSet rs) throws DaoException {
        try {
            
            Libelle libelle = new Libelle();
            libelle.setId(rs.getInt("ID"));
            libelle.setLibelle(rs.getString("LIBELLE"));
            
            return libelle;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementLibelle : pb JDBC\n" + ex.getMessage());
        }
    }
    
     /**
     *
     * Lire l'identifiant d'un enregistrement en fonction de son ordre, de son compte et de l'année.
     * 
     * @param ordre
     *          L'ordre de l'enregistrement.
     * @param idCompte
     *          L'identifiant du compte de l'enregistrement.
     * @param annee
     *          L'année de l'enregistrement.
     * @return
     *          L'identifiant de l'enregistrement.
     * @throws DaoException
     */
    public Integer lireIdEnrFromOrdreCompte(Integer ordre, Integer idCompte, String annee) throws DaoException{
        try {                    
            lireIdEnrFromOrdreCompte.setInt(1, ordre);
            lireIdEnrFromOrdreCompte.setInt(2, idCompte);
            lireIdEnrFromOrdreCompte.setString(3, annee);
            ResultSet rs = lireIdEnrFromOrdreCompte.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireIdEnrFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire l'identifiant d'un libellé en fonction de sa valeur.
     *
     * @param libelle
     *          La valeur du libellé.
     * @return
     *          L'identifiant du libellé.
     * @throws DaoException
     */
    public Integer lireIdLibelleFromLibelle(String libelle) throws DaoException{
        try {                    
            lireIdLibelleFromLibelle.setString(1, libelle);
            ResultSet rs = lireIdLibelleFromLibelle.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireIdLibelleFromLibelle : pb JDBC\n" + ex.getMessage());
        }
    }
    /**
     *
     * Permet de lire l'identifiant du mode de règlement en fonction de sa valeur.
     * 
     * @param libelle
     *          La valeur du mode de règlement.
     * @return
     *          L'identifiant du mode de règlement.
     * @throws DaoException
     */
    public Integer lireIdModeReglementFromLibelle(String libelle) throws DaoException{
        try {                    
            lireIdModeReglementFromLibelle.setString(1, libelle);
            ResultSet rs = lireIdModeReglementFromLibelle.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireIdModeReglementFromLibelle : pb JDBC\n" + ex.getMessage());
        }
    }
    /**
     * 
     * Permet de lire l'identifiant d'un motif en fonction de sa valeur.
     *
     * @param libelle
     *          La valeur du motif.
     * @return
     *          L'identifiant du motif.
     * @throws DaoException
     */
    public Integer lireIdMotifFromLibelle(String libelle) throws DaoException{
        try {                    
            lireIdMotifFromLibelle.setString(1, libelle);
            ResultSet rs = lireIdMotifFromLibelle.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireIdMotifFromLibelle : pb JDBC\n" + ex.getMessage());
        }
    }
    /**
     * 
     * Permet de lire l'identifiant d'un état en fonction de sa valeur.
     *
     * @param libelle
     *          La valeur de l'état.
     * @return
     *          L'identifiant de l'état.
     * @throws DaoException
     */
    public Integer lireIdEtatFromLibelle(String libelle) throws DaoException{
        try {                    
            lireIdEtatFromLibelle.setString(1, libelle);
            ResultSet rs = lireIdEtatFromLibelle.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireIdEtatFromLibelle : pb JDBC\n" + ex.getMessage());
        }
    }
    
    
    /**
     * 
     * Permet de lire et de charger tous les enregistrements contenus dans la base de données en fonction identifiant de comptabilité et d'une date.
     *
     * @param id
     *          L'idetifiant de comptabilité.
     * @param date
     *          La date du ou des enregistrement(s).
     * @return
     *          Un objet desEnregistrements contenant tous les enregistrements renvoyés avec les critères définis.
     * @throws DaoException
     */
    public List<Enregistrement> lireTousLesEnregistrements(Integer id, String date) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            lireTousLesEnregistrements.setInt(1, id);
            lireTousLesEnregistrements.setString(2, date);
            ResultSet rs = lireTousLesEnregistrements.executeQuery();
            while (rs.next()) {
                Enregistrement unEnregistrement = chargerUnEnregistrementEnregistrement(rs);
                desEnregistrements.add(unEnregistrement);
            }            
            return desEnregistrements;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesEnregistrements : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * Permet de lire et de charger tous les enregistrements en fonction d'une comptabilité, d'une date de début et d'une date de fin pour l'impression.
     * 
     * @param id
     *          L'identifiant de comptabilité.
     * @param dateDebut
     *          La date de début.
     * @param dateFin
     *          La date de fin.
     * @return
     *          Un objet desEnregistrements contenant tous les enregistrements renvoyés avec les critères définis.
     * @throws DaoException
     */
    public List<Enregistrement> lireTousLesEnregistrementsImpression(Integer id, String dateDebut, String dateFin) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            lireTousLesEnregistrementsImpression.setInt(1, id);
            lireTousLesEnregistrementsImpression.setString(2, dateDebut);
            lireTousLesEnregistrementsImpression.setString(3, dateFin);
            ResultSet rs = lireTousLesEnregistrementsImpression.executeQuery();
            while (rs.next()) {
                Enregistrement unEnregistrement = chargerUnEnregistrementEnregistrement(rs);
                desEnregistrements.add(unEnregistrement);
            }            
            return desEnregistrements;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesEnregistrementsImpression : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * Permet de lire et de charger tous les enregistrements en fonction d'un identifiant de comptabilité, d'une recette ou d'une dépense, d'un enregistrement
     * anticipé ou non et d'une année.
     * 
     * @param id
     *          L'identifiant de comptabilité.
     * @param recDep
     *          L'enregistrement est une recette ou une dépense.
     * @param anticipation
     *          L'enregistrement est anticipé ou non.
     * @param annee
     *          L'année de l'enregistrement;
     * @return
     *          Un objet desEnregistrements contenant tous les enregistrements renvoyés avec les critères définis.
     * @throws DaoException
     */
    public List<Enregistrement> lireEnregistrementsRecDepAnt(Integer id, String recDep, String anticipation, String annee) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            lireEnregistrementsRecDepAnt.setInt(1, id);
            lireEnregistrementsRecDepAnt.setString(2, recDep);
            lireEnregistrementsRecDepAnt.setString(3, anticipation);  
            lireEnregistrementsRecDepAnt.setString(4, annee);
            ResultSet rs = lireEnregistrementsRecDepAnt.executeQuery();
            while (rs.next()) {
                Enregistrement unEnregistrement = chargerUnEnregistrementEnregistrement(rs);
                desEnregistrements.add(unEnregistrement);
            }            
            return desEnregistrements;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireEnregistrementsRecDepAnt : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * Permet de lire et de charger les enregistrements anticipés en fonction d'une comptabilité et d'une année.
     * 
     * @param id
     *          L'identifiant de comptabilité
     * @param anticipation
     *          L'enregistrement est anticipé ou non.
     * @param annee
     *          L'année de l'enregistrement.
     * @return
     *          Un objet desEnregistrements contenant tous les enregistrements renvoyés avec les critères définis.
     * @throws DaoException
     */
    public List<Enregistrement> lireEnregistrementsAnt(Integer id, String anticipation, String annee) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            lireEnregistrementsAnt.setInt(1, id);
            lireEnregistrementsAnt.setString(2, anticipation);
            lireEnregistrementsAnt.setString(3, annee);
            ResultSet rs = lireEnregistrementsAnt.executeQuery();
            while (rs.next()) {
                Enregistrement unEnregistrement = chargerUnEnregistrementEnregistrement(rs);
                desEnregistrements.add(unEnregistrement);
            }            
            return desEnregistrements;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireEnregistrementsAnt : pb JDBC\n" + ex.getMessage());
        }
    }
    
    private Enregistrement chargerUnEnregistrementEnregistrement(ResultSet rs) throws DaoException {
        try {
            
            Enregistrement enregistrement = new Enregistrement();
            enregistrement.setId(rs.getString("ID"));
            enregistrement.setIdLibelle(rs.getString("LIBELLE.LIBELLE"));
            enregistrement.setModeReglement(rs.getString("MODEREGLEMENT.LIBELLE"));
            enregistrement.setIdCompte(rs.getString("ID_COMPTE"));
            enregistrement.setMotif(rs.getString("MOTIF.LIBELLE"));
            enregistrement.setIdEtat(rs.getString("ETAT.LIBELLE"));
            enregistrement.setRecetteDepense(rs.getString("RECETTEDEPENSE"));
            enregistrement.setDate(rs.getDate("DATEENREGISTREMENT"));
            enregistrement.setMontant(rs.getString("MONTANT"));
            enregistrement.setAncienSolde(rs.getString("ANCIENSOLDE"));
            enregistrement.setNouveauSolde(rs.getString("NOUVEAUSOLDE"));
            enregistrement.setDateFacture(rs.getString("DATEFACTURE"));
            enregistrement.setNumCHQ(rs.getString("NUMCHQ"));
            enregistrement.setAnticipation(rs.getString("ANTICIPATION"));
            enregistrement.setOrdre(rs.getString("ORDRE"));
            
            return enregistrement;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
    
     /**
      * 
      * Permet de lire le solde calculé avec le montant et le solde de l'enregistrement précédent en fonction donc de l'ordre, de l'année et de la comptabilité.
     *
     * @param ordre
     *          L'ordre de l'enregistrement.
     * @param idCompte
     *          L'identifiant de comptabilité.
     * @param annee
     *          L'année de l'enregistrement.
     * @return
     *          Le solde calculé à partir du montant et du solde de l'enregistrement précédent.
     * @throws DaoException
     */
    public BigDecimal lireNouveauSoldeFromOrdre(Integer ordre, Integer idCompte, String annee) throws DaoException{
        try {    
            lireNouveauSoldeFromOrdre.setInt(1, ordre);
            lireNouveauSoldeFromOrdre.setInt(2, idCompte);
            lireNouveauSoldeFromOrdre.setString(3, annee);
            ResultSet rs = lireNouveauSoldeFromOrdre.executeQuery();
            BigDecimal nouveauSolde = null;
            if (rs.next()) {
                nouveauSolde = rs.getBigDecimal(1);
            }
            
            return nouveauSolde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireNouveauSoldeFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire le montant d'un enregistrement à partir de son ordre.
     *
     * @param ordre
     *          L'ordre de l'enregistrement.
     * @param idCompte
     *          L'identifiant de comptabilité.
     * @param annee
     *          L'année de l'enregistrement.
     * @return
     *          Le montant de l'enregistrement.
     * @throws DaoException
     */
    public BigDecimal lireMontantFromOrdre(Integer ordre, Integer idCompte, String annee) throws DaoException{
        try {    
            lireMontantFromOrdre.setInt(1, ordre);
            lireMontantFromOrdre.setInt(2, idCompte);
            lireMontantFromOrdre.setString(3, annee);
            ResultSet rs = lireMontantFromOrdre.executeQuery();
            BigDecimal montant = null;
            if (rs.next()) {
                montant = rs.getBigDecimal(1);
            }
            
            return montant;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireMontantFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire la date de l'enregistrement à partir de son identifiant et de sa comptabilité.
     *
     * @param idEnr
     *          L'identifiant de l'enregistrement.
     * @param idCompte
     *          L'identifiant de la comptabilité.
     * @return
     *          La date de l'enregistrement.
     * @throws DaoException
     */
    public String lireDateEnregistrementFromIdCompteIdEnr(Integer idEnr, Integer idCompte) throws DaoException{
        try {     
            lireDateEnregistrementFromIdCompteIdEnr.setInt(1, idEnr);
            lireDateEnregistrementFromIdCompteIdEnr.setInt(2, idCompte);
            ResultSet rs = lireDateEnregistrementFromIdCompteIdEnr.executeQuery();
            String dateEnr = null;
            if (rs.next()) {
                dateEnr = rs.getString(1);
            }
            
            return dateEnr;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireDateEnregistrementFromIdCompteIdEnr : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire le solde d'une comptabilité pour l'impression en fonction de la date de début et de la date de fin.
     *
     * @param idCompte
     *          L'identifiant de comptabilité de l'enregistrement.
     * @param dateDebut
     *          La date de début des enregistrements.
     * @param dateFin
     *          La date de fin des enregistrements.
     * @return
     *          Le solde de la comptabilité à la date de fin.
     * @throws DaoException
     */
    public BigDecimal lireSoldeCompteImpression(Integer idCompte, java.sql.Date dateDebut, java.sql.Date dateFin)throws DaoException{
        try {            
            lireSoldeCompteImpression.setInt(1, idCompte);
            lireSoldeCompteImpression.setDate(2, dateDebut);
            lireSoldeCompteImpression.setDate(3, dateFin);
            lireSoldeCompteImpression.setDate(4, dateDebut);
            lireSoldeCompteImpression.setDate(5, dateFin);
            lireSoldeCompteImpression.setInt(6, idCompte);
            ResultSet rs = lireSoldeCompteImpression.executeQuery();
            BigDecimal solde = null;
            if (rs.next()) {
                solde = rs.getBigDecimal(1);
            }
            
            return solde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireSoldeCompteImpression : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire le solde d'une comptabilité.
     *
     * @param compte
     *          L'identifiant de comptabilité.
     * @return
     *          Le solde de la comptabilité.
     * @throws DaoException
     */
    public BigDecimal lireSoldeCompte(Integer compte) throws DaoException{
        try {    
            lireSoldeCompte.setInt(1, compte);
            ResultSet rs = lireSoldeCompte.executeQuery();
            BigDecimal solde = null;
            if (rs.next()) {
                solde = rs.getBigDecimal(1);
            }
            
            return solde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireSoldeCompte : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de lire le solde d'une comptabilité sans les anticipations.
     *
     * @param compte
     *          L'identifiant de la comptabilité.
     * @return
     *          Le solde de la comptabilité sans les anticipations.
     * @throws DaoException
     */
    public BigDecimal lireSoldeCompteSansAnticipation(Integer compte) throws DaoException{
        try {    
            lireDepenseAnticpation.setInt(1, compte);
            lireRecetteAnticpation.setInt(1, compte);
            ResultSet rsD = lireDepenseAnticpation.executeQuery();
            
            ResultSet rsR = lireRecetteAnticpation.executeQuery();
            
            BigDecimal recette = new BigDecimal(0);
            if (rsR.next()) {
                recette = rsR.getBigDecimal(1);
                if(recette == null){
                    recette = new BigDecimal(0);
                }
            }
            
            BigDecimal depense = new BigDecimal(0);
            if (rsD.next()) {
                depense = rsD.getBigDecimal(1);
                if(depense == null){
                    depense = new BigDecimal(0);
                }
            }
            
            
            
            lireSoldeCompte.setInt(1, compte);
            ResultSet rsC = lireSoldeCompte.executeQuery();
                        
            BigDecimal soldeSansAnticipation = new BigDecimal(0);
            if (rsC.next()) {
                
                soldeSansAnticipation = rsC.getBigDecimal(1).subtract(recette).add(depense);
            }
            
            return soldeSansAnticipation;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireSoldeCompteSansAnticipation : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet d'ajouter un enregistrement bancaire dan la base de données.
     *
     * @param id
     *          L'identifiant de l'enregistrement.
     * @param idLibelle
     *          L'identifiant du libellé de l'enregistrement.
     * @param idModeReglement
     *          L'identifiant du mode de règlement de l'enregistrement.
     * @param idCompte
     *          L'identifiant du compte de l'enregistrement.
     * @param idEtat
     *          L'identifiant de l'état de l'enregistrement.
     * @param idMotif
     *          L'identifiant du motif de l'entregistrement.
     * @param RecDep
     *          La valeur "Recette" si c'est une recette ou "Dépense" si c'est une dépense.
     * @param DateEnr
     *          La date de l'enregistrement.
     * @param montant
     *          Le montant de l'enregistrement.
     * @param ancienSolde
     *          Le solde de l'enregistrement précédent.
     * @param nouveauSolde
     *          Le nouveau solde calculé de l'enregistrement : montant + ancienSolde.
     * @param dateFacture
     *          La date de facture de l'enregistrement qui peut être nulle
     * @param numCHQ
     *          Le numéro de chèque de l'enregistrement qui peut être nul.
     * @param anticipation
     *          Peut être à VRAI si c'est un enregistrement anticipé, sinon à FAUX.
     * @param ordre
     *          L'ordre dans la base de données de l'enregistrement.
     * @return
     *          0 ou 1, 1 étant la réussite de l'ajout de l'enregistrement.
     * @throws DaoException
     */
    public int ajouterUnEnregistrement(Integer id, Integer idLibelle, Integer idModeReglement, Integer idCompte, Integer idEtat, Integer idMotif, String RecDep, java.sql.Date DateEnr, BigDecimal montant, BigDecimal ancienSolde, BigDecimal nouveauSolde, String dateFacture, String numCHQ, Boolean anticipation, Integer ordre) throws DaoException {
        try {
            
            ajouterEnregistrement.setInt(1, id);
            ajouterEnregistrement.setInt(2, idLibelle);
            ajouterEnregistrement.setInt(3, idModeReglement);
            ajouterEnregistrement.setInt(4, idCompte);
            ajouterEnregistrement.setInt(5, idEtat);
            ajouterEnregistrement.setInt(6, idMotif);
            ajouterEnregistrement.setString(7, RecDep);
            ajouterEnregistrement.setDate(8, DateEnr);
            ajouterEnregistrement.setBigDecimal(9, montant);
            ajouterEnregistrement.setBigDecimal(10, ancienSolde);
            ajouterEnregistrement.setBigDecimal(11, nouveauSolde);
            ajouterEnregistrement.setString(12, dateFacture);
            ajouterEnregistrement.setString(13, numCHQ);
            ajouterEnregistrement.setBoolean(14, anticipation);
            ajouterEnregistrement.setInt(15, ordre);
            
            int nb= ajouterEnregistrement.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - ajouterUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    /**
     *
     * Permet d'ajouter un libellé dans la base de données.
     * 
     * @param id
     *          L'identifiant du libellé.
     * @param libelle
     *          La valeur du libellé.
     * @param idCompte
     *          Le compte auquel est rattaché ce libellé.
     * @return
     *          0 ou 1, 1 étant la réussite de l'ajout de l'enregistrement.
     * @throws DaoException
     */
    public int ajouterLibelle(Integer id, String libelle, Integer idCompte) throws DaoException {
        try {
            
            ajouterLibelle.setInt(1, id);
            ajouterLibelle.setString(2, libelle);
            ajouterLibelle.setInt(3, idCompte);
            
            int nb= ajouterLibelle.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - ajouterLibelle : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    /**
     * 
     * Permet de récupérer le dernier identifiant des enregistrements de la base de données.
     *
     * @return
     *          Le dernier identifiant des enregistremnts de la base de données.
     * @throws DaoException
     */
    public Integer recupererDernierIdEnr() throws DaoException{
        try {                    
            ResultSet rs = recupererDernierIdEnr.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierIdEnr : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Récupérer le dernier solde d'un enregistrement en fonction de son identifiant.
     *
     * @param idEnr
     *          L'identifiant de l'enregistrement.
     * @return
     *          Le dernier solde de l'enregistrement.
     * @throws DaoException
     */
    public BigDecimal recupererDernierSolde(String annee, Integer idCompte) throws DaoException{
        try {        
            recupererDernierSolde.setString(1, annee);
            recupererDernierSolde.setString(2, annee);
            recupererDernierSolde.setInt(3, idCompte);
            recupererDernierSolde.setInt(4, idCompte);
            ResultSet rs = recupererDernierSolde.executeQuery();
            BigDecimal dernierSolde = null;
            if (rs.next()) {
                dernierSolde = rs.getBigDecimal(1);
            }
            
            return dernierSolde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierSolde : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de récupérer le dernier identifiant des libellés.
     *
     * @return
     *          Le dernier identifiant des libellés.
     * @throws DaoException
     */
    public Integer recupererDernierIdLibelle() throws DaoException{
        try {                    
            ResultSet rs = recupereDernierIdLibelle.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierIdLibelle : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de récupérer l'ancien solde du dernier des enregistrements en fonction d'une comptabilité.
     *
     * @param idCompte
     *          L'identifiant d'une comptabilité.
     * @return
     *          Le dernier ancien solde des enregistrements.
     * @throws DaoException
     */
    public BigDecimal recupererAncienDernierSolde(Integer idCompte) throws DaoException{
        try {     
            recupererAncienDernierSolde.setInt(1, idCompte);
            ResultSet rs = recupererAncienDernierSolde.executeQuery();
            BigDecimal solde = null;
            if (rs.next()) {
                solde = rs.getBigDecimal(1);
            }
            
            return solde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererAncienDernierSolde : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Récupérer le dernier solde 
     *
     * @param id
     * @param idCompte
     * @return
     * @throws DaoException
     */
    public BigDecimal recupererDernierSoldeRemiseCheques(Integer id, Integer idCompte) throws DaoException{
        try {        
            recupererDernierSoldeRemiseCheque.setInt(1, id);
            recupererDernierSoldeRemiseCheque.setInt(2, idCompte);
            ResultSet rs = recupererDernierSoldeRemiseCheque.executeQuery();
            BigDecimal solde = null;
            if (rs.next()) {
                solde = rs.getBigDecimal(1);
            }
            
            return solde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierSoldeRemiseCheques : pb JDBC\n" + ex.getMessage());
        }
    }
    
    
    
    /**
     * 
     * Récupérer le dernier ordre en fonction d'une comptabilité et d'une date d'un enregistrement.
     *
     * @param idCompte
     *          L'identifiant de comptabilité.
     * @param date
     *          La date de l'enregistrement.
     * @return
     *          Le dernier ordre des enregistrements.
     * @throws DaoException
     */
    public Integer recupererDernierOrdre(Integer idCompte, String date) throws DaoException{
        try {    
            recupererDernierOrdre.setInt(1, idCompte);
            recupererDernierOrdre.setString(2, date);
            ResultSet rs = recupererDernierOrdre.executeQuery();
            Integer ordre = null;
            if (rs.next()) {
                ordre = rs.getInt(1);
            }
            
            return ordre;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de mettre à jour le solde d'une comptabilité en fonction de son identifiant.
     *
     * @param idCompte
     *          L'identifiant de la comptabilité.
     * @param nouveauSolde
     *          Le nouveau solde pour la mise à jour.
     * @return
     *          0 ou 1, 1 étant la réussite de la mise à jour.
     * @throws DaoException
     */
    public int mettreAJourSoldeCompte(Integer idCompte, BigDecimal nouveauSolde) throws DaoException{
        try {    
            mettreAJourSoldeCompte.setBigDecimal(1, nouveauSolde);
            mettreAJourSoldeCompte.setInt(2, idCompte);
            int nb = mettreAJourSoldeCompte.executeUpdate();
            
            
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourSoldeCompte : pb JDBC\n" + ex.getMessage());
        }
    }
    
    
    
    /**
     * 
     * Permet de mettre à jour le nouveau solde et l'ancien solde d'un enregistrement d'une comptabilité en fonction de l'odre et de l'année de celui-ci.
     *
     * @param nouveauSolde
     *          Le nouveau solde pour la mise à jour.
     * @param ancienSolde
     *          L'anien solde pour la mise à jour.
     * @param idCompte
     *          L'identifiant de la comptabilité.
     * @param ordre
     *          L'ordre de l'enregistrement.
     * @param annee
     *          L'année de l'enregistrement.
     * @return
     *          0 ou 1, 1 étant la réussite de la mise à jour.
     * @throws DaoException
     */
    public int mettreAJourLesSoldes(BigDecimal nouveauSolde, BigDecimal ancienSolde, Integer idCompte, Integer ordre, String annee) throws DaoException {
        try{
            
            mettreAJourLesSoldes.setBigDecimal(1, nouveauSolde);
            mettreAJourLesSoldes.setBigDecimal(2, ancienSolde);
            mettreAJourLesSoldes.setInt(3, idCompte);
            mettreAJourLesSoldes.setInt(4, ordre);
            mettreAJourLesSoldes.setString(5, annee);
            
            int nb= mettreAJourLesSoldes.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourLesSoldes : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de faire l'ensemble des mise à jours lors d'un déplacement d'un enregistrement dans la JTable en fonction d'une comptabilité et d'une année.
     *
     * @param to
     *          L'ordre auquel l'enregistrement est déplacé.
     * @param from
     *          L'ordre duquel provient l'enregistrement déplacé.
     * @param idCompte
     *          L'identifiant de comptabilité.
     * @param annee
     *          L'année de l'enregistrement.
     * @throws DaoException
     */
    public void mettreAJourOrdre(Integer to, Integer from, int idCompte, String annee) throws DaoException, ParseException{
        
        Integer idEnrCompter  = lireIdEnrFromOrdreCompte(to+1, idCompte, "%" + annee + "%");
                
        Integer nbInf = compterNbOrdreIf(from, idCompte, "%" + annee + "%");
        Integer nbSup = compterNbOrdreSup(from, idCompte, "%" + annee + "%");
        
        try { 
            
            if(from > to){
                                
                int j = to;
                int k = to;
                
                BigDecimal nouveauSolde;
                BigDecimal ancienSolde;
              
                
                mettreAJourOrdre.setInt(1, to);
                mettreAJourOrdre.setInt(2, from);
                mettreAJourOrdre.setInt(3, idCompte);
                mettreAJourOrdre.setString(4, "%" + annee + "%");
                
                mettreAJourOrdre.executeUpdate(); 
                
                for(to=to;to<=nbInf;to++){
                    
                    
                   j = j + 1;
                    
                    Integer idEnr = lireIdEnrFromOrdreCompte(j-1, idCompte, "%" + annee + "%");
                    
                    mettreAJourOrdreInfSup(j, j-1, idEnr, idCompte, "%" + annee + "%");
                
                }
                
                if(k == 1){
                    
                    BigDecimal montant = lireMontantFromOrdre(k, idCompte, "%" + annee + "%");
                                     
                    int anneePrec = Integer.parseInt(annee);
                    
                    ancienSolde = recupererDernierSolde("%" + String.valueOf(anneePrec-1)+ "%", idCompte);
                    
                    if(verifierRecDep(k, idCompte, "%" + annee + "%") == false){
                        
                        mettreAJourLesSoldes(ancienSolde.add(montant), ancienSolde, idCompte, k, "%" + annee + "%");
                            
                    }else{
                        
                        mettreAJourLesSoldes(ancienSolde.subtract(montant), ancienSolde, idCompte, k, "%" + annee + "%");
                        
                    }
                    
                    for(int i=0; i<nbInf ; i++){

                       int l = k;
                       int ordre = k + i + 1; 
                       
                       try{
                           
                           if(verifierRecDep(ordre, idCompte, "%" + annee + "%") == false){
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").add(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                                
                           }else{
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").subtract(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                               
                           }
                           
                       }catch(Exception e){
                           
                       }
                        l = l - 1;
                    }
                }else{
                    
                    if(verifierRecDep(k, idCompte, "%" + annee + "%") == false){
                        
                        mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%").add(lireMontantFromOrdre(k, idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%"), idCompte, k, "%" + annee + "%");
                    
                    }else{
                        
                        mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%").subtract(lireMontantFromOrdre(k, idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%"), idCompte, k, "%" + annee + "%");
                  
                    } 
      
                    for(int i=0; i<k ; i++){

                       int l = k;
                       int ordre = k + i + 1; 
                       
                       try{
                           
                           if(verifierRecDep(ordre, idCompte, "%" + annee + "%") == false){
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").add(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                                
                           }else{
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").subtract(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                               
                           }
                           
                       }catch(Exception e){
                           
                       }
                       
                       l = l - 1;

                   }
                }
                
               
                System.out.print("annee" + annee + "idCompte" + idCompte);
                
                mettreAJourSoldeCompte(idCompte, recupererDernierSolde("%" + annee + "%", idCompte));
                
            }else{
                
                if(from < to){

                    
                    int j = from;
                    int k = from;

                    BigDecimal nouveauSolde;
                    BigDecimal ancienSolde = null;

                    mettreAJourOrdre.setInt(1, to);
                    mettreAJourOrdre.setInt(2, from);
                    mettreAJourOrdre.setInt(3, idCompte);
                    mettreAJourOrdre.setString(4, "%" + annee + "%");
                    

                    mettreAJourOrdre.executeUpdate();
                    
                    
                    

                    for(int i=0; i<=compterNbEnregistrement("%" + annee + "%", idCompte) ; i++){

                        j = j + 1;
                        Integer idEnr  = lireIdEnrFromOrdreCompte(j, idCompte, "%" + annee + "%");
                        
                        try{
                            
                           if(j<=to){

                                mettreAJourOrdreInfSup(j-1, j, idEnr, idCompte, "%" + annee + "%");

                            }else{

                                mettreAJourOrdreInfSup(j, j, idEnr, idCompte, "%" + annee + "%");

                            } 
                           
                        }catch(Exception e){

                        }
                    }
                    
                    if(k == 1){
                    
                    BigDecimal montant = lireMontantFromOrdre(k, idCompte, "%" + annee + "%");
                                     
                    int anneePrec = Integer.parseInt(annee);
                    
                    ancienSolde = recupererDernierSolde("%" + String.valueOf(anneePrec-1)+ "%", idCompte);
                    
                    if(verifierRecDep(k, idCompte, "%" + annee + "%") == false){
                        
                            mettreAJourLesSoldes(ancienSolde.add(montant), ancienSolde, idCompte, k, "%" + annee + "%");
                            
                    }else{
                        
                        mettreAJourLesSoldes(ancienSolde.subtract(montant), ancienSolde, idCompte, k, "%" + annee + "%");
                        
                    }
                    
                    for(int i=0; i<nbInf ; i++){

                       int l = k;
                       int ordre = k + i + 1; 
                       
                       try{
                           
                           if(verifierRecDep(ordre, idCompte, "%" + annee + "%") == false){
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").add(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                                
                           }else{
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").subtract(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                               
                           }
                           
                       }catch(Exception e){
                           
                       }
                        l = l - 1;
                    }
                        
                    }else{
                        
                        if(verifierRecDep(k, idCompte, "%" + annee + "%") == false){
                        
                        mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%").add(lireMontantFromOrdre(k, idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%"), idCompte, k, "%" + annee + "%");
                    
                    }else{
                        
                        mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%").subtract(lireMontantFromOrdre(k, idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(k-1, idCompte, "%" + annee + "%"), idCompte, k, "%" + annee + "%");
                  
                    }  

                        for(int i=0; i<=compterNbEnregistrement("%" + annee + "%", idCompte) ; i++){

                           int l = k;
                           int ordre = k + i + 1; 

                           try{

                               if(verifierRecDep(ordre, idCompte, "%" + annee + "%") == false){
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").add(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                                
                           }else{
                               
                                mettreAJourLesSoldes(lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%").subtract(lireMontantFromOrdre(l+(i+1), idCompte, "%" + annee + "%")), lireNouveauSoldeFromOrdre(l+i, idCompte, "%" + annee + "%"), idCompte, ordre, "%" + annee + "%");
                               
                           }

                           }catch(Exception e){

                           }

                            if(j<=to){
                                l = l - 1;
                            }else{
                                l = l + 1;
                            }

                       }
                    }
                }
                
                mettreAJourSoldeCompte(idCompte, recupererDernierSolde("%" + annee + "%", idCompte));

            }
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     * 
     * Permet de modifier un enregistrement en fonction de son identifiant et son année.
     *
     * @param id
     * @param idLibelle
     * @param idModeReglement
     * @param idCompte
     * @param idEtat
     * @param idMotif
     * @param RecDep
     * @param DateEnr
     * @param montant
     * @param ancienSolde
     * @param nouveauSolde
     * @param dateFacture
     * @param numCHQ
     * @param anticipation
     * @param annee
     * @return
     * @throws DaoException
     */
    public int modifierUnEnregistrement(Integer id, Integer idLibelle, Integer idModeReglement, Integer idCompte, Integer idEtat, Integer idMotif, String RecDep, String DateEnr, BigDecimal montant, BigDecimal ancienSolde, BigDecimal nouveauSolde, String dateFacture, String numCHQ, Boolean anticipation, String annee) throws DaoException {
        try {
            
            mettreAJourEnregistrement.setInt(14, id);
            mettreAJourEnregistrement.setInt(1, idLibelle);
            mettreAJourEnregistrement.setInt(2, idModeReglement);
            mettreAJourEnregistrement.setInt(3, idCompte);
            mettreAJourEnregistrement.setInt(4, idMotif);
            mettreAJourEnregistrement.setInt(5, idEtat);
            mettreAJourEnregistrement.setString(6, RecDep);
            mettreAJourEnregistrement.setString(7, DateEnr);
            mettreAJourEnregistrement.setBigDecimal(8, montant);
            mettreAJourEnregistrement.setBigDecimal(9, ancienSolde);
            mettreAJourEnregistrement.setBigDecimal(10, nouveauSolde);
            mettreAJourEnregistrement.setString(11, dateFacture);
            mettreAJourEnregistrement.setString(12, numCHQ);
            mettreAJourEnregistrement.setBoolean(13, anticipation);
            mettreAJourEnregistrement.setString(15,annee);
            
            int nb= mettreAJourEnregistrement.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - modifierUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    /**
     *
     * @param ordre
     * @param idLibelle
     * @param idModeReglement
     * @param idEtat
     * @param idMotif
     * @param DateEnr
     * @param montant
     * @param nouveauSolde
     * @param dateFacture
     * @param anticipation
     * @param numchq
     * @param idCompte
     * @param annee
     * @return
     * @throws DaoException
     */
    public int modifierUnEnregistrementFromOrdre(Integer ordre, Integer idLibelle, Integer idModeReglement, Integer idEtat, Integer idMotif, java.sql.Date DateEnr, BigDecimal montant, BigDecimal nouveauSolde, String dateFacture, Boolean anticipation, String numchq, Integer idCompte, String annee) throws DaoException {
        try {
            
            mettreAJourEnregistrementFromOrdre.setInt(12, idCompte);
            mettreAJourEnregistrementFromOrdre.setInt(11, ordre);
            mettreAJourEnregistrementFromOrdre.setInt(1, idLibelle);
            mettreAJourEnregistrementFromOrdre.setInt(2, idModeReglement);
            mettreAJourEnregistrementFromOrdre.setInt(3, idMotif);
            mettreAJourEnregistrementFromOrdre.setInt(4, idEtat);
            mettreAJourEnregistrementFromOrdre.setDate(5, DateEnr);
            mettreAJourEnregistrementFromOrdre.setBigDecimal(6, montant);
            mettreAJourEnregistrementFromOrdre.setBigDecimal(7, nouveauSolde);
            mettreAJourEnregistrementFromOrdre.setString(8, dateFacture);
            mettreAJourEnregistrementFromOrdre.setBoolean(9, anticipation);
            mettreAJourEnregistrementFromOrdre.setString(10, numchq);
            mettreAJourEnregistrementFromOrdre.setString(13, annee);
            
            int nb= mettreAJourEnregistrementFromOrdre.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - modifierUnEnregistrementFromOrdre : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    
    
   
    
    /**
     *
     * @param nouvelOrdre
     * @param ancienOrdre
     * @param id
     * @param idCompte
     * @param annee
     * @return
     * @throws DaoException
     */
    public int mettreAJourOrdreInfSup(Integer nouvelOrdre, Integer ancienOrdre, Integer id, Integer idCompte, String annee) throws DaoException {
        try {
            
            mettreAJourOrdreInfSup.setInt(1, nouvelOrdre);
            mettreAJourOrdreInfSup.setInt(2, ancienOrdre);
            mettreAJourOrdreInfSup.setInt(3, id);
            mettreAJourOrdreInfSup.setInt(4, idCompte);
            mettreAJourOrdreInfSup.setString(5, annee);
                    
            int nb= mettreAJourOrdreInfSup.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourOrdreInfSup : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    /**
     *
     * @param ordre
     * @param idCompte
     * @param annee
     * @return
     * @throws DaoException
     */
    public int supprimerEnregistrementFromOrdre(Integer ordre, Integer idCompte, String annee) throws DaoException{
        try{
            
            supprimerEnregistrementFromOrdre.setInt(1, ordre);
            supprimerEnregistrementFromOrdre.setInt(2, idCompte);
            supprimerEnregistrementFromOrdre.setString(3,annee);
            
            int nb= supprimerEnregistrementFromOrdre.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - supprimerEnregistrementFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * @param ordre
     * @param idCompte
     * @param annee
     * @return
     * @throws DaoException
     */
    public Boolean verifierRecDep(Integer ordre, Integer idCompte, String annee) throws DaoException{
        try {    
            verifierRecDep.setInt(1, ordre);
            verifierRecDep.setInt(2, idCompte);
            verifierRecDep.setString(3, annee);
            ResultSet rs = verifierRecDep.executeQuery();
            Boolean depense = null;
            if (rs.next()) {
                String recdep = rs.getString(1);
            
            if( "Dépense".equals(recdep)){
                depense = true;
            }else{
                depense = false;
            }
            }
            return depense;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireMontantFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * @param to
     * @param idCompte
     * @param date
     * @return
     * @throws DaoException
     */
    public Integer compterNbOrdreIf(Integer to, Integer idCompte, String date) throws DaoException{
        try {                  
            compterNbOrdreInf.setInt(2, to);
            compterNbOrdreInf.setInt(1, idCompte);
            compterNbOrdreInf.setString(3, date);
            ResultSet rs = compterNbOrdreInf.executeQuery();
            Integer nbInf = null;
            if (rs.next()) {
                nbInf = rs.getInt(1);
            }
            
            return nbInf;
        } catch (SQLException ex) {
            throw new DaoException("DAO - compterNbOrdreIf : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * @param to
     * @param idCompte
     * @param date
     * @return
     * @throws DaoException
     */
    public Integer compterNbOrdreSup(Integer to, Integer idCompte, String date) throws DaoException{
        try {                  
            compterNbOrdreSup.setInt(2, to);
            compterNbOrdreSup.setInt(1, idCompte);
            compterNbOrdreSup.setString(3, date);
            ResultSet rs = compterNbOrdreSup.executeQuery();
            Integer nbSup = null;
            if (rs.next()) {
                nbSup = rs.getInt(1);
            }
            
            return nbSup;
        } catch (SQLException ex) {
            throw new DaoException("DAO - compterNbOrdreSup : pb JDBC\n" + ex.getMessage());
        }
    }
    
    /**
     *
     * @param valeur
     * @param idCompte
     * @param annee
     * @return
     * @throws DaoException
     */
    public List<Enregistrement> rechercherValeurEnregistrement(String valeur, Integer idCompte, String annee) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            rechercherValeurEnregistrement.setString(1, valeur);   
            rechercherValeurEnregistrement.setInt(2, idCompte);
            rechercherValeurEnregistrement.setString(3, annee);
            ResultSet rs = rechercherValeurEnregistrement.executeQuery();
            while (rs.next()) {
                Enregistrement unEnregistrement = chargerUnEnregistrementEnregistrement(rs);
                desEnregistrements.add(unEnregistrement);
            }            
            return desEnregistrements;
        } catch (SQLException ex) {
            throw new DaoException("DAO - rechercherValeurEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
    
     /**
     *
     * @param annee
     * @param idCompte
     * @return
     * @throws DaoException
     */
    public int compterNbEnregistrement(String annee, Integer idCompte) throws DaoException{
        try {    
            compterNbEnregistrement.setString(1, annee);
            compterNbEnregistrement.setInt(2, idCompte);
            ResultSet rs = compterNbEnregistrement.executeQuery();
            int nbEnregistrement = 0;
            if (rs.next()) {
                nbEnregistrement = rs.getInt(1);
            }
            
            return nbEnregistrement;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireSoldeCompte : pb JDBC\n" + ex.getMessage());
        }
    }
}
