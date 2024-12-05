/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmd.entities;

import java.time.LocalDate;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class Favorito {
    
    @BsonProperty("id_usuario") 
    private ObjectId idUsuario; 
    @BsonProperty("id_referencia") 
    private ObjectId idReferencia; 
    private String tipo; // Artista, Album, Cancion 
    private String genero;
    @BsonProperty("nombre_cancion")
    private String nombreCancion; 
    @BsonProperty("fecha_agregacion") 
    private LocalDate fechaAgregacion;

    public Favorito() {
    }

    public Favorito(Builder builder) {
        this.idUsuario = builder.idUsuario;
        this.idReferencia = builder.idReferencia;
        this.tipo = builder.tipo;
        this.genero = builder.genero;
        this.nombreCancion = builder.nombreCancion;
        this.fechaAgregacion = builder.fechaAgregacion;
    }


    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public ObjectId getIdReferencia() {
        return idReferencia;
    }

    public String getTipo() {
        return tipo;
    }

    public String getGenero() {
        return genero;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public LocalDate getFechaAgregacion() {
        return fechaAgregacion;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdReferencia(ObjectId idReferencia) {
        this.idReferencia = idReferencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public void setFechaAgregacion(LocalDate fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }

    @Override
    public String toString() {
        return "Favorito{" + "idUsuario=" + idUsuario + 
                ", idReferencia=" + idReferencia + ", tipo=" + tipo + 
                ", fechaAgregacion=" + fechaAgregacion + '}';
    }
    
    public static class Builder{
        
        ObjectId idUsuario;
        ObjectId idReferencia;
        String tipo;
        String genero;
        String nombreCancion;
        LocalDate fechaAgregacion;

        public Builder setIdUsuario(ObjectId idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder setIdReferencia(ObjectId idReferencia) {
            this.idReferencia = idReferencia;
            return this;
        }

        public Builder setTipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder setGenero(String genero) {
            this.genero = genero;
            return this;
        }

        public Builder setNombreCancion(String nombreCancion) {
            this.nombreCancion = nombreCancion;
            return this;
        }

        public Builder setFechaAgregacion(LocalDate fechaAgregacion) {
            this.fechaAgregacion = fechaAgregacion;
            return this;
        }
        
        public Favorito build(){
            return new Favorito(this);
        }
    }
    
}
