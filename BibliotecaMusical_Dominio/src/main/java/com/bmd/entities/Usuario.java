/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bmd.entities;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class Usuario {
    
    @BsonId
    private ObjectId id;
    private String nombre;
    private String correo;
    private String contrasena;
    @BsonProperty("imagen_perfil")
    private String imagenPerfil;
    private List<Favorito> favoritos;
    @BsonProperty("restringidos")
    private List<String> generosRestringidos;

    public Usuario() {
      
    }
    
    private Usuario(Builder builder) { 
        this.id = builder.id;
        this.nombre = builder.nombre; 
        this.correo = builder.correo; 
        this.contrasena = builder.contrasena; 
        this.imagenPerfil = builder.imagenPerfil; 
        this.favoritos = builder.favoritos; 
        this.generosRestringidos = builder.generosRestringidos;
    }

    public ObjectId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public List<String> getGenerosRestringidos() {
        return generosRestringidos;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public void setGenerosRestringidos(List<String> generosRestringidos) {
        this.generosRestringidos = generosRestringidos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + 
                ", correo=" + correo + ", contrasena=" + contrasena + 
                ", imagenPerfil=" + imagenPerfil + ", favoritos=" + 
                favoritos + ", generosBaneados=" + generosRestringidos + '}';
    }
    
    public static class Builder {
        private ObjectId id;
        private String nombre;
        private String correo;
        private String contrasena;
        private String imagenPerfil;
        private List<Favorito> favoritos;
        private List<String> generosRestringidos;

        public Builder setId(ObjectId id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder setContrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Builder setImagenPerfil(String imagenPerfil) {
            this.imagenPerfil = imagenPerfil;
            return this;
        }

        public Builder setFavoritos(List<Favorito> favoritos) {
            this.favoritos = favoritos;
            return this;
        }

        public Builder setGenerosRestringidos(List<String> generosRestringidos) {
            this.generosRestringidos = generosRestringidos;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }
    
}
