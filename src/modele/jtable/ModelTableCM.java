/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jtable;

import controleurs.CtrlAfficherCompteCM;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import metier.Compta;
import modele.dao.DaoException;
import modele.dao.DaoH2;

/**
 *
 * @author btssio
 */
public class ModelTableCM extends DefaultTableModel implements Reorderable {
    private Object data;
        
    /**
     *
     * @param from
     * @param to
     */
    public void reorder(int from, int to) {
    Object row = getDataVector().remove(from);
    if( from < to ){ to --;}
    getDataVector().add(to, row);
    
    
    fireTableDataChanged();
        try {
            to = to + 1;
            from = from + 1;      
            try {
                mettreAJourOrdreCM(to, from, Integer.valueOf(getValueAt(from-1, 13).toString()), String.valueOf(getValueAt(from-1, 1)));
            } catch (ParseException ex) {    
                Logger.getLogger(ModelTableCM.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DaoException ex) {
            Logger.getLogger(ModelTableCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
      
  }
  
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

            if(columnIndex==0){
                return false;
            }else{
                if(rowIndex==0){
                    return true;
                }else{
                    return true;
                }
            }
    }
  
    /**
     *
     * @param to
     * @param from
     * @param idCompte
     * @param annee
     * @throws DaoException
     */
    public void mettreAJourOrdreCM(int to, int from, int idCompte, String annee) throws DaoException, ParseException{
       
        DaoH2 dao = new DaoH2("gestComptes", "sa", "");
         
        dao.connecter();
        annee = annee.substring(annee.length() - 4);
        
        dao.mettreAJourOrdre(to, from, idCompte, annee);
        
   }          

   
}
