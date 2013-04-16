/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import com.alee.extended.painter.BorderPainter;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextField;
import com.toedter.calendar.JYearChooser;
import controleurs.Controleur;
import controleurs.CtrlAfficherCompteCM;
import controleurs.CtrlImpression;
import java.awt.Color;
import modele.jtable.TableRowTransferHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
import metier.Compta;
import modele.dao.DaoException;
import modele.jtable.ModelTableCM;

/**
 *
 * @author btssio
 */
public class VueAfficherCompteCM extends VueAbstraite{

    DefaultTableModel modeleJTableCM = new ModelTableCM();
     
    DefaultComboBoxModel modeleComboboxCompta;
    
    /**
     *
     * @param ctrl
     */
    public VueAfficherCompteCM(Controleur ctrl) {
        super(ctrl);
        initComponents();
        
        
        this.pack();
        this.setLocationRelativeTo(null);
        
        modeleJTableCM = new ModelTableCM();
           
        jTableCM.setModel(modeleJTableCM);
        
        modeleComboboxCompta = new DefaultComboBoxModel();
        webComboBoxCompta.setModel(modeleComboboxCompta);
        
        jTableCM.setDragEnabled(true);
        jTableCM.setDropMode(DropMode.INSERT_ROWS);
        jTableCM.setTransferHandler(new TableRowTransferHandler(jTableCM));
        
        
        BorderPainter bpRecette = new BorderPainter ();
        bpRecette.setRound ( 20 );
        bpRecette.setWidth ( 5 );
        bpRecette.setColor ( new Color ( 255,245,193 ) );
        webButtonRecette.setPainter ( bpRecette );
        
        BorderPainter bpDepense = new BorderPainter ();
        bpDepense.setRound ( 20 );
        bpDepense.setWidth ( 5 );
        bpDepense.setColor ( new Color ( 168,247,255 ) );
        webButtonDepense.setPainter ( bpDepense );
        
        BorderPainter bpAnticipation = new BorderPainter ();
        bpAnticipation.setRound ( 20 );
        bpAnticipation.setWidth ( 5 );
        bpAnticipation.setColor ( new Color ( 145,255,81 ) );
        webButtonAnticipation.setPainter ( bpAnticipation );
        
        
        
                
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCM = new javax.swing.JTable();
        jLabelSoldeCompteCIO = new javax.swing.JLabel();
        jTextFieldSoldeCompteCIO = new javax.swing.JTextField();
        jLabelSoldeCompteCIOSansAnticipation = new javax.swing.JLabel();
        jTextFieldSoldeCompteCIOSansAnticipation = new javax.swing.JTextField();
        jPanelLegende = new javax.swing.JPanel();
        jLabelLegende = new javax.swing.JLabel();
        webButtonRecette = new com.alee.laf.button.WebButton();
        webButtonDepense = new com.alee.laf.button.WebButton();
        webButtonAnticipation = new com.alee.laf.button.WebButton();
        jButtonValiderAnnee = new javax.swing.JButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButtonAnnulerAnnee = new javax.swing.JButton();
        webTextFieldRecherche = new com.alee.laf.text.WebTextField();
        webButton1 = new com.alee.laf.button.WebButton();
        webComboBoxCompta = new com.alee.laf.combobox.WebComboBox();
        webLabelChoisirCompta = new com.alee.laf.label.WebLabel();
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
        setTitle("Compte du crédit Mutuel");

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

        jLabelSoldeCompteCIO.setText("Solde du compte :");

        jTextFieldSoldeCompteCIO.setEditable(false);

        jLabelSoldeCompteCIOSansAnticipation.setText("Solde du compte sans anticipation :");

        jTextFieldSoldeCompteCIOSansAnticipation.setEditable(false);

        jLabelLegende.setText("Légende :");

        webButtonRecette.setBackground(new java.awt.Color(255, 245, 193));
        webButtonRecette.setText("Recette");
        webButtonRecette.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        webButtonRecette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButtonRecetteActionPerformed(evt);
            }
        });

        webButtonDepense.setText("Dépense");
        webButtonDepense.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        webButtonDepense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButtonDepenseActionPerformed(evt);
            }
        });

        webButtonAnticipation.setText("Anticipation");
        webButtonAnticipation.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        webButtonAnticipation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButtonAnticipationActionPerformed(evt);
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
                .addGap(31, 31, 31)
                .addComponent(webButtonRecette, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(webButtonDepense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(webButtonAnticipation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLegendeLayout.setVerticalGroup(
            jPanelLegendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLegendeLayout.createSequentialGroup()
                .addComponent(jLabelLegende)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLegendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webButtonRecette, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webButtonDepense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webButtonAnticipation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        webTextFieldRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                webTextFieldRechercheKeyPressed(evt);
            }
        });

        webButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });

        webLabelChoisirCompta.setText("Choisir comptabilité :");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(webLabelChoisirCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(webComboBoxCompta, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonValiderAnnee)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAnnulerAnnee)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(webTextFieldRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelSoldeCompteCIO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSoldeCompteCIO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSoldeCompteCIOSansAnticipation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSoldeCompteCIOSansAnticipation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelLegende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(webTextFieldRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(webComboBoxCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabelChoisirCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonValiderAnnee)
                            .addComponent(jButtonAnnulerAnnee)))
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSoldeCompteCIO)
                            .addComponent(jTextFieldSoldeCompteCIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSoldeCompteCIOSansAnticipation)
                            .addComponent(jTextFieldSoldeCompteCIOSansAnticipation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanelLegende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjouterActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).afficherAjouterEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAjouterActionPerformed

    private void jMenuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitterActionPerformed

    private void jMenuItemCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).afficherAfficherCompteCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCMActionPerformed

    private void jButtonValiderAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderAnneeActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).viderJtableModel();
            
            Compta unCompte = (Compta) getModeleComboboxCompta().getSelectedItem();
            Integer idCompte = unCompte.getId();
            
            ((CtrlAfficherCompteCM)controleur).chargerEnregistrement(jYearChooser1.getYear(), idCompte);
            ((CtrlAfficherCompteCM)controleur).chargerSoldeCompteCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_jButtonValiderAnneeActionPerformed

    private void jButtonAnnulerAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerAnneeActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).afficherAfficherCompteCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAnnulerAnneeActionPerformed

    private void jYearChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jYearChooser1MouseClicked
        
    }//GEN-LAST:event_jYearChooser1MouseClicked

    private void jMenuItemCIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCIOActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).afficherAfficherCompteCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCIOActionPerformed

    private void webButtonRecetteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButtonRecetteActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).chargerEnregistrementRecpDepAnt(2, "Recette", "FALSE", jYearChooser1.getYear());
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_webButtonRecetteActionPerformed

    private void webButtonDepenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButtonDepenseActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).chargerEnregistrementRecpDepAnt(2, "Dépense", "FALSE", jYearChooser1.getYear());
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_webButtonDepenseActionPerformed

    private void webButtonAnticipationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButtonAnticipationActionPerformed
         try {
            ((CtrlAfficherCompteCM)controleur).chargerEnregistrementAnt(2, "TRUE", jYearChooser1.getYear());
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_webButtonAnticipationActionPerformed

    private void jMenuItemCIOArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCIOArchActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).afficherArchivageCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCIOArchActionPerformed

    private void jMenuItemCMArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMArchActionPerformed
       try {
            ((CtrlAfficherCompteCM)controleur).afficherArchivageCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCMArchActionPerformed

    private void webTextFieldRechercheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_webTextFieldRechercheKeyPressed
        
        Compta unCompte = (Compta) getModeleComboboxCompta().getSelectedItem();
            Integer idCompte = unCompte.getId();
        if(evt.getKeyCode() == evt.VK_ENTER){
            try {
                ((CtrlAfficherCompteCM)controleur).rechercherValeurEnregistrement(webTextFieldRecherche.getText(), idCompte, jYearChooser1.getYear());
            } catch (DaoException ex) {
                Logger.getLogger(VueAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_webTextFieldRechercheKeyPressed

    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
        
        Compta unCompte = (Compta) getModeleComboboxCompta().getSelectedItem();
            Integer idCompte = unCompte.getId();
        try {
            ((CtrlAfficherCompteCM)controleur).rechercherValeurEnregistrement(webTextFieldRecherche.getText(), idCompte, jYearChooser1.getYear());
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_webButton1ActionPerformed

    private void jMenuItemImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimerActionPerformed
        try {
            ((CtrlAfficherCompteCM)controleur).afficherImpression();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemImprimerActionPerformed


    /**
     *
     * @return
     */
    public JButton getjButtonValiderAnnee() {
        return jButtonValiderAnnee;
    }

    /**
     *
     * @param jButtonValiderAnnee
     */
    public void setjButtonValiderAnnee(JButton jButtonValiderAnnee) {
        this.jButtonValiderAnnee = jButtonValiderAnnee;
    }

    /**
     *
     * @return
     */
    public JYearChooser getjYearChooser1() {
        return jYearChooser1;
    }

    /**
     *
     * @param jYearChooser1
     */
    public void setjYearChooser1(JYearChooser jYearChooser1) {
        this.jYearChooser1 = jYearChooser1;
    }

       
    
    /**
     *
     * @return
     */
    public DefaultTableModel getModeleJTableCM() {
        return modeleJTableCM;
    }

    /**
     *
     * @param modeleJTableCM
     */
    public void setModeleJTableCM(DefaultTableModel modeleJTableCM) {
        this.modeleJTableCM = modeleJTableCM;
    }

    /**
     *
     * @return
     */
    public JMenu getjMenuAfficher() {
        return jMenuAfficher;
    }

    /**
     *
     * @param jMenuAfficher
     */
    public void setjMenuAfficher(JMenu jMenuAfficher) {
        this.jMenuAfficher = jMenuAfficher;
    }

    /**
     *
     * @return
     */
    public JMenu getjMenuArchiver() {
        return jMenuArchiver;
    }

    /**
     *
     * @param jMenuArchiver
     */
    public void setjMenuArchiver(JMenu jMenuArchiver) {
        this.jMenuArchiver = jMenuArchiver;
    }

    /**
     *
     * @return
     */
    public JMenuBar getjMenuBarMenu() {
        return jMenuBarMenu;
    }

    /**
     *
     * @param jMenuBarMenu
     */
    public void setjMenuBarMenu(JMenuBar jMenuBarMenu) {
        this.jMenuBarMenu = jMenuBarMenu;
    }

    /**
     *
     * @return
     */
    public JMenu getjMenuFichier() {
        return jMenuFichier;
    }

    /**
     *
     * @param jMenuFichier
     */
    public void setjMenuFichier(JMenu jMenuFichier) {
        this.jMenuFichier = jMenuFichier;
    }

    /**
     *
     * @return
     */
    public JMenuItem getjMenuItemAjouter() {
        return jMenuItemAjouter;
    }

    /**
     *
     * @param jMenuItemAjouter
     */
    public void setjMenuItemAjouter(JMenuItem jMenuItemAjouter) {
        this.jMenuItemAjouter = jMenuItemAjouter;
    }

    /**
     *
     * @return
     */
    public JMenuItem getjMenuItemCIO() {
        return jMenuItemCIO;
    }

    /**
     *
     * @param jMenuItemCIO
     */
    public void setjMenuItemCIO(JMenuItem jMenuItemCIO) {
        this.jMenuItemCIO = jMenuItemCIO;
    }

    /**
     *
     * @return
     */
    public JMenuItem getjMenuItemCIOArch() {
        return jMenuItemCIOArch;
    }

    /**
     *
     * @param jMenuItemCIOArch
     */
    public void setjMenuItemCIOArch(JMenuItem jMenuItemCIOArch) {
        this.jMenuItemCIOArch = jMenuItemCIOArch;
    }

    /**
     *
     * @return
     */
    public JMenuItem getjMenuItemCM() {
        return jMenuItemCM;
    }

    /**
     *
     * @param jMenuItemCM
     */
    public void setjMenuItemCM(JMenuItem jMenuItemCM) {
        this.jMenuItemCM = jMenuItemCM;
    }

    /**
     *
     * @return
     */
    public JMenuItem getjMenuItemCMArch() {
        return jMenuItemCMArch;
    }

    /**
     *
     * @param jMenuItemCMArch
     */
    public void setjMenuItemCMArch(JMenuItem jMenuItemCMArch) {
        this.jMenuItemCMArch = jMenuItemCMArch;
    }

    /**
     *
     * @return
     */
    public JMenuItem getjMenuItemQuitter() {
        return jMenuItemQuitter;
    }

    /**
     *
     * @param jMenuItemQuitter
     */
    public void setjMenuItemQuitter(JMenuItem jMenuItemQuitter) {
        this.jMenuItemQuitter = jMenuItemQuitter;
    }

    /**
     *
     * @return
     */
    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    /**
     *
     * @param jScrollPane1
     */
    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    /**
     *
     * @return
     */
    public JTable getjTableCM() {
        return jTableCM;
    }

    /**
     *
     * @param jTableCM
     */
    public void setjTableCM(JTable jTableCM) {
        this.jTableCM = jTableCM;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelSoldeCompteCIO() {
        return jLabelSoldeCompteCIO;
    }

    /**
     *
     * @param jLabelSoldeCompteCIO
     */
    public void setjLabelSoldeCompteCIO(JLabel jLabelSoldeCompteCIO) {
        this.jLabelSoldeCompteCIO = jLabelSoldeCompteCIO;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldSoldeCompteCIO() {
        return jTextFieldSoldeCompteCIO;
    }

    /**
     *
     * @param jTextFieldSoldeCompteCIO
     */
    public void setjTextFieldSoldeCompteCIO(JTextField jTextFieldSoldeCompteCIO) {
        this.jTextFieldSoldeCompteCIO = jTextFieldSoldeCompteCIO;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelSoldeCompteCIOSansAnticipation() {
        return jLabelSoldeCompteCIOSansAnticipation;
    }

    /**
     *
     * @param jLabelSoldeCompteCIOSansAnticipation
     */
    public void setjLabelSoldeCompteCIOSansAnticipation(JLabel jLabelSoldeCompteCIOSansAnticipation) {
        this.jLabelSoldeCompteCIOSansAnticipation = jLabelSoldeCompteCIOSansAnticipation;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldSoldeCompteCIOSansAnticipation() {
        return jTextFieldSoldeCompteCIOSansAnticipation;
    }

    /**
     *
     * @param jTextFieldSoldeCompteCIOSansAnticipation
     */
    public void setjTextFieldSoldeCompteCIOSansAnticipation(JTextField jTextFieldSoldeCompteCIOSansAnticipation) {
        this.jTextFieldSoldeCompteCIOSansAnticipation = jTextFieldSoldeCompteCIOSansAnticipation;
    }

    
    /**
     *
     * @return
     */
    public JLabel getjLabelLegende() {
        return jLabelLegende;
    }

    /**
     *
     * @param jLabelLegende
     */
    public void setjLabelLegende(JLabel jLabelLegende) {
        this.jLabelLegende = jLabelLegende;
    }

    /**
     *
     * @return
     */
    public JPanel getjPanelLegende() {
        return jPanelLegende;
    }

    /**
     *
     * @param jPanelLegende
     */
    public void setjPanelLegende(JPanel jPanelLegende) {
        this.jPanelLegende = jPanelLegende;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleComboboxCompta() {
        return modeleComboboxCompta;
    }

    /**
     *
     * @param modeleComboboxCompta
     */
    public void setModeleComboboxCompta(DefaultComboBoxModel modeleComboboxCompta) {
        this.modeleComboboxCompta = modeleComboboxCompta;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonAnnulerAnnee() {
        return jButtonAnnulerAnnee;
    }

    /**
     *
     * @param jButtonAnnulerAnnee
     */
    public void setjButtonAnnulerAnnee(JButton jButtonAnnulerAnnee) {
        this.jButtonAnnulerAnnee = jButtonAnnulerAnnee;
    }

    /**
     *
     * @return
     */
    public WebButton getWebButton1() {
        return webButton1;
    }

    /**
     *
     * @param webButton1
     */
    public void setWebButton1(WebButton webButton1) {
        this.webButton1 = webButton1;
    }

    /**
     *
     * @return
     */
    public WebButton getWebButtonAnticipation() {
        return webButtonAnticipation;
    }

    /**
     *
     * @param webButtonAnticipation
     */
    public void setWebButtonAnticipation(WebButton webButtonAnticipation) {
        this.webButtonAnticipation = webButtonAnticipation;
    }

    /**
     *
     * @return
     */
    public WebButton getWebButtonDepense() {
        return webButtonDepense;
    }

    /**
     *
     * @param webButtonDepense
     */
    public void setWebButtonDepense(WebButton webButtonDepense) {
        this.webButtonDepense = webButtonDepense;
    }

    /**
     *
     * @return
     */
    public WebButton getWebButtonRecette() {
        return webButtonRecette;
    }

    /**
     *
     * @param webButtonRecette
     */
    public void setWebButtonRecette(WebButton webButtonRecette) {
        this.webButtonRecette = webButtonRecette;
    }

    /**
     *
     * @return
     */
    public WebComboBox getWebComboBoxCompta() {
        return webComboBoxCompta;
    }

    /**
     *
     * @param webComboBoxCompta
     */
    public void setWebComboBoxCompta(WebComboBox webComboBoxCompta) {
        this.webComboBoxCompta = webComboBoxCompta;
    }

    /**
     *
     * @return
     */
    public WebLabel getWebLabelChoisirCompta() {
        return webLabelChoisirCompta;
    }

    /**
     *
     * @param webLabelChoisirCompta
     */
    public void setWebLabelChoisirCompta(WebLabel webLabelChoisirCompta) {
        this.webLabelChoisirCompta = webLabelChoisirCompta;
    }

    /**
     *
     * @return
     */
    public WebTextField getWebTextFieldRecherche() {
        return webTextFieldRecherche;
    }

    /**
     *
     * @param webTextFieldRecherche
     */
    public void setWebTextFieldRecherche(WebTextField webTextFieldRecherche) {
        this.webTextFieldRecherche = webTextFieldRecherche;
    }

    
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnulerAnnee;
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
    private javax.swing.JMenuItem jMenuItemImprimer;
    private javax.swing.JMenuItem jMenuItemQuitter;
    private javax.swing.JPanel jPanelLegende;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCM;
    private javax.swing.JTextField jTextFieldSoldeCompteCIO;
    private javax.swing.JTextField jTextFieldSoldeCompteCIOSansAnticipation;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.alee.laf.button.WebButton webButton1;
    private com.alee.laf.button.WebButton webButtonAnticipation;
    private com.alee.laf.button.WebButton webButtonDepense;
    private com.alee.laf.button.WebButton webButtonRecette;
    private com.alee.laf.combobox.WebComboBox webComboBoxCompta;
    private com.alee.laf.label.WebLabel webLabelChoisirCompta;
    private com.alee.laf.text.WebTextField webTextFieldRecherche;
    // End of variables declaration//GEN-END:variables

   
}
