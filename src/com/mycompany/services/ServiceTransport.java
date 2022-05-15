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

import com.mycomany.entities.OptionTransport;

import com.mycomany.utils.Statics1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTransport {

    public ArrayList<OptionTransport> transports;
    
    public static ServiceTransport instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceTransport() {
         req = new ConnectionRequest();
    }

    public static ServiceTransport getInstance() {
        if (instance == null) {
            instance = new ServiceTransport();
        }
        return instance;
    }

    public boolean addOptiontransport(OptionTransport t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics1.BASE_URL + "/transport/new?matricule=" + t.getMatricule()+ "&capacite=" + t.getCapacite();
       
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("matricule", t.getMatricule()+"");
       req.addArgument("capacite", t.getCapacite()+"");
      
      
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
    public ArrayList<OptionTransport> parseTransports(String jsonText){
        try {
           transports=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> TransportsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)TransportsListJson.get("root");
            for(Map<String,Object> obj : list){
                OptionTransport t = new OptionTransport();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setMatricule(obj.get("matricule").toString());
                
                 int capacite = (int) Float.parseFloat(obj.get("capacite").toString());
                t.setCapacite((int)capacite);
               
                
              
          
               
                
                transports.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return transports;
    }
    
    public ArrayList<OptionTransport> getAllTransport(){
        req = new ConnectionRequest();
       
        String url = Statics1.BASE_URL+"/transport/";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                transports = parseTransports(new String(req.getResponseData()));
                req.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return transports;
    }
}

