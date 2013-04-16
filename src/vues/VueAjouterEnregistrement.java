/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;
import com.alee.extended.date.WebCalendar;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.managers.language.*;
import com.toedter.calendar.JDateChooser;
import controleurs.Controleur;
import controleurs.CtrlAjouterEnregistrement;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import modele.dao.DaoException;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Thibault
 */
public class VueAjouterEnregistrement extends VueAbstraite {

    DefaultComboBoxModel modeleJComboBoxCompte;
    DefaultComboBoxModel modeleJComboBoxMotif;
    DefaultComboBoxModel modeleJComboBoxModeReglement;
    DefaultComboBoxModel modeleJComboBoxEtat;
    DefaultComboBoxModel modeleJComboBoxLibelle;
    DefaultComboBoxModel modeleJComboBoxLibelle1;
    DefaultComboBoxModel modeleJComboBoxLibelle2;
    DefaultComboBoxModel modeleJComboBoxLibelle3;
    DefaultComboBoxModel modeleJComboBoxLibelle5;
    DefaultComboBoxModel modeleComboboxCompta;
    
    
    /**
     *
     * @param ctrl
     */
    public VueAjouterEnregistrement(Controleur ctrl) {
        super(ctrl);
        initComponents();
        
        
        this.pack();
        this.setLocationRelativeTo(null);
        
        
        final JFrame f = (JFrame) SwingUtilities.getRoot(getComponent(0));
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    storeOptions(f);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        JTextArea ta = new JTextArea(20,50);
        f.add(ta);
        f.pack();

        File optionsFile = new File(fileName);
        if (optionsFile.exists()) {
            try {
                restoreOptions(f);
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            f.setLocationByPlatform(true);
        }
        f.setVisible(true);
        
               
       
        modeleJComboBoxMotif= new DefaultComboBoxModel();
        jComboBoxMotif.setModel(modeleJComboBoxMotif);
        
        modeleJComboBoxModeReglement= new DefaultComboBoxModel();
        jComboBoxModRegl.setModel(modeleJComboBoxModeReglement);
        
        modeleJComboBoxEtat= new DefaultComboBoxModel();
        jComboBoxEtat.setModel(modeleJComboBoxEtat);
        
        modeleJComboBoxLibelle= new DefaultComboBoxModel();
        jComboBoxLibelle.setModel(modeleJComboBoxLibelle);
        
        
        modeleJComboBoxLibelle1= new DefaultComboBoxModel();
        jComboBoxLibelle1.setModel(modeleJComboBoxLibelle1);
        
        modeleJComboBoxLibelle2= new DefaultComboBoxModel();
        jComboBoxLibelle2.setModel(modeleJComboBoxLibelle2);
                
        modeleJComboBoxLibelle3= new DefaultComboBoxModel();
        jComboBoxLibelle3.setModel(modeleJComboBoxLibelle3);
        
        
        modeleJComboBoxLibelle5= new DefaultComboBoxModel();
        jComboBoxLibelle5.setModel(modeleJComboBoxLibelle5);
        
        modeleComboboxCompta= new DefaultComboBoxModel();
        webComboBoxCompta.setModel(modeleComboboxCompta);
        
        jLabelDateFacture.setVisible(false);
        jTextFieldDateFacture.setVisible(false);
        jTextFieldNumCHQ.setVisible(false);
        jLabelNumCHQ.setVisible(false);
        jRadioButtonRegl.setVisible(false);
        jRadioButtonLibFacture.setVisible(false);
        jRadioButtonLibPret.setVisible(false);
        jRadioButtonLibRestaurant.setVisible(false);
        jLabelMontant1.setVisible(false);
        jLabelMontant2.setVisible(false);
        jLabelMontant3.setVisible(false);
        jLabelMontant4.setVisible(false);
        jTextFieldMontant1.setVisible(false);
        jTextFieldMontant2.setVisible(false);
        jTextFieldMontant3.setVisible(false);
        jTextFieldMontant4.setVisible(false);
        jLabelDateFacture1.setVisible(false);
        jTextFieldDateFacture1.setVisible(false);
        jLabelDateFacture2.setVisible(false);
        jTextFieldDateFacture2.setVisible(false);
        jLabelDateFacture3.setVisible(false);
        jTextFieldDateFacture3.setVisible(false);
        jLabelDateFacture5.setVisible(false);
        jTextFieldDateFacture5.setVisible(false);
        jComboBoxLibelle1.setVisible(false);
        jComboBoxLibelle2.setVisible(false);
        jComboBoxLibelle3.setVisible(false);
        jComboBoxLibelle5.setVisible(false);
        jLabelValidation.setVisible(false);
        jButtonAfficher.setVisible(false);
        jButtonModifier.setVisible(false);
        jButtonNouveau.setVisible(false);
        jButtonValiderModifier.setVisible(false);
        
        AutoCompleteDecorator.decorate(jComboBoxLibelle);
       
        jDateChooserDateEnregistrement.setLocale(Locale.FRENCH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButtonValider = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jLabelReDep = new javax.swing.JLabel();
        jComboBoxRecDep = new javax.swing.JComboBox();
        jLabelMotif = new javax.swing.JLabel();
        jComboBoxMotif = new javax.swing.JComboBox();
        jLabelDateEnregistrement = new javax.swing.JLabel();
        jLabelLibelle = new javax.swing.JLabel();
        jRadioButtonLibRestaurant = new javax.swing.JRadioButton();
        jRadioButtonLibFacture = new javax.swing.JRadioButton();
        jRadioButtonLibPret = new javax.swing.JRadioButton();
        jLabelDateFacture5 = new javax.swing.JLabel();
        jTextFieldDateFacture5 = new javax.swing.JTextField();
        jLabelModeRegl = new javax.swing.JLabel();
        jComboBoxModRegl = new javax.swing.JComboBox();
        jLabelMontant = new javax.swing.JLabel();
        jTextFieldMontant = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxEtat = new javax.swing.JComboBox();
        jLabelNumCHQ = new javax.swing.JLabel();
        jTextFieldNumCHQ = new javax.swing.JTextField();
        jRadioButtonRegl = new javax.swing.JRadioButton();
        jComboBoxLibelle5 = new javax.swing.JComboBox();
        jComboBoxLibelle1 = new javax.swing.JComboBox();
        jComboBoxLibelle2 = new javax.swing.JComboBox();
        jComboBoxLibelle3 = new javax.swing.JComboBox();
        jLabelDateFacture1 = new javax.swing.JLabel();
        jTextFieldDateFacture1 = new javax.swing.JTextField();
        jLabelDateFacture2 = new javax.swing.JLabel();
        jTextFieldDateFacture2 = new javax.swing.JTextField();
        jTextFieldDateFacture3 = new javax.swing.JTextField();
        jLabelMontant1 = new javax.swing.JLabel();
        jTextFieldMontant1 = new javax.swing.JTextField();
        jLabelMontant2 = new javax.swing.JLabel();
        jTextFieldMontant2 = new javax.swing.JTextField();
        jLabelMontant3 = new javax.swing.JLabel();
        jTextFieldMontant3 = new javax.swing.JTextField();
        jLabelMontant4 = new javax.swing.JLabel();
        jTextFieldMontant4 = new javax.swing.JTextField();
        jComboBoxLibelle = new javax.swing.JComboBox();
        jLabelDateFacture = new javax.swing.JLabel();
        jTextFieldDateFacture = new javax.swing.JTextField();
        jLabelValidation = new javax.swing.JLabel();
        jButtonNouveau = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonAfficher = new javax.swing.JButton();
        jButtonValiderModifier = new javax.swing.JButton();
        jDateChooserDateEnregistrement = new com.toedter.calendar.JDateChooser();
        webLabelCompta = new com.alee.laf.label.WebLabel();
        webComboBoxCompta = new com.alee.laf.combobox.WebComboBox();
        jLabelDateFacture3 = new javax.swing.JLabel();
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
        setTitle("Ajouter un enregistrement");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButtonValider.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButtonAnnuler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jLabelReDep.setText("Recette / Depense ?");

        jComboBoxRecDep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "Recette", "Dépense" }));
        jComboBoxRecDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRecDepActionPerformed(evt);
            }
        });

        jLabelMotif.setText("Motif :");

        jComboBoxMotif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMotif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMotifActionPerformed(evt);
            }
        });

        jLabelDateEnregistrement.setText("Date de l'enregistrement :");

        jLabelLibelle.setText("Libellé :");

        buttonGroup1.add(jRadioButtonLibRestaurant);
        jRadioButtonLibRestaurant.setText("Restaurant ?");
        jRadioButtonLibRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLibRestaurantActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonLibFacture);
        jRadioButtonLibFacture.setText("Facture ?");
        jRadioButtonLibFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLibFactureActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonLibPret);
        jRadioButtonLibPret.setText("Prêt ?");
        jRadioButtonLibPret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLibPretActionPerformed(evt);
            }
        });

        jLabelDateFacture5.setText("Date facture :");

        jTextFieldDateFacture5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateFacture5ActionPerformed(evt);
            }
        });

        jLabelModeRegl.setText("Mode de règlement :");

        jComboBoxModRegl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxModRegl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxModReglActionPerformed(evt);
            }
        });

        jLabelMontant.setText("Montant :");

        jLabel1.setText("Etat :");

        jComboBoxEtat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelNumCHQ.setText("Numéro de chèque :");

        jTextFieldNumCHQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumCHQActionPerformed(evt);
            }
        });

        jRadioButtonRegl.setText("Règlement ?");
        jRadioButtonRegl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonReglActionPerformed(evt);
            }
        });

        jComboBoxLibelle5.setEditable(true);
        jComboBoxLibelle5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxLibelle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLibelle5ActionPerformed(evt);
            }
        });

        jComboBoxLibelle1.setEditable(true);
        jComboBoxLibelle1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxLibelle2.setEditable(true);
        jComboBoxLibelle2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxLibelle3.setEditable(true);
        jComboBoxLibelle3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelDateFacture1.setText("Date facture :");

        jTextFieldDateFacture1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateFacture1ActionPerformed(evt);
            }
        });

        jLabelDateFacture2.setText("Date facture :");

        jTextFieldDateFacture2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateFacture2ActionPerformed(evt);
            }
        });

        jTextFieldDateFacture3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateFacture3ActionPerformed(evt);
            }
        });

        jLabelMontant1.setText("Montant :");

        jLabelMontant2.setText("Montant :");

        jLabelMontant3.setText("Montant :");

        jLabelMontant4.setText("Montant :");

        jComboBoxLibelle.setEditable(true);
        jComboBoxLibelle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelDateFacture.setText("Date facture :");

        jTextFieldDateFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateFactureActionPerformed(evt);
            }
        });

        jLabelValidation.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabelValidation.setForeground(new java.awt.Color(255, 0, 0));
        jLabelValidation.setText("Nouvelle entrée enregistrée");

        jButtonNouveau.setText("Nouveau");
        jButtonNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNouveauActionPerformed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jButtonAfficher.setText("Afficher");
        jButtonAfficher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfficherActionPerformed(evt);
            }
        });

        jButtonValiderModifier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonValiderModifier.setText("Valider");
        jButtonValiderModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderModifierActionPerformed(evt);
            }
        });

        webLabelCompta.setText("Comptabilité :");

        jLabelDateFacture3.setText("Date facture :");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelMontant)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxEtat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabelDateFacture3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldDateFacture3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelMontant4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldMontant4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(61, 61, 61)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelDateFacture)
                                        .addComponent(jLabelLibelle))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jRadioButtonRegl)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButtonLibRestaurant)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButtonLibFacture)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButtonLibPret)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBoxLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextFieldDateFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jComboBoxLibelle2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxLibelle1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxLibelle5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxLibelle3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelDateFacture5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldDateFacture5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelDateFacture1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldDateFacture1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelDateFacture2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldDateFacture2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelMontant2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldMontant2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelMontant3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldMontant3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelMontant1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldMontant1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(50, 50, 50)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabelMotif)
                                                .addComponent(jLabelReDep)
                                                .addComponent(jLabelModeRegl)
                                                .addComponent(webLabelCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabelDateEnregistrement)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jComboBoxRecDep, 0, 244, Short.MAX_VALUE)
                                                .addComponent(jComboBoxMotif, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBoxModRegl, 0, 244, Short.MAX_VALUE)
                                                .addComponent(jDateChooserDateEnregistrement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabelNumCHQ)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldNumCHQ, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(webComboBoxCompta, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabelValidation)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAnnuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonValiderModifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonValider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNouveau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAfficher)))
                .addContainerGap(532, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webLabelCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webComboBoxCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserDateEnregistrement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateEnregistrement))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelReDep)
                    .addComponent(jComboBoxRecDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMotif)
                    .addComponent(jComboBoxMotif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelModeRegl)
                    .addComponent(jComboBoxModRegl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumCHQ)
                    .addComponent(jTextFieldNumCHQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelLibelle)
                        .addComponent(jRadioButtonRegl))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonLibRestaurant)
                        .addComponent(jRadioButtonLibFacture)
                        .addComponent(jRadioButtonLibPret)
                        .addComponent(jComboBoxLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDateFacture)
                    .addComponent(jTextFieldDateFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLibelle5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateFacture5)
                    .addComponent(jTextFieldDateFacture5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMontant1)
                    .addComponent(jTextFieldMontant1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLibelle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateFacture1)
                    .addComponent(jTextFieldDateFacture1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMontant2)
                    .addComponent(jTextFieldMontant2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLibelle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateFacture2)
                    .addComponent(jTextFieldDateFacture2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMontant3)
                    .addComponent(jTextFieldMontant3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLibelle3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDateFacture3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMontant4)
                    .addComponent(jTextFieldMontant4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateFacture3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMontant)
                    .addComponent(jTextFieldMontant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxEtat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValidation)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonNouveau)
                    .addComponent(jButtonModifier)
                    .addComponent(jButtonAfficher)
                    .addComponent(jButtonValiderModifier))
                .addGap(93, 93, 93))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjouterActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherAjouterEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAjouterActionPerformed

    private void jMenuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitterActionPerformed

    private void jTextFieldDateFacture5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateFacture5ActionPerformed
     
    }//GEN-LAST:event_jTextFieldDateFacture5ActionPerformed

    private void jComboBoxRecDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRecDepActionPerformed
    
        try {
            
            String recdep = jComboBoxRecDep.getSelectedItem().toString();
            
            Boolean result = null;
            if("Recette".equals(recdep)){
                result = false;
                jComboBoxMotif.removeAllItems();
                jRadioButtonRegl.setVisible(true);
                jRadioButtonLibFacture.setVisible(false);
                jRadioButtonLibPret.setVisible(false);
                jRadioButtonLibRestaurant.setVisible(false);
            }else{
                if("Dépense".equals(recdep)){
                    result = true;
                    jComboBoxMotif.removeAllItems();
                    jRadioButtonLibFacture.setVisible(true);
                    jRadioButtonLibPret.setVisible(true);
                    jRadioButtonLibRestaurant.setVisible(true);
                    jRadioButtonRegl.setVisible(false);
                }else{

                }
            }
            ((CtrlAjouterEnregistrement)controleur).chargerListeMotifsFromRecDep(result);
        } catch (DaoException ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_jComboBoxRecDepActionPerformed

    private void jComboBoxMotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMotifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMotifActionPerformed

    private void jRadioButtonLibFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLibFactureActionPerformed
        ((CtrlAjouterEnregistrement)controleur).affichagePourFactureEtRèglement("Facture");
    }//GEN-LAST:event_jRadioButtonLibFactureActionPerformed

    private void jRadioButtonLibRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLibRestaurantActionPerformed
        ((CtrlAjouterEnregistrement)controleur).affichagePourRestaurantEtPret("Restaurant");
    }//GEN-LAST:event_jRadioButtonLibRestaurantActionPerformed

    private void jRadioButtonLibPretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLibPretActionPerformed
        ((CtrlAjouterEnregistrement)controleur).affichagePourRestaurantEtPret("Prêt");
    }//GEN-LAST:event_jRadioButtonLibPretActionPerformed

    private void jTextFieldNumCHQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumCHQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumCHQActionPerformed

    private void jComboBoxModReglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxModReglActionPerformed
        ((CtrlAjouterEnregistrement)controleur).afficherRemiseChq();
    }//GEN-LAST:event_jComboBoxModReglActionPerformed

    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherAccueil();
        } catch (DaoException ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jMenuItemCIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCIOActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherAfficherCompteCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCIOActionPerformed

    private void jRadioButtonReglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonReglActionPerformed
        ((CtrlAjouterEnregistrement)controleur).affichagePourFactureEtRèglement("Règlement");
    }//GEN-LAST:event_jRadioButtonReglActionPerformed

    private void jTextFieldDateFacture1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateFacture1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateFacture1ActionPerformed

    private void jTextFieldDateFacture2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateFacture2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateFacture2ActionPerformed

    private void jTextFieldDateFacture3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateFacture3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateFacture3ActionPerformed

    private void jTextFieldDateFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateFactureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateFactureActionPerformed

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        
        
        try {
           ((CtrlAjouterEnregistrement)controleur).enregistrerEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void jComboBoxLibelle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLibelle5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLibelle5ActionPerformed

    private void jButtonNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNouveauActionPerformed
        final JFrame f = (JFrame) SwingUtilities.getRoot(getComponent(0));
        try {
            storeOptions(f);
        } catch (Exception ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherAjouterEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonNouveauActionPerformed

    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
      
            ((CtrlAjouterEnregistrement)controleur).modifierEnregistrement();
        
    }//GEN-LAST:event_jButtonModifierActionPerformed

    private void jButtonValiderModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderModifierActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).validerModifierEnregistrement();
        } catch (DaoException ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonValiderModifierActionPerformed

    private void jButtonAfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfficherActionPerformed
        try {
            if((webComboBoxCompta.getSelectedItem().toString()).contains("CIC")){
                
                ((CtrlAjouterEnregistrement)controleur).afficherAfficherCompteCIO();
                
            }else{
                
                ((CtrlAjouterEnregistrement)controleur).afficherAfficherCompteCM();
                
            }
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAfficherActionPerformed

    private void jMenuItemCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMActionPerformed
       try {
            ((CtrlAjouterEnregistrement)controleur).afficherAfficherCompteCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCMActionPerformed

    private void jMenuItemCIOArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCIOArchActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherArchivageCIO();
        } catch (DaoException ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCIOArchActionPerformed

    private void jMenuItemCMArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCMArchActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherArchivageCM();
        } catch (DaoException ex) {
            Logger.getLogger(VueAjouterEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCMArchActionPerformed

    private void jMenuItemImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimerActionPerformed
        try {
            ((CtrlAjouterEnregistrement)controleur).afficherImpression();
        } catch (DaoException ex) {
            Logger.getLogger(VueAfficherCompteCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemImprimerActionPerformed


    /** This will end up in the current directory
    A more sensible location is a sub-directory of user.home.
    (left as an exercise for the reader) */
    public static final String fileName = "options.prop";

    /** Store location & size of UI */
    public static void storeOptions(Frame f) throws Exception {
        File file = new File(fileName);
        file.delete();
        Properties p = new Properties();
        // restore the frame from 'full screen' first!
        f.setExtendedState(Frame.NORMAL);
        Rectangle r = f.getBounds();
        int x = (int)r.getX();
        int y = (int)r.getY();
        int w = (int)r.getWidth();
        int h = (int)r.getHeight();

        p.setProperty("x", "" + x);
        p.setProperty("y", "" + y);
        p.setProperty("w", "" + w);
        p.setProperty("h", "" + h);

        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        p.store(br, "Properties of the user frame");
    }

    /** Restore location & size of UI */
    public static void restoreOptions(Frame f) throws IOException {
        File file = new File(fileName);
        Properties p = new Properties();
        BufferedReader br = new BufferedReader(new FileReader(file));
        p.load(br);

        int x = Integer.parseInt(p.getProperty("x"));
        int y = Integer.parseInt(p.getProperty("y"));
        int w = Integer.parseInt(p.getProperty("w"));
        int h = Integer.parseInt(p.getProperty("h"));

        Rectangle r = new Rectangle(x,y,w,h);

        f.setBounds(r);
    }
    
    /**
     *
     * @return
     */
    public JLabel getjLabelValidation() {
        return jLabelValidation;
    }

    /**
     *
     * @param jLabelValidation
     */
    public void setjLabelValidation(JLabel jLabelValidation) {
        this.jLabelValidation = jLabelValidation;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonAfficher() {
        return jButtonAfficher;
    }

    /**
     *
     * @param jButtonAfficher
     */
    public void setjButtonAfficher(JButton jButtonAfficher) {
        this.jButtonAfficher = jButtonAfficher;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonModifier() {
        return jButtonModifier;
    }

    /**
     *
     * @param jButtonModifier
     */
    public void setjButtonModifier(JButton jButtonModifier) {
        this.jButtonModifier = jButtonModifier;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonNouveau() {
        return jButtonNouveau;
    }

    /**
     *
     * @param jButtonNouveau
     */
    public void setjButtonNouveau(JButton jButtonNouveau) {
        this.jButtonNouveau = jButtonNouveau;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonValiderModifier() {
        return jButtonValiderModifier;
    }

    /**
     *
     * @param jButtonValiderModifier
     */
    public void setjButtonValiderModifier(JButton jButtonValiderModifier) {
        this.jButtonValiderModifier = jButtonValiderModifier;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxMotif() {
        return modeleJComboBoxMotif;
    }

    /**
     *
     * @param modeleJComboBoxMotif
     */
    public void setModeleJComboBoxMotif(DefaultComboBoxModel modeleJComboBoxMotif) {
        this.modeleJComboBoxMotif = modeleJComboBoxMotif;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxModeReglement() {
        return modeleJComboBoxModeReglement;
    }

    /**
     *
     * @param modeleJComboBoxModeReglement
     */
    public void setModeleJComboBoxModeReglement(DefaultComboBoxModel modeleJComboBoxModeReglement) {
        this.modeleJComboBoxModeReglement = modeleJComboBoxModeReglement;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxEtat() {
        return modeleJComboBoxEtat;
    }

    /**
     *
     * @param modeleJComboBoxEtat
     */
    public void setModeleJComboBoxEtat(DefaultComboBoxModel modeleJComboBoxEtat) {
        this.modeleJComboBoxEtat = modeleJComboBoxEtat;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxLibelle() {
        return modeleJComboBoxLibelle;
    }

    /**
     *
     * @param modeleJComboBoxLibelle
     */
    public void setModeleJComboBoxLibelle(DefaultComboBoxModel modeleJComboBoxLibelle) {
        this.modeleJComboBoxLibelle = modeleJComboBoxLibelle;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxLibelle1() {
        return modeleJComboBoxLibelle1;
    }

    /**
     *
     * @param modeleJComboBoxLibelle1
     */
    public void setModeleJComboBoxLibelle1(DefaultComboBoxModel modeleJComboBoxLibelle1) {
        this.modeleJComboBoxLibelle1 = modeleJComboBoxLibelle1;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxLibelle2() {
        return modeleJComboBoxLibelle2;
    }

    /**
     *
     * @param modeleJComboBoxLibelle2
     */
    public void setModeleJComboBoxLibelle2(DefaultComboBoxModel modeleJComboBoxLibelle2) {
        this.modeleJComboBoxLibelle2 = modeleJComboBoxLibelle2;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxLibelle3() {
        return modeleJComboBoxLibelle3;
    }

    /**
     *
     * @param modeleJComboBoxLibelle3
     */
    public void setModeleJComboBoxLibelle3(DefaultComboBoxModel modeleJComboBoxLibelle3) {
        this.modeleJComboBoxLibelle3 = modeleJComboBoxLibelle3;
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getModeleJComboBoxLibelle5() {
        return modeleJComboBoxLibelle5;
    }

    /**
     *
     * @param modeleJComboBoxLibelle5
     */
    public void setModeleJComboBoxLibelle5(DefaultComboBoxModel modeleJComboBoxLibelle5) {
        this.modeleJComboBoxLibelle5 = modeleJComboBoxLibelle5;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    /**
     *
     * @param buttonGroup1
     */
    public void setButtonGroup1(ButtonGroup buttonGroup1) {
        this.buttonGroup1 = buttonGroup1;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonAnnuler() {
        return jButtonAnnuler;
    }

    /**
     *
     * @param jButtonAnnuler
     */
    public void setjButtonAnnuler(JButton jButtonAnnuler) {
        this.jButtonAnnuler = jButtonAnnuler;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonValider() {
        return jButtonValider;
    }

    /**
     *
     * @param jButtonValider
     */
    public void setjButtonValider(JButton jButtonValider) {
        this.jButtonValider = jButtonValider;
    }

    /**
     *
     * @return
     */
    public JDateChooser getjDateChooserDateEnregistrement() {
        return jDateChooserDateEnregistrement;
    }

    /**
     *
     * @param jDateChooserDateEnregistrement
     */
    public void setjDateChooserDateEnregistrement(JDateChooser jDateChooserDateEnregistrement) {
        this.jDateChooserDateEnregistrement = jDateChooserDateEnregistrement;
    }

     


    /**
     *
     * @return
     */
    public JComboBox getjComboBoxEtat() {
        return jComboBoxEtat;
    }

    /**
     *
     * @param jComboBoxEtat
     */
    public void setjComboBoxEtat(JComboBox jComboBoxEtat) {
        this.jComboBoxEtat = jComboBoxEtat;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxLibelle() {
        return jComboBoxLibelle;
    }

    /**
     *
     * @param jComboBoxLibelle
     */
    public void setjComboBoxLibelle(JComboBox jComboBoxLibelle) {
        this.jComboBoxLibelle = jComboBoxLibelle;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxLibelle1() {
        return jComboBoxLibelle1;
    }

    /**
     *
     * @param jComboBoxLibelle1
     */
    public void setjComboBoxLibelle1(JComboBox jComboBoxLibelle1) {
        this.jComboBoxLibelle1 = jComboBoxLibelle1;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxLibelle2() {
        return jComboBoxLibelle2;
    }

    /**
     *
     * @param jComboBoxLibelle2
     */
    public void setjComboBoxLibelle2(JComboBox jComboBoxLibelle2) {
        this.jComboBoxLibelle2 = jComboBoxLibelle2;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxLibelle3() {
        return jComboBoxLibelle3;
    }

    /**
     *
     * @param jComboBoxLibelle3
     */
    public void setjComboBoxLibelle3(JComboBox jComboBoxLibelle3) {
        this.jComboBoxLibelle3 = jComboBoxLibelle3;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxLibelle5() {
        return jComboBoxLibelle5;
    }

    /**
     *
     * @param jComboBoxLibelle5
     */
    public void setjComboBoxLibelle5(JComboBox jComboBoxLibelle5) {
        this.jComboBoxLibelle5 = jComboBoxLibelle5;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxModRegl() {
        return jComboBoxModRegl;
    }

    /**
     *
     * @param jComboBoxModRegl
     */
    public void setjComboBoxModRegl(JComboBox jComboBoxModRegl) {
        this.jComboBoxModRegl = jComboBoxModRegl;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxMotif() {
        return jComboBoxMotif;
    }

    /**
     *
     * @param jComboBoxMotif
     */
    public void setjComboBoxMotif(JComboBox jComboBoxMotif) {
        this.jComboBoxMotif = jComboBoxMotif;
    }

    /**
     *
     * @return
     */
    public JComboBox getjComboBoxRecDep() {
        return jComboBoxRecDep;
    }

    /**
     *
     * @param jComboBoxRecDep
     */
    public void setjComboBoxRecDep(JComboBox jComboBoxRecDep) {
        this.jComboBoxRecDep = jComboBoxRecDep;
    }

    

    /**
     *
     * @return
     */
    public JLabel getjLabel1() {
        return jLabel1;
    }

    /**
     *
     * @param jLabel1
     */
    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelDateEnregistrement() {
        return jLabelDateEnregistrement;
    }

    /**
     *
     * @param jLabelDateEnregistrement
     */
    public void setjLabelDateEnregistrement(JLabel jLabelDateEnregistrement) {
        this.jLabelDateEnregistrement = jLabelDateEnregistrement;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelDateFacture() {
        return jLabelDateFacture;
    }

    /**
     *
     * @param jLabelDateFacture
     */
    public void setjLabelDateFacture(JLabel jLabelDateFacture) {
        this.jLabelDateFacture = jLabelDateFacture;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelDateFacture1() {
        return jLabelDateFacture1;
    }

    /**
     *
     * @param jLabelDateFacture1
     */
    public void setjLabelDateFacture1(JLabel jLabelDateFacture1) {
        this.jLabelDateFacture1 = jLabelDateFacture1;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelDateFacture2() {
        return jLabelDateFacture2;
    }

    /**
     *
     * @param jLabelDateFacture2
     */
    public void setjLabelDateFacture2(JLabel jLabelDateFacture2) {
        this.jLabelDateFacture2 = jLabelDateFacture2;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelDateFacture3() {
        return jLabelDateFacture3;
    }

    /**
     *
     * @param jLabelDateFacture3
     */
    public void setjLabelDateFacture3(JLabel jLabelDateFacture3) {
        this.jLabelDateFacture3 = jLabelDateFacture3;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelDateFacture5() {
        return jLabelDateFacture5;
    }

    /**
     *
     * @param jLabelDateFacture5
     */
    public void setjLabelDateFacture5(JLabel jLabelDateFacture5) {
        this.jLabelDateFacture5 = jLabelDateFacture5;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelLibelle() {
        return jLabelLibelle;
    }

    /**
     *
     * @param jLabelLibelle
     */
    public void setjLabelLibelle(JLabel jLabelLibelle) {
        this.jLabelLibelle = jLabelLibelle;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelModeRegl() {
        return jLabelModeRegl;
    }

    /**
     *
     * @param jLabelModeRegl
     */
    public void setjLabelModeRegl(JLabel jLabelModeRegl) {
        this.jLabelModeRegl = jLabelModeRegl;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelMontant() {
        return jLabelMontant;
    }

    /**
     *
     * @param jLabelMontant
     */
    public void setjLabelMontant(JLabel jLabelMontant) {
        this.jLabelMontant = jLabelMontant;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelMontant1() {
        return jLabelMontant1;
    }

    /**
     *
     * @param jLabelMontant1
     */
    public void setjLabelMontant1(JLabel jLabelMontant1) {
        this.jLabelMontant1 = jLabelMontant1;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelMontant2() {
        return jLabelMontant2;
    }

    /**
     *
     * @param jLabelMontant2
     */
    public void setjLabelMontant2(JLabel jLabelMontant2) {
        this.jLabelMontant2 = jLabelMontant2;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelMontant3() {
        return jLabelMontant3;
    }

    /**
     *
     * @param jLabelMontant3
     */
    public void setjLabelMontant3(JLabel jLabelMontant3) {
        this.jLabelMontant3 = jLabelMontant3;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelMontant4() {
        return jLabelMontant4;
    }

    /**
     *
     * @param jLabelMontant4
     */
    public void setjLabelMontant4(JLabel jLabelMontant4) {
        this.jLabelMontant4 = jLabelMontant4;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelMotif() {
        return jLabelMotif;
    }

    /**
     *
     * @param jLabelMotif
     */
    public void setjLabelMotif(JLabel jLabelMotif) {
        this.jLabelMotif = jLabelMotif;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelNumCHQ() {
        return jLabelNumCHQ;
    }

    /**
     *
     * @param jLabelNumCHQ
     */
    public void setjLabelNumCHQ(JLabel jLabelNumCHQ) {
        this.jLabelNumCHQ = jLabelNumCHQ;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelReDep() {
        return jLabelReDep;
    }

    /**
     *
     * @param jLabelReDep
     */
    public void setjLabelReDep(JLabel jLabelReDep) {
        this.jLabelReDep = jLabelReDep;
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
    public JRadioButton getjRadioButtonLibFacture() {
        return jRadioButtonLibFacture;
    }

    /**
     *
     * @param jRadioButtonLibFacture
     */
    public void setjRadioButtonLibFacture(JRadioButton jRadioButtonLibFacture) {
        this.jRadioButtonLibFacture = jRadioButtonLibFacture;
    }

    /**
     *
     * @return
     */
    public JRadioButton getjRadioButtonLibPret() {
        return jRadioButtonLibPret;
    }

    /**
     *
     * @param jRadioButtonLibPret
     */
    public void setjRadioButtonLibPret(JRadioButton jRadioButtonLibPret) {
        this.jRadioButtonLibPret = jRadioButtonLibPret;
    }

    /**
     *
     * @return
     */
    public JRadioButton getjRadioButtonLibRestaurant() {
        return jRadioButtonLibRestaurant;
    }

    /**
     *
     * @param jRadioButtonLibRestaurant
     */
    public void setjRadioButtonLibRestaurant(JRadioButton jRadioButtonLibRestaurant) {
        this.jRadioButtonLibRestaurant = jRadioButtonLibRestaurant;
    }

    /**
     *
     * @return
     */
    public JRadioButton getjRadioButtonRegl() {
        return jRadioButtonRegl;
    }

    /**
     *
     * @param jRadioButtonRegl
     */
    public void setjRadioButtonRegl(JRadioButton jRadioButtonRegl) {
        this.jRadioButtonRegl = jRadioButtonRegl;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldDateFacture() {
        return jTextFieldDateFacture;
    }

    /**
     *
     * @param jTextFieldDateFacture
     */
    public void setjTextFieldDateFacture(JTextField jTextFieldDateFacture) {
        this.jTextFieldDateFacture = jTextFieldDateFacture;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldDateFacture1() {
        return jTextFieldDateFacture1;
    }

    /**
     *
     * @param jTextFieldDateFacture1
     */
    public void setjTextFieldDateFacture1(JTextField jTextFieldDateFacture1) {
        this.jTextFieldDateFacture1 = jTextFieldDateFacture1;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldDateFacture2() {
        return jTextFieldDateFacture2;
    }

    /**
     *
     * @param jTextFieldDateFacture2
     */
    public void setjTextFieldDateFacture2(JTextField jTextFieldDateFacture2) {
        this.jTextFieldDateFacture2 = jTextFieldDateFacture2;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldDateFacture3() {
        return jTextFieldDateFacture3;
    }

    /**
     *
     * @param jTextFieldDateFacture3
     */
    public void setjTextFieldDateFacture3(JTextField jTextFieldDateFacture3) {
        this.jTextFieldDateFacture3 = jTextFieldDateFacture3;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldDateFacture5() {
        return jTextFieldDateFacture5;
    }

    /**
     *
     * @param jTextFieldDateFacture5
     */
    public void setjTextFieldDateFacture5(JTextField jTextFieldDateFacture5) {
        this.jTextFieldDateFacture5 = jTextFieldDateFacture5;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldMontant() {
        return jTextFieldMontant;
    }

    /**
     *
     * @param jTextFieldMontant
     */
    public void setjTextFieldMontant(JTextField jTextFieldMontant) {
        this.jTextFieldMontant = jTextFieldMontant;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldMontant1() {
        return jTextFieldMontant1;
    }

    /**
     *
     * @param jTextFieldMontant1
     */
    public void setjTextFieldMontant1(JTextField jTextFieldMontant1) {
        this.jTextFieldMontant1 = jTextFieldMontant1;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldMontant2() {
        return jTextFieldMontant2;
    }

    /**
     *
     * @param jTextFieldMontant2
     */
    public void setjTextFieldMontant2(JTextField jTextFieldMontant2) {
        this.jTextFieldMontant2 = jTextFieldMontant2;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldMontant3() {
        return jTextFieldMontant3;
    }

    /**
     *
     * @param jTextFieldMontant3
     */
    public void setjTextFieldMontant3(JTextField jTextFieldMontant3) {
        this.jTextFieldMontant3 = jTextFieldMontant3;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldMontant4() {
        return jTextFieldMontant4;
    }

    /**
     *
     * @param jTextFieldMontant4
     */
    public void setjTextFieldMontant4(JTextField jTextFieldMontant4) {
        this.jTextFieldMontant4 = jTextFieldMontant4;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldNumCHQ() {
        return jTextFieldNumCHQ;
    }

    /**
     *
     * @param jTextFieldNumCHQ
     */
    public void setjTextFieldNumCHQ(JTextField jTextFieldNumCHQ) {
        this.jTextFieldNumCHQ = jTextFieldNumCHQ;
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
    public WebLabel getWebLabelCompta() {
        return webLabelCompta;
    }

    /**
     *
     * @param webLabelCompta
     */
    public void setWebLabelCompta(WebLabel webLabelCompta) {
        this.webLabelCompta = webLabelCompta;
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAfficher;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonNouveau;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JButton jButtonValiderModifier;
    private javax.swing.JComboBox jComboBoxEtat;
    private javax.swing.JComboBox jComboBoxLibelle;
    private javax.swing.JComboBox jComboBoxLibelle1;
    private javax.swing.JComboBox jComboBoxLibelle2;
    private javax.swing.JComboBox jComboBoxLibelle3;
    private javax.swing.JComboBox jComboBoxLibelle5;
    private javax.swing.JComboBox jComboBoxModRegl;
    private javax.swing.JComboBox jComboBoxMotif;
    private javax.swing.JComboBox jComboBoxRecDep;
    private com.toedter.calendar.JDateChooser jDateChooserDateEnregistrement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDateEnregistrement;
    private javax.swing.JLabel jLabelDateFacture;
    private javax.swing.JLabel jLabelDateFacture1;
    private javax.swing.JLabel jLabelDateFacture2;
    private javax.swing.JLabel jLabelDateFacture3;
    private javax.swing.JLabel jLabelDateFacture5;
    private javax.swing.JLabel jLabelLibelle;
    private javax.swing.JLabel jLabelModeRegl;
    private javax.swing.JLabel jLabelMontant;
    private javax.swing.JLabel jLabelMontant1;
    private javax.swing.JLabel jLabelMontant2;
    private javax.swing.JLabel jLabelMontant3;
    private javax.swing.JLabel jLabelMontant4;
    private javax.swing.JLabel jLabelMotif;
    private javax.swing.JLabel jLabelNumCHQ;
    private javax.swing.JLabel jLabelReDep;
    private javax.swing.JLabel jLabelValidation;
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
    private javax.swing.JRadioButton jRadioButtonLibFacture;
    private javax.swing.JRadioButton jRadioButtonLibPret;
    private javax.swing.JRadioButton jRadioButtonLibRestaurant;
    private javax.swing.JRadioButton jRadioButtonRegl;
    private javax.swing.JTextField jTextFieldDateFacture;
    private javax.swing.JTextField jTextFieldDateFacture1;
    private javax.swing.JTextField jTextFieldDateFacture2;
    private javax.swing.JTextField jTextFieldDateFacture3;
    private javax.swing.JTextField jTextFieldDateFacture5;
    private javax.swing.JTextField jTextFieldMontant;
    private javax.swing.JTextField jTextFieldMontant1;
    private javax.swing.JTextField jTextFieldMontant2;
    private javax.swing.JTextField jTextFieldMontant3;
    private javax.swing.JTextField jTextFieldMontant4;
    private javax.swing.JTextField jTextFieldNumCHQ;
    private com.alee.laf.combobox.WebComboBox webComboBoxCompta;
    private com.alee.laf.label.WebLabel webLabelCompta;
    // End of variables declaration//GEN-END:variables

   
}
