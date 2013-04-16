/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import com.alee.laf.WebLookAndFeel;


/**
 *
 * @author btssio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        WebLookAndFeel.install ();
        
        
        Controleur leControleurAccueil= new CtrlAccueil(null);      
        
    }
}
