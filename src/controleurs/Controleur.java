package controleurs;

import vues.VueAbstraite;

/**
 * 
 * Classe mère pour l'ensemble des contrôleurs. Elle permet de définir le contrôleur que l'on souhaite et de lui affecter une vue.
 *
 * @author Thibault Perroin
 */
public class Controleur{
    
    
    private Controleur ctrl;
    
    
    protected VueAbstraite vue;

    /**
     * 
     * Constructeur pour la mise en place des contrôleurs dans les classes de contrôleurs.
     *
     * @param ctrl
     *          Le contrôleur à créer.
     */
    public Controleur(Controleur ctrl) {
        this.ctrl = ctrl;
    }
    
    /**
     * 
     * Permet d'afficher la vue du contrôleur en question.
     *
     */
    public void afficherVue(){
        vue.afficher();
    }
    
    /**
     * 
     * Permet de cacher la vue du contrôleurs en question.
     *
     */
    public void cacherVue(){
        vue.cacher();
    }

    /**
     * 
     * Permet de récupérer le contrôleur courant.
     *
     * @return
     *          Le contrôleur courant.
     */
    public Controleur getCtrl() {
        return ctrl;
    }

    /**
     * 
     * Permet de définir un contrôleur.
     *
     * @param ctrl
     *          Le contrôleur que l'on souhaite créer.
     */
    public void setCtrl(Controleur ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * 
     * Permet de récupérer la vue du contrôleur.
     *
     * @return
     *          La vue du contrôleur.
     */
    public VueAbstraite getVue() {
        return vue;
    }

    /**
     * 
     * Permet d'affecter une vue à un contrôleur.
     *
     * @param vue
     *          La vue que l'on souhaite affecter au contrôleur.
     */
    public void setVue(VueAbstraite vue) {
        this.vue = vue;
    }
    
    
}
