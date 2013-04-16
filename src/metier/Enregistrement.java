package metier;

/**************************************************************************
* Source File	:  Enregistrement.java
* Author                   :  tibo  
* Project name         :  Espace de travail* Created                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Definition of the class Enregistrement
**************************************************************************/



import java.util.*;



/**
 *
 * @author btssio
 */
public  class Enregistrement  {
    
    private String id;
    private String prelevementEncaissement;
    private String recetteDepense;
    private Date date;
    private String montant;
    private String ancienSolde;
    private String nouveauSolde;
    private String numCHQ;
    private String dateFacture;
    private String anticipation;
    private String ordre;


    String IdCompte;

    String IdEtat;

    String modeReglement;

    String motif;

    String IdLibelle;
    
    /**
     *
     */
    public Enregistrement() {
        this.id = null;
        this.prelevementEncaissement = "";
        this.recetteDepense = "";
        this.date = null;
        this.montant = "";
        this.ancienSolde = "";
        this.nouveauSolde = "";
        this.numCHQ = "";
        this.dateFacture = "";
        this.anticipation = null;
        this.IdCompte = null;
        this.IdEtat = null;
        this.modeReglement = null;
        this.motif = null;
        this.IdLibelle = null;
        this.ordre = null;
    }

    /**
     *
     * @param id
     * @param prelevementEncaissement
     * @param recetteDepense
     * @param date
     * @param montant
     * @param ancienSolde
     * @param nouveauSolde
     * @param numCHQ
     * @param dateFacture
     * @param anticipation
     * @param IdCompte
     * @param IdEtat
     * @param modeReglement
     * @param motif
     * @param libelle
     * @param ordre
     */
    public Enregistrement(String id, String prelevementEncaissement, String recetteDepense, Date date, String montant, String ancienSolde, String nouveauSolde, String numCHQ, String dateFacture, String anticipation, String IdCompte, String IdEtat, String modeReglement, String motif, String libelle, String ordre) {
        this.id = id;
        this.prelevementEncaissement = prelevementEncaissement;
        this.recetteDepense = recetteDepense;
        this.date = date;
        this.montant = montant;
        this.ancienSolde = ancienSolde;
        this.nouveauSolde = nouveauSolde;
        this.numCHQ = numCHQ;
        this.dateFacture = dateFacture;
        this.anticipation = anticipation;
        this.IdCompte = IdCompte;
        this.IdEtat = IdEtat;
        this.modeReglement = modeReglement;
        this.motif = motif;
        this.IdLibelle = IdLibelle;
        this.ordre = ordre;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getPrelevementEncaissement() {
        return prelevementEncaissement;
    }

    /**
     *
     * @param prelevementEncaissement
     */
    public void setPrelevementEncaissement(String prelevementEncaissement) {
        this.prelevementEncaissement = prelevementEncaissement;
    }

    /**
     *
     * @return
     */
    public String getRecetteDepense() {
        return recetteDepense;
    }

    /**
     *
     * @param recetteDepense
     */
    public void setRecetteDepense(String recetteDepense) {
        this.recetteDepense = recetteDepense;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getMontant() {
        return montant;
    }

    /**
     *
     * @param montant
     */
    public void setMontant(String montant) {
        this.montant = montant;
    }

    /**
     *
     * @return
     */
    public String getAncienSolde() {
        return ancienSolde;
    }

    /**
     *
     * @param ancienSolde
     */
    public void setAncienSolde(String ancienSolde) {
        this.ancienSolde = ancienSolde;
    }

    /**
     *
     * @return
     */
    public String getNouveauSolde() {
        return nouveauSolde;
    }

    /**
     *
     * @param nouveauSolde
     */
    public void setNouveauSolde(String nouveauSolde) {
        this.nouveauSolde = nouveauSolde;
    }

    /**
     *
     * @return
     */
    public String getNumCHQ() {
        return numCHQ;
    }

    /**
     *
     * @param numCHQ
     */
    public void setNumCHQ(String numCHQ) {
        this.numCHQ = numCHQ;
    }

    /**
     *
     * @return
     */
    public String getDateFacture() {
        return dateFacture;
    }

    /**
     *
     * @param dateFacture
     */
    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    /**
     *
     * @return
     */
    public String getAnticipation() {
        return anticipation;
    }

    /**
     *
     * @param anticipation
     */
    public void setAnticipation(String anticipation) {
        this.anticipation = anticipation;
    }

    /**
     *
     * @return
     */
    public String getIdCompte() {
        return IdCompte;
    }

    /**
     *
     * @param IdCompte
     */
    public void setIdCompte(String IdCompte) {
        this.IdCompte = IdCompte;
    }

    /**
     *
     * @return
     */
    public String getIdEtat() {
        return IdEtat;
    }

    /**
     *
     * @param IdEtat
     */
    public void setIdEtat(String IdEtat) {
        this.IdEtat = IdEtat;
    }

    /**
     *
     * @return
     */
    public String getModeReglement() {
        return modeReglement;
    }

    /**
     *
     * @param modeReglement
     */
    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

    /**
     *
     * @return
     */
    public String getMotif() {
        return motif;
    }

    /**
     *
     * @param motif
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     *
     * @return
     */
    public String getIdLibelle() {
        return IdLibelle;
    }

    /**
     *
     * @param IdLibelle
     */
    public void setIdLibelle(String IdLibelle) {
        this.IdLibelle = IdLibelle;
    }

    /**
     *
     * @return
     */
    public String getOrdre() {
        return ordre;
    }

    /**
     *
     * @param ordre
     */
    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return "Enregistrement{" + "id=" + id + ", prelevementEncaissement=" + prelevementEncaissement + ", recetteDepense=" + recetteDepense + ", date=" + date + ", montant=" + montant + ", ancienSolde=" + ancienSolde + ", nouveauSolde=" + nouveauSolde + ", numCHQ=" + numCHQ + ", dateFacture=" + dateFacture + ", anticipation=" + anticipation + ", ordre=" + ordre + ", IdCompte=" + IdCompte + ", IdEtat=" + IdEtat + ", modeReglement=" + modeReglement + ", motif=" + motif + ", IdLibelle=" + IdLibelle + ", ordre=" + ordre + '}';
    }

        
    	
	
}


