/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FichesProduits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Louis
 */
public class Controller {

    ResultJFrame windowResult;
    Modele modele;
    ResultSet cursor;

    public Controller() {
        windowResult = new ResultJFrame();
        modele = Modele.getInstance();

        /*
        *
         */
        windowResult.BtnPrecedent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePrevious();
            }
        });

        windowResult.BtnSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveNext();
            }
        });

    }

    /*
    *Controleur par référence
     */
    public void controlByRef(String ref) {
        ResultSet rs = modele.getProduitByRef(ref);
        try {
            cursor = modele.getProduitByRef(ref);
            windowResult.BtnSuivant.setEnabled(false);
            windowResult.BtnPrecedent.setEnabled(false);
            
            if (cursor.next()) {
                updateScreenRef();
            } else {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowResult.show();
    }

    /*
    *Controleur par catégorie
     */
    public void controlByCat(int id) {

        windowResult.BtnPrecedent.setEnabled(false);
        windowResult.BtnSuivant.setEnabled(false);
        try {
            cursor = modele.getProduitByCat(id);
            if (cursor.next()) {
                windowResult.BtnSuivant.setEnabled(true);
                updateScreen();

            } else {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowResult.show();
    }

    private void movePrevious() {
        try {
            if (cursor.isFirst()) {
                windowResult.BtnPrecedent.setEnabled(false);
            } else {
                cursor.previous();
                windowResult.BtnSuivant.setEnabled(true);
                updateScreen();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void moveNext() {
        try {
            if (cursor.isLast()) {
                windowResult.BtnSuivant.setEnabled(false);
            } else {
                cursor.next();
                windowResult.BtnPrecedent.setEnabled(true);
                updateScreen();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateScreen() throws SQLException {

        String reference = cursor.getString("reference");
        String libelle_produit = cursor.getString("libelle_produit");
        String description = cursor.getString("description");

        windowResult.ReferenceTextField.setText(reference);
        windowResult.LibelleTextField.setText(libelle_produit);
        windowResult.DescriptionTextArea.setText(description);
    }

    private void updateScreenRef() throws SQLException {

        String reference = cursor.getString("reference");
        String libelle_produit = cursor.getString("libelle_produit");
        String description = cursor.getString("description");

        windowResult.ReferenceTextField.setText(reference);
        windowResult.LibelleTextField.setText(libelle_produit);
        windowResult.DescriptionTextArea.setText(description);
    }

}
