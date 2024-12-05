/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdm.conexion;

import com.bmd.conexionIntefaces.IConexionMongo;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase ConexionMongo implementa la interfaz IConexionMongo.
 * Esta clase utiliza el patrón Singleton para manejar la conexión con MongoDB.
 */
public class ConexionMongo implements IConexionMongo {

    // Instancia única de la clase para implementar el patrón Singleton
    private static ConexionMongo instance;
    
    // Cliente de MongoDB
    private MongoClient mongoClient;
    
    // Base de datos de MongoDB
    private MongoDatabase database;
    
    // Registro de Codecs para manejar POJOs
    private static CodecRegistry pojoCodecRegistry;

    // Inicialización estática del CodecRegistry
    static {
        pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
    }
    
    /**
     * Constructor privado para evitar la creación de nuevas instancias.
     * Conecta a la base de datos usando la URI proporcionada.
     */
    public ConexionMongo() {
        // Conecta a la base de datos usando la URI proporcionada
        MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
            .build();
        mongoClient = MongoClients.create(settings);
        // Selecciona la base de datos
        database = mongoClient.getDatabase("bibliotecaMusical#4");
    }

    /**
     * Método estático para obtener la instancia única de la clase.
     * Utiliza doble verificación para asegurar que solo se crea una instancia.
     *
     * @return La instancia única de ConexionMongo.
     */
    public static ConexionMongo getInstance() {
        if (instance == null) {
            synchronized (ConexionMongo.class) {
                if (instance == null) {
                    instance = new ConexionMongo();
                }
            }
        }
        return instance;
    }

    /**
     * Obtiene una colección específica de la base de datos.
     *
     * @param collectionName Nombre de la colección.
     * @return La colección de documentos de MongoDB.
     */
    @Override
    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    @Override
    public void closeConnection() {
        mongoClient.close();
    }
    
    /**
     * Obtiene una colección específica de la base de datos con soporte para POJOs.
     *
     * @param collectionName Nombre de la colección.
     * @param clazz Clase del POJO.
     * @param <T> Tipo del POJO.
     * @return La colección de documentos de MongoDB con soporte para POJOs.
     */
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        return database.getCollection(collectionName, clazz);
    }
}



