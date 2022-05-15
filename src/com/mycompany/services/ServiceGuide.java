/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycomany.entities.OptionGuide;

import com.mycomany.utils.Statics1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceGuide {

    public ArrayList<OptionGuide> guides;
    
    public static ServiceGuide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceGuide() {
         req = new ConnectionRequest();
    }

    public static ServiceGuide getInstance() {
        if (instance == null) {
            instance = new ServiceGuide();
        }
        return instance;
    }

    public boolean addOptionGuide(OptionGuide t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics1.BASE_URL + "/guide/new?Nom=" + t.getNom()+ "&Prenom=" + t.getPrenom()+ "&tel=" + t.getTel();
       
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("nom", t.getNom());
       req.addArgument("prenom", t.getPrenom()+"");
       req.addArgument("tel", t.getTel()+"");
      
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<OptionGuide> parseGuides(String jsonText){
        try {
           guides=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> guidesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)guidesListJson.get("root");
            for(Map<String,Object> obj : list){
                OptionGuide t = new OptionGuide();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("Nom").toString());
                t.setPrenom(obj.get("Prenom").toString());
                 int tel = (int) Float.parseFloat(obj.get("Tel").toString());
                t.setTel((int)tel);
               
                
              
          
               
                
                guides.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return guides;
    }
    
    public ArrayList<OptionGuide> getAllguides(){
        req = new ConnectionRequest();
       
        String url = Statics1.BASE_URL+"/guide/";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                guides = parseGuides(new String(req.getResponseData()));
                req.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return guides;
    }
}

