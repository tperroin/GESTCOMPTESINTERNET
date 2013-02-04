package metier;

/**************************************************************************
* Source File	:  Compte.java
* Author                   :  tibo  
* Project name         :  Espace de travail* Created                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Definition of the class Compte
**************************************************************************/



public  class Compte  { 
    
    private Integer id;
    private String libelle;
    private Number soldeDepart;
	
    Enregistrement enregistrements[];
    
    public Compte() {
        this.id = null;
        this.libelle = "";
        this.soldeDepart = null;
    }

    public Compte(Integer id, String libelle, Number soldeDepart, Enregistrement[] enregistrements) {
        this.id = id;
        this.libelle = libelle;
        this.soldeDepart = soldeDepart;
        this.enregistrements = enregistrements;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Number getSoldeDepart() {
        return soldeDepart;
    }

    public void setSoldeDepart(Number soldeDepart) {
        this.soldeDepart = soldeDepart;
    }

    public Enregistrement[] getEnregistrements() {
        return enregistrements;
    }

    public void setEnregistrements(Enregistrement[] enregistrements) {
        this.enregistrements = enregistrements;
    }

    @Override
    public String toString() {
        return libelle;
    }
	 
    
}


