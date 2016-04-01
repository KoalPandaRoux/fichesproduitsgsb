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

    /*
    * Mise en place singleton 
    */
public class Modele {

    // Chaine de connexion à la base de donnée mysql
    private String cnxString = "jdbc:mysql://localhost:3306/fichesproduits-gsb";
    // Connexion
    private Connection cnx;
    // Singleton : instance unique d'une classe
    private static Modele instance = null;
    public static Modele getInstance(){
        if(instance == null){
            instance = new Modele();
        }
        return instance;
    }
    
   
    //configuration BDD
    private Modele() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(cnxString, "root", "");
            System.out.println("Cela fonctionne");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erreur de chargement du driver");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion à la base" + ex.getMessage());
        }

    }

    /**
     * Retourne un resultset contenant le produit trouvé par ref
     *
     * @param reference
     * @return ResultSet
     */
    public ResultSet getProduitByRef(String reference) {
        String SQL = "SELECT * FROM produit WHERE reference = '" + reference + "'";
        ResultSet rs;
        try {
            // on crée la requête : statement en EN
            Statement requete = cnx.createStatement();
            //On envoie la requête et on récupère ce qu'elle renvoie
            rs = requete.executeQuery(SQL);
        } catch (SQLException ex) {
            rs = null;
        }
        return rs;
    }
    
    /**
     * Retourne un resultset contenant le produit trouvé par categorie
     * 
     * @param id
     * @return  ResultSet
     */
      public ResultSet getProduitByCat(int id){
        String SQL = "SELECT * FROM produit WHERE id_categorie =" + id;
        ResultSet rs;
          try {
            // on crée la requête : statement en EN
            Statement requete = cnx.createStatement();
            //On envoie la requête et on récupère ce qu'elle renvoie
            rs = requete.executeQuery(SQL);
          } catch (SQLException ex) {
              rs = null;
          }
          return rs;
        }
}
    
    

