package modele.dao;



import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Compte;
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
 * @author btssio
 * @version finale (après modification de la BD)
 */
public abstract class Dao {

    private String piloteJdbc;
    private String urlBd;
    private String loginBd;
    private String mdpBd;
    private Connection cnx;
    
   private PreparedStatement lireToutesLesBanques;
   private PreparedStatement lireMotifsFromDepRec;
   private PreparedStatement lireTousLesModesReglements;
   private PreparedStatement lireTousLesEtats;
   private PreparedStatement lireTousLesLibelles;
   private PreparedStatement ajouterEnregistrement;
   private PreparedStatement recupererDernierIdEnr;
   private PreparedStatement lireSoldeCompte;
   private PreparedStatement mettreAJourSoldeCompte;
   private PreparedStatement ajouterLibelle;
   private PreparedStatement recupereDernierIdLibelle;
   private PreparedStatement mettreAJourEnregistrement;
   private PreparedStatement recupererAncienDernierSolde;
   private PreparedStatement recupererDernierSoldeRemiseCheque;
   private PreparedStatement lireTousLesEnregistrements;
   private PreparedStatement lireRecetteAnticpation;
   private PreparedStatement lireDepenseAnticpation;
   private PreparedStatement lireEnregistrementsRecDepAnt;
   private PreparedStatement lireEnregistrementsAnt;
   private PreparedStatement recupererDernierOrdre;
   private PreparedStatement mettreAJourOrdre;
   private PreparedStatement mettreAJourOrdreInfSup;
   private PreparedStatement compterNbOrdreInf;
   private PreparedStatement compterNbOrdreSup;
   private PreparedStatement lireIdEnrFromOrdreCompte;
   private PreparedStatement mettreAJourLesSoldes;
   private PreparedStatement lireNouveauSoldeFromOrdre;
   private PreparedStatement lireMontantFromOrdre;
   private PreparedStatement recupererDernierSolde;
   private PreparedStatement verifierRecDep;
   private PreparedStatement compterNbEnregistrement;
   private PreparedStatement mettreAJourEnregistrementFromOrdre;
   private PreparedStatement lireIdLibelleFromLibelle;
   private PreparedStatement lireIdModeReglementFromLibelle;
   private PreparedStatement lireIdEtatFromLibelle;
   private PreparedStatement lireIdMotifFromLibelle;
   private PreparedStatement supprimerEnregistrementFromOrdre;
   

    public Dao(String piloteJdbc, String urlBd, String loginBd, String mdpBd) {
        this.piloteJdbc = piloteJdbc;
        this.urlBd = urlBd;
        this.loginBd = loginBd;
        this.mdpBd = mdpBd;
    }

    public void connecter() throws DaoException {
        try {
            Class.forName(piloteJdbc);
            cnx = DriverManager.getConnection(urlBd, loginBd, mdpBd);
            
            
           lireToutesLesBanques = cnx.prepareStatement("SELECT * FROM COMPTE");
           lireMotifsFromDepRec = cnx.prepareStatement("SELECT * FROM MOTIF WHERE recetteDepense = ?");
           lireTousLesModesReglements = cnx.prepareStatement("SELECT * FROM MODEREGLEMENT");
           lireTousLesEtats = cnx.prepareStatement("SELECT * FROM ETAT");
           lireTousLesLibelles = cnx.prepareStatement("SELECT * FROM LIBELLE ORDER BY LIBELLE");
           ajouterEnregistrement = cnx.prepareStatement("INSERT INTO ENREGISTREMENT VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
           recupererDernierIdEnr = cnx.prepareStatement("SELECT MAX(ID) FROM ENREGISTREMENT");
           lireSoldeCompte = cnx.prepareStatement("SELECT SOLDEDEPART FROM COMPTE WHERE ID = ?");
           mettreAJourSoldeCompte = cnx.prepareStatement("UPDATE COMPTE SET SOLDEDEPART = ? WHERE ID = ?");
           ajouterLibelle = cnx.prepareStatement("INSERT INTO LIBELLE VALUES (?, ?)");
           recupereDernierIdLibelle = cnx.prepareStatement("SELECT MAX(ID) FROM LIBELLE");
           mettreAJourEnregistrement = cnx.prepareStatement("UPDATE ENREGISTREMENT SET ID_LIBELLE = ?, ID_MODEREGLEMENT = ?, ID_COMPTE = ?, ID_MOTIF = ?, ID_ETAT = ?, RECETTEDEPENSE = ?, DATEENREGISTREMENT = ?, MONTANT = ?, ANCIENSOLDE =?, NOUVEAUSOLDE = ?, DATEFACTURE = ?, NUMCHQ = ?, ANTICIPATION = ? WHERE ID = ? ");
           mettreAJourEnregistrementFromOrdre= cnx.prepareStatement("UPDATE ENREGISTREMENT SET ID_LIBELLE = ?, ID_MODEREGLEMENT = ?, ID_MOTIF = ?, ID_ETAT = ?, DATEENREGISTREMENT = ?, MONTANT = ?, NOUVEAUSOLDE = ?, DATEFACTURE = ?, ANTICIPATION = ?, NUMCHQ = ? WHERE ORDRE = ? AND ID_COMPTE = ?");
           recupererAncienDernierSolde = cnx.prepareStatement("SELECT ANCIENSOLDE  FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ID IN (SELECT MAX (ID) FROM ENREGISTREMENT)");
           recupererDernierSoldeRemiseCheque = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE ID = ? AND ID_COMPTE = ?");
           lireTousLesEnregistrements = cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ID_COMPTE = ? "
                   + "AND DATEENREGISTREMENT LIKE ?"
                   + "ORDER BY ORDRE");
           lireRecetteAnticpation = cnx.prepareStatement("SELECT SUM(MONTANT) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ANTICIPATION = TRUE AND RECETTEDEPENSE = 'Recette'");
           lireDepenseAnticpation = cnx.prepareStatement("SELECT SUM(MONTANT) FROM ENREGISTREMENT WHERE ID_COMPTE =  ? AND ANTICIPATION = TRUE AND RECETTEDEPENSE = 'Dépense' "
                   + "AND ANTICIPATION = TRUE AND RECETTEDEPENSE = 'Dépense' ");
           lireEnregistrementsRecDepAnt = cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ID_COMPTE = ? "
                   + "AND ENREGISTREMENT.RECETTEDEPENSE = ? "
                   + "AND ANTICIPATION = ? "
                   + "ORDER BY ORDRE");
           lireEnregistrementsAnt = cnx.prepareStatement("SELECT * FROM ENREGISTREMENT "
                   + "INNER JOIN MOTIF ON ID_MOTIF = MOTIF.ID "
                   + "INNER JOIN MODEREGLEMENT ON ID_MODEREGLEMENT = MODEREGLEMENT.ID "
                   + "INNER JOIN LIBELLE ON ID_LIBELLE = LIBELLE.ID "
                   + "INNER JOIN ETAT ON ID_ETAT = ETAT.ID "
                   + "WHERE ID_COMPTE = ? "
                   + "AND ANTICIPATION = ? "
                   + "ORDER BY ORDRE");
           recupererDernierOrdre = cnx.prepareStatement("SELECT MAX(ORDRE) FROM ENREGISTREMENT WHERE ID_COMPTE = ?");
           mettreAJourOrdre = cnx.prepareStatement("UPDATE ENREGISTREMENT SET ORDRE = ? WHERE ORDRE = ? AND ID_COMPTE = ?");
           mettreAJourOrdreInfSup = cnx.prepareStatement("UPDATE ENREGISTREMENT SET ORDRE = ? WHERE ORDRE = ? AND ID = ? AND ID_COMPTE = ?");
           compterNbOrdreInf = cnx.prepareStatement("SELECT COUNT(*) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ORDRE < ?");
           compterNbOrdreSup = cnx.prepareStatement("SELECT COUNT(*) FROM ENREGISTREMENT WHERE ID_COMPTE = ? AND ORDRE > ?");
           lireIdEnrFromOrdreCompte = cnx.prepareStatement("SELECT ID FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ?");
           mettreAJourLesSoldes = cnx.prepareStatement("UPDATE ENREGISTREMENT SET NOUVEAUSOLDE = ?, ANCIENSOLDE = ? WHERE ID_COMPTE = ? AND ORDRE = ?");
           lireNouveauSoldeFromOrdre = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ?");
           lireMontantFromOrdre = cnx.prepareStatement("SELECT MONTANT FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ?");
           recupererDernierSolde = cnx.prepareStatement("SELECT NOUVEAUSOLDE FROM ENREGISTREMENT WHERE ORDRE = (SELECT MAX(ORDRE) FROM ENREGISTREMENT) AND ID_COMPTE = ?");
           verifierRecDep = cnx.prepareStatement("SELECT RECETTEDEPENSE FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ?");
           compterNbEnregistrement = cnx.prepareStatement("SELECT COUNT(*) FROM ENREGISTREMENT");
           lireIdLibelleFromLibelle = cnx.prepareStatement("SELECT ID FROM LIBELLE WHERE LIBELLE = ?");
           lireIdModeReglementFromLibelle = cnx.prepareStatement("SELECT ID FROM MODEREGLEMENT WHERE LIBELLE = ?");
           lireIdEtatFromLibelle = cnx.prepareStatement("SELECT ID FROM ETAT WHERE LIBELLE = ?");
           lireIdMotifFromLibelle = cnx.prepareStatement("SELECT ID FROM MOTIF WHERE LIBELLE = ?");
           supprimerEnregistrementFromOrdre = cnx.prepareStatement("DELETE FROM ENREGISTREMENT WHERE ORDRE = ? AND ID_COMPTE = ?");
           
           
           
        } catch (SQLException ex) {
            throw new DaoException("DAO - connecter : pb de connexion\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new DaoException("DAO - connecter : pb de pilote JDBC\n" + ex.getMessage());
        }
    }

    public void deconnecter() throws DaoException {
        try {
            cnx.close();
        } catch (SQLException ex) {
            throw new DaoException("DAO - déconnecter : pb JDBC\n" + ex.getMessage());
        }
    }
    
    
    
   public List<Compte> lireToutesLesBanques() throws DaoException{
        try {
            List<Compte> desComptes = new ArrayList<Compte>();
            ResultSet rs = lireToutesLesBanques.executeQuery();
            while (rs.next()) {
                Compte unVisiteur = chargerUnEnregistrementCompte(rs);
                desComptes.add(unVisiteur);
            }            
            return desComptes;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesVisiteurs : pb JDBC\n" + ex.getMessage());
        }
    }

    private Compte chargerUnEnregistrementCompte(ResultSet rs) throws DaoException {
        try {
            
            Compte compte = new Compte();
            compte.setId(rs.getInt("ID"));
            compte.setLibelle(rs.getString("LIBELLE"));
            compte.setSoldeDepart(rs.getFloat("SOLDEDEPART"));
            
            return compte;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementCompte : pb JDBC\n" + ex.getMessage());
        }
    }
    
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
    
    public int ajouterUnEnregistrement(Integer id, Integer idLibelle, Integer idModeReglement, Integer idCompte, Integer idEtat, Integer idMotif, String RecDep, String DateEnr, BigDecimal montant, BigDecimal ancienSolde, BigDecimal nouveauSolde, String dateFacture, String numCHQ, Boolean anticipation, Integer ordre) throws DaoException {
        try {
            
            ajouterEnregistrement.setInt(1, id);
            ajouterEnregistrement.setInt(2, idLibelle);
            ajouterEnregistrement.setInt(3, idModeReglement);
            ajouterEnregistrement.setInt(4, idCompte);
            ajouterEnregistrement.setInt(5, idEtat);
            ajouterEnregistrement.setInt(6, idMotif);
            ajouterEnregistrement.setString(7, RecDep);
            ajouterEnregistrement.setString(8, DateEnr);
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
    
    public Float recupererDernierSolde(Integer idCompte) throws DaoException{
        try {        
            recupererDernierSolde.setInt(1, idCompte);
            ResultSet rs = recupererDernierSolde.executeQuery();
            Float dernierSolde = null;
            if (rs.next()) {
                dernierSolde = rs.getFloat(1);
            }
            
            return dernierSolde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierIdEnr : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public Float lireSoldeCompte(Integer compte) throws DaoException{
        try {    
            lireSoldeCompte.setInt(1, compte);
            ResultSet rs = lireSoldeCompte.executeQuery();
            Float solde = null;
            if (rs.next()) {
                solde = rs.getFloat(1);
            }
            
            return solde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireSoldeCompte : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public int compterNbEnregistrement() throws DaoException{
        try {    
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

    
    public Float lireSoldeCompteSansAnticipation(Integer compte) throws DaoException{
        try {    
            lireDepenseAnticpation.setInt(1, compte);
            lireRecetteAnticpation.setInt(1, compte);
            ResultSet rsD = lireDepenseAnticpation.executeQuery();
            
            ResultSet rsR = lireRecetteAnticpation.executeQuery();
            
            Float recette = null;
            if (rsR.next()) {
                recette = rsR.getFloat(1);
            }
            
            Float depense = null;
            if (rsD.next()) {
                depense = rsD.getFloat(1);
            }
            
            lireSoldeCompte.setInt(1, compte);
            ResultSet rsC = lireSoldeCompte.executeQuery();
            
            Float soldeSansAnticipation = null;
            if (rsC.next()) {
                soldeSansAnticipation = rsC.getFloat(1) - recette + depense;
            }
            
            return soldeSansAnticipation;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireSoldeCompte : pb JDBC\n" + ex.getMessage());
        }
    }
    
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
    
    
    
    public int mettreAJourLesSoldes(BigDecimal nouveauSolde, BigDecimal ancienSolde, Integer idCompte, Integer ordre) throws DaoException {
        try{
            
            mettreAJourLesSoldes.setBigDecimal(1, nouveauSolde);
            mettreAJourLesSoldes.setBigDecimal(2, ancienSolde);
            mettreAJourLesSoldes.setInt(3, idCompte);
            mettreAJourLesSoldes.setInt(4, ordre);
            
            int nb= mettreAJourLesSoldes.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - modifierUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public int supprimerEnregistrementFromOrdre(Integer ordre, Integer idCompte) throws DaoException{
        try{
            
            supprimerEnregistrementFromOrdre.setInt(1, ordre);
            supprimerEnregistrementFromOrdre.setInt(2, idCompte);
            
            int nb= supprimerEnregistrementFromOrdre.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - supprimerEnregistrementFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public Float lireNouveauSoldeFromOrdre(Integer ordre, Integer idCompte) throws DaoException{
        try {    
            lireNouveauSoldeFromOrdre.setInt(1, ordre);
            lireNouveauSoldeFromOrdre.setInt(2, idCompte);
            ResultSet rs = lireNouveauSoldeFromOrdre.executeQuery();
            Float nouveauSolde = null;
            if (rs.next()) {
                nouveauSolde = rs.getFloat(1);
            }
            
            return nouveauSolde;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireNouveauSoldeFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public Float lireMontantFromOrdre(Integer ordre, Integer idCompte) throws DaoException{
        try {    
            lireMontantFromOrdre.setInt(1, ordre);
            lireMontantFromOrdre.setInt(2, idCompte);
            ResultSet rs = lireMontantFromOrdre.executeQuery();
            Float montant = null;
            if (rs.next()) {
                montant = rs.getFloat(1);
            }
            
            return montant;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireMontantFromOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public Boolean verifierRecDep(Integer ordre, Integer idCompte) throws DaoException{
        try {    
            verifierRecDep.setInt(1, ordre);
            verifierRecDep.setInt(2, idCompte);
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
    
    public void mettreAJourOrdreCIO(Integer to, Integer from) throws DaoException{
        
        Integer nbInf = compterNbOrdreIf(from, 1);
        Integer nbSup = compterNbOrdreSup(from, 1);
        
        try { 
            
            
            
            if(from > to){
                
                int j = to;
                int k = to;
                
                BigDecimal nouveauSolde;
                BigDecimal ancienSolde;
              
                mettreAJourOrdre.setInt(1, to);
                mettreAJourOrdre.setInt(2, from);
                mettreAJourOrdre.setInt(3, 1);
                
                mettreAJourOrdre.executeUpdate(); 
                
                for(to=to;to<=nbInf;to++){
                    
                   j = j + 1;
                    
                    Integer idEnr = lireIdEnrFromOrdreCompte(j-1, 1);
                    
                    mettreAJourOrdreInfSup(j, j-1, idEnr, 1);
                
                }
                
                if(k == 1){
                    
                    nouveauSolde = new BigDecimal(lireMontantFromOrdre(k+nbSup, 1));
                    
                    if(verifierRecDep(k, 1) == false){
                        
                            mettreAJourLesSoldes(nouveauSolde, new BigDecimal(0), 1, k);
                            
                    }else{
                        
                        mettreAJourLesSoldes(nouveauSolde.negate(), new BigDecimal(0), 1, k);
                        
                    }
                }else{
                    
                    if(verifierRecDep(k, 1) == false){
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)).add(new BigDecimal(lireMontantFromOrdre(k, 1))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)), 1, k);
                    
                    }else{
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)).subtract(new BigDecimal(lireMontantFromOrdre(k, 1))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)), 1, k);
                  
                    } 
      
                    for(int i=0; i<k ; i++){

                       int l = k;
                       int ordre = k + i + 1; 
                       
                       try{
                           
                           if(verifierRecDep(ordre, 1) == false){
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)).add(new BigDecimal(lireMontantFromOrdre(l+(i+1), 1))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)), 1, ordre);
                                
                           }else{
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)).subtract(new BigDecimal(lireMontantFromOrdre(l+(i+1), 1))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)), 1, ordre);
                               
                           }
                           
                       }catch(Exception e){
                           
                       }
                       
                       l = l - 1;

                   }
                }
                
                mettreAJourSoldeCompte(1, new BigDecimal(recupererDernierSolde(1)));
                
            }else{
                
                if(from < to){

                    int j = from;
                    int k = from;

                    BigDecimal nouveauSolde;
                    BigDecimal ancienSolde = null;

                    mettreAJourOrdre.setInt(1, to);
                    mettreAJourOrdre.setInt(2, from);
                    mettreAJourOrdre.setInt(3, 1);
                    

                    mettreAJourOrdre.executeUpdate();

                    for(int i=0; i<=compterNbEnregistrement() ; i++){

                        j = j + 1;
                        Integer idEnr  = lireIdEnrFromOrdreCompte(j, 1);
                        
                        try{
                            
                           if(j<=to){

                                mettreAJourOrdreInfSup(j-1, j, idEnr, 1);

                            }else{

                                mettreAJourOrdreInfSup(j, j, idEnr, 1);

                            } 
                           
                        }catch(Exception e){

                        }
                    }
                    
                    if(k == 1){

                        nouveauSolde = new BigDecimal(lireMontantFromOrdre(k+nbInf, 1));
                        
                        if(verifierRecDep(k, 1) == false){
                            
                            mettreAJourLesSoldes(nouveauSolde, new BigDecimal(0), 1, k);
                                
                        }else{
                            
                            mettreAJourLesSoldes(nouveauSolde.negate(), new BigDecimal(0), 1, k);
                            
                        }
                        
                    }else{
                        
                        if(verifierRecDep(k, 1) == false){
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)).add(new BigDecimal(lireMontantFromOrdre(k, 1))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)), 1, k);
                    
                    }else{
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)).subtract(new BigDecimal(lireMontantFromOrdre(k, 1))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 1)), 1, k);
                  
                    }  

                        for(int i=0; i<=compterNbEnregistrement() ; i++){

                           int l = k;
                           int ordre = k + i + 1; 

                           try{

                               if(verifierRecDep(ordre, 1) == false){
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)).add(new BigDecimal(lireMontantFromOrdre(l+(i+1), 1))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)), 1, ordre);
                                
                           }else{
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)).subtract(new BigDecimal(lireMontantFromOrdre(l+(i+1), 1))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 1)), 1, ordre);
                               
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
                
                mettreAJourSoldeCompte(1, new BigDecimal(recupererDernierSolde(1)));

            }
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public void mettreAJourOrdreCM(Integer to, Integer from) throws DaoException{
        
        Integer nbInf = compterNbOrdreIf(from, 2);
        Integer nbSup = compterNbOrdreSup(from, 2);
        
        try { 
            
            
            
            if(from > to){
                
                int j = to;
                int k = to;
                
                BigDecimal nouveauSolde;
                BigDecimal ancienSolde;
              
                mettreAJourOrdre.setInt(1, to);
                mettreAJourOrdre.setInt(2, from);
                mettreAJourOrdre.setInt(3, 2);
                
                mettreAJourOrdre.executeUpdate(); 
                
                for(to=to;to<=nbInf;to++){
                    
                   j = j + 1;
                    
                    Integer idEnr = lireIdEnrFromOrdreCompte(j-1, 2);
                    
                    mettreAJourOrdreInfSup(j, j-1, idEnr, 2);
                
                }
                
                if(k == 1){
                    
                    nouveauSolde = new BigDecimal(lireMontantFromOrdre(k+nbSup, 2));
                    
                    if(verifierRecDep(k, 2) == false){
                        
                            mettreAJourLesSoldes(nouveauSolde, new BigDecimal(0), 2, k);
                            
                    }else{
                        
                        mettreAJourLesSoldes(nouveauSolde.negate(), new BigDecimal(0), 2, k);
                        
                    }
                }else{
                    
                    if(verifierRecDep(k, 2) == false){
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)).add(new BigDecimal(lireMontantFromOrdre(k, 2))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)), 2, k);
                    
                    }else{
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)).subtract(new BigDecimal(lireMontantFromOrdre(k, 2))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)), 2, k);
                  
                    }  
      
                    for(int i=0; i<k ; i++){

                       int l = k;
                       int ordre = k + i + 1; 
                       
                       try{
                           
                           if(verifierRecDep(ordre, 2) == false){
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)).add(new BigDecimal(lireMontantFromOrdre(l+(i+1), 2))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)), 2, ordre);
                                
                           }else{
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)).subtract(new BigDecimal(lireMontantFromOrdre(l+(i+1), 2))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)), 2, ordre);
                               
                           }
                           
                       }catch(Exception e){
                           
                       }
                       
                       l = l - 1;

                   }
                }
                
                mettreAJourSoldeCompte(2, new BigDecimal(recupererDernierSolde(2)));
                
            }else{
                
                if(from < to){

                    int j = from;
                    int k = from;

                    BigDecimal nouveauSolde;
                    BigDecimal ancienSolde = null;

                    mettreAJourOrdre.setInt(1, to);
                    mettreAJourOrdre.setInt(2, from);
                    mettreAJourOrdre.setInt(3, 2);
                    

                    mettreAJourOrdre.executeUpdate();

                    for(int i=0; i<=compterNbEnregistrement() ; i++){

                        j = j + 1;
                        Integer idEnr  = lireIdEnrFromOrdreCompte(j, 2);
                        
                        try{
                            
                           if(j<=to){

                                mettreAJourOrdreInfSup(j-1, j, idEnr, 2);

                            }else{

                                mettreAJourOrdreInfSup(j, j, idEnr, 2);

                            } 
                           
                        }catch(Exception e){

                        }
                    }
                    
                    if(k == 1){

                        nouveauSolde = new BigDecimal(lireMontantFromOrdre(k+nbInf, 2));
                        
                        if(verifierRecDep(k, 2) == false){
                        
                            mettreAJourLesSoldes(nouveauSolde, new BigDecimal(0), 2, k);
                            
                    }else{
                        
                        mettreAJourLesSoldes(nouveauSolde.negate(), new BigDecimal(0), 2, k);
                        
                    }
                        
                    }else{
                        
                        if(verifierRecDep(k, 2) == false){
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)).add(new BigDecimal(lireMontantFromOrdre(k, 2))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)), 2, k);
                    
                    }else{
                        
                        mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)).subtract(new BigDecimal(lireMontantFromOrdre(k, 2))), new BigDecimal(lireNouveauSoldeFromOrdre(k-1, 2)), 2, k);
                  
                    }  

                        for(int i=0; i<=compterNbEnregistrement() ; i++){

                           int l = k;
                           int ordre = k + i + 1; 

                           try{

                               if(verifierRecDep(ordre, 2) == false){
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)).add(new BigDecimal(lireMontantFromOrdre(l+(i+1), 2))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)), 2, ordre);
                                
                           }else{
                               
                                mettreAJourLesSoldes(new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)).subtract(new BigDecimal(lireMontantFromOrdre(l+(i+1), 2))), new BigDecimal(lireNouveauSoldeFromOrdre(l+i, 2)), 2, ordre);
                               
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
                
                mettreAJourSoldeCompte(2, new BigDecimal(recupererDernierSolde(2)));

            }
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourOrdre : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public int ajouterLibelle(Integer id, String libelle) throws DaoException {
        try {
            
            ajouterLibelle.setInt(1, id);
            ajouterLibelle.setString(2, libelle);
            
            int nb= ajouterLibelle.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - ajouterLibelle : pb JDBC\n" + ex.getMessage());
        }        
    }
    
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
    
    public Integer compterNbOrdreIf(Integer to, Integer idCompte) throws DaoException{
        try {                  
            compterNbOrdreInf.setInt(2, to);
            compterNbOrdreInf.setInt(1, idCompte);
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
    
    public Integer compterNbOrdreSup(Integer to, Integer idCompte) throws DaoException{
        try {                  
            compterNbOrdreSup.setInt(2, to);
            compterNbOrdreSup.setInt(1, idCompte);
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
    
    public int modifierUnEnregistrement(Integer id, Integer idLibelle, Integer idModeReglement, Integer idCompte, Integer idEtat, Integer idMotif, String RecDep, String DateEnr, BigDecimal montant, BigDecimal ancienSolde, BigDecimal nouveauSolde, String dateFacture, String numCHQ, Boolean anticipation) throws DaoException {
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
            
            int nb= mettreAJourEnregistrement.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - modifierUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    public int modifierUnEnregistrementFromOrdre(Integer ordre, Integer idLibelle, Integer idModeReglement, Integer idEtat, Integer idMotif, String DateEnr, BigDecimal montant, BigDecimal nouveauSolde, String dateFacture, Boolean anticipation, String numchq, Integer idCompte) throws DaoException {
        try {
            
            mettreAJourEnregistrementFromOrdre.setInt(12, idCompte);
            mettreAJourEnregistrementFromOrdre.setInt(11, ordre);
            mettreAJourEnregistrementFromOrdre.setInt(1, idLibelle);
            mettreAJourEnregistrementFromOrdre.setInt(2, idModeReglement);
            mettreAJourEnregistrementFromOrdre.setInt(3, idMotif);
            mettreAJourEnregistrementFromOrdre.setInt(4, idEtat);
            mettreAJourEnregistrementFromOrdre.setString(5, DateEnr);
            mettreAJourEnregistrementFromOrdre.setBigDecimal(6, montant);
            mettreAJourEnregistrementFromOrdre.setBigDecimal(7, nouveauSolde);
            mettreAJourEnregistrementFromOrdre.setString(8, dateFacture);
            mettreAJourEnregistrementFromOrdre.setBoolean(9, anticipation);
            mettreAJourEnregistrementFromOrdre.setString(10, numchq);
            
            int nb= mettreAJourEnregistrementFromOrdre.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - modifierUnEnregistrementFromOrdre : pb JDBC\n" + ex.getMessage());
        }        
    }
    
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
    
    public List<Enregistrement> lireEnregistrementsRecDepAnt(Integer id, String recDep, String anticipation) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            lireEnregistrementsRecDepAnt.setInt(1, id);
            lireEnregistrementsRecDepAnt.setString(2, recDep);
            lireEnregistrementsRecDepAnt.setString(3, anticipation);            
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
    
    public List<Enregistrement> lireEnregistrementsAnt(Integer id, String anticipation) throws DaoException{
        try {
            List<Enregistrement> desEnregistrements = new ArrayList<Enregistrement>();
            lireEnregistrementsAnt.setInt(1, id);
            lireEnregistrementsAnt.setString(2, anticipation);            
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
            enregistrement.setDate(rs.getString("DATEENREGISTREMENT"));
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
    
    public Integer recupererDernierOrdre(Integer idCompte) throws DaoException{
        try {    
            recupererDernierOrdre.setInt(1, idCompte);
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
    
    public Integer lireIdEnrFromOrdreCompte(Integer ordre, Integer idCompte) throws DaoException{
        try {                    
            lireIdEnrFromOrdreCompte.setInt(1, ordre);
            lireIdEnrFromOrdreCompte.setInt(2, idCompte);
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
    
    public int mettreAJourOrdreInfSup(Integer nouvelOrdre, Integer ancienOrdre, Integer id, Integer idCompte) throws DaoException {
        try {
            
            mettreAJourOrdreInfSup.setInt(1, nouvelOrdre);
            mettreAJourOrdreInfSup.setInt(2, ancienOrdre);
            mettreAJourOrdreInfSup.setInt(3, id);
            mettreAJourOrdreInfSup.setInt(4, idCompte);
                    
            int nb= mettreAJourOrdreInfSup.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - mettreAJourOrdreInfSup : pb JDBC\n" + ex.getMessage());
        }        
    }
    
    public void export(String[] args) throws Exception {
        
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("NAME", Types.VARCHAR, 255, 0);
        rs.addColumn("EMAIL", Types.VARCHAR, 255, 0);
        rs.addRow("Bob Meier", "bob.meier@abcde.abc");
        rs.addRow("John Jones", "john.jones@abcde.abc");
        new Csv().write("data/test.csv", rs, null);
    }
}
