package metier;

/**************************************************************************
* Source File	:  Compta.java
* Author                   :  tibo  
* Project name         :  Espace de travail* Created                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Definition of the class Compte
**************************************************************************/



public  class Compta  { 
    
    private Integer id;
    private String libelle;
    private Integer idCompte;
    private Number soldeDepart;
	
    Enregistrement enregistrements[];
    
    /**
     *
     */
    public Compta() {
        this.id = null;
        this.libelle = "";
        this.idCompte = null;
        this.soldeDepart = null;
    }

    /**
     *
     * @param id
     * @param libelle
     * @param idCompte
     * @param soldeDepart
     * @param enregistrements
     */
    public Compta(Integer id, String libelle, Integer idCompte, Number soldeDepart, Enregistrement[] enregistrements) {
        this.id = id;
        this.libelle = libelle;
        this.idCompte = idCompte;
        this.soldeDepart = soldeDepart;
        this.enregistrements = enregistrements;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     *
     * @param libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     *
     * @return
     */
    public Number getSoldeDepart() {
        return soldeDepart;
    }

    /**
     *
     * @param soldeDepart
     */
    public void setSoldeDepart(Number soldeDepart) {
        this.soldeDepart = soldeDepart;
    }

    /**
     *
     * @return
     */
    public Enregistrement[] getEnregistrements() {
        return enregistrements;
    }

    /**
     *
     * @param enregistrements
     */
    public void setEnregistrements(Enregistrement[] enregistrements) {
        this.enregistrements = enregistrements;
    }

    /**
     *
     * @return
     */
    public Integer getIdCompte() {
        return idCompte;
    }

    /**
     *
     * @param idCompte
     */
    public void setIdCompte(Integer idCompte) {
        this.idCompte = idCompte;
    }
    
    

    @Override
    public String toString() {
        return libelle;
    }
	 
    
}


