package metier;


import metier.Enregistrement;

/**************************************************************************
* Source File	:  ModeReglement.java
* Author                   :  tibo  
* Project name         :  Espace de travail* Created                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Definition of the class ModeReglement
**************************************************************************/


public  class ModeReglement  { 
	
    private Integer id;
    private String libelle;

    Enregistrement enregistrements[];
    
    /**
     *
     */
    public ModeReglement() {
        this.id = null;
        this.libelle = "";
    }

    /**
     *
     * @param id
     * @param libelle
     * @param enregistrements
     */
    public ModeReglement(Integer id, String libelle, Enregistrement[] enregistrements) {
        this.id = id;
        this.libelle = libelle;
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

    @Override
    public String toString() {
        return libelle;
    }
    
    


}


