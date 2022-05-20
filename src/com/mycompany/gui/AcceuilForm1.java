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
public class AcceuilForm1 extends BaseForm {
    
    Form current;
    public AcceuilForm1(Resources res ) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
       
        getTitleArea().setUIID("Container");
        setTitle("Bienvenu à Win?");
         
        getContentPane().setScrollVisible(true);
        
        
        super.addSideMenu(res);
        
       
       
        
        
        
        
      
        Button btnModifier = new Button("Espace Programme");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 

           new AcceuilForm(res).show();
      
        });
   
       
   
        
        Container content = BoxLayout.encloseY(
              
                createLineSeparator(),
                createLineSeparator(),//ligne de séparation
                btnModifier
              
                
               
        );
        
        add(content);
        show();
        
        
    }
}
