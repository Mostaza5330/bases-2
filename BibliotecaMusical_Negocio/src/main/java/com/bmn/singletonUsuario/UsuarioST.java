/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.singletonUsuario;

import com.bmd.entities.Usuario;

/**
 *
 * @author skevi
 */
public class UsuarioST {
    
    private static Usuario usuario;
    
    private UsuarioST(){
    }
    
    public static Usuario getInstance(){
        if (usuario == null) {
            return new Usuario();
        }
        else{
            return usuario;
        }
    }

    public static void setUsuario(Usuario usuario) {
        UsuarioST.usuario = usuario;
    }
    
}
