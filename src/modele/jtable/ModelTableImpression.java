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

/**
 *
 * @author btssio
 */
public class ModelTableImpression extends DefaultTableModel implements Reorderable {
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
              
  }
  
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

            return false;
            
    }
     
}
