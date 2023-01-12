/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojekpws.finalprojekpws;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 20200140111
 */
@RestController
@CrossOrigin
public class Controller {
    Pwsuas data = new Pwsuas();
    PwsuasJpaController dctrl = new PwsuasJpaController();
    
    @RequestMapping("/getNama")
    @ResponseBody
    public String getNamebyID(){
        
        try {data = dctrl.findPwsuas(1);}
        catch (Exception error){}
        
        return data.getNama();
    }
    
    @RequestMapping("/getNik")
    @ResponseBody
    public String getNikbyID (){
        
        try{data = dctrl.findPwsuas(1);}
        catch(Exception error) {}
        
        return data.getNik().toString();
    }
    
     @RequestMapping("/getAddress")
    @ResponseBody
    public String getAddressbyID (){
        
        try{data = dctrl.findPwsuas(1);}
        catch(Exception error) {}
        
        return data.getAddress().toString();
    }
    
     @RequestMapping("/getPhoto")
    @ResponseBody
    public String getPhotobyID (){
        
        try{data = dctrl.findPwsuas(1);}
        catch(Exception error) {}
        
        return data.getPhoto().toString();
    }

    
    @RequestMapping("/GET")
    @ResponseBody
    public List<Pwsuas> getDatabyID(){
        List<Pwsuas> datas = new ArrayList<>();
        try {datas = dctrl.findPwsuasEntities();}
        catch(Exception error) {}        
        return datas;
    }
    
    @RequestMapping("/getData/{id}")
    @ResponseBody
    public String getData (@PathVariable("id") int id){
        try {data = dctrl.findPwsuas(id);}
        catch(Exception error) {}
        
        String result = data.getNama()+ "<br>" + data.getAddress().toString(); 
        return result;
    }
    
    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Pwsuas data = new Pwsuas(); //jika ingin banyak data pake List atau ArrayList
        data = mapper.readValue(json_receive, Pwsuas.class);
        dctrl.create(data);
        message = data.getNama()+" Saved";
        return message;
    }
    

    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Pwsuas newdatas = new Pwsuas(); //jika ingin banyak data pake List atau ArrayList
        
        newdatas = mapper.readValue(json_receive, Pwsuas.class);
        try {dctrl.edit(newdatas);} catch (Exception e){}
        message = newdatas.getNama()+" Saved";
        return message;
    }
    
        @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Pwsuas newdatas = new Pwsuas(); //jika ingin banyak data pake List atau ArrayList     
        newdatas = mapper.readValue(json_receive, Pwsuas.class);
        dctrl.destroy(newdatas.getId());
        return "deleted";
    }
    
}
