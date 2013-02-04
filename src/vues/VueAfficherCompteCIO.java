/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import controleurs.Controleur;
import controleurs.CtrlAfficherCompteCIO;
import controleurs.CtrlAjouterEnregistrement;
import modele.jtable.TableColumnAdjuster;
import modele.jtable.TableRowTransferHandler;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modele.dao.DaoException;
import modele.jtable.ModelTable;

/**
 *
 * @author btssio
 */
public class VueAfficherCompteCIO extends VueAbstraite{

     DefaultTableModel modeleJTableCIO = new ModelTable();
    
    public VueAfficherCompteCIO(Controleur ctrl) {
        super(ctrl);
        initComponents();
        
        modeleJTableCIO = new ModelTable();
           
        jTableCIO.setModel(modeleJTableCIO);
        
        jTableCIO.setDragEnabled(true);
        jTableCIO.setDropMode(DropMode.INSERT_ROWS);
        jTableCIO.setTransferHandler(new TableRowTransferHandler(jTableCIO));
                
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCIO = new javax.swing.JTable();
        jLabelSoldeCompteCIO = new javax.swing.JLabel();
        jTextFieldSoldeCompteCIO = new javax.swing.JTextField();
        jLabelSoldeCompteCIOSansAnticipation = new javax.swing.JLabel();
        jTextFieldSoldeCompteCIOSansAnticipation = new javax.swing.JTextField();
        jPanelLegende = new javax.swing.JPanel();
        jLabelLegende = new javax.swing.JLabel();
        jButtonRecette = new javax.swing.JButton();
        jButtonDepense = new javax.swing.JButton();
        jButtonAnticipation = new javax.swing.JButton();
        jButtonValModif = new javax.swing.JButton();
        jButtonValiderAnnee = new javax.swing.JButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButtonAnnulerAnnee = new javax.swing.JButton();
        jMenuBarMenu = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuItemAjouter = new javax.swing.JMenuItem();
        jMenuItemQuitter = new javax.swing.JMenuItem();
        jMenuAfficher = new javax.swing.JMenu();
        jMenuItemCIO = new javax.swing.JMenuItem();
        jMenuItemCM = new javax.swing.JMenuItem();
        jMenuArchiver = new javax.swing.JMenu();
        jMenuItemCIOArch = new javax.swing.JMenuItem();
        jMenuItemCMArch = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableCIO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCIO.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableCIO.setRowHeight(30);
        jTableCIO.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableCIO);

        jLabelSoldeCompteCIO.setText("Solde du compte :");

        jTextFieldSoldeCompteCIO.setEditable(false);

        jLabelSoldeCompteCIOSansAnticipation.setText("Solde du compte sans anticipation :");

        jTextFieldSoldeCompteCIOSansAnticipation.setEditable(false);

        jLabelLegende.setText("Légende :");

        jButtonRecette.setBackground(new java.awt.Color(255, 245, 193));
        jButtonRecette.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButtonRecette.setText("Recette");
        jButtonRecette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecetteActionPerformed(evt);
            }
        });

        jButtonDepense.setBackground(new java.awt.Color(255, 66, 66));
        jButtonDepense.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButtonDepense.setText("Dépense");
        jButtonDepense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDepenseActionPerformed(evt);
            }
        });

        jButtonAnticipation.setBackground(new java.awt.Color(145, 255, 81));
        jButtonAnticipation.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButtonAnticipation.setText("Anticipation");
        jButtonAnticipation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnticipationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLegendeLayout = new javax.swing.GroupLayout(jPanelLegende);
        jPanelLegende.setLayout(jPanelLegendeLayout);
        jPanelLegendeLayout.setHorizontalGroup(
            jPanelLegendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLegendeLayout.createSequentialGroup()
                .addComponent(jLabelLegende)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelLegendeLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButtonRecette)
                .addGap(18, 18, 18)
                .addComponent(jButtonDepense)
                .addGap(18, 18, 18)
                .addComponent(jButtonAnticipation)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanelLegendeLayout.setVerticalGroup(
            jPanelLegendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLegendeLayout.createSequentialGroup()
                .addComponent(jLabelLegende)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLegendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRecette)
                    .addComponent(jButtonDepense)
                    .addComponent(jButtonAnticipation))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jButtonValModif.setText("Valider modification");
        jButtonValModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValModifActionPerformed(evt);
            }
        });

        jButtonValiderAnnee.setText("Charger");
        jButtonValiderAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderAnneeActionPerformed(evt);
            }
        });

        jYearChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jYearChooser1MouseClicked(evt);
            }
        });

        jButtonAnnulerAnnee.setText("Annuler");
        jButtonAnnulerAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerAnneeActionPerformed(evt);
            }
        });

        jMenuFichier.setText("Fichier");

        jMenuItemAjouter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAjouter.setText("Ajouter");
        jMenuItemAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAjouterActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemAjouter);

        jMenuItemQuitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemQuitter.setText("Quitter");
        jMenuItemQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitterActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemQuitter);

        jMenuBarMenu.add(jMenuFichier);

        jMenuAfficher.setText("Afficher");

        jMenuItemCIO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCIO.setText("Compte CIC Ouest");
        jMenuAfficher.add(jMenuItemCIO);

        jMenuItemCM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCM.setText("Compte Crédit Mutuel");
        jMenuItemCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCMActionPerformed(evt);
            }
        });
        jMenuAfficher.add(jMenuItemCM);

        jMenuBarMenu.add(jMenuAfficher);

        jMenuArchiver.setText("Archiver");

        jMenuItemCIOArch.setText("Compte CIC Ouest");
        jMenuArchiver.add(jMenuItemCIOArch);

        jMenuItemCMArch.setText("Compte Crédit Mutuel");
        jMenuArchiver.add(jMenuItemCMArch);

        jMenuBarMenu.add(jMenuArchiver);

        setJMenuBar(jMenuBarMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelSoldeCompteCIO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSoldeCompteCIO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSoldeCompteCIOSansAnticipation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSoldeCompteCIOSansAnticipation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jPanelLegende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jButtonValModif))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonValiderAnnee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAnnulerAnnee)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonValiderAnnee)
                        .addComponent(jButtonAnnulerAnnee))
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelSoldeCompteCIO)
                                    .addComponent(jTextFieldSoldeCompteCIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelSoldeCompteCIOSansAnticipation)
                                    .addComponent(jTextFieldSoldeCompteCIOSansAnticipation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanelLegende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButtonValModif)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjouterActionPerformed
        try {
            ((CtrlAfficherCompteCIO)controleur).afficherAjouterEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAjouterActionPerformed

    private void jMenuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitterActionPerformed

    private void jMenuItemCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMActionPerformed

    }//GEN-LAST:event_jMenuItemCMActionPerformed

    private void jButtonRecetteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecetteActionPerformed
        try {
            ((CtrlAfficherCompteCIO)controleur).chargerEnregistrementRecpDepAnt(1, "Recette", "FALSE");
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRecetteActionPerformed

    private void jButtonDepenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDepenseActionPerformed
       try {
            ((CtrlAfficherCompteCIO)controleur).chargerEnregistrementRecpDepAnt(1, "Dépense", "FALSE");
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDepenseActionPerformed

    private void jButtonAnticipationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnticipationActionPerformed
        try {
            ((CtrlAfficherCompteCIO)controleur).chargerEnregistrementAnt(1, "TRUE");
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAnticipationActionPerformed

    private void jButtonValModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValModifActionPerformed
        try {
            ((CtrlAfficherCompteCIO)controleur).afficherAfficherCompteCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonValModifActionPerformed

    private void jButtonValiderAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderAnneeActionPerformed
        try {
            ((CtrlAfficherCompteCIO)controleur).viderJtableModel();
            ((CtrlAfficherCompteCIO)controleur).chargerEnregistrement(jYearChooser1.getYear());
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_jButtonValiderAnneeActionPerformed

    private void jButtonAnnulerAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerAnneeActionPerformed
        try {
            ((CtrlAfficherCompteCIO)controleur).afficherAfficherCompteCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAnnulerAnneeActionPerformed

    private void jYearChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jYearChooser1MouseClicked
        
    }//GEN-LAST:event_jYearChooser1MouseClicked

    

    public JButton getjButtonValModif() {
        return jButtonValModif;
    }

    public void setjButtonValModif(JButton jButtonValModif) {
        this.jButtonValModif = jButtonValModif;
    }

    public JButton getjButtonValiderAnnee() {
        return jButtonValiderAnnee;
    }

    public void setjButtonValiderAnnee(JButton jButtonValiderAnnee) {
        this.jButtonValiderAnnee = jButtonValiderAnnee;
    }

    public JYearChooser getjYearChooser1() {
        return jYearChooser1;
    }

    public void setjYearChooser1(JYearChooser jYearChooser1) {
        this.jYearChooser1 = jYearChooser1;
    }

       
    
    public DefaultTableModel getModeleJTableCIO() {
        return modeleJTableCIO;
    }

    public void setModeleJTableCIO(DefaultTableModel modeleJTableCIO) {
        this.modeleJTableCIO = modeleJTableCIO;
    }

    public JMenu getjMenuAfficher() {
        return jMenuAfficher;
    }

    public void setjMenuAfficher(JMenu jMenuAfficher) {
        this.jMenuAfficher = jMenuAfficher;
    }

    public JMenu getjMenuArchiver() {
        return jMenuArchiver;
    }

    public void setjMenuArchiver(JMenu jMenuArchiver) {
        this.jMenuArchiver = jMenuArchiver;
    }

    public JMenuBar getjMenuBarMenu() {
        return jMenuBarMenu;
    }

    public void setjMenuBarMenu(JMenuBar jMenuBarMenu) {
        this.jMenuBarMenu = jMenuBarMenu;
    }

    public JMenu getjMenuFichier() {
        return jMenuFichier;
    }

    public void setjMenuFichier(JMenu jMenuFichier) {
        this.jMenuFichier = jMenuFichier;
    }

    public JMenuItem getjMenuItemAjouter() {
        return jMenuItemAjouter;
    }

    public void setjMenuItemAjouter(JMenuItem jMenuItemAjouter) {
        this.jMenuItemAjouter = jMenuItemAjouter;
    }

    public JMenuItem getjMenuItemCIO() {
        return jMenuItemCIO;
    }

    public void setjMenuItemCIO(JMenuItem jMenuItemCIO) {
        this.jMenuItemCIO = jMenuItemCIO;
    }

    public JMenuItem getjMenuItemCIOArch() {
        return jMenuItemCIOArch;
    }

    public void setjMenuItemCIOArch(JMenuItem jMenuItemCIOArch) {
        this.jMenuItemCIOArch = jMenuItemCIOArch;
    }

    public JMenuItem getjMenuItemCM() {
        return jMenuItemCM;
    }

    public void setjMenuItemCM(JMenuItem jMenuItemCM) {
        this.jMenuItemCM = jMenuItemCM;
    }

    public JMenuItem getjMenuItemCMArch() {
        return jMenuItemCMArch;
    }

    public void setjMenuItemCMArch(JMenuItem jMenuItemCMArch) {
        this.jMenuItemCMArch = jMenuItemCMArch;
    }

    public JMenuItem getjMenuItemQuitter() {
        return jMenuItemQuitter;
    }

    public void setjMenuItemQuitter(JMenuItem jMenuItemQuitter) {
        this.jMenuItemQuitter = jMenuItemQuitter;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getjTableCIO() {
        return jTableCIO;
    }

    public void setjTableCIO(JTable jTableCIO) {
        this.jTableCIO = jTableCIO;
    }

    public JLabel getjLabelSoldeCompteCIO() {
        return jLabelSoldeCompteCIO;
    }

    public void setjLabelSoldeCompteCIO(JLabel jLabelSoldeCompteCIO) {
        this.jLabelSoldeCompteCIO = jLabelSoldeCompteCIO;
    }

    public JTextField getjTextFieldSoldeCompteCIO() {
        return jTextFieldSoldeCompteCIO;
    }

    public void setjTextFieldSoldeCompteCIO(JTextField jTextFieldSoldeCompteCIO) {
        this.jTextFieldSoldeCompteCIO = jTextFieldSoldeCompteCIO;
    }

    public JLabel getjLabelSoldeCompteCIOSansAnticipation() {
        return jLabelSoldeCompteCIOSansAnticipation;
    }

    public void setjLabelSoldeCompteCIOSansAnticipation(JLabel jLabelSoldeCompteCIOSansAnticipation) {
        this.jLabelSoldeCompteCIOSansAnticipation = jLabelSoldeCompteCIOSansAnticipation;
    }

    public JTextField getjTextFieldSoldeCompteCIOSansAnticipation() {
        return jTextFieldSoldeCompteCIOSansAnticipation;
    }

    public void setjTextFieldSoldeCompteCIOSansAnticipation(JTextField jTextFieldSoldeCompteCIOSansAnticipation) {
        this.jTextFieldSoldeCompteCIOSansAnticipation = jTextFieldSoldeCompteCIOSansAnticipation;
    }

    public JButton getjButtonAnticipation() {
        return jButtonAnticipation;
    }

    public void setjButtonAnticipation(JButton jButtonAnticipation) {
        this.jButtonAnticipation = jButtonAnticipation;
    }

    public JButton getjButtonDepense() {
        return jButtonDepense;
    }

    public void setjButtonDepense(JButton jButtonDepense) {
        this.jButtonDepense = jButtonDepense;
    }

    public JButton getjButtonRecette() {
        return jButtonRecette;
    }

    public void setjButtonRecette(JButton jButtonRecette) {
        this.jButtonRecette = jButtonRecette;
    }

    public JLabel getjLabelLegende() {
        return jLabelLegende;
    }

    public void setjLabelLegende(JLabel jLabelLegende) {
        this.jLabelLegende = jLabelLegende;
    }

    public JPanel getjPanelLegende() {
        return jPanelLegende;
    }

    public void setjPanelLegende(JPanel jPanelLegende) {
        this.jPanelLegende = jPanelLegende;
    }

       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnulerAnnee;
    private javax.swing.JButton jButtonAnticipation;
    private javax.swing.JButton jButtonDepense;
    private javax.swing.JButton jButtonRecette;
    private javax.swing.JButton jButtonValModif;
    private javax.swing.JButton jButtonValiderAnnee;
    private javax.swing.JLabel jLabelLegende;
    private javax.swing.JLabel jLabelSoldeCompteCIO;
    private javax.swing.JLabel jLabelSoldeCompteCIOSansAnticipation;
    private javax.swing.JMenu jMenuAfficher;
    private javax.swing.JMenu jMenuArchiver;
    private javax.swing.JMenuBar jMenuBarMenu;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItemAjouter;
    private javax.swing.JMenuItem jMenuItemCIO;
    private javax.swing.JMenuItem jMenuItemCIOArch;
    private javax.swing.JMenuItem jMenuItemCM;
    private javax.swing.JMenuItem jMenuItemCMArch;
    private javax.swing.JMenuItem jMenuItemQuitter;
    private javax.swing.JPanel jPanelLegende;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCIO;
    private javax.swing.JTextField jTextFieldSoldeCompteCIO;
    private javax.swing.JTextField jTextFieldSoldeCompteCIOSansAnticipation;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables

   
}
