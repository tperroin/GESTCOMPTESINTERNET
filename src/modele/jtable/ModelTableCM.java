/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jtable;

import controleurs.CtrlAfficherCompteCM;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modele.dao.DaoException;
import modele.dao.DaoH2;

public class ModelTableCM extends DefaultTableModel implements Reorderable {
    private Object data;
        
  public void reorder(int from, int to) {
    Object row = getDataVector().remove(from);
    if( from < to ){ to --;}
    getDataVector().add(to, row);
    fireTableDataChanged();
        try {
            to = to + 1;
            from = from + 1;
            mettreAJourOrdreCM(to, from);
    
        } catch (DaoException ex) {
            Logger.getLogger(ModelTableCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
      
  }
  
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

            return columnIndex==0 ? false : true;
    }
  
   public void mettreAJourOrdreCM(int to, int from) throws DaoException{
        DaoH2 dao = new DaoH2("gestComptes", "sa", "");
         
        dao.connecter();
        
        dao.mettreAJourOrdreCM(to, from);
        
   }       
   
}
