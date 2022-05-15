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
public class OptionTransport {
    private int id;
    private String Matricule;
    private int capacite;

    public OptionTransport() {
    }

    public OptionTransport(int id, String Matricule, int capacite) {
        this.id = id;
        this.Matricule = Matricule;
        this.capacite = capacite;
    }

    public OptionTransport(String Matricule, int capacite) {
        this.Matricule = Matricule;
        this.capacite = capacite;
    }
    

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return Matricule;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "OptionTransport{" + "id=" + id + ", Matricule=" + Matricule + ", capacite=" + capacite + '}';
    }
    
    
}
