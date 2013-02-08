/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import controleurs.Controleur;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author btssio
 */
public abstract class VueAbstraite extends JFrame{
    // associations
    protected Controleur controleur=null;
    
    public VueAbstraite(Controleur ctrl) {
        this.controleur = ctrl;
    }

    public Controleur getControleur() {
        return controleur;
    }
    
    public void afficher(){
        this.setVisible(true);
    }
    
    public void cacher(){
        this.setVisible(false);
    }
    
}