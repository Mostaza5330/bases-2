/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.UsuarioIniciarSesionDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.InicioSesionBO;
import com.bmn.singletonUsuario.UsuarioST;

/**
 *
 * @author skevi
 */
public class PruebaIniciarSesion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        InicioSesionBO inicio = BOFactory.inicioSesionFactory();
        
        UsuarioIniciarSesionDTO usuario= 
                new UsuarioIniciarSesionDTO("kevin.sanchez@gmail.com", "password123");
        
        inicio.iniciarSesion(usuario);
        
        System.out.println(UsuarioST.getInstance().toString());
        
        }
        catch(BOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
