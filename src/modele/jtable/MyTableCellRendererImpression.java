package modele.jtable;


import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.NumberFormatter;
import vues.VueAfficherCompteCIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author btssio
 */
public class MyTableCellRendererImpression extends DefaultTableCellRenderer 
{
    /**
     *
     */
    public MyTableCellRendererImpression() 
    {
        super();
        setOpaque(true);
    } 
    
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) 
    { 
        
         DecimalFormat numberFormat = new DecimalFormat("#,###.##;(#,###.##)");
         
        if("Recette".equals((String)table.getValueAt(row, 10)))  
        {
            
            setForeground(Color.black);        
            setBackground(new Color(255,245,193));            
        }else{
            setForeground(Color.black);        
            setBackground(new Color(168,247,255)); 
        }
        
        if("Vérifié".equals((String)table.getValueAt(row, 8))){
            setForeground(Color.black);        
            setBackground(new Color(255,102,102)); 
        }
        
        
        if((Boolean)table.getValueAt(row, 9) == true){
            setForeground(Color.black);        
            setBackground(new Color(145,255,81));
        }
        
               
        
        setText(value !=null ? value.toString() : "");
        
        return this;
    }
}