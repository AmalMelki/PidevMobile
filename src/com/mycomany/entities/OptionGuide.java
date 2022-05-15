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
public class OptionGuide {
    private int id;
    private String nom;
    private String prenom;
    private int tel;

    public OptionGuide() {
    }
    

    public OptionGuide(int id) {
        this.id = id;
    }

    public OptionGuide(int id, String nom, String prenom, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }
  public OptionGuide( String nom, String prenom, int tel) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "OptionGuide{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + '}';
    }
    
    
}
