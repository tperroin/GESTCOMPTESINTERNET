package metier;

/**************************************************************************
* Source File	:  Motif.java
* Author                   :  tibo  
* Project name         :  GestComptes                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Classe Motif
**************************************************************************/



public class Motif  { 
		
    private Integer id;
    private String libelle;
    private Boolean recetteDepense;
    Enregistrement enregistrements[];

    /**
     *
     */
    public Motif() {
        this.id = null;
        this.libelle = "";
        this.recetteDepense = null;
    }
    
    /**
     *
     * @param id
     * @param libelle
     * @param recetteDepense
     * @param enregistrements
     */
    public Motif(Integer id, String libelle, Boolean recetteDepense, Enregistrement[] enregistrements) {
        this.id = id;
        this.libelle = libelle;
        this.recetteDepense = recetteDepense;
        this.enregistrements = enregistrements;
    }
    
    /**
     *
     * @param idMotif
     */
    public Motif(String idMotif) {
        id = getId();
        libelle = getLibelle();
        recetteDepense = getRecetteDepense();
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
    public Boolean getRecetteDepense() {
        return recetteDepense;
    }

    /**
     *
     * @param recetteDepense
     */
    public void setRecetteDepense(Boolean recetteDepense) {
        this.recetteDepense = recetteDepense;
    }

    /**
     *
     * @return
     */
    public Enregistrement[] getEnregistrement() {
        return enregistrements;
    }

    /**
     *
     * @param enregistrements
     */
    public void setEnregistrement(Enregistrement[] enregistrements) {
        this.enregistrements = enregistrements;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    

}


