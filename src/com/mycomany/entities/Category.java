/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author DEll
 */
public class Category {
    private int id;
    private String titre;

    public Category() {
    }

    
    
    public Category(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Category(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", titre=" + titre + '}';
    }
    
    
    
}
