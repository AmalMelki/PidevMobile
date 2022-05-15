/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Category;
import com.mycomany.entities.OptionGuide;
import com.mycomany.entities.OptionTransport;
import com.mycomany.entities.Programme;

import com.mycompany.services.ServiceCategory;
import com.mycompany.services.ServiceGuide;
import com.mycompany.services.ServiceProgramme;

import com.mycompany.services.ServiceTransport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AjoutProgrammeForm1 extends BaseForm {
    
    
    Form current;
    public AjoutProgrammeForm1(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Programme");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Programmess", barGroup);
        mesListes.setUIID("SelectBar");
       /* RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");*/
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
         ListProgrammeForm1 a = new ListProgrammeForm1(res);
           a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
       // bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
        TextField titre = new TextField("", "titre!!");
        titre.setUIID("TextFieldBlack");
        addStringValue("Objet",titre);
        
        TextField description = new TextField("", "entrer description!!");
        description.setUIID("TextFieldBlack");
        addStringValue("Description",description);
        
         TextField addresse = new TextField("", "entrer addresse!!");
        addresse.setUIID("TextFieldBlack");
        addStringValue("addresse",addresse);
        
        TextField prix = new TextField("", "entrer prix!!");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix",prix);
        
         Picker pk = new Picker();
          //Label resDate = new Label("Datede naissance"+pk.getText());
         pk.setUIID("TextFieldBlack");
        addStringValue("date",pk);
        
         ServiceCategory sc = new ServiceCategory();
         ComboBox cb_c = new ComboBox();
        ArrayList<Category>list = ServiceCategory.getInstance().getAllcategories();
        for (int i = 0; i <list.size(); i++) {
          cb_c.addItem(list.get(i).getTitre());
        }     
        addStringValue("",cb_c);
        
             ServiceGuide sg = new ServiceGuide();
         ComboBox cb_g = new ComboBox();
        ArrayList<OptionGuide>listg = ServiceGuide.getInstance().getAllguides();
        for (int i = 0; i <listg.size(); i++) {
          cb_g.addItem(listg.get(i).getNom());
        }
             
        addStringValue("",cb_g);
        
             ServiceTransport st = new ServiceTransport();
         ComboBox cb_t = new ComboBox();
        ArrayList<OptionTransport>listt = ServiceTransport.getInstance().getAllTransport();
        for (int i = 0; i <listt.size(); i++) {
          cb_t.addItem(listt.get(i).getMatricule());
        }
             
        addStringValue("",cb_t);
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
       
      
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                if(titre.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                   Programme p = new Programme(String.valueOf(titre.getText()
                                  ).toString(),
                                  String.valueOf(description.getText()).toString(),
                                  Float.valueOf(prix.getText()),
                                   String.valueOf(addresse.getText()).toString(),
                                   String.valueOf(cb_g.getSelectedItem()).toString(),
                                   String.valueOf(cb_c.getSelectedItem()).toString(),
                                   String.valueOf(cb_t.getSelectedItem().toString())
                                    
                  );
                    
                    System.out.println("data  reclamation == "+p);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceProgramme.getInstance().addProgramme(p);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    //new ListReclamationForm(res).show();
                    
                    
                  //  refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
   
   
    
}
