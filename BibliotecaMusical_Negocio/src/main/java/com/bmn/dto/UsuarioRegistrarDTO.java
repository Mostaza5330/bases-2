/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

/**
 *
 * @author skevi
 */
public class UsuarioRegistrarDTO {
    
    private String nombre;
    private String correo;
    private String contrasena;
    private String contrasenaConfirmar;
    private String imagenPerfil;
    
    private UsuarioRegistrarDTO (Builder builder) { 
        this.nombre = builder.nombre; 
        this.correo = builder.correo; 
        this.contrasena = builder.contrasena; 
        this.contrasenaConfirmar = builder.contrasenaConfirmar;
        this.imagenPerfil = builder.imagenPerfil; 
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

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasenaConfirmar() {
        return contrasenaConfirmar;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }
    
    public static class Builder {
        private String nombre;
        private String correo;
        private String contrasena;
        private String contrasenaConfirmar;
        private String imagenPerfil;
        
         public UsuarioRegistrarDTO.Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public UsuarioRegistrarDTO.Builder setCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public UsuarioRegistrarDTO.Builder setContrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Builder setContrasenaConfirmar(String contrasenaConfirmar) {
            this.contrasenaConfirmar = contrasenaConfirmar;
            return this;
        }

        public UsuarioRegistrarDTO.Builder setImagenPerfil(String imagenPerfil) {
            this.imagenPerfil = imagenPerfil;
            return this;
        }
        
        public UsuarioRegistrarDTO build() {
            return new UsuarioRegistrarDTO (this);
        }
    }
    
}
