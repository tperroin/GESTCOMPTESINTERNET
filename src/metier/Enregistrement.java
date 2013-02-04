package metier;

/**************************************************************************
* Source File	:  Enregistrement.java
* Author                   :  tibo  
* Project name         :  Espace de travail* Created                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Definition of the class Enregistrement
**************************************************************************/



import java.util.*;



public  class Enregistrement  {
    
    private String id;
    private String prelevementEncaissement;
    private String recetteDepense;
    private String date;
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

    public Enregistrement(String id, String prelevementEncaissement, String recetteDepense, String date, String montant, String ancienSolde, String nouveauSolde, String numCHQ, String dateFacture, String anticipation, String IdCompte, String IdEtat, String modeReglement, String motif, String libelle, String ordre) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrelevementEncaissement() {
        return prelevementEncaissement;
    }

    public void setPrelevementEncaissement(String prelevementEncaissement) {
        this.prelevementEncaissement = prelevementEncaissement;
    }

    public String getRecetteDepense() {
        return recetteDepense;
    }

    public void setRecetteDepense(String recetteDepense) {
        this.recetteDepense = recetteDepense;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getAncienSolde() {
        return ancienSolde;
    }

    public void setAncienSolde(String ancienSolde) {
        this.ancienSolde = ancienSolde;
    }

    public String getNouveauSolde() {
        return nouveauSolde;
    }

    public void setNouveauSolde(String nouveauSolde) {
        this.nouveauSolde = nouveauSolde;
    }

    public String getNumCHQ() {
        return numCHQ;
    }

    public void setNumCHQ(String numCHQ) {
        this.numCHQ = numCHQ;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getAnticipation() {
        return anticipation;
    }

    public void setAnticipation(String anticipation) {
        this.anticipation = anticipation;
    }

    public String getIdCompte() {
        return IdCompte;
    }

    public void setIdCompte(String IdCompte) {
        this.IdCompte = IdCompte;
    }

    public String getIdEtat() {
        return IdEtat;
    }

    public void setIdEtat(String IdEtat) {
        this.IdEtat = IdEtat;
    }

    public String getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getIdLibelle() {
        return IdLibelle;
    }

    public void setIdLibelle(String IdLibelle) {
        this.IdLibelle = IdLibelle;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return "Enregistrement{" + "id=" + id + ", prelevementEncaissement=" + prelevementEncaissement + ", recetteDepense=" + recetteDepense + ", date=" + date + ", montant=" + montant + ", ancienSolde=" + ancienSolde + ", nouveauSolde=" + nouveauSolde + ", numCHQ=" + numCHQ + ", dateFacture=" + dateFacture + ", anticipation=" + anticipation + ", ordre=" + ordre + ", IdCompte=" + IdCompte + ", IdEtat=" + IdEtat + ", modeReglement=" + modeReglement + ", motif=" + motif + ", IdLibelle=" + IdLibelle + '}';
    }

        
    	
	
}


