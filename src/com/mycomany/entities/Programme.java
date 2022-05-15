/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;



/**
 *
 * @author bhk
 */
public class Programme {
    private int id;
    private float prix;
    private String titre,description,adresse;
    private String categorie;
    private String guide;
    private String transport;
    private String region;
    private String date;
    private String image;
    private int Rating;
  

    public Programme() {
    }

   
    public Programme(String titre, String description,int prix,String adresse) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.adresse = adresse;
    }

    public Programme(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public Programme(String titre, String description, float prix, String adresse,  String guide,String categorie, String transport) {
        this.prix = prix;
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
        this.categorie = categorie;
        this.guide = guide;
        this.transport = transport;
     
    }

 

   

  
    
       


    public int getId() {
        return id;
    }

    public Float getPrix() {
        return prix;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getGuide() {
        return guide;
    }

    public String getTransport() {
        return transport;
    }

    public String getRegion() {
        return region;
    }

    public String getDate() {
        return date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Programme{" + "id=" + id + ", prix=" + prix + ", titre=" + titre + ", description=" + description + ", adresse=" + adresse + ", categorie=" + categorie + ", guide=" + guide + ", transport=" + transport + ", region=" + region + ", date=" + date + ", image=" + image + '}';
    }

 

 

   

  

    

   

   

  
   

    
  
    
    
}
