/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Programme;
import com.mycompany.services.ServiceProgramme;


/**
 *
 * @author Lenovo
 */
public class ModifierProgrammeForm extends BaseForm {
    
    Form current;
    public ModifierProgrammeForm(Resources res , Programme r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Programme");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Titre = new TextField(r.getTitre() , "Titre" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription() , "Description" , 20 , TextField.ANY);
        TextField adr = new TextField(r.getAdresse() , "Adresse" , 20 , TextField.ANY);
       
               TextField prix = new TextField(String.valueOf(r.getPrix()) , "Prix" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
        
        
        
        Titre.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        adr.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
        
        Titre.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        adr.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setTitre(Titre.getText());
           r.setDescription(description.getText());
           r.setAdresse(adr.getText());
           r.setPrix(Float.parseFloat(prix.getText()));
           
          
       
       //appel fonction modfier reclamation men service
       
       if(ServiceProgramme.getInstance().modifierReclamation(r)) { // if true
           new ListProgrammeForm1(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListProgrammeForm1(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Titre),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(adr),
                createLineSeparator(),
                  new FloatingHint(prix),
                createLineSeparator(),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
