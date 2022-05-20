
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Annonce;
import com.mycomany.utils.Statics;
import com.mycomany.utils.Statics_1;
import static com.mycompany.services.ServiceUtilisateur.resultOk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServicesAnnonce {

    public ArrayList<Annonce> annonces;
    
    public static ServicesAnnonce instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesAnnonce() {
         req = new ConnectionRequest();
    }

    public static ServicesAnnonce getInstance() {
        if (instance == null) {
            instance = new ServicesAnnonce();
        }
        return instance;
    }

    public boolean addAnnonce(Annonce t) {
        System.out.println(t);
        System.out.println("********");
      
       String url = Statics_1.BASE_URL + "/new?titre=" + t.getTitre()+ "&description=" + t.getDescription()+ "&prix=" + t.getPrix()+ "&type=" + t.getType();
       
        System.out.println(url);
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("titre", t.getTitre());
       req.addArgument("description", t.getDescription()+"");
       req.addArgument("prix", t.getPrix()+"");
       req.addArgument("type", t.getType()+"");
    
   
      
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

    public ArrayList<Annonce> parseAnnonces(String jsonText){
        try {
           annonces=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> annoncesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)annoncesListJson.get("root");
            for(Map<String,Object> obj : list){
                Annonce t = new Annonce();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTitre(obj.get("titre").toString());
                t.setDescription(obj.get("description").toString());
                 float prix = Float.parseFloat(obj.get("prix").toString());
                t.setPrix((Float)prix);
                t.setType(obj.get("type").toString());
                
              
          
               
                
                annonces.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return annonces;
    }
    
    public ArrayList<Annonce> getAllAnnonces(){
        req = new ConnectionRequest();
       
        String url = Statics_1.BASE_URL+"/";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  annonces = parseAnnonces(new String(req.getResponseData()));
                req.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return annonces;
    }
       
    
    public boolean deleteAnnonce(int id ) {
        String url = Statics_1.BASE_URL +"/delete?id="+id;
        
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
  
       //Update 
    public boolean modifierAnnonce(Annonce annonce) {
        String url = Statics_1.BASE_URL +"/"+annonce.getId()+"/edit?"+"&titre="+annonce.getTitre()+"&description="+annonce.getDescription()+"&type="+annonce.getType()+"&prix="+annonce.getPrix();
        req.setUrl(url);
        System.out.println(url);
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
