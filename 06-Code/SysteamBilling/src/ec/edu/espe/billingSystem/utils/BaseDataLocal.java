/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template files, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.billingSystem.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import ec.edu.espe.billingSystem.model.Customer;
import java.net.UnknownHostException;

/**
 *
 * @author Bryan Azuero
 */
public class BaseDataLocal {
    DB dataBase;
    DBCollection colection;
    BasicDBObject mainFile = new BasicDBObject();
    BasicDBObject files = new BasicDBObject();

    public BaseDataLocal() throws UnknownHostException{
        Mongo mongo;
        mongo = new Mongo("localHost",27017);
        dataBase = mongo.getDB("DataBase1");
        colection = dataBase.getCollection("Person");
        System.out.println("Established connection");
    }
     public void dBCustomer(Customer customer){
        BasicDBObject file = new BasicDBObject();
        file.put("Name",customer.getName());
        file.put("Last Name",customer.getLastName());
        file.put("Address",customer.getAddress());
        file.put("Id Document",customer.getDocument());
        file.put("Number Phone",customer.getPhone());
        mainFile.put("Customer", file);
    }
     public void id(int id){
         mainFile.put("ID", id);
     }
     
     public void savePerson(){
         colection.insert(mainFile);
     }
     public void readPerson(int id){
         BasicDBObject consultation = new BasicDBObject();
         consultation.put("ID", id);
         DBCursor cursor = colection.find(consultation);
         while (cursor.hasNext()){
             System.out.println(cursor.next());
         }
     }
     public void updatePerson(String object,int id){
         if(object.equalsIgnoreCase("Customer")){
             Customer customer = new Customer();
             //customer.
             dBCustomer(customer);
         }
         BasicDBObject update = new BasicDBObject();
         update.append("$set", mainFile);
         BasicDBObject searchID = new BasicDBObject();
         searchID.append("ID", id);
         colection.update(searchID, update);
         
     }
     public void deletePerson(int id){
         colection.remove(new BasicDBObject().append("ID",id));
     }
     public void show(String coleccion){
        colection = dataBase.getCollection(coleccion);
        DBCursor cursor = colection.find();
        while(cursor.hasNext()){
            System.out.println("*" + cursor.next());
        }
    }
     public void SeachrByName(String coleccion,String name){
        colection = dataBase.getCollection(coleccion);
        BasicDBObject consultation = new BasicDBObject();
        consultation.put("Name",name);
        DBCursor cursor = colection.find(consultation);
        while(cursor.hasNext()){
            System.out.println("--"+ cursor.next().get("Name")+" - " 
                    + cursor.curr().get("Last Name")+
                    " - "+ cursor.curr().get("Address")+" - "
                    +cursor.curr().get("Id Document")+" - "+ 
                    cursor.curr().get("Number Phone"));
        }
    }
    public void updateAdrees(String coleccion,String name,String addressNew){
        colection = dataBase.getCollection(coleccion);
        BasicDBObject upgradeAddress = new BasicDBObject();
        upgradeAddress.append("$set",new BasicDBObject().append("Address",addressNew));
        BasicDBObject seachrByName = new BasicDBObject();
        seachrByName.append("Name",name);
        colection.updateMulti(seachrByName,upgradeAddress);
    }
    public void updateName(String coleccion,String name,String nameNew){
        colection = dataBase.getCollection(coleccion);
        BasicDBObject upgradeName = new BasicDBObject();
        upgradeName.append("$set",new BasicDBObject().append("Name",nameNew));
        BasicDBObject seachrByName = new BasicDBObject();
        seachrByName.append("Name",name);
        colection.updateMulti(seachrByName,upgradeName);
        
    }
    public void updateLastName(String coleccion,String name,String lastNameNew){
        colection = dataBase.getCollection(coleccion);
        BasicDBObject upgradeLastname = new BasicDBObject();
        upgradeLastname.append("$set",new BasicDBObject().append("Last Name",lastNameNew));
        BasicDBObject seachrByName = new BasicDBObject();
        seachrByName.append("Name",name);
        colection.updateMulti(seachrByName,upgradeLastname);
    }
    public void updatePhone(String coleccion,String name,String phoneNew){
        colection = dataBase.getCollection(coleccion);
        BasicDBObject upgradePhone = new BasicDBObject();
        upgradePhone.append("$set",new BasicDBObject().append("Last Name",phoneNew));
        BasicDBObject seachrByName = new BasicDBObject();
        seachrByName.append("Name",name);
        colection.updateMulti(seachrByName,upgradePhone);
    }
    
    public void delate(String coleccion,String name){
        colection = dataBase.getCollection(coleccion);
        colection.remove(new BasicDBObject().append("Name",name));
    }
     
     
    
}
