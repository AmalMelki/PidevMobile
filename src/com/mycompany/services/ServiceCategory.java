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
import com.mycomany.entities.Category;


import com.mycomany.utils.Statics1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCategory {

    public ArrayList<Category> categories;
    
    public static ServiceCategory instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategory() {
         req = new ConnectionRequest();
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }

    public boolean addCategory(Category t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics1.BASE_URL + "/category/new?Titre=" + t.getTitre();
       
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("nom", t.getTitre());
      ;
      
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
   public ArrayList<Category> parsecategories(String jsonText){
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> categoriesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)categoriesListJson.get("root");
            for(Map<String,Object> obj : list){
                Category t = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTitre(obj.get("Titre").toString());
               
              
          
               
                
               categories.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return categories;
    }
    
        public ArrayList<Category> getAllcategories(){
        req = new ConnectionRequest();
       
        String url = Statics1.BASE_URL+"/category/";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               categories = parsecategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
}

