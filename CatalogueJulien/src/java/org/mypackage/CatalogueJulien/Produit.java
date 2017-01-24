package org.mypackage.CatalogueJulien;

/**
 * @author Julien
 */
public class Produit {
    private String description;
    private String nom;
    private int id;
    
    /**
     * Constructeur vide
     */
    public Produit(){
        description = null;
        nom = null;
        id = -1;
    }
    
    /**
     * Constructeur 3 paramètres
     * @param id
     * @param nom
     * @param description
     */
    public Produit( int id, String nom, String description){
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
