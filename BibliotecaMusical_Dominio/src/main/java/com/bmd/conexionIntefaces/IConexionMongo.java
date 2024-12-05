/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmd.conexionIntefaces;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author skevi
 */
public interface IConexionMongo {
    
    public MongoCollection<Document> getCollection(String collectionName);
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz);
    public void closeConnection();
    
}
