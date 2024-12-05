/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

import com.bmn.dto.constantes.Genero;
import com.bmn.dto.constantes.Tipo;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class FavoritoDTO {
    
    private ObjectId idUsuario;
    private String idReferencia;
    private Tipo tipo;
    private Genero genero;
    private String nombreCancion;
    private LocalDate fechaAgregacion;

    public FavoritoDTO() {
    }

    public FavoritoDTO(Builder builder){
        this.idUsuario = builder.idUsuario;
        this.idReferencia = builder.idReferencia;
        this.nombreCancion = builder.nombreCancion;
        this.tipo = builder.tipo;
        this.fechaAgregacion = builder.fechaAgregacion;
    }
    

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public String getIdReferencia() {
        return idReferencia;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getFechaAgregacion() {
        return fechaAgregacion;
    }

    @Override
    public String toString() {
        return "Favorito{" + ", idUsuario=" + idUsuario + 
                ", idReferencia=" + idReferencia + ", tipo=" + tipo + 
                ", fechaAgregacion=" + fechaAgregacion + '}';
    }
    
    
    public static class Builder {
        private ObjectId idUsuario;
        private String idReferencia;
        private String nombreCancion;
        private Tipo tipo;
        private Genero genero;
        private LocalDate fechaAgregacion;

        public Builder setIdUsuario(ObjectId idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder setIdReferencia(String idReferencia) {
            this.idReferencia = idReferencia;
            return this;
        }

        public Builder setNombreCancion(String nombreCancion) {
            this.nombreCancion = nombreCancion;
            return this;
        }

        public Builder setTipo(Tipo tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder setGenero(Genero genero) {
            this.genero = genero;
            return this;
        }

        public Builder setFechaAgregacion(LocalDate fechaAgregacion) {
            this.fechaAgregacion = fechaAgregacion;
            return this;
        }
        
        public FavoritoDTO build(){
            return new FavoritoDTO(this);
        }
    }
    
}
