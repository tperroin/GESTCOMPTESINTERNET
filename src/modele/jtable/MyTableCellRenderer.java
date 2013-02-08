package modele.jtable;


import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import vues.VueAfficherCompteCIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author btssio
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer 
{
    public MyTableCellRenderer() 
    {
        super();
        setOpaque(true);
    } 
    
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) 
    { 
        if("Recette".equals((String)table.getValueAt(row, 11)))  
        {
            
            setForeground(Color.black);        
            setBackground(new Color(255,245,193));            
        }else{
            setForeground(Color.black);        
            setBackground(new Color(168,247,255)); 
        }
        
        
        if((Boolean)table.getValueAt(row, 9) == true){
            setForeground(Color.black);        
            setBackground(new Color(145,255,81));
        }
        setText(value !=null ? value.toString() : "");
        
        
        return this;
    }
}