/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.UsuarioActualizarDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ActualizarUsuarioBO;
import com.bmn.singletonUsuario.UsuarioST;

/**
 *
 * @author skevi
 */
public class PruebaActualizarUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        ActualizarUsuarioBO actualizar = BOFactory.actualizarUsuarioFactory();
        
        UsuarioActualizarDTO usuario = new UsuarioActualizarDTO.Builder()
        .setNombre("Xitlali Andrade")
        .setCorreo("xiltali.asdasodb@gmail.com")
        .setContrasena("password123")
        .setContrasenaConfirmar("password123")
        .setImagenPerfil("url_to_profile_image.jpg")
        .build();
        
        actualizar.ActualizarUsuario(usuario);
        
            System.out.println(UsuarioST.getInstance().toString());
        }
        catch(BOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
