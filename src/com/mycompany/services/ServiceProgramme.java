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
import com.mycomany.entities.OptionGuide;
import com.mycomany.entities.OptionTransport;
import com.mycomany.entities.Programme;

import com.mycomany.utils.Statics1;
import static com.mycompany.services.ServiceUtilisateur.resultOk;




import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceProgramme {

    public ArrayList<Programme> programmes;
    
    public static ServiceProgramme instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProgramme() {
         req = new ConnectionRequest();
    }

    public static ServiceProgramme getInstance() {
        if (instance == null) {
            instance = new ServiceProgramme();
        }
        return instance;
    }

    public boolean addProgramme(Programme t) {
        System.out.println(t);
        System.out.println("********");
      
       String url = Statics1.BASE_URL + "/new?titre=" + t.getTitre()+ "&desc=" + t.getDescription()+ "&prix=" + t.getPrix()+ "&adresse=" + t.getAdresse()+ "&guide=" +2+ "&category=" + 1+ "&transport=" + 1;
       
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("name", t.getTitre());
       req.addArgument("desc", t.getDescription()+"");
       req.addArgument("prix", t.getPrix()+"");
       req.addArgument("adresse", t.getAdresse()+"");
       req.addArgument("guide", 2+"");
       req.addArgument("category", 1+"");
       req.addArgument("transport", 1+"");
   
      
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

    public ArrayList<Programme> parseProgrammes(String jsonText){
        try {
           programmes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> programmesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)programmesListJson.get("root");
            for(Map<String,Object> obj : list){
                Programme t = new Programme();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTitre(obj.get("Titre").toString());
                t.setDescription(obj.get("Description").toString());
                 float prix = Float.parseFloat(obj.get("prix").toString());
                t.setPrix((Float)prix);
                t.setAdresse(obj.get("adresse").toString());
                
              
          
               
                
                programmes.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return programmes;
    }
    
    public ArrayList<Programme> getAllProgrammes(){
        req = new ConnectionRequest();
       
        String url = Statics1.BASE_URL+"/";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                programmes = parseProgrammes(new String(req.getResponseData()));
                req.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return programmes;
    }
       
    
    public boolean deleteReclamation(int id ) {
        String url = Statics1.BASE_URL +"/delete?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
      public int getCategorieId(String c){
          System.out.println(c);
        ServiceCategory sc = new ServiceCategory();
      int id=0 ;
        ArrayList<Category>list = ServiceCategory.getInstance().getAllcategories();
        System.out.println(list);
        for (Category C : list) {
         if (C.getTitre() == c){
             id = C.getId();}
           return id;
            
          
        }
          System.out.println(id);
        return id;
    }
       //Update 
    public boolean modifierReclamation(Programme programme) {
        String url = Statics1.BASE_URL +"/"+programme.getId()+"/edit?"+"&titre="+programme.getTitre()+"&description="+programme.getDescription()+"&addresse="+programme.getAdresse()+"&prix="+programme.getPrix();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
 
}
