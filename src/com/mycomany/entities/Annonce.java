/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author cyrine
 */
public class Annonce {
    private  int id;
    private String titre;
    private float prix;
    private boolean disponible;
    private String description;
    private String type;
    private String date;

    public Annonce() {
    }

    public Annonce(int id, float prix,String titre, boolean disponible, String description, String type, String date) {
        this.id = id;
        this.titre=titre;
        this.prix = prix;
        this.disponible = disponible;
        this.description = description;
        this.type = type;
        this.date= date;
    }

    public Annonce(String titre, String description,float prix, String type) {
        this.titre=titre;
          this.description = description;
        this.prix = prix;
        
      
        this.type = type;
       
    }

   

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", titre=" + titre + ", prix=" + prix + ", disponible=" + disponible + ", description=" + description + ", type=" + type + ", date=" + date + '}';
    }
    
}
