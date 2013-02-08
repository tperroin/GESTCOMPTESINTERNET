/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jtable;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
    
    
    
  JLabel lbl = new JLabel();

  ImageIcon icon = new ImageIcon(getClass().getResource("/images/expand.png"));

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
    lbl.setText((String) value);
    lbl.setIcon(icon);
    lbl.setHorizontalAlignment(CENTER);
    return lbl;
  }
}