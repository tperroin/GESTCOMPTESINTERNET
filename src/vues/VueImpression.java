/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import controleurs.Controleur;
import controleurs.CtrlImpression;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import metier.Compta;
import modele.dao.DaoException;
import modele.jtable.ModelTableCM;
import modele.jtable.ModelTableImpression;

/**
 *
 * @author btssio
 */
public class VueImpression extends VueAbstraite{

    DefaultTableModel modeleJTableImpression = new ModelTableImpression();
     
    DefaultComboBoxModel modeleComboboxCompta;
    
    /**
     *
     * @param ctrl
     */
    public VueImpression(Controleur ctrl) {
        super(ctrl);
        initComponents();
        
        
        this.pack();
        this.setLocationRelativeTo(null);
        
        modeleJTableImpression = new ModelTableImpression();
           
        jTableCM.setModel(modeleJTableImpression);
        
        modeleComboboxCompta = new DefaultComboBoxModel();
        webComboBoxCompta.setModel(modeleComboboxCompta);
        
        jDateChooser1.setLocale(Locale.FRENCH);
        jDateChooser2.setLocale(Locale.FRENCH);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCM = new javax.swing.JTable();
        webLabelChoisirCompta = new com.alee.laf.label.WebLabel();
        webComboBoxCompta = new com.alee.laf.combobox.WebComboBox();
        jButtonAperçu = new javax.swing.JButton();
        jButtonImprimer = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jMenuBarMenu = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuItemAjouter = new javax.swing.JMenuItem();
        jMenuItemImprimer = new javax.swing.JMenuItem();
        jMenuItemQuitter = new javax.swing.JMenuItem();
        jMenuAfficher = new javax.swing.JMenu();
        jMenuItemCIO = new javax.swing.JMenuItem();
        jMenuItemCM = new javax.swing.JMenuItem();
        jMenuArchiver = new javax.swing.JMenu();
        jMenuItemCIOArch = new javax.swing.JMenuItem();
        jMenuItemCMArch = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableCM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCM.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableCM.setRowHeight(30);
        jTableCM.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableCM);

        webLabelChoisirCompta.setText("Choisir comptabilité :");

        jButtonAperçu.setText("Aperçu");
        jButtonAperçu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAperçuActionPerformed(evt);
            }
        });

        jButtonImprimer.setText("Imprimer");
        jButtonImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimerActionPerformed(evt);
            }
        });

        jMenuFichier.setText("Fichier");

        jMenuItemAjouter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAjouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file2.png"))); // NOI18N
        jMenuItemAjouter.setText("Ajouter");
        jMenuItemAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAjouterActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemAjouter);

        jMenuItemImprimer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemImprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        jMenuItemImprimer.setText("Imprimer...");
        jMenuItemImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImprimerActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemImprimer);

        jMenuItemQuitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemQuitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/switch.png"))); // NOI18N
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
        jMenuItemCIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cic3-053351800-2259-28012009-1.jpg"))); // NOI18N
        jMenuItemCIO.setText("Compte CIC Ouest");
        jMenuItemCIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCIOActionPerformed(evt);
            }
        });
        jMenuAfficher.add(jMenuItemCIO);

        jMenuItemCM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12920-skyphon-CreditMutuel.png"))); // NOI18N
        jMenuItemCM.setText("Compte Crédit Mutuel");
        jMenuItemCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCMActionPerformed(evt);
            }
        });
        jMenuAfficher.add(jMenuItemCM);

        jMenuBarMenu.add(jMenuAfficher);

        jMenuArchiver.setText("Archiver");

        jMenuItemCIOArch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cic3-053351800-2259-28012009-1.jpg"))); // NOI18N
        jMenuItemCIOArch.setText("Compte CIC Ouest");
        jMenuItemCIOArch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCIOArchActionPerformed(evt);
            }
        });
        jMenuArchiver.add(jMenuItemCIOArch);

        jMenuItemCMArch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12920-skyphon-CreditMutuel.png"))); // NOI18N
        jMenuItemCMArch.setText("Compte Crédit Mutuel");
        jMenuItemCMArch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCMArchActionPerformed(evt);
            }
        });
        jMenuArchiver.add(jMenuItemCMArch);

        jMenuBarMenu.add(jMenuArchiver);

        setJMenuBar(jMenuBarMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webLabelChoisirCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(webComboBoxCompta, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAperçu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImprimer)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(webComboBoxCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabelChoisirCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAperçu)
                            .addComponent(jButtonImprimer))))
                .addContainerGap(385, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(56, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjouterActionPerformed
        try {
            ((CtrlImpression)controleur).afficherAjouterEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAjouterActionPerformed

    private void jMenuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitterActionPerformed

    private void jMenuItemCIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCIOActionPerformed
        try {
            ((CtrlImpression)controleur).afficherAfficherCompteCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCIOActionPerformed

    private void jMenuItemCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMActionPerformed
        try {
            ((CtrlImpression)controleur).afficherAfficherCompteCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCMActionPerformed

    private void jMenuItemCIOArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCIOArchActionPerformed
        try {
            ((CtrlImpression)controleur).afficherArchivageCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCIOArchActionPerformed

    private void jMenuItemCMArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMArchActionPerformed
        try {
            ((CtrlImpression)controleur).afficherArchivageCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCMArchActionPerformed

    private void jMenuItemImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimerActionPerformed
       try {
            ((CtrlImpression)controleur).afficherImpression();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getModeleJTableImpression() {
        return modeleJTableImpression;
    }

    public void setModeleJTableImpression(DefaultTableModel modeleJTableCM) {
        this.modeleJTableImpression = modeleJTableCM;
    }

    public DefaultComboBoxModel getModeleComboboxCompta() {
        return modeleComboboxCompta;
    }

    public void setModeleComboboxCompta(DefaultComboBoxModel modeleComboboxCompta) {
        this.modeleComboboxCompta = modeleComboboxCompta;
    }

    public JButton getjButtonAperçu() {
        return jButtonAperçu;
    }

    public void setjButtonAperçu(JButton jButtonAperçu) {
        this.jButtonAperçu = jButtonAperçu;
    }

    public JButton getjButtonImprimer() {
        return jButtonImprimer;
    }

    public void setjButtonImprimer(JButton jButtonImprimer) {
        this.jButtonImprimer = jButtonImprimer;
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

    public JMenuItem getjMenuItemImprimer() {
        return jMenuItemImprimer;
    }

    public void setjMenuItemImprimer(JMenuItem jMenuItemImprimer) {
        this.jMenuItemImprimer = jMenuItemImprimer;
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

    public JTable getjTableImpression() {
        return jTableCM;
    }

    public void setjTableImpression(JTable jTableCM) {
        this.jTableCM = jTableCM;
    }

    public WebComboBox getWebComboBoxCompta() {
        return webComboBoxCompta;
    }

    public void setWebComboBoxCompta(WebComboBox webComboBoxCompta) {
        this.webComboBoxCompta = webComboBoxCompta;
    }

    public WebLabel getWebLabelChoisirCompta() {
        return webLabelChoisirCompta;
    }

    public void setWebLabelChoisirCompta(WebLabel webLabelChoisirCompta) {
        this.webLabelChoisirCompta = webLabelChoisirCompta;
    }//GEN-LAST:event_jMenuItemImprimerActionPerformed

    private void jButtonAperçuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAperçuActionPerformed
        try {
            ((CtrlImpression)controleur).viderJtableModel();
            
            Compta unCompte = (Compta) getModeleComboboxCompta().getSelectedItem();
            Integer idCompte = unCompte.getId();
            java.sql.Date dateDebut = new java.sql.Date(jDateChooser1.getCalendar().getTimeInMillis());
            java.sql.Date dateFin = new java.sql.Date(jDateChooser2.getCalendar().getTimeInMillis());
            
            ((CtrlImpression)controleur).chargerEnregistrementImpression(dateDebut, dateFin, idCompte);
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAperçuActionPerformed

    private void jButtonImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimerActionPerformed
        try {
            Compta unCompte = (Compta) getModeleComboboxCompta().getSelectedItem();
            Integer idCompte = unCompte.getId();
            
            java.sql.Date dateDebut = new java.sql.Date(jDateChooser1.getCalendar().getTimeInMillis());
            java.sql.Date dateFin = new java.sql.Date(jDateChooser2.getCalendar().getTimeInMillis());
            
            ((CtrlImpression)controleur).imprimer(idCompte, dateDebut, dateFin);
        } catch (DaoException ex) {
            Logger.getLogger(VueImpression.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonImprimerActionPerformed

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAperçu;
    private javax.swing.JButton jButtonImprimer;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JMenu jMenuAfficher;
    private javax.swing.JMenu jMenuArchiver;
    private javax.swing.JMenuBar jMenuBarMenu;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItemAjouter;
    private javax.swing.JMenuItem jMenuItemCIO;
    private javax.swing.JMenuItem jMenuItemCIOArch;
    private javax.swing.JMenuItem jMenuItemCM;
    private javax.swing.JMenuItem jMenuItemCMArch;
    private javax.swing.JMenuItem jMenuItemImprimer;
    private javax.swing.JMenuItem jMenuItemQuitter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCM;
    private com.alee.laf.combobox.WebComboBox webComboBoxCompta;
    private com.alee.laf.label.WebLabel webLabelChoisirCompta;
    // End of variables declaration//GEN-END:variables
}
