/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmd.entities;

import org.bson.codecs.pojo.annotations.BsonId; 
import org.bson.codecs.pojo.annotations.BsonProperty; 
import java.time.LocalDate; 
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class Album {

    @BsonId 
    private ObjectId id; 
    private String nombre; 
    @BsonProperty("imagen_portada") 
    private String imagenPortada; 
    @BsonProperty("fecha_lanzamiento") 
    private LocalDate fechaLanzamiento; 
    private String genero; 
    private Artista artista; 
    private List<String> canciones;

    public Album() {
    }

    private Album(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.imagenPortada = builder.imagenPortada;
        this.fechaLanzamiento = builder.fechaLanzamiento;
        this.genero = builder.genero;
        this.artista = builder.artista;
        this.canciones = builder.canciones;
    }

    public ObjectId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public Artista getArtista() {
        return artista;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", nombre=" + nombre
                + ", imagenPortada=" + imagenPortada
                + ", fechaLanzamiento=" + fechaLanzamiento
                + ", genero=" + genero + ", artista=" + artista
                + ", canciones=" + canciones + '}';
    }

    public static class Builder {

        private ObjectId id;
        private String nombre;
        private String imagenPortada;
        private LocalDate fechaLanzamiento;
        private String genero;
        private Artista artista;
        private List<String> canciones;

        public Builder setId(ObjectId id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setImagenPortada(String imagenPortada) {
            this.imagenPortada = imagenPortada;
            return this;
        }

        public Builder setFechaLanzamiento(LocalDate fechaLanzamiento) {
            this.fechaLanzamiento = fechaLanzamiento;
            return this;
        }

        public Builder setGenero(String genero) {
            this.genero = genero;
            return this;
        }

        public Builder setArtista(Artista artista) {
            this.artista = artista;
            return this;
        }

        public Builder setCanciones(List<String> canciones) {
            this.canciones = canciones;
            return this;
        }

        public Album build() {
            return new Album(this);
        }

    }

}
