    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmd.entities;

import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class Artista {
    
    @BsonId 
    private ObjectId id; 
    @BsonProperty("tipo_artista") 
    private String tipoArtista; 
    private String nombre; 
    private String imagen; 
    private String genero; 
    private List<Integrante> integrantes; 
    private List<Album> albumes;

    public Artista() {
    }

    private Artista(Builder builder) {
        this.id = builder.id;
        this.tipoArtista = builder.tipoArtista;
        this.nombre = builder.nombre;
        this.imagen = builder.imagen;
        this.genero = builder.genero;
        this.integrantes = builder.integrantes;
        this.albumes = builder.albumes;
    }

    public ObjectId getId() {
        return id;
    }

    public String getTipoArtista() {
        return tipoArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getGenero() {
        return genero;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public List<Album> getalbumes() {
        return albumes;
    }
    
    public void setalbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setTipoArtista(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "id: " + id + 
                ", tipoArtista: " + tipoArtista + 
                ", nombre: " + nombre + 
                ", imagen: " + imagen + 
                ", genero: " + genero + 
                ", integrantes: " + integrantes + 
                ", albumes: " + albumes + '}';
    }
    
    public static class Builder {
        
        private ObjectId id;
        private String tipoArtista;
        private String nombre;
        private String imagen;
        private String genero;
        private List<Integrante> integrantes;
        private List<Album> albumes;

        public Builder setId(ObjectId id) {
            this.id = id;
            return this;
        }

        public Builder setTipoArtista(String tipoArtista){
            this.tipoArtista = tipoArtista;
            return this;
        }
        
        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setImagen(String imagen) {
            this.imagen = imagen;
            return this;
        }

        public Builder setGenero(String genero) {
            this.genero = genero;
            return this;
        }
        
        public Builder setIntegrantes(List<Integrante> integrantes) {
            this.integrantes = integrantes;
            return this;
        }

        public Builder setalbumes(List<Album> albumes) {
            this.albumes = albumes;
            return this;
        }

        public Artista build() {
            return new Artista(this);
        }
    }

}

