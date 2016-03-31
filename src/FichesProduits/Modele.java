/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FichesProduits;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Louis Chesnais
 */
public class Modele {
    // chaine de connexion
    String cnxString = "jdbc:mysql://localhost:3306/fichesproduits-gsb";
    // Connexion
    Connection cnx;
    public Modele() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(cnxString,"root","");
            System.out.println("Cela fonctionne :p");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erreur de chargement du driver");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion à la base"  + ex.getMessage()) ;
        }
        
    }
    
    public ResultSet getProduitByRef(String reference){
        String SQL = "SELECT * FROM produit";
        try {
            // on crée la requête : statement en EN
            Statement requete = cnx.createStatement();
            //On envoie la requête et on récupère ce qu'elle renvoie
            ResultSet rs = requete.executeQuery(SQL);
            // on boucle sur le tableau qu'on nous renvoie
            while (rs.next()){
                System.out.println("/ id_produit "+rs.getInt("id_produit")+"/ libelle_produit "+rs.getString("libelle_produit")+"/ description "+rs.getString("description")+"/ reference "+rs.getInt("reference"));
            }
        } catch (SQLException ex) {
            System.out.println("problème de création de requête");
        }
    }
    
    
    public ResultSet getProduitByCat(int id){
        String SQL = "SELECT * FROM categorie";
        try {
            // on crée la requête : statement en EN
            Statement requete = cnx.createStatement();
            //On envoie la requête et on récupère ce qu'elle renvoie
            ResultSet rs = requete.executeQuery(SQL);
            // on boucle sur le tableau qu'on nous renvoie
            while (rs.next()){
                System.out.println("/ id_categorie "+rs.getInt("id_produit")+"/ libelle_categorie "+rs.getString("libelle_categorie"));
            }
        } catch (SQLException ex) {
            System.out.println("problème de création de requête");
        }
        return "yo";
    }
    
}