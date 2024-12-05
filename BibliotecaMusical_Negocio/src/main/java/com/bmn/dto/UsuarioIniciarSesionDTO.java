/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

/**
 *
 * @author skevi
 */
public class UsuarioIniciarSesionDTO {
    
    private String correo;
    private String contrasena;
    private String contrasenaObtenida;

    public UsuarioIniciarSesionDTO() {
    }

    public UsuarioIniciarSesionDTO(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getContrasenaObtenida() {
        return contrasenaObtenida;
    }

    public void setContrasenaObtenida(String contrasenaObtenida) {
        this.contrasenaObtenida = contrasenaObtenida;
    }

    @Override
    public String toString() {
        return "UsuarioIniciarSesionDTO{" + "correo=" + correo + 
                ", contrasena=" + contrasena + ", contrasenaObtenida=" + 
                contrasenaObtenida + '}';
    }
    
}
