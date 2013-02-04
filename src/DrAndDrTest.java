import java.awt.datatransfer.StringSelection; 
import java.awt.datatransfer.Transferable; 
import javax.swing.DropMode; 
import javax.swing.JComponent; 
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.ListSelectionModel; 
import javax.swing.SwingUtilities; 
import javax.swing.TransferHandler; 
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 
import javax.swing.table.DefaultTableModel; 

public class DrAndDrTest { 

        boolean useSubstance = true; 

        public DrAndDrTest() { 
                
        } 

        private void initGUI() throws ClassNotFoundException { 
                final JTable table = new JTable(); 

                table.setDropMode(DropMode.INSERT_ROWS); 
                table.setDragEnabled(true); 
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 

                final DefaultTableModel tm = new DefaultTableModel(); 
                for (int i = 0; i < 10; i++) { 
                        tm.addColumn("Column: " + i); 
                        String[] text = new String[10]; 
                        tm.addRow(text); 
                } 
                table.setModel(tm); 

                table.setTransferHandler(new TransferHandler() { 
                        public boolean canImport(TransferHandler.TransferSupport info) { return true; } 
                        public boolean importData(TransferHandler.TransferSupport info) { return true; }	
                        public void exportDone(JComponent c, Transferable t, int action) {} 
                        public int getSourceActions(JComponent c) { return MOVE; } 
                        protected Transferable createTransferable(JComponent c) { 
                                return new StringSelection("Test"); 
                        } 
                }); 

                JScrollPane scroll = new JScrollPane(table);	
                JFrame frame = new JFrame("DRAG TEST"); 
                frame.add(scroll); 
                frame.setSize(500,500); 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                frame.setVisible(true); 
        } 

        public static void main(String[] args) { 
                new DrAndDrTest(); 
        } 
} 
