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
import com.mycomany.entities.Annonce;
import com.mycompany.services.ServicesAnnonce;



/**
 *
 * @author Lenovo
 */
public class ModifierAnnonceForm extends BaseForm {
    
    Form current;
    public ModifierAnnonceForm(Resources res , Annonce a) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Programme");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Titre = new TextField(a.getTitre() , "Titre" , 20 , TextField.ANY);
        TextField description = new TextField(a.getDescription() , "Description" , 20 , TextField.ANY);
        TextField adr = new TextField(a.getType() , "Type" , 20 , TextField.ANY);
       
               TextField prix = new TextField(String.valueOf(a.getPrix()) , "Prix" , 20 , TextField.ANY);
 
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
           
           a.setTitre(Titre.getText());
           a.setDescription(description.getText());
           a.setType(adr.getText());
           a.setPrix(Float.parseFloat(prix.getText()));
           
          
       
       //appel fonction modfier reclamation men service
       
       if(ServicesAnnonce.getInstance().modifierAnnonce(a)) { 
           new ListAnnonceForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListAnnonceForm(res).show();
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
